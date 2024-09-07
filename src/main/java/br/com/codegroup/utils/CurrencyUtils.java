package br.com.codegroup.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

public class CurrencyUtils {
	public static Double getDoubleValue(String orcamentoTotal) {
		DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance(Locale.GERMAN);
        decimalFormat.setParseBigDecimal(true);
        
        Number number;
		try {
			number = decimalFormat.parse(orcamentoTotal.trim());
			return number.doubleValue();
		} catch (ParseException e) {
			return new BigDecimal(0).doubleValue();
		}
	}
	
	public static String formatCurrency(Double value) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00", symbols);
        
        return "R$ " + decimalFormat.format(value);
	}
}
