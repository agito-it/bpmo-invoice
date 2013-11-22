package org.agito.bpmo.sample.invoice;

// @@begin imports

import de.agito.cps.core.annotations.BPMO;
import de.agito.cps.core.annotations.Expression;
import de.agito.cps.core.annotations.ExpressionDependency;
import de.agito.cps.core.bpmo.DataTypeFactory;
import de.agito.cps.core.bpmo.ExpressionType;
import de.agito.cps.core.bpmo.MessageSeverity;
import de.agito.cps.core.bpmo.PrincipalType;
import de.agito.cps.core.bpmo.api.controller.BPMOController;
import de.agito.cps.core.bpmo.api.controller.IBPMOControllerContext;
import de.agito.cps.core.engine.runtime.BusinessLog;
import de.agito.cps.core.utils.ConvertUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.xml.datatype.DatatypeConstants;
import org.agito.bpmo.sample.invoice.Invoice;
import org.agito.bpmo.sample.invoice.InvoiceAccess;
import org.agito.bpmo.sample.invoice.InvoiceAccess.InvoiceDate;
import org.agito.bpmo.sample.invoice.InvoiceAccess.InvoiceReceived;
import org.agito.bpmo.sample.invoice.InvoiceAccess.TaxPositions;
import org.agito.bpmo.sample.invoice.InvoiceAction;
import org.agito.bpmo.sample.invoice.InvoiceLanguage;
import org.agito.bpmo.sample.invoice.InvoiceLifecycle;
import org.agito.bpmo.sample.invoice.InvoiceProcessActivity;

// @@end

// @@begin head:controller
/**
 * BPMOController for Invoice.
 * 
 * @author agito
 */
// @@end
@BPMO(id = "Invoice", version = "1.0.0", xml = "org/agito/bpmo/sample/invoice/Invoice.bpmo")
public class InvoiceController extends BPMOController<InvoiceAccess, InvoiceAction, InvoiceLifecycle, InvoiceLanguage, InvoiceProcessActivity, Invoice> {

	public InvoiceController(final IBPMOControllerContext context) {
		super(context);
	}

	// @@begin head:validate:InvoiceReceived
	/**
	 * Hook for validate expression of InvoiceReceived
	 */
	// @@end
	@Expression(artifact = "Invoice$InvoiceReceived", type = ExpressionType.VALIDATION)
	@ExpressionDependency("Invoice$InvoiceDate")
	public boolean cpsValidateInvoiceReceived(final InvoiceAccess bpmoAccess) {
		final InvoiceReceived invoiceReceived = bpmoAccess.getInvoiceReceived();
		final InvoiceDate invoiceDate = bpmoAccess.getInvoiceDate();
		// @@begin body:validate:InvoiceReceived

		if (invoiceDate.getValue() != null && invoiceReceived.getValue() != null)
			if (invoiceReceived.getValue().compare(invoiceDate.getValue()) == DatatypeConstants.LESSER) {
				invoiceReceived
						.getContext()
						.addMessage(
								DataTypeFactory
										.getInstance()
										.createMessage(MessageSeverity.ERROR,
												"invalid Date", //$NON-NLS-1$
												String.format(
														Texts.getString("InvoiceController.InvoiceReceivedValidation"), invoiceDate.getDefinition().getLabel() //$NON-NLS-1$
																.getText())));
				return false;
			}

		return true;
		// @@end
	}

	// @@begin head:calculate:TaxPositions$TaxAmount
	/**
	 * Hook for calculate expression of TaxPositions$TaxAmount
	 */
	// @@end
	@Expression(artifact = "Invoice$TaxPositions$TaxAmount", type = ExpressionType.CALCULATE)
	@ExpressionDependency({ "Invoice$TaxPositions$NetAmount", "Invoice$TaxPositions$TaxRate" })
	public BigDecimal cpsCalculateTaxPositions$TaxAmount(final InvoiceAccess bpmoAccess, final TaxPositions.Row rowAccess) {
		final TaxPositions.NetAmount taxPositions$NetAmount = rowAccess.getNetAmount();
		final TaxPositions.TaxRate taxPositions$TaxRate = rowAccess.getTaxRate();
		/*
		 * Calculate the tax amount of position
		 */
		// @@begin body:calculate:TaxPositions$TaxAmount

		if (taxPositions$NetAmount.getValue() != null && taxPositions$TaxRate.getValue() != null)
			return taxPositions$NetAmount.getValue().divide(new BigDecimal(100))
					.multiply(new BigDecimal(taxPositions$TaxRate.getValue().getKey()))
					.setScale(2, RoundingMode.HALF_UP);
		return null;

		// @@end
	}

	// @@begin head:calculate:TaxPositions$TotalAmount
	/**
	 * Hook for calculate expression of TaxPositions$TotalAmount
	 */
	// @@end
	@Expression(artifact = "Invoice$TaxPositions$TotalAmount", type = ExpressionType.CALCULATE)
	@ExpressionDependency({ "Invoice$TaxPositions$NetAmount", "Invoice$TaxPositions$TaxRate" })
	public BigDecimal cpsCalculateTaxPositions$TotalAmount(final InvoiceAccess bpmoAccess, final TaxPositions.Row rowAccess) {
		final TaxPositions.NetAmount taxPositions$NetAmount = rowAccess.getNetAmount();
		final TaxPositions.TaxRate taxPositions$TaxRate = rowAccess.getTaxRate();
		/*
		 * Calculate the total amount of position
		 */
		// @@begin body:calculate:TaxPositions$TotalAmount

		if (taxPositions$NetAmount.getValue() != null && taxPositions$TaxRate.getValue() != null)
			return taxPositions$NetAmount.getValue().divide(new BigDecimal(100))
					.multiply(new BigDecimal(taxPositions$TaxRate.getValue().getKey()))
					.setScale(2, RoundingMode.HALF_UP).add(taxPositions$NetAmount.getValue());
		return null;

		// @@end
	}

	// @@begin others

	@Override
	public void cpsInitBPMO(InvoiceAccess bpmoAccess, boolean isNew) {
		if (isNew) // create a default tax position row
			bpmoAccess.getTaxPositions().createAndAddRow();

	}

	@Override
	public void cpsBeforeSaveBPMO(InvoiceAccess bpmoAccess) {
		// calculate title
		Map<InvoiceLanguage, String> title = new HashMap<InvoiceLanguage, String>(InvoiceLanguage.values().length);

		// set title for default language only, because there is nothing language specific portion in the title
		String invoiceNumber = bpmoAccess.getInvoiceNumber().getValue();
		String invoiceParty = bpmoAccess.getInvoicingParty().getValue();
		title.put(InvoiceLanguage.en, String.format("%s / %s", invoiceNumber == null ? "" : invoiceNumber,
				invoiceParty == null ? "" : invoiceParty));

		bpmoAccess.getBPMO().setTitle(title);

	}

	@Override
	public Object cpsExecuteBPMOAction(InvoiceAccess bpmoAccess, InvoiceAction action, Map<String, Object> parameters) {
		try {
			switch (action) {
			case BackendBooking:
				// get a interface to back end system and write data

				// -------- do something on interface

				// write processing info to business log
				BusinessLog businessLog = DataTypeFactory.getInstance().createBusinessLog();
				businessLog.addInfoLogEntry("Request processed",
						String.format("Processing id \"%s\"", UUID.randomUUID().toString()));
				bpmoAccess.getBPMO().saveBusinessLog(businessLog, true);

				parameters.put("IsProcessed", true);

				break;
			case ResolveOrder:
				parameters.put("IsResolved", false);

				// retrieve order information from back end and write to BO if a OrderNumber was given by initiator
				if (bpmoAccess.getOrderNumber().getValue() != null) {
					bpmoAccess.getOrderCostCenter().setValue("5677757");
					bpmoAccess.getOrderProfitcenter().setValue("100012345");

					// in this sample use the initiator as approver to avoid necessary user login switch
					bpmoAccess.getApprover().setValue(bpmoAccess.getBPMOHeader().getInitiator().getId(),
							PrincipalType.USER);
					bpmoAccess.getShipmentChecked().setValue(true);
					bpmoAccess.getOrderChecked().setValue(true);
					parameters.put("IsResolved", true);
				}
				break;
			case GetApprover:
				// return responsible approver id from BO
				parameters.put("approver", bpmoAccess.getApprover().getValue().getId());
				break;

			}
			return parameters;
		} catch (RuntimeException e) {
			// for error diagnostics write errors to business log
			BusinessLog businessLog = DataTypeFactory.getInstance().createBusinessLog();
			businessLog.addErrorLogEntry(MessageSeverity.ERROR.toString(),
					String.format("Error on execution of action %s", action), ConvertUtils.getStackTraceAsString(e));
			bpmoAccess.getBPMO().saveBusinessLog(businessLog, true);

			throw e;
		}
	}

	// @@end
}
