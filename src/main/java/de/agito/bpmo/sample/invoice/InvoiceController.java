package de.agito.bpmo.sample.invoice;

// @@begin imports
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import de.agito.bpmo.sample.invoice.InvoiceAccess.TaxPositions;
import de.agito.cps.core.annotations.BPMO;
import de.agito.cps.core.annotations.Expression;
import de.agito.cps.core.annotations.ExpressionDependency;
import de.agito.cps.core.bpmo.ExpressionType;
import de.agito.cps.core.bpmo.api.controller.BPMOController;
import de.agito.cps.core.bpmo.api.controller.IBPMOControllerContext;
// @@end

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

	// @@begin head:calculate:TaxPositions$TaxAmount
	/**
	 * Hook for calculate expression of TaxPositions$TaxAmount
	 */
	// @@end
	@Expression(artifact = "Invoice$TaxPositions$TaxAmount", type = ExpressionType.VALUE_CALCULATION)
	@ExpressionDependency({ "Invoice$TaxPositions$NetAmount", "Invoice$TaxPositions$TaxRate" })
	public BigDecimal cpsCalculateTaxPositions$TaxAmount(InvoiceAccess bpmoAccess, TaxPositions.Current rowAccess) {
		/*
		 * Calculate the tax amount of position
		 */
		final TaxPositions.Current.NetAmount taxPositions$NetAmount = rowAccess.getNetAmount();
		final TaxPositions.Current.TaxRate taxPositions$TaxRate = rowAccess.getTaxRate();
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
	@Expression(artifact = "Invoice$TaxPositions$TotalAmount", type = ExpressionType.VALUE_CALCULATION)
	@ExpressionDependency({ "Invoice$TaxPositions$NetAmount", "Invoice$TaxPositions$TaxRate" })
	public BigDecimal cpsCalculateTaxPositions$TotalAmount(InvoiceAccess bpmoAccess, TaxPositions.Current rowAccess) {
		/*
		 * Calculate the total amount of position
		 */
		final TaxPositions.Current.NetAmount taxPositions$NetAmount = rowAccess.getNetAmount();
		final TaxPositions.Current.TaxRate taxPositions$TaxRate = rowAccess.getTaxRate();
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
			bpmoAccess.getTaxPositions().createAndAddCurrentRow().getTaxRate();

	}

	@Override
	public void cpsBeforeSaveBPMO(InvoiceAccess bpmoAccess) {
		// calculate title
		Map<InvoiceLanguage, String> title = new HashMap<InvoiceLanguage, String>(InvoiceLanguage.values().length);
		for (InvoiceLanguage language : InvoiceLanguage.values()) {
			switch (language) {
			case de:
				title.put(language, String.format("%s", "Title"));
				break;
			case en:
				title.put(language, String.format("%s", "Title"));
				break;
			}
		}
		getBPMO().setTitle(title);

	}
	// @@end
}
