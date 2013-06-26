package org.agito.bpmo.sample.invoice;

import de.agito.cps.core.bpmo.api.enums.IProcessActivity;

/**
 * Process Activity Enum for Invoice.
 *
 * @author agito
 */
public enum InvoiceProcessActivity implements IProcessActivity {

	$DRAFT,
	ResolveOrder,
	CheckOrder,
	Approval,
	Transfer;

}

