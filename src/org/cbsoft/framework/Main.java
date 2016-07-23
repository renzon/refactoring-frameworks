package org.cbsoft.framework;

public class Main {
	
	public static void main(String[] args){
		Product p = new Product("notebook", "HP", 1999.99, "2348203894032948","Não deve aparecer");
		XMLFormatter xmlFormatter = new XMLFormatter();
		PropertiesFormatter pptFmtter = new PropertiesFormatter();
		Compressor compressor = new Compressor();
		Crypto crypto = new Crypto(5);
		CompositePostProcessor cryptoCompressor=new CompositePostProcessor(crypto,compressor);
		
//		FileSerializer cxs = new FileSerializer(xmlFormatter,compressor);
//		cxs.generateFile("product.zip", p);
		
		FileSerializer sps = new FileSerializer(pptFmtter,compressor);
		sps.generateFile("product.zip", p);
		
	}

}
