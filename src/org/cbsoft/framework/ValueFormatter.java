package org.cbsoft.framework;

import java.lang.annotation.Annotation;

public interface ValueFormatter {
	Object format(Object value);
	
	void read(Annotation a);

}
