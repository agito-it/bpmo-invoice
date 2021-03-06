package org.agito.bpmo.sample.invoice.recources;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import de.agito.cps.core.context.ClientContextFactory;
import de.agito.cps.core.utils.Constants;

public class InvoiceTextResourceUtils {
private final static String BUNDLE_PATH =  InvoiceTextResourceUtils.class.getPackage().getName().replace(".", "/").concat("/Text");
public static String getText(InvoiceTextResource key, Object... params) {return getMessage(key.toString(), params, ClientContextFactory.getLocale());}
public static String getText(InvoiceTextResource key, Locale locale, Object... params) {return getMessage(key.toString(), params, locale);}
private static String getMessage(String key, Object[] params, Locale locale) {ResourceBundle rb = ResourceBundle.getBundle(BUNDLE_PATH, locale == null ? Constants.DEFAULT_LOCALE : locale);StringBuffer sb = new StringBuffer();if (params == null) {params = new Object[] {};}if (params.length > 0) {MessageFormat mf = new MessageFormat(rb.getString(key)); mf.format(params, sb, null);} else {sb.append(rb.getString(key));}return sb.toString();}}