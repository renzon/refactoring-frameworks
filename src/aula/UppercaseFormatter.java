package aula;

import java.lang.annotation.Annotation;

import org.cbsoft.framework.ValueFormatter;

public class UppercaseFormatter implements ValueFormatter {

	@Override
	public Object format(Object value) {
		return value.toString().toUpperCase();
	}

	@Override
	public void read(Annotation a) {
	}

}
