package de.agito.bpmo.sample.invoice;

import de.agito.cps.core.bpmo.api.enums.ILanguage;
import java.util.Locale;

/**
 * Languages for Invoice.
 *
 * @author Jörg Burmeister
 */
public enum InvoiceLanguage implements ILanguage {

	en("en", new Locale("en"), true),
	de("de", new Locale("de"), false);

	private InvoiceLanguage(String code, Locale locale, boolean defaultIndicator) { this.code = code; this.locale = locale; this.defaultIndicator = defaultIndicator; }
	private String code;
	private boolean defaultIndicator;
	private Locale locale;
	public String getCode() { return code; }
	public boolean isDefault() { return defaultIndicator; }
	public Locale getLocale() { return locale; }

}

