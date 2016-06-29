package org.cbsoft.framework;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CompositePostProcessor implements PostProcessor {

	List<PostProcessor> processors;
	
	public  CompositePostProcessor(PostProcessor ... processors) {
		this.processors=Arrays.asList(processors);
	}

	@Override
	public byte[] postProcess(byte[] bytes) throws IOException {
		for(PostProcessor p:processors){
			bytes=p.postProcess(bytes);
		}
		return bytes;
	}

}
