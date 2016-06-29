package org.cbsoft.framework;

import java.util.Map;

public interface DataFormatter {
	 abstract byte[] formatData(Map<String, Object> props);
}
