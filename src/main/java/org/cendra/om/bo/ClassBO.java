package org.cendra.om.bo;

import java.util.List;

import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.model.clazz.ClazzAtt;
import org.cendra.om.persist.dao.ClazzDAO;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilTypesVisibilityClass;

public class ClassBO {

	private ClazzDAO clazzDAO;

	public ClassBO(ClazzDAO clazzDAO) {
		super();
		this.clazzDAO = clazzDAO;
	}

	public Clazz create() throws Exception {

		Clazz clazz = new Clazz();
		clazz = clazzDAO.create(clazz);

		return clazz;

	}

	public Clazz create(Clazz clazz) throws Exception {

		checkClass("Create Class", clazz, "");

		if (ifExists(clazz)) {
			throw new IllegalArgumentException(
					"Se intento crear una clase que ya existe '"
							+ clazz.getName() + "'. ");
		}

		clazz = clazzDAO.create(clazz);

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
		if (clazz.getVisibility() == null) {
			throw new IllegalArgumentException(operation
					+ ". Clase con visibilidad nula, '" + clazz.getName()
					+ ". " + msg);
		}

		if (UtilTypesVisibilityClass.ifExistsTVisibility(clazz.getVisibility()) == false) {

			throw new IllegalArgumentException(operation
					+ ". Clase con visibilidad que no existe, '"
					+ clazz.getName() + ". " + msg);
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

		int c = 0;

		for (ClazzAtt item : clazz.getAtts()) {
			if (item.getName().trim()
					.equalsIgnoreCase(clazzAtt.getName().trim())) {
				if (c >= 1) {
					throw new IllegalArgumentException(operation
							+ ". Atributo con nombre repetido, '"
							+ clazzAtt.getName()
							+ "', ya existe un atributo con el mismo nombre. "
							+ msg);
				}
				c++;

			}
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
				&& ifExists(clazz) == false) {
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

	public boolean ifExists(Clazz clazz) throws Exception {

		return clazzDAO.ifExists(clazz.getName());
	}

	public Clazz findById(String id) throws Exception {
		return clazzDAO.findById(id);
	}

	public List<Clazz> find() throws Exception {
		return clazzDAO.find();
	}

}