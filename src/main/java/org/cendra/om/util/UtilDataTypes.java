package org.cendra.om.util;

import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.model.clazz.TypeCardinality;

public class UtilDataTypes {

	private static String packageName = "org.cendra.om.model.datatype.";

	public static final TypeCardinality INTERNAL_OBJECT_LIST = new TypeCardinality(
			"INTERNAL_OBJECT_LIST");
	public static final TypeCardinality INTERNAL_OBJECT = new TypeCardinality(
			"INTERNAL_OBJECT");
	public static final TypeCardinality EXTERNAL_LEFT_OBJECT = new TypeCardinality(
			"EXTERNAL_LEFT_OBJECT");
	public static final TypeCardinality EXTERNAL_N_N_OBJECT = new TypeCardinality(
			"LEFT_N_N_OBJECT");

	public static final TypeCardinality[] CARDINALITIES = { INTERNAL_OBJECT, INTERNAL_OBJECT_LIST, 
			EXTERNAL_LEFT_OBJECT, EXTERNAL_N_N_OBJECT, };

	public static final Clazz[] PRIMITIVES_TYPES = { buildBoolean(),
			buildString(), buildShort(), buildInteger(), buildLong(),
			buildFloat(), buildDouble(), buildDate() };

	public static final String[] PRIMITIVES_TYPES_NAMES = {
			buildBoolean().getName(), buildString().getName(),
			buildShort().getName(), buildInteger().getName(),
			buildLong().getName(), buildFloat().getName(),
			buildDouble().getName(), buildDate().getName() };

	public static Clazz buildBoolean() {

		return buildString("Boolean");
	}

	public static Clazz buildString() {

		return buildString("String");
	}

	public static Clazz buildShort() {

		return buildString("Short");
	}

	public static Clazz buildInteger() {

		return buildString("Integer");
	}

	public static Clazz buildLong() {

		return buildString("Long");
	}

	public static Clazz buildFloat() {

		return buildString("Float");
	}

	public static Clazz buildDouble() {

		return buildString("Double");
	}
	
	public static Clazz buildDate() {

		return buildString("Date");
	}

	private static Clazz buildString(String name) {

		Clazz clazz = new Clazz();
		clazz.setId(packageName + name);
		clazz.setFinalClass(true);
		clazz.setVisibility(UtilTypesVisibilityClass.PUBLIC);
		clazz.setName(clazz.getId());

		return clazz;
	}

	public static boolean isPrimitiveType(Clazz clazz) {
		for (Clazz primitiveType : PRIMITIVES_TYPES) {
			if (clazz.equals(primitiveType)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isPrimitiveType(String clazzName) {
		for (Clazz primitiveType : PRIMITIVES_TYPES) {
			if (clazzName.equals(primitiveType.getName())) {
				return true;
			}
		}

		return false;
	}

	public static boolean isBooleanType(Clazz clazz) {
		if (clazz.getName().equals(buildBoolean().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isStringType(Clazz clazz) {
		if (clazz.getName().equals(buildString().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isShortType(Clazz clazz) {
		if (clazz.getName().equals(buildShort().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isIntegerType(Clazz clazz) {
		if (clazz.getName().equals(buildInteger().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isLongType(Clazz clazz) {
		if (clazz.getName().equals(buildLong().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isFloatType(Clazz clazz) {
		if (clazz.getName().equals(buildFloat().getName())) {
			return true;
		}

		return false;
	}
	
	public static boolean isDoubleType(Clazz clazz) {
		if (clazz.getName().equals(buildDouble().getName())) {
			return true;
		}

		return false;
	}
	
	public static boolean isDateType(Clazz clazz) {
		if (clazz.getName().equals(buildDate().getName())) {
			return true;
		}

		return false;
	}

//	public static boolean isDoubleType(String clazzName) {
//		if (clazzName.equals(buildDouble().getName())) {
//			return true;
//		}
//
//		return false;
//	}

	public static Clazz getPrimitiveType(String clazzName) {
		for (Clazz primitiveType : PRIMITIVES_TYPES) {
			if (clazzName.equals(primitiveType.getName())) {
				return primitiveType;
			}
		}

		return null;
	}

	public static boolean ifExistsTypeCardinality(
			TypeCardinality typeCardinality) {
		for (TypeCardinality item : UtilDataTypes.CARDINALITIES) {
			if (item.equals(typeCardinality)) {
				return true;
			}
		}

		return false;
	}

}
