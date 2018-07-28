package org.cendra.om.bo.clazz;

import org.cendra.om.bo.clazz.model.Clazz;
import org.cendra.om.bo.clazz.model.persist.ClazzPersist;
import org.cendra.om.bo.object.CreateObjectBO;
import org.cendra.om.util.UtilSerializeObjects;
import org.cendra.om.util.UtilTypesComponents;
import org.cendra.om.util.UtilTypesVisibilityClass;

import com.google.gson.JsonObject;

public class CreateClassBO {

	private CreateObjectBO createObjectBO;
	private IfExistsClassBO ifExistsClassBO;
	private UtilSerializeObjects utilSerializeObjects;

	public CreateClassBO(CreateObjectBO createObjectBO, IfExistsClassBO ifExistsClassBO,
			UtilSerializeObjects utilSerializeObjects) {
		super();
		this.createObjectBO = createObjectBO;
		this.ifExistsClassBO = ifExistsClassBO;
		this.utilSerializeObjects = utilSerializeObjects;
	}

	public Clazz create() throws Exception {

		Clazz classComponent = new Clazz();

		JsonObject jsonObject = createObjectBO.create(UtilTypesComponents.CLASS_COMPONENT);
		classComponent.setId(jsonObject.get("id").getAsString());
		classComponent.setVirtual(jsonObject.get("virtual").getAsBoolean());

		return classComponent;

	}

	public Clazz create(Clazz classComponent) throws Exception {

		if (classComponent == null) {
			throw new IllegalArgumentException("Se intento crear una clase nula.");
		}

		if (classComponent.getName() == null) {
			throw new IllegalArgumentException("Se intento crear una clase con un nombre nulo.");
		}

		if (classComponent.getName().trim().length() == 0) {
			throw new IllegalArgumentException("Se intento crear una clase con un nombre vacio.");
		}

		if (ifExistsClassBO.ifExistsClass(classComponent)) {
			throw new IllegalArgumentException(
					"Se intento crear una clase con un nombre que ya existe. " + classComponent.getName());
		}

		if (classComponent.getName().matches("^[a-zA-Z'.]{1,100}$") == false) {
			// this.name = null;
			throw new IllegalArgumentException(
					"Se intento colocar un nombre incorrecto a una clase. '" + classComponent.getName() + "'");
		}
		if (Character.isUpperCase(classComponent.getSimpleName().charAt(0)) == false) {
			// this.name = null;
			throw new IllegalArgumentException(
					"Se intento colocar un nombre incorrecto a una clase, el mismo no es CamelCase. '"
							+ classComponent.getName() + "'");
		}

		for (Clazz e : classComponent.getExtendsClass()) {
			checkExtendsClass(classComponent, e);
		}
		
		666 controlar que el tipo de dato de cada atributo exista

		// Gson gson = new Gson();
		// String jsonString = gson.toJson(new ClassComponentPersist(classComponent));

//		String jsonString = serializeObjects.toJsonByGson(new ClassComponentPersist(classComponent));
		String jsonString = utilSerializeObjects.toJsonByJackson(new ClazzPersist(classComponent));

		JsonObject jsonObject = createObjectBO.create(jsonString, UtilTypesComponents.CLASS_COMPONENT);
		
		classComponent.setId(jsonObject.get("id").getAsString());
		classComponent.setVirtual(jsonObject.get("virtual").getAsBoolean());

		return classComponent;
	}

	private void checkExtendsClass(Clazz classComponent, Clazz extendsClass) throws Exception {
		
		if (extendsClass == null) {
			throw new IllegalArgumentException(classComponent.getName() + ". Se intento extender de una clase nula.");
		}
		if (classComponent.getName() == null) {
			throw new IllegalArgumentException("Antes de extender de una clase, se debe asignar un nombre a la clase.");
		}
		if (classComponent.getName() == null) {
			throw new IllegalArgumentException(classComponent.getName()
					+ ". Se intento extender de una clase sin nombre. " + extendsClass.getName());
		}
		if (classComponent.getName().equals(extendsClass.getName())) {
			throw new IllegalArgumentException(
					classComponent.getName() + ". Se intento extender una clase asi misma. " + extendsClass.getName());
		}

		// cyclicalHeritageControl(classComponent, extendsClass);

		if (extendsClass.getVirtual() == null) {
			throw new IllegalArgumentException(classComponent.getName()
					+ ". Se intento extender de una clase con el atributo 'virtual' nulo. " + extendsClass.getName());
		}
		if (extendsClass.getVirtual() == true) {
			throw new IllegalArgumentException(classComponent.getName()
					+ ". Se intento extender de una clase con el atributo 'virtual' verdadero. "
					+ extendsClass.getName());
		}
		if (extendsClass.getFinalClass() == true) {
			throw new IllegalArgumentException(
					classComponent.getName() + ". Se intento extender de una clase con el atributo 'final' verdadero. "
							+ extendsClass.getName());
		}
		if (extendsClass.getVisibility().equals(UtilTypesVisibilityClass.PRIVATE)
				&& extendsClass.getPackagesName().equals(classComponent.getPackagesName()) == false) {
			throw new IllegalArgumentException(classComponent.getName()
					+ ". Se intento extender de una clase privada que no se encuentra en el mismo paquete. "
					+ extendsClass.getName());
		}

		if (extendsClass.getVisibility().equals(UtilTypesVisibilityClass.PUBLICDOWN)) {
			String[] ePackages = extendsClass.getPackages();
			String[] thisPackages = classComponent.getPackages();

			for (int i = 0; i < ePackages.length; i++) {
				if (ePackages[i].equals(thisPackages[i]) == false) {
					throw new IllegalArgumentException(classComponent.getName()
							+ ". Se intento extender de una clase pública descendente que no se encuentra en la misma ruta de paquete. "
							+ extendsClass.getName());
				}
			}

		}

		int c = 0;

		for (Clazz item : classComponent.getExtendsClass()) {
			if (item.getName().equals(extendsClass.getName())) {
				if (c >= 1) {
					throw new IllegalArgumentException(classComponent.getName()
							+ ". Se intento extender de una clase al que ya se está extendiendo. "
							+ extendsClass.getName());
				}
				c++;
			}
		}
		
		
		if (ifExistsClassBO.ifExistsClass(extendsClass) == false) {
			throw new IllegalArgumentException(
					"Se intento crear una clase que extiende de una clase que no existe. " + classComponent.getName() + " extends " + extendsClass.getName());
		}
		
		
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
