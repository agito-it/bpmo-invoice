package org.agito.bpmo.sample.invoice.bpmo;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import de.agito.cps.core.context.ClientContextFactory;

public class Texts {
	private static final String BUNDLE_NAME = "org.agito.bpmo.sample.invoice.text"; //$NON-NLS-1$

	public static String getString(String key, Locale locale) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME,
					locale == null ? ClientContextFactory.getLocale() : locale);
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	public static String getString(String key) {
		return getString(key, null);
	}
}
