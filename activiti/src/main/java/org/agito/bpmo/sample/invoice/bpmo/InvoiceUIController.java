package org.agito.bpmo.sample.invoice.bpmo;

// @@begin imports

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Table.Align;

import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.annotation.StyleController;
import de.agito.cps.ui.vaadin.bpmo.enums.ColumnCalculationMode;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayout.Colspan;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayout.MaxColums;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayoutManager;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowTableContent;
import de.agito.cps.ui.vaadin.bpmo.styles.IFlowStyleController;
import de.agito.cps.ui.vaadin.common.resources.UIDataTypeFactory;

// @@end

// @@begin head:uicontroller
/**
 * Vaadin UI Controller for Invoice.
 * 
 * @author agito
 */
// @@end
public class InvoiceUIController
		extends
		BPMOUIController<InvoiceAccess, InvoiceAction, InvoiceLifecycle, InvoiceLanguage, InvoiceProcessActivity, Invoice> {

	public InvoiceUIController(final IBPMOUIControllerContext context) {
		super(context);
	}

	// @@begin head:init:Invoice
	/**
	 * Hook for node element init of Invoice
	 */
	// @@end
	@Navigation(artifact = "Invoice", type = NavigationType.NODE_ELEMENT_INIT)
	public void cpsInitInvoice(final InvoiceAccess bpmoAccess) {
		// @@begin body:init:Invoice

		// get LayoutManager
		IFlowLayoutManager layoutManager = styleController.getLayoutManager();

		// set max content width
		layoutManager.setMaxCols(MaxColums.COL4);

		// add initial fields to group
		layoutManager
				.createAndAddGroupContent()
				.setCaption(Texts.getString("InvoiceUIController.InvoiceDetails"))
				.createAndAddElements(Invoice.InvoiceAttachment, Invoice.InvoicingParty, Invoice.InvoiceNumber,
						Invoice.InvoiceDate, Invoice.InvoiceReceived, Invoice.TermOfPayment)
				.setColspan(Colspan.DIMENSION_2); //$NON-NLS-1$

		// manage finance accounting informations
		// add all remaining content elements excepting TaxPositions to group
		layoutManager
				.createAndAddGroupContent()
				.setCaption(Texts.getString("InvoiceUIController.AccountingInformations")).addRemainingElements(Invoice.TaxPositions).setColspan(Colspan.DIMENSION_2); //$NON-NLS-1$

		// manage TaxPositions layout
		IFlowTableContent tableContent = layoutManager.createAndAddTableContent(Invoice.TaxPositions)
				.addRemainingElements().setPageLength(3);
		tableContent.getColumn(Invoice.TaxPositions$NetAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPositions$TaxAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPositions$TotalAmount).setFooterCalculationMode(ColumnCalculationMode.SUM)
				.setWidth(200);
		tableContent.getColumn(Invoice.TaxPositions$TaxRate).setColumnAlignment(Align.RIGHT).setWidth(200);

		// create attachment viewer
		layoutManager
				.createAndAddCustomContent()
				.setComponent(
						UIDataTypeFactory
								.getInstance()
								.createEmbeddedAttachmentViewer(
										bpmoAccess.getInvoiceAttachment().getContext().getCharacteristicValue()
												.getCurrentValue(), bpmoAccess.getBPMOHeader().isNew())
								.setViewerHeight(750, Unit.PIXELS)).setColspan(Colspan.DIMENSION_FULL);
		// @@end
	}

	// @@begin head:destroy:Invoice
	/**
	 * Hook for node element destroy of Invoice
	 */
	// @@end
	@Navigation(artifact = "Invoice", type = NavigationType.NODE_ELEMENT_DESTROY)
	public void cpsDestroyInvoice(final InvoiceAccess bpmoAccess) {
		// @@begin body:destroy:Invoice
		// @@end
	}

	// @@begin others
	@StyleController
	private IFlowStyleController styleController;
	// @@end
}
