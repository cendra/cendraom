package org.cendra.om.bo.clazz;

import org.cendra.om.bo.clazz.model.Clazz;
import org.cendra.om.bo.clazz.model.ClazzAtt;
import org.cendra.om.bo.clazz.model.persist.ClazzPersist;
import org.cendra.om.bo.object.CreateObjectBO;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilSerializeObjects;
import org.cendra.om.util.UtilTypesComponents;
import org.cendra.om.util.UtilTypesVisibilityClass;

import com.google.gson.JsonObject;

public class CreateClassBO {

	private CreateObjectBO createObjectBO;
	private IfExistsClassBO ifExistsClassBO;
	private UtilSerializeObjects utilSerializeObjects;

	public CreateClassBO(CreateObjectBO createObjectBO,
			IfExistsClassBO ifExistsClassBO,
			UtilSerializeObjects utilSerializeObjects) {
		super();
		this.createObjectBO = createObjectBO;
		this.ifExistsClassBO = ifExistsClassBO;
		this.utilSerializeObjects = utilSerializeObjects;
	}

	public Clazz create() throws Exception {

		Clazz classComponent = new Clazz();

		JsonObject jsonObject = createObjectBO
				.create(UtilTypesComponents.CLASS_COMPONENT);
		classComponent.setId(jsonObject.get("id").getAsString());
		classComponent.setVirtual(jsonObject.get("virtual").getAsBoolean());

		return classComponent;

	}

	public Clazz create(Clazz clazz) throws Exception {

		checkClass("Create Class", clazz, "");

		if (ifExistsClassBO.ifExistsClass(clazz)) {
			throw new IllegalArgumentException(
					"Se intento crear una clase que ya existe '"
							+ clazz.getName() + "'. ");
		}

		// ------------------------------------------------------------------------------------

		// Gson gson = new Gson();
		// String jsonString = gson.toJson(new
		// ClassComponentPersist(classComponent));

		// String jsonString = serializeObjects.toJsonByGson(new
		// ClassComponentPersist(classComponent));
		String jsonString = utilSerializeObjects
				.toJsonByJackson(new ClazzPersist(clazz));

		JsonObject jsonObject = createObjectBO.create(jsonString,
				UtilTypesComponents.CLASS_COMPONENT);

		clazz.setId(jsonObject.get("id").getAsString());
		clazz.setVirtual(jsonObject.get("virtual").getAsBoolean());

		return clazz;
	}

	private boolean checkClass(String operation, Clazz clazz, String msg)
			throws Exception {

		if (clazz == null) {
			throw new IllegalArgumentException(operation + ". Clase nula. "
					+ msg);
		}
		if (clazz.getName() == null) {
			throw new IllegalArgumentException(operation
					+ ". Clase con un nombre nulo. " + msg);
		}
		if (clazz.getName().trim().length() == 0) {
			throw new IllegalArgumentException(operation
					+ ". Clase con un nombre vacio. " + msg);
		}
		String regex = "^[a-zA-Z'.]{1,100}$";
		if (clazz.getName().matches(regex) == false) {
			throw new IllegalArgumentException(operation
					+ ". Clase con nombre incorrecto, '" + clazz.getName()
					+ "', se espera un nombre con la forma \"" + regex + "\". "
					+ msg);
		}
		if (Character.isUpperCase(clazz.getSimpleName().charAt(0)) == false) {
			throw new IllegalArgumentException(operation
					+ ". Clase con nombre incorrecto, '" + clazz.getName()
					+ "', se espera que sea CamelCase. " + msg);
		}

		for (Clazz e : clazz.getExtendsClass()) {
			checkExtendsClass(operation, clazz, e);
		}

		for (ClazzAtt e : clazz.getAtts()) {
			checkClassAtt(operation, clazz, e);
		}

		return true;
	}

	private void checkExtendsClass(String operation, Clazz clazz,
			Clazz extendsClazz) throws Exception {

		String msg = clazz.getName() + " extends " + extendsClazz.getName();

		checkClass(operation, extendsClazz, msg);

		if (clazz.getName().equals(extendsClazz.getName())) {
			throw new IllegalArgumentException(operation
					+ ". Se intento extender una clase asi misma. " + msg);
		}

		int c = 0;

		for (Clazz item : clazz.getExtendsClass()) {
			if (item.getName().equals(extendsClazz.getName())) {
				if (c >= 1) {
					throw new IllegalArgumentException(
							operation
									+ ". Se intento extender de una clase al que ya se está extendiendo. "
									+ msg);
				}
				c++;
			}
		}

		if (extendsClazz.getFinalClass() == true) {
			throw new IllegalArgumentException(
					". Se intento extender de una clase con el atributo 'final' verdadero. "
							+ msg);
		}

		// cyclicalHeritageControl(classComponent, extendsClass);

		checkUsableClass(operation, extendsClazz, msg);

	}

	private void checkClassAtt(String operation, Clazz clazz, ClazzAtt clazzAtt)
			throws Exception {

		String msg = clazz.getName() + ".";

		if (clazzAtt == null) {
			throw new IllegalArgumentException(operation + ". Atributo nulo. "
					+ msg);
		}

		msg += clazzAtt.getName();

		if (clazzAtt.getName() == null) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con un nombre nulo. " + msg);
		}
		if (clazzAtt.getName().trim().length() == 0) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con un nombre vacio. " + msg);
		}
		String regex = "^[a-zA-Z'.]{1,100}$";
		if (clazzAtt.getName().matches(regex) == false) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre incorrecto, '"
					+ clazzAtt.getName()
					+ "', se espera un nombre con la forma \"" + regex + "\". "
					+ msg);
		}
		if (Character.isLowerCase(clazzAtt.getName().charAt(0)) == false) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre incorrecto, '"
					+ clazzAtt.getName() + "', se espera que sea CamelCase. "
					+ msg);
		}
		if (clazzAtt.getOrderAtt() == null) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con un orden nulo. " + msg);
		}
		if (clazzAtt.getTypeCardinality() == null) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con cardinalidad nula. " + msg);
		}
		if (clazzAtt.getTypeCardinality().getName() == null) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre de cardinalidad nula. " + msg);
		}
		if (clazzAtt.getTypeCardinality().getName().trim().length() == 0) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre de cardinalidad vacia. " + msg);
		}
		if (UtilDataTypes
				.ifExistsTypeCardinality(clazzAtt.getTypeCardinality()) == false) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre de cardinalidad que no existe, '"
					+ clazzAtt.getTypeCardinality().getName() + "'. " + msg);
		}

		String dataTypeName = null;

		if (clazzAtt != null && clazzAtt.getDataType() != null) {
			dataTypeName = clazzAtt.getDataType().getName();
		}

		msg += " ( dataType: " + dataTypeName + " )";

		checkClass(operation, clazzAtt.getDataType(), msg);

		checkUsableClass(operation, clazzAtt.getDataType(), msg);

	}

	private boolean checkUsableClass(String operation, Clazz clazz, String msg)
			throws Exception {

		if (clazz.getVirtual() == null) {
			throw new IllegalArgumentException(
					operation
							+ ". Se intento utilizar de una clase con el atributo 'virtual' nulo, "
							+ clazz.getName() + ". " + msg);
		}
		if (clazz.getVirtual() == true) {
			throw new IllegalArgumentException(
					operation
							+ ". Se intento utilizar de una clase con el atributo 'virtual' verdadero,  "
							+ clazz.getName() + ". " + msg);
		}

		if (clazz.getVisibility().equals(UtilTypesVisibilityClass.PRIVATE)
				&& clazz.getPackagesName().equals(clazz.getPackagesName()) == false) {

			throw new IllegalArgumentException(
					operation
							+ ". Se intento utilizar de una clase privada que no se encuentra en el mismo paquete, "
							+ clazz.getName() + ". " + msg);
		}

		if (clazz.getVisibility().equals(UtilTypesVisibilityClass.PUBLICDOWN)) {

			String[] ePackages = clazz.getPackages();
			String[] thisPackages = clazz.getPackages();

			for (int i = 0; i < ePackages.length; i++) {

				if (ePackages[i].equals(thisPackages[i]) == false) {

					throw new IllegalArgumentException(
							operation
									+ ". Se intento utilizar una clase 'pública descendente' que no se encuentra en la misma ruta de paquete, "
									+ clazz.getName() + ". " + msg);
				}
			}

		}

		if (UtilDataTypes.isPrimitiveType(clazz) == false
				&& ifExistsClassBO.ifExistsClass(clazz) == false) {
			throw new IllegalArgumentException(operation
					+ ". La clase no existe '" + clazz.getName() + "'. " + msg);
		}

		return true;

	}

	// private void cyclicalHeritageControl(ClassComponent classComponent,
	// ClassComponent inheritedClass) {
	//
	// if (classComponent.getName().equals(inheritedClass.getName())) {
	// throw new IllegalArgumentException(classComponent.getName()
	// + ". Se intento extender de una clase asi misma, en base a una herencia
	// cíclica. "
	// + inheritedClass.getName());
	// }
	//
	// if (inheritedClass.getExtendsClass() != null) {
	// for (ClassComponent item : inheritedClass.getExtendsClass()) {
	// cyclicalHeritageControl(classComponent, item);
	// }
	// }
	//
	// }

}