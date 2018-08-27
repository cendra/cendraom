package org.cendra.om.util;

import org.cendra.om.model.clazz.old.TypeCardinality;
import org.cendra.om.model.clazz.old.XClazzX;
import org.cendra.om.model.clazz.old.XTypeX;

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

	public static final TypeCardinality[] CARDINALITIES = { INTERNAL_OBJECT,
			INTERNAL_OBJECT_LIST, EXTERNAL_LEFT_OBJECT, EXTERNAL_N_N_OBJECT, };

	public static final XTypeX[] PRIMITIVES_TYPES = { buildBoolean(),
			buildString(), buildShort(), buildInteger(), buildLong(),
			buildFloat(), buildDouble(), buildDate() };

	public static final String[] PRIMITIVES_TYPES_NAMES = {
			buildBoolean().getName(), buildString().getName(),
			buildShort().getName(), buildInteger().getName(),
			buildLong().getName(), buildFloat().getName(),
			buildDouble().getName(), buildDate().getName() };

	public static XTypeX buildBoolean() {

		return buildPrimitiveType("Boolean");
	}

	public static XTypeX buildString() {

		return buildPrimitiveType("String");
	}

	public static XTypeX buildShort() {

		return buildPrimitiveType("Short");
	}

	public static XTypeX buildInteger() {

		return buildPrimitiveType("Integer");
	}

	public static XTypeX buildLong() {

		return buildPrimitiveType("Long");
	}

	public static XTypeX buildFloat() {

		return buildPrimitiveType("Float");
	}

	public static XTypeX buildDouble() {

		return buildPrimitiveType("Double");
	}

	public static XTypeX buildDate() {

		return buildPrimitiveType("Date");
	}

	private static XTypeX buildPrimitiveType(String name) {

		XTypeX clazz = new XClazzX();
		clazz.setId(packageName + name);
		clazz.setFinalType(true);
		clazz.setVisibility(UtilTypesVisibilityClass.PUBLIC);
		clazz.setName(clazz.getId());

		return clazz;
	}

	public static XTypeX buildInternalClass(String packageName, String attName) {

		XTypeX clazz = new XClazzX(true);
		// clazz.setId(packageName + attName);
		clazz.setFinalType(true);
		clazz.setVisibility(UtilTypesVisibilityClass.PRIVATE);
		clazz.setName(packageName + "." + attName);

		return clazz;
	}

	public static boolean isPrimitiveType(XTypeX clazz) {
		for (XTypeX primitiveType : PRIMITIVES_TYPES) {
			if (clazz.equals(primitiveType)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isPrimitiveType(String clazzName) {
		for (XTypeX primitiveType : PRIMITIVES_TYPES) {
			if (clazzName.equals(primitiveType.getName())) {
				return true;
			}
		}

		return false;
	}

	public static boolean isBooleanType(XTypeX clazz) {
		if (clazz.getName().equals(buildBoolean().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isStringType(XTypeX clazz) {
		if (clazz.getName().equals(buildString().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isShortType(XTypeX clazz) {
		if (clazz.getName().equals(buildShort().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isIntegerType(XTypeX clazz) {
		if (clazz.getName().equals(buildInteger().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isLongType(XTypeX clazz) {
		if (clazz.getName().equals(buildLong().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isFloatType(XTypeX clazz) {
		if (clazz.getName().equals(buildFloat().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isDoubleType(XTypeX clazz) {
		if (clazz.getName().equals(buildDouble().getName())) {
			return true;
		}

		return false;
	}

	public static boolean isDateType(XTypeX clazz) {
		if (clazz.getName().equals(buildDate().getName())) {
			return true;
		}

		return false;
	}

	// public static boolean isDoubleType(String clazzName) {
	// if (clazzName.equals(buildDouble().getName())) {
	// return true;
	// }
	//
	// return false;
	// }

	public static XTypeX getPrimitiveType(String clazzName) {
		for (XTypeX primitiveType : PRIMITIVES_TYPES) {
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
