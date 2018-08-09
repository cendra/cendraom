package org.cendra.om.util;

import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.model.clazz.TypeCardinality;

public class UtilDataTypes {

	private static String packageName = "org.cendra.om.model.datatype.";

	public static final TypeCardinality CARDINALITY_1_1 = new TypeCardinality("1-1");
	public static final TypeCardinality CARDINALITY_1_N = new TypeCardinality("1-N");
	public static final TypeCardinality CARDINALITY_NN = new TypeCardinality("N-N");
	public static final TypeCardinality[] CARDINALITIES = { CARDINALITY_1_1,
			CARDINALITY_1_N, CARDINALITY_NN };
	
	public static final Clazz[] PRIMITIVES_TYPES = { buildString() };

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
	
	public static boolean isPrimitiveType(Clazz clazz){
		for(Clazz primitiveType : PRIMITIVES_TYPES){
			if(clazz.equals(primitiveType)){
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isPrimitiveType(String clazzName){
		for(Clazz primitiveType : PRIMITIVES_TYPES){
			if(clazzName.equals(primitiveType.getName())){
				return true;
			}
		}
		
		return false;
	}
	
	public static Clazz getPrimitiveType(String clazzName){
		for(Clazz primitiveType : PRIMITIVES_TYPES){
			if(clazzName.equals(primitiveType.getName())){
				return primitiveType;
			}
		}
		
		return null;
	}
	
	public static boolean ifExistsTypeCardinality(TypeCardinality typeCardinality){
		for (TypeCardinality item : UtilDataTypes.CARDINALITIES) {
			if (item.equals(typeCardinality)) {				
				return true;
			}
		}
		
		return false;
	}

}
