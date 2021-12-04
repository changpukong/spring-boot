package yfu.practice.springboot.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.function.UnaryOperator;

public class MapReflectUtil {
	
	private MapReflectUtil() {
	}

	public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) throws Exception {
		return map2Object(map, clazz, null);
	}

	public static <T> T map2Object(Map<String, Object> map, Class<T> clazz, UnaryOperator<String> prop2KeyConverter)
			throws Exception {
		if (map == null) {
			return null;
		}

		T obj = clazz.getDeclaredConstructor().newInstance();
		PropertyDescriptor[] properties = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
		for (PropertyDescriptor property : properties) {
			Method setter = property.getWriteMethod();
			if (setter != null) {
				String key = prop2KeyConverter == null ? property.getName()
						: prop2KeyConverter.apply(property.getName());
				if (property.getPropertyType() == java.lang.String.class) {
					setter.invoke(obj, toString(map.get(key)));
				} else {
					setter.invoke(obj, map.get(key));
				}
			}
		}

		return obj;
	}

	public static String toString(Object obj) {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		if (obj == null) {
			return null;
		} else if (obj instanceof java.lang.String) {
			return (String) obj;
		} else if (obj instanceof java.sql.Date) {
			return date.format(obj);
		} else if (obj instanceof java.sql.Timestamp) {
			return timestamp.format(obj);
		}
		return obj.toString();
	}
}