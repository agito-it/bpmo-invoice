package org.agito.bpmo.sample.invoice;

// @@begin imports

import de.agito.cps.core.logger.Logger;
import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.annotation.StyleController;
import de.agito.cps.ui.vaadin.bpmo.enums.ColumnAlignment;
import de.agito.cps.ui.vaadin.bpmo.enums.ColumnCalculationMode;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
import de.agito.cps.ui.vaadin.bpmo.enums.SeparatorStyle;
import de.agito.cps.ui.vaadin.bpmo.enums.UNIT;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowLayoutManager;
import de.agito.cps.ui.vaadin.bpmo.layout.flow.IFlowTableContent;
import de.agito.cps.ui.vaadin.bpmo.styles.IFlowStyleController;
import de.agito.cps.ui.vaadin.common.resources.UIDataTypeFactory;

import org.agito.bpmo.sample.invoice.Invoice;
import org.agito.bpmo.sample.invoice.InvoiceAccess;
import org.agito.bpmo.sample.invoice.InvoiceAction;
import org.agito.bpmo.sample.invoice.InvoiceLanguage;
import org.agito.bpmo.sample.invoice.InvoiceLifecycle;
import org.agito.bpmo.sample.invoice.InvoiceProcessActivity;

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

	@SuppressWarnings("unused")
	private final static Logger LOGGER = Logger.getLogger(InvoiceUIController.class);

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

		// set fixed content width
		layoutManager.setWidth((layoutManager.getContentElementBaseWidth() * 4) + 80, UNIT.PIXEL);

		// add Headline
		layoutManager.createAndAddSeparator().setTitle(bpmoAccess.getBPMODefinition().getLabel().getText())
				.addTitleStyleName(SeparatorStyle.H2).addTitleStyleName(SeparatorStyle.HR)
				.setContentWidth(700, UNIT.PIXEL).setWidth(100, UNIT.PERCENTAGE);

		// add initial fields to group
		layoutManager
				.createAndAddGroupContent()
				.setCaption(Texts.getString("InvoiceUIController.InvoiceDetails"))
				.createAndAddElements(Invoice.InvoiceAttachment, Invoice.InvoicingParty, Invoice.InvoiceNumber,
						Invoice.InvoiceDate, Invoice.InvoiceReceived, Invoice.TermOfPayment).setDimension(2); //$NON-NLS-1$

		// add separator for horizontal separation of groups
		layoutManager.createAndAddSeparator().setWidth(10, UNIT.PIXEL);

		// manage finance accounting informations
		// add all remaining content elements excepting TaxPositions to group
		layoutManager
				.createAndAddGroupContent()
				.setCaption(Texts.getString("InvoiceUIController.AccountingInformations")).addRemainingElements(Invoice.TaxPositions).setDimension(2); //$NON-NLS-1$

		layoutManager.addLineBreak();

		// manage TaxPositions layout
		IFlowTableContent tableContent = layoutManager.createAndAddTableContent(Invoice.TaxPositions).addRemainingElements()
				.setPageLength(3);
		tableContent.getColumn(Invoice.TaxPositions$NetAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPositions$TaxAmount).setFooterCalculationMode(ColumnCalculationMode.SUM);
		tableContent.getColumn(Invoice.TaxPositions$TotalAmount).setFooterCalculationMode(ColumnCalculationMode.SUM)
				.setWidth(200);
		tableContent.getColumn(Invoice.TaxPositions$TaxRate).setColumnAlignment(ColumnAlignment.ALIGN_RIGHT)
				.setWidth(200);

		// create attachment viewer
		layoutManager.createAndAddCustomContent().setComponent(
				UIDataTypeFactory
						.getInstance()
						.createEmbeddedAttachmentViewer(
								bpmoAccess.getInvoiceAttachment().getContext().getCharacteristicValue()
										.getCurrentValue(), bpmoAccess.getBPMOHeader().isNew())
						.setViewerHeight(750, UNIT.PIXEL));
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
