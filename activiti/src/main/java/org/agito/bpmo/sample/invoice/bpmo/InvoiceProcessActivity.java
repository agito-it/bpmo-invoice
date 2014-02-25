package org.agito.bpmo.sample.invoice.bpmo;


import de.agito.cps.core.bpmo.api.enums.IProcessActivity;


/**
 * Process Activity Enum for Invoice.
 *
 * @author andreas.weise
 */
public enum InvoiceProcessActivity implements IProcessActivity {

	$DRAFT,
	ResolveOrder,
	CheckOrder,
	Approval,
	Transfer;

}

