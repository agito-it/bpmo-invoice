package de.agito.bpmo.sample.invoice;

// @@begin imports
import de.agito.bpmo.sample.invoice.Invoice;
import de.agito.bpmo.sample.invoice.InvoiceAccess;
import de.agito.bpmo.sample.invoice.InvoiceAccess.TaxPostions;
import de.agito.bpmo.sample.invoice.InvoiceAction;
import de.agito.bpmo.sample.invoice.InvoiceLanguage;
import de.agito.bpmo.sample.invoice.InvoiceLifecycle;
import de.agito.bpmo.sample.invoice.InvoiceProcessActivity;
import de.agito.cps.core.annotations.BPMO;
import de.agito.cps.core.annotations.Expression;
import de.agito.cps.core.annotations.ExpressionDependency;
import de.agito.cps.core.bpmo.ExpressionType;
import de.agito.cps.core.bpmo.api.controller.BPMOController;
import de.agito.cps.core.bpmo.api.controller.IBPMOControllerContext;
import java.math.BigDecimal;
// @@end
import java.math.RoundingMode;

// @@begin head:controller
/**
 * BPMOController for Invoice.
 * 
 * @author Jörg Burmeister
 */
// @@end
@BPMO(id = "Invoice", version = "1.0.0", xml = "de/agito/bpmo/sample/invoice/Invoice.bpmo")
public class InvoiceController
		extends
		BPMOController<InvoiceAccess, InvoiceAction, InvoiceLifecycle, InvoiceLanguage, InvoiceProcessActivity, Invoice> {

	public InvoiceController(IBPMOControllerContext context) {
		super(context);
	}

	// @@begin head:calculate:TaxPostions$TaxAmount
	/**
	 * Hook for calculate expression of TaxPostions$TaxAmount
	 */
	// @@end
	@Expression(artifact = "Invoice$TaxPostions$TaxAmount", type = ExpressionType.VALUE_CALCULATION)
	@ExpressionDependency({ "Invoice$TaxPostions$NetAmount", "Invoice$TaxPostions$TaxRate" })
	public BigDecimal cpsCalculateTaxPostions$TaxAmount(InvoiceAccess bpmoAccess, TaxPostions.Current rowAccess) {
		/*
		 * Calculate the tax amount of position
		 */
		final TaxPostions.Current.NetAmount taxPostions$NetAmount = rowAccess.getNetAmount();
		final TaxPostions.Current.TaxRate taxPostions$TaxRate = rowAccess.getTaxRate();
		// @@begin body:calculate:TaxPostions$TaxAmount

		if (taxPostions$NetAmount.getValue() != null && taxPostions$TaxRate.getValue() != null)
			return taxPostions$NetAmount.getValue().divide(new BigDecimal(100))
					.multiply(new BigDecimal(taxPostions$TaxRate.getValue().getKey()))
					.setScale(2, RoundingMode.HALF_UP);
		return null;
		// @@end
	}

	// @@begin head:calculate:TaxPostions$TotalAmount
	/**
	 * Hook for calculate expression of TaxPostions$TotalAmount
	 */
	// @@end
	@Expression(artifact = "Invoice$TaxPostions$TotalAmount", type = ExpressionType.VALUE_CALCULATION)
	@ExpressionDependency({ "Invoice$TaxPostions$NetAmount", "Invoice$TaxPostions$TaxRate" })
	public BigDecimal cpsCalculateTaxPostions$TotalAmount(InvoiceAccess bpmoAccess, TaxPostions.Current rowAccess) {
		/*
		 * Calculate the total amount of position
		 */
		final TaxPostions.Current.NetAmount taxPostions$NetAmount = rowAccess.getNetAmount();
		final TaxPostions.Current.TaxRate taxPostions$TaxRate = rowAccess.getTaxRate();
		// @@begin body:calculate:TaxPostions$TotalAmount

		if (taxPostions$NetAmount.getValue() != null && taxPostions$TaxRate.getValue() != null)
			return taxPostions$NetAmount.getValue().divide(new BigDecimal(100))
					.multiply(new BigDecimal(taxPostions$TaxRate.getValue().getKey()))
					.setScale(2, RoundingMode.HALF_UP).add(taxPostions$NetAmount.getValue());
		return null;

		// @@end
	}

	// @@begin others

	// @@end
}
