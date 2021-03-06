package org.agito.bpmo.sample.invoice.bpmo;


import de.agito.cps.core.bpmo.api.enums.ILifecycle;


/**
 * Lifecycle Enum for Invoice.
 *
 * @author joerg.burmeister
 */
public enum InvoiceLifecycle implements ILifecycle {

	NEW(false, "InvoiceProcess");

	private InvoiceLifecycle(boolean supportsOriginalValue, String processDefinitionId) { this.supportsOriginalValue = supportsOriginalValue; this.processDefinitionId = processDefinitionId; }
	private boolean supportsOriginalValue;
	private String processDefinitionId;
	public boolean supportsOriginalValue() { return supportsOriginalValue; }
	public String getProcessDefinitionId() { return processDefinitionId; }

}

