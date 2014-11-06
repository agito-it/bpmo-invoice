package org.agito.bpmo.sample.invoice.bpmo;


import de.agito.cps.core.bpmo.api.enums.IAction;


/**
 * BPMO Actions for Invoice.
 *
 * @author joerg.burmeister
 */
public enum InvoiceAction implements IAction {

	ResolveOrder,
	GetApprover,
	BackendBooking;

}

