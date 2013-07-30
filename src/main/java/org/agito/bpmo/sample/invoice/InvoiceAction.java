package org.agito.bpmo.sample.invoice;

import de.agito.cps.core.bpmo.api.enums.IAction;

/**
 * BPMO Actions for Invoice.
 *
 * @author andreas.weise
 */
public enum InvoiceAction implements IAction {

	ResolveOrder,
	GetApprover,
	BackendBooking;

}

