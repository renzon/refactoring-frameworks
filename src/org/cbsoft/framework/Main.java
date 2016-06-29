package org.cbsoft.framework;

public class Main {
	
	public static void main(String[] args){
		Product p = new Product("notebook", "HP", 1999.99, "2348203894032948");
		XMLFormatter xmlFormatter = new XMLFormatter();
		PropertiesFormatter pptFmtter = new PropertiesFormatter();
		Compressor compressor = new Compressor();
		
		FileSerializer cxs = new FileSerializer(xmlFormatter,compressor);
		cxs.generateFile("product.zip", p);
		
		FileSerializer sps = new FileSerializer(pptFmtter,new Crypto(5));
		sps.generateFile("product.txt", p);
		
	}

}
