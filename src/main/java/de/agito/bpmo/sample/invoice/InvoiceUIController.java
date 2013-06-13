package de.agito.bpmo.sample.invoice;

// @@begin imports
import de.agito.bpmo.sample.invoice.Invoice;
import de.agito.bpmo.sample.invoice.InvoiceAccess;
import de.agito.bpmo.sample.invoice.InvoiceAction;
import de.agito.bpmo.sample.invoice.InvoiceLanguage;
import de.agito.bpmo.sample.invoice.InvoiceLifecycle;
import de.agito.bpmo.sample.invoice.InvoiceProcessActivity;
import de.agito.cps.ui.vaadin.bpmo.BPMOUIController;
import de.agito.cps.ui.vaadin.bpmo.IBPMOUIControllerContext;
import de.agito.cps.ui.vaadin.bpmo.annotation.Navigation;
import de.agito.cps.ui.vaadin.bpmo.enums.NavigationType;
// @@end

// @@begin head:uicontroller
/**
 * Vaadin UI Controller for Invoice.
 * 
 * @author Jörg Burmeister
 */
// @@end
public class InvoiceUIController extends BPMOUIController<InvoiceAccess, InvoiceAction, InvoiceLifecycle, InvoiceLanguage, InvoiceProcessActivity, Invoice> {

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

	// @@end
}
