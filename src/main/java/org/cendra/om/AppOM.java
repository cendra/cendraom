package org.cendra.om;

import java.util.List;

import org.cendra.om.bo.ClassBO;
import org.cendra.om.model.clazz.Clazz;
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

		Clazz tipoDocumento = new Clazz();
		tipoDocumento.setAbstractClass(false);
		tipoDocumento.setFinalClass(true);
		tipoDocumento.setVisibility(UtilTypesVisibilityClass.PUBLIC);
		tipoDocumento.setName("org.cendra.persona.TipoDocumento");
		tipoDocumento.addAtt("nombre");
		tipoDocumento = classBO.create(tipoDocumento);
		System.out.println(tipoDocumento);

		Clazz persona = new Clazz();
		persona.setAbstractClass(true);
		// persona.setFinalClass(true);
		persona.setVisibility(UtilTypesVisibilityClass.PUBLICDOWN);
		persona.setName("org.cendra.persona.Persona");
		persona.addAtt("nombre");
		persona = classBO.create(persona);
		System.out.println(persona);

		Clazz personaFisica = new Clazz();
		personaFisica.setName("org.cendra.persona.fisica.PersonaFisica");
		personaFisica.addAtt("apellido");
		personaFisica.addAtt("tipoDocumento", tipoDocumento);
		personaFisica.addAtt("edad");
		personaFisica.addExtendClass(persona);
		// personaFisica.addExtendClass(persona);
		// personaFisica.addExtendClass(personaFisica);
		personaFisica = classBO.create(personaFisica);
		System.out.println(personaFisica);

		Clazz persona2 = new Clazz();
		persona2.setName("org.cendra.persona.fisica.PersonaB");
		persona2.addExtendClass(personaFisica);
		// persona2.addExtendClass(persona);
		persona2.setAbstractClass(true);
		// persona2.setFinalClass(true);
		persona2.setVisibility(UtilTypesVisibilityClass.PUBLICDOWN);
		persona2.addAtt("legajo");
		persona2 = classBO.create(persona2);
		System.out.println(persona2);

		// FindClassBO findClassBO = ContextOM.getInstance(args)
		// .buildFindClassBO();
		List<Clazz> listClazz = classBO.find();

		for (Clazz clazz : listClazz) {
			if (clazz.getExtendsClass().size() > 0) {
				System.out.println(clazz.getName() + " -> "
						+ clazz.getExtendsClass().get(0).getName());
			} else {
				System.out.println(clazz.getName());
			}

//			System.out.println();
//			for (ClazzAtt att : clazz.getAtts()) {
//				System.out.println(clazz.getName() + "." + att.getName() + " ("
//						+ att.getDataType().getName() + ")");
//			}

		}

		// System.out.println(listClazz);

	}
}
