package de.agito.bpmo.sample.invoice;

// @@begin imports
import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.annotation.StyleManager;
import de.agito.cps.ui.vaadin.bpmo.enums.ColumnCalculationMode;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayoutManager;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.ITableContent;
import de.agito.cps.ui.vaadin.bpmo.styles.IDefaultStyleController;
// @@end

// @@begin head:uicontroller
/**
 * Vaadin UI Controller for Invoice.
 * 
 * @author Jörg Burmeister
 */
// @@end
public class InvoiceUIController
		extends
		BPMOUIController<InvoiceAccess, InvoiceAction, InvoiceLifecycle, InvoiceLanguage, InvoiceProcessActivity, Invoice> {

	public InvoiceUIController(IBPMOUIControllerContext context) {
		super(context);
	}

	// @@begin head:init:Invoice
	/**
	 * Hook for node element init of Invoice
	 */
	// @@end
	@Navigation(artifact = "Invoice", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitInvoice(InvoiceAccess bpmoAccess) {
		// @@begin body:init:Invoice
		IFlowLayoutManager layoutManager = styleController.getLayoutManager();
		// add initial fields to group
		layoutManager
				.createAndAddGroupContent()
				.setCaption("Invoice details")
				.createAndAddElements(Invoice.InvoiceAttachment, Invoice.InvoicingParty, Invoice.InvoiceNumber,
						Invoice.InvoiceDate, Invoice.InvoiceReceived, Invoice.TermOfPayment);
		layoutManager.addLineBreak();

		// manage table layout
		ITableContent tableContent = layoutManager.createAndAddTableContent(Invoice.TaxPostions).fulfillContent();
		tableContent.getColumn(Invoice.TaxPostions$NetAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPostions$TaxAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPostions$TotalAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.setDimension(2);
		layoutManager.addLineBreak();

		// manage finance accounting informations
		layoutManager.createAndAddGroupContent().setCaption("Accounting informations").fulfillContent();
		// @@end
	}

	// @@begin head:destroy:Invoice
	/**
	 * Hook for node element destroy of Invoice
	 */
	// @@end
	@Navigation(artifact = "Invoice", type = NavigationType.NODE_ELEMENT_DESTROY)
	public void cpsDestroyInvoice(InvoiceAccess bpmoAccess) {
		// @@begin body:destroy:Invoice

		// @@end
	}

	// @@begin others
	@StyleManager
	private IDefaultStyleController styleController;
	// @@end
}
