package beanutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.Converter;

public class MyConverter implements Converter {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (type != Date.class) {
			return null;
		}
		if (value == null || "".equals(value.toString().trim())) {
			return null;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return (T) sdf.parse(value.toString());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
