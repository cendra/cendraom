package org.cendra.om.util;

import org.cendra.om.bo.clazz.model.Clazz;
import org.cendra.om.bo.clazz.model.TypeCardinality;


public class UtilDataTypes {

	private static String packageName = "org.cendra.om.model.datatype.";
	
	public static TypeCardinality CARDINALITY_1_1 = new TypeCardinality("1-1");
	public static TypeCardinality CARDINALITY_1_N = new TypeCardinality("1-N");
	public static TypeCardinality CARDINALITY_NN = new TypeCardinality("N-N");
	
	public static Clazz buildString() {
		
		return buildString("String");
	}
	
	
	private static Clazz buildString(String name) {
		
		Clazz clazz = new Clazz();
		clazz.setId(packageName + name);
		clazz.setFinalClass(true);
		clazz.setVisibility(UtilTypesVisibilityClass.PUBLIC);
		clazz.setName(clazz.getId());			
		
		return clazz;
	}

}
