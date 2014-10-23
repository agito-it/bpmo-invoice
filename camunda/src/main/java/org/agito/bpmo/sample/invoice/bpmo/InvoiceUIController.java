package org.agito.bpmo.sample.invoice.bpmo;

// @@begin imports

import org.agito.bpmo.sample.invoice.recources.InvoiceTextResource;
import org.agito.bpmo.sample.invoice.recources.InvoiceTextResourceUtils;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Table.Align;

import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.annotation.StyleController;
import de.agito.cps.ui.vaadin.bpmo.enums.ColumnCalculationMode;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayoutManager;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowTabSheet;
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
		//
		IFlowTabSheet tabSheet = layoutManager.createAndAddTabSheet();
		tabSheet.setColspan(3);
		tabSheet.createAndAddTabContent(InvoiceTextResource.CAPTION_INVOICE_DETAILS)
				.setTitle(InvoiceTextResourceUtils.getText(InvoiceTextResource.CAPTION_INVOICE_DETAILS))
				.createAndAddElements(Invoice.InvoiceAttachment, Invoice.InvoicingParty, Invoice.InvoiceNumber,
						Invoice.InvoiceDate, Invoice.InvoiceReceived, Invoice.TermOfPayment);
		tabSheet.createAndAddTabContent(InvoiceTextResource.CAPTION_ACCOUNTING_INFO)
				.setTitle(InvoiceTextResourceUtils.getText(InvoiceTextResource.CAPTION_ACCOUNTING_INFO))
				.addRemainingElements(Invoice.TaxPositions);

		layoutManager.newLine();

		// manage TaxPositions layout
		IFlowTableContent tableContent = layoutManager.createAndAddTableContent(Invoice.TaxPositions)
				.addRemainingElements().setPageLength(3);
		tableContent.setColspan(3);
		tableContent.getColumn(Invoice.TaxPositions$NetAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPositions$TaxAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPositions$TotalAmount).setFooterCalculationMode(ColumnCalculationMode.SUM)
				.setWidth(200);
		tableContent.getColumn(Invoice.TaxPositions$TaxRate).setColumnAlignment(Align.RIGHT).setWidth(200);

		layoutManager.newLine();
		// create attachment viewer
		layoutManager
				.createAndAddCustomContent()
				.setComponent(
						UIDataTypeFactory.getInstance().createEmbeddedAttachmentViewer(
								bpmoAccess.getInvoiceAttachment().getContext().getCharacteristicValue()
										.getCurrentValue(), bpmoAccess.getBPMOHeader().isNew())).setColspan(4)
				.setHeight(750, Unit.PIXELS);
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
