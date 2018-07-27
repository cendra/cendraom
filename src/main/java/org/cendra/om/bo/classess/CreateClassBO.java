package org.cendra.om.bo.classess;

import org.cendra.om.bo.core.CreateObjectBO;
import org.cendra.om.model.classes.ClassComponent;
import org.cendra.om.model.classes.persist.ClassComponentPersist;
import org.cendra.om.util.SerializeObjects;
import org.cendra.om.util.TypesComponents;
import org.cendra.om.util.TypesVisibilityClass;

import com.google.gson.JsonObject;

public class CreateClassBO {

	private CreateObjectBO createObjectBO;
	private IfExistsClassBO ifExistsClassBO;
	private SerializeObjects serializeObjects;

	public CreateClassBO(CreateObjectBO createObjectBO, IfExistsClassBO ifExistsClassBO,
			SerializeObjects serializeObjects) {
		super();
		this.createObjectBO = createObjectBO;
		this.ifExistsClassBO = ifExistsClassBO;
		this.serializeObjects = serializeObjects;
	}

	public ClassComponent create() throws Exception {

		ClassComponent classComponent = new ClassComponent();

		JsonObject jsonObject = createObjectBO.create(TypesComponents.CLASS_COMPONENT);
		classComponent.setId(jsonObject.get("id").toString());
		classComponent.setVirtual(jsonObject.get("virtual").getAsBoolean());

		return classComponent;

	}

	public ClassComponent create(ClassComponent classComponent) throws Exception {

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

		for (ClassComponent e : classComponent.getExtendsClass()) {
			checkExtendsClass(classComponent, e);
		}

		// Gson gson = new Gson();
		// String jsonString = gson.toJson(new ClassComponentPersist(classComponent));

//		String jsonString = serializeObjects.toJsonByGson(new ClassComponentPersist(classComponent));
		String jsonString = serializeObjects.toJsonByJackson(new ClassComponentPersist(classComponent));

		JsonObject jsonObject = createObjectBO.create(jsonString, TypesComponents.CLASS_COMPONENT);
		classComponent.setId(jsonObject.get("id").toString());
		classComponent.setVirtual(jsonObject.get("virtual").getAsBoolean());

		return classComponent;
	}

	private void checkExtendsClass(ClassComponent classComponent, ClassComponent extendsClass) {
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
		if (extendsClass.getVisibility().equals(TypesVisibilityClass.PRIVATE)
				&& extendsClass.getPackagesName().equals(classComponent.getPackagesName()) == false) {
			throw new IllegalArgumentException(classComponent.getName()
					+ ". Se intento extender de una clase privada que no se encuentra en el mismo paquete. "
					+ extendsClass.getName());
		}

		if (extendsClass.getVisibility().equals(TypesVisibilityClass.PUBLICDOWN)) {
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

		for (ClassComponent item : classComponent.getExtendsClass()) {
			if (item.getName().equals(extendsClass.getName())) {
				if (c >= 1) {
					throw new IllegalArgumentException(classComponent.getName()
							+ ". Se intento extender de una clase al que ya se está extendiendo. "
							+ extendsClass.getName());
				}
				c++;
			}
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
