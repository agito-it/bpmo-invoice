package de.agito.bpmo.sample.invoice;

import de.agito.cps.core.bpmo.api.enums.IAction;

/**
 * BPMO Actions for Invoice.
 *
 * @author Jörg Burmeister
 */
public enum InvoiceAction implements IAction {

	ResolveOrder,
	BackendBooking;

}

