package org.cendra.om.persist.dao.impl.dbrms.pg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cendra.om.bo.XClazzBOX;
import org.cendra.om.model.clazz.old.XAttributeX;
import org.cendra.om.model.clazz.old.XTypeX;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilUUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ImplClazzInstancePgDAO {

	private XClazzBOX clazzBO;

	public ImplClazzInstancePgDAO(XClazzBOX clazzBO) {
		super();
		this.clazzBO = clazzBO;
	}

	public JsonObject insert(String className, JsonObject json)
			throws Exception {

		XTypeX clazz = clazzBO.findByName(className);

		if (clazz == null) {
			throw new IllegalArgumentException(
					"Se pretende crear una instancia de una clase que no existe. "
							+ className + ".");
		}
		// if (clazz.getAbstractClass() == true) {
		// throw new IllegalArgumentException(
		// "Se pretende crear una instancia de una clase abstracta. "
		// + className + ".");
		// }

		if (json.isJsonArray()) {
			throw new IllegalArgumentException(
					"Se pretende crear una instancia de una clase en base a un array de objetos. Se espera un solo objeto para crear. "
							+ json + ".");
		}

		insert(clazz, json);

		String id = UtilUUID.buildUUID();

		json.addProperty("id", id);

		return json;
	}

	private void insert(XTypeX clazz, JsonObject json) throws Exception {

		Map<String, String> fieldNames = new HashMap<String, String>();

		List<Object> argsList = new ArrayList<Object>();

		Set<Map.Entry<String, JsonElement>> entries = json.entrySet();
		for (Map.Entry<String, JsonElement> entry : entries) {

			String attName = entry.getKey().trim();

			// checkAttExists(attName, clazz, json);

			boolean clazzNotFound = true;

			// for (int i = 0; i < clazz.getExtendsClass().size(); i++) {

			XTypeX extendsClass = null;

//			if (clazz.getExtendsClass().getAtts().size() == 0) {
//				extendsClass = clazzBO.findByName(clazz.getExtendsClass()
//						.getName());
//			} else {
//				extendsClass = clazz.getExtendsClass();
//			}

//			clazz.setExtendsClass(extendsClass);

			for (XAttributeX clazzAtt : extendsClass.getAtts()) {

				if (clazzAtt.getName().equals(attName)) {

					if (UtilDataTypes.isPrimitiveType(clazzAtt.getDataType())) {

						String fields = "";

						if (fieldNames.containsKey(extendsClass.getName())) {
							fields = fieldNames.get(extendsClass.getName())
									+ ", " + attName;
						} else {
							fields = attName;
						}

						fieldNames.put(extendsClass.getName(), fields);
					}

					clazzNotFound = false;

					break;

				}
			}
			// }

			for (XAttributeX clazzAtt : clazz.getAtts()) {

				if (clazzAtt.getName().equals(attName)) {

					if (UtilDataTypes.isPrimitiveType(clazzAtt.getDataType())) {
						String fields = "";

						if (fieldNames.containsKey(clazz.getName())) {
							fields = fieldNames.get(clazz.getName()) + ", "
									+ attName;
						} else {
							fields = attName;
						}

						fieldNames.put(clazz.getName(), fields);

					}

					clazzNotFound = false;

					break;
				}
			}

			if (clazzNotFound) {
				throw new IllegalArgumentException(
						"El atributo no existe, se pretende crear una instancia de una clase que no coincide con la estructura de la clase especiicada. "
								+ clazz.getName()
								+ "."
								+ attName
								+ "\n"
								+ json
								+ ".");
			}

		} // ENDS ATTS

		List<String> sqlInsertList = new ArrayList<String>();

		// for (Clazz extendsClass : clazz.getExtendsClass()) {
//		String schemaName = clazz.getExtendsClass().getPackagesName()
//				.replace(".", "_");
//		String fieldNamesString = "id, "
//				+ fieldNames.get(clazz.getExtendsClass().getName());
//
//		String argsString = "?";
//		int cantArgs = fieldNamesString.split(",").length;
//		for (int i = 0; i < cantArgs; i++) {
//			argsString += ", ?";
//		}
//		String sqlInsert = "INSERT INTO " + schemaName + "."
//				+ clazz.getExtendsClass().getSimpleName() + " ("
//				+ fieldNamesString + ") VALUES (" + argsString + ")";
//
//		sqlInsertList.add(sqlInsert);
//
//		System.out.println(sqlInsert);
		// }

//		schemaName = clazz.getPackagesName().replace(".", "_");
//		fieldNamesString = "id, " + fieldNames.get(clazz.getName());
//
//		argsString = "?";
//		cantArgs = fieldNamesString.split(",").length;
//		for (int i = 0; i < cantArgs; i++) {
//			argsString += ", ?";
//		}
//		sqlInsert = "INSERT INTO " + schemaName + "." + clazz.getSimpleName()
//				+ " (" + fieldNamesString + ") VALUES (" + argsString + ")";
//
//		sqlInsertList.add(sqlInsert);
//
//		System.out.println(sqlInsert);

		// System.out.println("----------------------------------------------------------");
		// // System.out.println(fieldNames);
		// for (Map.Entry<String, String> fieldNamesItem :
		// fieldNames.entrySet()) {
		// System.out.println(fieldNamesItem.getKey() + " = " +
		// fieldNamesItem.getValue());
		// }
		// System.out.println("----------------------------------------------------------");

	} // END INSERT



}
