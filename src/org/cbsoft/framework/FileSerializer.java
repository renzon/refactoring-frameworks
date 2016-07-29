package org.cbsoft.framework;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileSerializer {
	private DataFormatter df;

	public FileSerializer(DataFormatter df, PostProcessor pp) {
		super();
		this.df = df;
		this.pp = pp;
	}

	private PostProcessor pp;

	public void generateFile(String filename, Object obj) {
		byte[] bytes = df.formatData(getPropertiesList(obj));

		try {
			bytes = pp.postProcess(bytes);
			FileOutputStream fileout = new FileOutputStream(filename);
			fileout.write(bytes);
			fileout.close();
		} catch (Exception e) {
			throw new RuntimeException("Problems writing the file", e);
		}
	}
	
//	@Override
	public Map<String, Object> getPropertiesList(Object obj) {
		Map<String,Object> props = new HashMap<String, Object>();
		Class<?> cls=obj.getClass();
		for(Method m: cls.getMethods()){
			String getterName = m.getName();
			if(getterName.startsWith("get")  
					&& m.getParameterTypes().length==0
					&& m.getReturnType()!= void.class
					&& !getterName.equals("getClass")
					&& !m.isAnnotationPresent(DontIncludeOnFile.class)){
				
				try {
					Object valor= m.invoke(obj);
					String simpleName=getterName.substring(3).toLowerCase();
					valor = formatValue(m, valor);
					
					props.put(simpleName, valor);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new RuntimeException("Não foi possível acessar propriedade "+getterName);
				}
				
				
			}
		}
		return props;
	}

	private Object formatValue(Method m, Object valor) throws InstantiationException, IllegalAccessException {
		List<Annotation> fmtAnnotations = Arrays.asList(m.getAnnotations()).stream().
			filter(a -> a.annotationType().
					isAnnotationPresent(FormatterImplementation.class)
		).collect(Collectors.toList());
		for(Annotation a: fmtAnnotations){
			FormatterImplementation fi = a.annotationType().getAnnotation(FormatterImplementation.class);
			ValueFormatter i = fi.value().newInstance();
			i.read(a);
			valor=i.format(valor);
		}
		return valor;
	}

}