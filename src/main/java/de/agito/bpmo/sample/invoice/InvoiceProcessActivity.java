package de.agito.bpmo.sample.invoice;

import de.agito.cps.core.bpmo.api.enums.IProcessActivity;

/**
 * Process Activity Enum for Invoice.
 *
 * @author Jörg Burmeister
 */
public enum InvoiceProcessActivity implements IProcessActivity {

	$DRAFT,
	CheckOrder,
	Approval;

}

