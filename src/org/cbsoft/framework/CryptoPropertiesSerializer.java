package org.cbsoft.framework;

import java.io.FileOutputStream;
import java.util.Map;


public class CryptoPropertiesSerializer {
	
	private int delay;
	
	public CryptoPropertiesSerializer(int delay) {
		this.delay = delay;
	}

	public void generateFile(String filename, PropertiesGetter propGetter){
		byte[] bytes = createPropertiesFormat(propGetter.getPropertiesList());
		
		try {
			bytes = encript(bytes);	
			FileOutputStream fileout = new FileOutputStream(filename);
			fileout.write(bytes);
			fileout.close();
		} catch (Exception e) {
			throw new RuntimeException("Problems writing the file",e);
		}
		
	}

	private byte[] encript(byte[] bytes) {
		byte[] newBytes = new byte[bytes.length];
		for(int i=0;i<bytes.length;i++){
			newBytes[i]= (byte) ((bytes[i]+delay) % Byte.MAX_VALUE);
		}
		return newBytes;
	}

	private byte[] createPropertiesFormat(Map<String, Object> props) {
		StringBuilder propFileBuilder = new StringBuilder();
		for(String prop : props.keySet()){
			propFileBuilder.append(prop + "="+props.get(prop)+"\n");
		}
		byte[] bytes = propFileBuilder.toString().getBytes();
		return bytes;
	}

}
