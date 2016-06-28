package org.cbsoft.framework;

public class Main {
	
	public static void main(String[] args){
		Product p = new Product("notebook", "HP", 1999.99, "2348203894032948");
		
		FileSerializer cxs = new CompressedXMLSerializer();
		cxs.generateFile("product.zip", p);
		
		CryptoPropertiesSerializer sps = new CryptoPropertiesSerializer(5);
		sps.generateFile("product.txt", p);
		
	}

}
