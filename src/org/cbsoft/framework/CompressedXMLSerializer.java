package org.cbsoft.framework;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressedXMLSerializer extends FileSerializer {
	
	@Override
	protected byte[] postProcess(byte[] bytes)
			throws IOException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ZipOutputStream out = new ZipOutputStream(byteOut);
		out.putNextEntry(new ZipEntry("internal"));
		out.write(bytes);
		out.closeEntry();
		out.close();
		return byteOut.toByteArray();
	}

	@Override
	protected byte[] formatData(Map<String, Object> props) {
		StringBuilder propFileBuilder = new StringBuilder();
		propFileBuilder.append("<properties>");
		for(String prop : props.keySet()){
			propFileBuilder.append("<"+prop + ">"+props.get(prop)+"</"+prop + ">");
		}
		propFileBuilder.append("</properties>");
		byte[] bytes = propFileBuilder.toString().getBytes();
		return bytes;
	}

}
