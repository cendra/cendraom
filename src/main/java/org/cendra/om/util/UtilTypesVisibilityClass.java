package org.cendra.om.util;

import org.cendra.om.model.clazz.TypeVisibilityClass;

public class UtilTypesVisibilityClass {

	public static TypeVisibilityClass PUBLIC = new TypeVisibilityClass("public");
	public static TypeVisibilityClass PRIVATE = new TypeVisibilityClass(
			"private");
	public static TypeVisibilityClass PUBLICDOWN = new TypeVisibilityClass(
			"publicdown");

	public static final TypeVisibilityClass[] VISIBILITIES = { PUBLIC, PRIVATE,
			PUBLICDOWN };

	public static boolean ifExistsTVisibility(
			TypeVisibilityClass typeVisibilityClass) {
		for (TypeVisibilityClass item : VISIBILITIES) {
			if (item.equals(typeVisibilityClass)) {
				return true;
			}
		}

		return false;
	}
}
