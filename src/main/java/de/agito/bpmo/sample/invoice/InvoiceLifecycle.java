package de.agito.bpmo.sample.invoice;

import de.agito.cps.core.bpmo.api.enums.ILifecycle;

/**
 * Lifecycle Enum for Invoice.
 *
 * @author Jörg Burmeister
 */
public enum InvoiceLifecycle implements ILifecycle {

	NEW(false, "FinancialManagement_Invoice");

	private InvoiceLifecycle(boolean supportsOriginalValue, String processDefinitionId) { this.supportsOriginalValue = supportsOriginalValue; this.processDefinitionId = processDefinitionId; }
	private boolean supportsOriginalValue;
	private String processDefinitionId;
	public boolean supportsOriginalValue() { return supportsOriginalValue; }
	public String getProcessDefinitionId() { return processDefinitionId; }

}

