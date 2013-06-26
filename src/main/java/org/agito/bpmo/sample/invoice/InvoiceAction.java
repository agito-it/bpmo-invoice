package org.agito.bpmo.sample.invoice;

import de.agito.cps.core.bpmo.api.enums.IAction;

/**
 * BPMO Actions for Invoice.
 *
 * @author agito
 */
public enum InvoiceAction implements IAction {

	ResolveOrder,
	GetApprover,
	BackendBooking;

}

