package yfu.practice.springboot.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.context.annotation.Bean;

//@Configuration
public class EntityMapConfig {
	
	private static final String PACKAGE_NAME = "yfu.practice.springboot.entity";

	@Bean
	public Map<String, Class<?>> entityMap() {
		// 此種寫法打包成jar檔好像會有找不到Entity的問題
		InputStream inputStream = ClassLoader.getSystemClassLoader()	// 只載入classpath下的class的加載器
				.getResourceAsStream(PACKAGE_NAME.replace('.', '/'));
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		return reader.lines()
				.filter(e -> e.endsWith(".class"))
				.map(e -> e.substring(0, e.lastIndexOf('.')))
				.map(this::getEntity)
				.filter(Objects::nonNull)
				.collect(Collectors.toMap(this::getTableName, Function.identity()));
	}
	
	private Class<?> getEntity(String className) {
		try {
			String classFullName = new StringBuilder().append(PACKAGE_NAME).append('.').append(className).toString();
			Class<?> clazz = Class.forName(classFullName);
			return clazz.isAnnotationPresent(Entity.class) ? clazz : null;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String getTableName(Class<?> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		return table != null && !table.name().isEmpty() ? table.name() : clazz.getSimpleName();
	}
	
}