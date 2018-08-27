package org.cendra.om.bo;

import java.util.List;

import org.cendra.om.model.clazz.old.XAttributeX;
import org.cendra.om.model.clazz.old.XClazzX;
import org.cendra.om.model.clazz.old.XInterfazeX;
import org.cendra.om.model.clazz.old.XTypeX;
import org.cendra.om.persist.dao.XClazzDAO;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilTypesVisibilityClass;

public abstract class XTypeBO {

	private XClazzDAO clazzDAO;

	public XTypeBO(XClazzDAO clazzDAO) {
		super();
		this.clazzDAO = clazzDAO;
	}

	// public Type create() throws Exception {
	//
	// Clazz clazz = new Clazz();
	// clazz = clazzDAO.create(clazz);
	//
	// return clazz;
	//
	// }

	public XTypeX create(XTypeX type) throws Exception {

		String operation = "";

		if (type instanceof XClazzX) {
			operation = "Class";
		} else if (type instanceof XInterfazeX) {
			operation = "Interface";
		}

		checkType("Create " + operation, type, "");

		if (ifExists(type)) {
			throw new IllegalArgumentException(
					"Se intento crear una clase que ya existe '"
							+ type.getName() + "'. ");
		}

		type = clazzDAO.create(type);

		return type;
	}

	private boolean checkType(String operation, XTypeX type, String msg)
			throws Exception {

		if (type == null) {
			throw new IllegalArgumentException(operation + ". Clase nula. "
					+ msg);
		}
		if (type.getName() == null) {
			throw new IllegalArgumentException(operation
					+ ". Clase con un nombre nulo. " + msg);
		}
		if (type.getName().trim().length() == 0) {
			throw new IllegalArgumentException(operation
					+ ". Clase con un nombre vacio. " + msg);
		}
		String regex = "^[a-zA-Z'.]{1,25}$";
		if (type.getSimpleName().matches(regex) == false) {
			throw new IllegalArgumentException(operation
					+ ". Clase con nombre incorrecto, '" + type.getName()
					+ "', se espera un nombre con la forma \"" + regex + "\". "
					+ msg);
		}
		if (Character.isUpperCase(type.getSimpleName().charAt(0)) == false) {
			throw new IllegalArgumentException(operation
					+ ". Clase con nombre incorrecto, '" + type.getName()
					+ "', se espera que sea CamelCase. " + msg);
		}
		if (type.getVisibility() == null) {
			throw new IllegalArgumentException(operation
					+ ". Clase con visibilidad nula, '" + type.getName() + ". "
					+ msg);
		}

		if (UtilTypesVisibilityClass.ifExistsTVisibility(type.getVisibility()) == false) {

			throw new IllegalArgumentException(operation
					+ ". Clase con visibilidad que no existe, '"
					+ type.getName() + ". " + msg);
		}

		// for (Clazz e : clazz.getExtendsClass()) {
		if (type instanceof XClazzX && ((XClazzX) type).isInternalClazz() == false
				&& ((XClazzX) type).getExtendsClazz() != null) {
			checkExtendsClass(operation, ((XClazzX) type),
					((XClazzX) type).getExtendsClazz());
		} else if (type instanceof XInterfazeX) {

		}

		// }

		for (XAttributeX e : type.getAtts()) {
			checkAttribute(operation, type, e);
		}

		return true;
	}

	private void checkExtendsClass(String operation, XClazzX clazz,
			XClazzX extendsClazz) throws Exception {

		String msg = clazz.getName() + " extends " + extendsClazz.getName();

		checkType(operation, extendsClazz, msg);

		if (clazz.getName().equals(extendsClazz.getName())) {
			throw new IllegalArgumentException(operation
					+ ". Se intento extender una clase asi misma. " + msg);
		}

		int c = 0;

		// for (Clazz item : clazz.getExtendsClass()) {
		if (clazz.getExtendsClazz().getName().equals(extendsClazz.getName())) {
			if (c >= 1) {
				throw new IllegalArgumentException(
						operation
								+ ". Se intento extender de una clase al que ya se está extendiendo. "
								+ msg);
			}
			c++;
		}
		// }

		if (extendsClazz.getFinalType() == true) {
			throw new IllegalArgumentException(
					". Se intento extender de una clase con el atributo 'final' verdadero. "
							+ msg);
		}

		// cyclicalHeritageControl(classComponent, extendsClass);

		checkUsableType(operation, extendsClazz, msg);

	}

	private void checkAttribute(String operation, XTypeX clazz,
			XAttributeX attribute) throws Exception {

		String msg = clazz.getName() + ".";

		if (attribute == null) {
			throw new IllegalArgumentException(operation + ". Atributo nulo. "
					+ msg);
		}

		msg += attribute.getName();

		if (attribute.getName() == null) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con un nombre nulo. " + msg);
		}
		if (attribute.getName().trim().length() == 0) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con un nombre vacio. " + msg);
		}
		String regex = "^[a-zA-Z0-9'.]{1,25}$";
		if (attribute.getName().matches(regex) == false) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre incorrecto, '"
					+ attribute.getName()
					+ "', se espera un nombre con la forma \"" + regex + "\". "
					+ msg);
		}
		if (Character.isDigit(attribute.getName().charAt(0))) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre incorrecto, '"
					+ attribute.getName()
					+ "', se espera la primera letra no sea un número. " + msg);
		}
		if (Character.isLowerCase(attribute.getName().charAt(0)) == false) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre incorrecto, '"
					+ attribute.getName() + "', se espera que sea CamelCase. "
					+ msg);
		}

		int c = 0;

		for (XAttributeX item : clazz.getAtts()) {
			if (item.getName().trim()
					.equalsIgnoreCase(attribute.getName().trim())) {
				if (c >= 1) {
					throw new IllegalArgumentException(operation
							+ ". Atributo con nombre repetido, '"
							+ attribute.getName()
							+ "', ya existe un atributo con el mismo nombre. "
							+ msg);
				}
				c++;

			}
		}

		if (attribute.getOrderAtt() == null) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con un orden nulo. " + msg);
		}
		if (attribute.getTypeCardinality() == null) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con cardinalidad nula. " + msg);
		}
		if (attribute.getTypeCardinality().getName() == null) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre de cardinalidad nula. " + msg);
		}
		if (attribute.getTypeCardinality().getName().trim().length() == 0) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre de cardinalidad vacia. " + msg);
		}
		if (UtilDataTypes.ifExistsTypeCardinality(attribute
				.getTypeCardinality()) == false) {
			throw new IllegalArgumentException(operation
					+ ". Atributo con nombre de cardinalidad que no existe, '"
					+ attribute.getTypeCardinality().getName() + "'. " + msg);
		}

		String dataTypeName = null;

		if (attribute != null && attribute.getDataType() != null) {
			dataTypeName = attribute.getDataType().getName();
		}

		msg += " ( dataType: " + dataTypeName + " )";

		checkType(operation, attribute.getDataType(), msg);

		checkUsableType(operation, attribute.getDataType(), msg);

	}

	private boolean checkUsableType(String operation, XTypeX type, String msg)
			throws Exception {

		if (type.getVirtual() == null) {
			throw new IllegalArgumentException(
					operation
							+ ". Se intento utilizar de una clase con el atributo 'virtual' nulo, "
							+ type.getName() + ". " + msg);
		}
		if (type.getVirtual() == true) {
			throw new IllegalArgumentException(
					operation
							+ ". Se intento utilizar de una clase con el atributo 'virtual' verdadero,  "
							+ type.getName() + ". " + msg);
		}

		if (type.getVisibility().equals(UtilTypesVisibilityClass.PRIVATE)
				&& type.getPackagesName().equals(type.getPackagesName()) == false) {

			throw new IllegalArgumentException(
					operation
							+ ". Se intento utilizar de una clase privada que no se encuentra en el mismo paquete, "
							+ type.getName() + ". " + msg);
		}

		if (type.getVisibility().equals(UtilTypesVisibilityClass.PUBLICDOWN)) {

			String[] ePackages = type.getPackages();
			String[] thisPackages = type.getPackages();

			for (int i = 0; i < ePackages.length; i++) {

				if (ePackages[i].equals(thisPackages[i]) == false) {

					throw new IllegalArgumentException(
							operation
									+ ". Se intento utilizar una clase 'pública descendente' que no se encuentra en la misma ruta de paquete, "
									+ type.getName() + ". " + msg);
				}
			}

		}

		if (type instanceof XClazzX) {
			if (UtilDataTypes.isPrimitiveType(type) == false
					&& ((XClazzX) type).isInternalClazz() == false
					&& ifExists(type) == false) {
				throw new IllegalArgumentException(operation
						+ ". La clase no existe '" + type.getName() + "'. "
						+ msg);
			}
		} else if (type instanceof XInterfazeX) {
			if (UtilDataTypes.isPrimitiveType(type) == false
					&& ifExists(type) == false) {
				throw new IllegalArgumentException(operation
						+ ". La clase no existe '" + type.getName() + "'. "
						+ msg);
			}
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

	public boolean ifExists(XTypeX clazz) throws Exception {

		return clazzDAO.ifExists(clazz.getName());
	}

	public XTypeX findById(String id) throws Exception {
		return clazzDAO.findById(id);
	}

	public XTypeX findByName(String name) throws Exception {
		return clazzDAO.findByName(name);
	}

	public List<XClazzX> find() throws Exception {
		return clazzDAO.find();
	}

}