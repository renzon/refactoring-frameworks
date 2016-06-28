package org.cbsoft.framework;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public abstract class FileSerializer {

	public FileSerializer() {
		super();
	}

	protected abstract byte[] formatData(Map<String, Object> props);

	protected abstract byte[] postProcess(byte[] bytes) throws IOException;

	public void generateFile(String filename, PropertiesGetter propGetter) {
		byte[] bytes = formatData(propGetter.getPropertiesList());
		
	    try {
	    	bytes = postProcess(bytes);
			FileOutputStream fileout = new FileOutputStream(filename);
			fileout.write(bytes);
			fileout.close();
		} catch (Exception e) {
			throw new RuntimeException("Problems writing the file",e);
		}
	}

}