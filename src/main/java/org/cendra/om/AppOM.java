package org.cendra.om;

import java.util.List;

import org.cendra.om.bo.ClassBO;
import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilTypesVisibilityClass;

/**
 * Hello world!
 *
 */
public class AppOM {

	public static void main(String[] args) throws Exception {

		// String path = "/home/dmansilla/dev/salidas/objects/";
		String path = "D:\\dev\\salidas_pruebas\\cendraom";
		String pathJdbc = "D:\\dev\\source\\cendraom\\src\\main\\java\\jdbc.properties";

		args = new String[] { ContextOM.ARG_NAME_SOURCE + ContextOM.SOURCE_PG,
				ContextOM.ARG_NAME_PATH + path,
				ContextOM.ARG_NAME_PATH_JDBC + pathJdbc };

		// -------------------------------------------------------------------------

		// CreateObjectBO createObjectBO =
		// ContextOM.getInstance(args).buildCreateObjectBO();
		//
		// JsonObject jsonObject =
		// createObjectBO.create(TypesComponents.INSTANCE_COMPONENT);
		//
		// System.out.println(jsonObject);
		//
		// jsonObject.addProperty("CARACOL", true);
		//
		// jsonObject = createObjectBO.create(jsonObject,
		// TypesComponents.INSTANCE_COMPONENT);
		//
		// System.out.println(jsonObject);
		//
		// System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
		//
		// ListObjectsBO listObjectsBO =
		// ContextOM.getInstance().buildListObjectsBO();
		//
		// List<JsonObject> listJsonObjects = listObjectsBO.list();
		//
		// System.out.println(listJsonObjects);

		// -------------------------------------------------------------------------

		ClassBO classBO = ContextOM.getInstance(args).buildClassBO();

		// Clazz tipoDocumentoIdentidad = new Clazz();
		// tipoDocumentoIdentidad.setAbstractClass(false);
		// tipoDocumentoIdentidad.setFinalClass(true);
		// tipoDocumentoIdentidad.setVisibility(UtilTypesVisibilityClass.PUBLIC);
		// tipoDocumentoIdentidad.setName("org.cendra.person.TipoDocumentoIdentidad");
		// tipoDocumentoIdentidad.addAtt("codigo");
		// tipoDocumentoIdentidad.addAtt("nombre");
		// tipoDocumentoIdentidad.addAtt("descripcion");
		// tipoDocumentoIdentidad = classBO.create(tipoDocumentoIdentidad);
		// System.out.println(tipoDocumentoIdentidad);

		// ----------------------------------------------------------

		Clazz personAddress = new Clazz();
		personAddress.setName("org.cendra.person.PersonAddress");
		personAddress.addAtt("countryCode");
		personAddress.addAtt("adminAreaLevel1Code");
		personAddress.addAtt("adminAreaLevel2");
		personAddress.addAtt("locality");
		personAddress.addAtt("neighbourhood");
		personAddress.addAtt("street");
		personAddress.addAtt("streetNumber");
		personAddress.addAtt("buildingFloor");
		personAddress.addAtt("buildingRoom");
		personAddress.addAtt("building");
		personAddress.addAtt("postalCode");
		personAddress.addAtt("comment");

		personAddress = classBO.create(personAddress);
		System.out.println(personAddress);

		// ----------------------------------------------------------

		Clazz personEmail = new Clazz();
		personEmail.setName("org.cendra.person.PersonEmail");
		personEmail.addAtt("email");
		personEmail.addAtt("comment");

		personEmail = classBO.create(personEmail);
		System.out.println(personEmail);

		// ----------------------------------------------------------

		Clazz personPhone = new Clazz();
		personPhone.setName("org.cendra.person.PersonPhone");
		personPhone.addAtt("countryCode");
		personPhone.addAtt("phoneNumber");
		personPhone.addAtt("comment");

		personPhone = classBO.create(personPhone);
		System.out.println(personEmail);

		// ----------------------------------------------------------

		Clazz person = new Clazz();
		person.setAbstractClass(true);
		// person.setVisibility(UtilTypesVisibilityClass.PUBLIC);
		person.setName("org.cendra.person.Person");
		person.addAtt("fullName"); // primer ejemplo de un att calculado
		person.addAtt("birthDate", UtilDataTypes.buildDate());
		person.addAtt("emails", personEmail, UtilDataTypes.INTERNAL_OBJECT_LIST);
		person.addAtt("phones", personPhone, UtilDataTypes.INTERNAL_OBJECT_LIST);
		person.addAtt("addresses", personAddress, UtilDataTypes.INTERNAL_OBJECT_LIST);

		person = classBO.create(person);
		System.out.println(person);

		// ----------------------------------------------------------

		Clazz humanIdType = new Clazz();
		humanIdType.setName("org.cendra.person.human.HumanIdType");
		humanIdType.addAtt("code");
		humanIdType.addAtt("name");
		humanIdType.addAtt("countryCode");
		humanIdType.addAtt("comment");

		humanIdType = classBO.create(humanIdType);
		System.out.println(personEmail);

		// ----------------------------------------------------------

		Clazz humanId = new Clazz();
		humanId.setName("org.cendra.person.human.HumanId");
		humanId.addAtt("idNumber");
		humanId.addAtt("code");
		humanId.addAtt("name");
		humanId.addAtt("countryCode");
		humanId.addAtt("comment");
		humanId.addAtt("type", humanIdType, UtilDataTypes.EXTERNAL_LEFT_OBJECT);

		humanId = classBO.create(humanId);
		System.out.println(personEmail);

		// ----------------------------------------------------------

		Clazz humanNationality = new Clazz();
		humanNationality.setName("org.cendra.person.human.HumanNationality");
		humanNationality.addAtt("countryCode");
		humanNationality.addAtt("comment");

		humanNationality = classBO.create(humanNationality);
		System.out.println(personEmail);

		// ----------------------------------------------------------

		Clazz humanBirth = new Clazz();
		humanBirth.setName("org.cendra.person.human.HumanBirth");
		humanBirth.addAtt("countryCode");
		humanBirth.addAtt("adminAreaLevel1Code");
		humanBirth.addAtt("adminAreaLevel2");
		humanBirth.addAtt("locality");
		humanBirth.addAtt("lat", UtilDataTypes.buildDouble());
		humanBirth.addAtt("lng", UtilDataTypes.buildDouble());

		humanBirth = classBO.create(humanBirth);
		System.out.println(personEmail);

		// ----------------------------------------------------------

		Clazz human = new Clazz();
		human.addExtendClass(person);
		human.setName("org.cendra.person.human.Human");
		human.addAtt("givenName");
		human.addAtt("middleName");
		human.addAtt("familyName");
		human.addAtt("male", UtilDataTypes.buildBoolean());
		human.addAtt("urlPhoto");
		human.addAtt("comment");
		human.addAtt("birth", humanBirth, UtilDataTypes.INTERNAL_OBJECT);
		human.addAtt("ids", humanId, UtilDataTypes.INTERNAL_OBJECT_LIST);
		human.addAtt("nationalities", humanNationality,
				UtilDataTypes.INTERNAL_OBJECT_LIST);

		human = classBO.create(human);
		System.out.println(human);

		// ----------------------------------------------------------

		// List<Clazz> listClazz = classBO.find();
		//
		// for (Clazz clazz : listClazz) {
		// if (clazz.getExtendsClass().size() > 0) {
		// System.out.println(clazz.getName() + " -> "
		// + clazz.getExtendsClass().get(0).getName());
		// } else {
		// System.out.println(clazz.getName());
		// }
		//
		// // System.out.println();
		// // for (ClazzAtt att : clazz.getAtts()) {
		// // System.out.println(clazz.getName() + "." + att.getName() + " ("
		// // + att.getDataType().getName() + ")");
		// // }
		//
		// }

		// System.out.println(listClazz);

	}
}
