package org.agito.bpmo.sample.invoice.bpmo;


import de.agito.cps.core.bpmo.api.enums.IProcessActivity;


/**
 * Process Activity Enum for Invoice.
 *
 * @author joerg.burmeister
 */
public enum InvoiceProcessActivity implements IProcessActivity {

	$DRAFT,
	ResolveOrder,
	CheckOrder,
	Approval,
	Transfer;

}

