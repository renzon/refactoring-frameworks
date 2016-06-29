package org.cbsoft.framework;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
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

	public void generateFile(String filename, PropertiesGetter propGetter) {
		byte[] bytes = df.formatData(propGetter.getPropertiesList());

		try {
			bytes = pp.postProcess(bytes);
			FileOutputStream fileout = new FileOutputStream(filename);
			fileout.write(bytes);
			fileout.close();
		} catch (Exception e) {
			throw new RuntimeException("Problems writing the file", e);
		}
	}

}