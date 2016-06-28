package org.cbsoft.framework;

import java.io.FileOutputStream;
import java.util.Map;


public class CryptoPropertiesSerializer extends FileSerializer {
	
	private int delay;
	
	public CryptoPropertiesSerializer(int delay) {
		this.delay = delay;
	}

	protected byte[] postProcess(byte[] bytes) {
		byte[] newBytes = new byte[bytes.length];
		for(int i=0;i<bytes.length;i++){
			newBytes[i]= (byte) ((bytes[i]+delay) % Byte.MAX_VALUE);
		}
		return newBytes;
	}
	protected byte[] formatData(Map<String, Object> props) {

		StringBuilder propFileBuilder = new StringBuilder();
		for(String prop : props.keySet()){
			propFileBuilder.append(prop + "="+props.get(prop)+"\n");
		}
		byte[] bytes = propFileBuilder.toString().getBytes();
		return bytes;
	}

}
