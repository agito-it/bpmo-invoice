package de.agito.bpmo.sample.invoice;

// @@begin imports

import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.annotation.StyleManager;
import de.agito.cps.ui.vaadin.bpmo.enums.ColumnAlignment;
import de.agito.cps.ui.vaadin.bpmo.enums.ColumnCalculationMode;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
import de.agito.cps.ui.vaadin.bpmo.enums.UNIT;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayoutManager;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.ISeparator;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.ITableContent;
import de.agito.cps.ui.vaadin.bpmo.styles.IDefaultStyleController;
import de.agito.cps.ui.vaadin.common.resources.DataTypeFactory;
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

		// get LayoutManager
		IFlowLayoutManager layoutManager = styleController.getLayoutManager();

		// set fixed content width
		layoutManager.setWidth((layoutManager.getContentElementBaseWidth() * 4) + 80, UNIT.PIXEL);

		// add Headline
		layoutManager.createAndAddSeparator().setTitle(getBPMO().getBPMODefinition().getLabel().getText())
				.addTitleStyleName(ISeparator.Style.H2).addTitleStyleName(ISeparator.Style.HR)
				.setContentWidth(700, UNIT.PIXEL).setWidth(100, UNIT.PERCENTAGE);

		// add initial fields to group
		layoutManager
				.createAndAddGroupContent()
				.setCaption(Messages.getString("InvoiceUIController.InvoiceDetails"))
				.createAndAddElements(Invoice.InvoiceAttachment, Invoice.InvoicingParty, Invoice.InvoiceNumber,
						Invoice.InvoiceDate, Invoice.InvoiceReceived, Invoice.TermOfPayment).setDimension(2)
				.setHeight(240, UNIT.PIXEL); //$NON-NLS-1$

		// add separator for horizontal separation of groups
		layoutManager.createAndAddSeparator().setWidth(10, UNIT.PIXEL);

		// manage finance accounting informations
		// add all remaining content elements excepting TaxPostions to group
		layoutManager
				.createAndAddGroupContent()
				.setCaption(Messages.getString("InvoiceUIController.AccountingInformations")).fulfillContent(Invoice.TaxPostions).setDimension(2).setHeight(240, UNIT.PIXEL); //$NON-NLS-1$

		layoutManager.addLineBreak();

		// manage TaxPostions layout
		ITableContent tableContent = layoutManager.createAndAddTableContent(Invoice.TaxPostions).fulfillContent()
				.setPageLength(3);
		tableContent.getColumn(Invoice.TaxPostions$NetAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPostions$TaxAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPostions$TotalAmount).setFooterCalculationMode(ColumnCalculationMode.SUM)
				.setWidth(200);
		tableContent.getColumn(Invoice.TaxPostions$TaxRate).setColumnAlignment(ColumnAlignment.ALIGN_RIGHT)
				.setWidth(200);

		// create attachment viewer
		layoutManager.createAndAddCustomContent().setComponent(
				DataTypeFactory.getInstance().createEmbeddedAttachmentViewer(
						bpmoAccess.getInvoiceAttachment().getContext().getCharacteristicValue().getCurrentValue(),
						getBPMO().getBPMOHeader().isNew()));
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
