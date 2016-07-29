package org.cbsoft.framework;

import java.lang.annotation.Annotation;

public class PrefixFormatter implements ValueFormatter {
	private String prefix;

	@Override
	public Object format(Object value) {
		return prefix+value;
	}

	@Override
	public void read(Annotation a) {
		prefix=((Prefix) a).value();
		
	}

}
