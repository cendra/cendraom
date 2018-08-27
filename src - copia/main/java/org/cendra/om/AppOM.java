package org.cendra.om;

import java.time.LocalDate;

import org.cendra.om.bo.XClazzBOX;
import org.cendra.om.bo.XInterfazeBOX;
import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.model.clazz.old.XClazzX;
import org.cendra.om.model.clazz.old.XInterfazeX;
import org.cendra.om.persist.dao.impl.dbrms.pg.ClazzDAOPg;
import org.cendra.om.persist.dao.impl.dbrms.pg.ImplClazzInstancePgDAO;

import com.google.gson.JsonObject;

/**
 * Hello world!
 *
 */
public class AppOM {

	// setConnection
	// find
	// findByEdge
	// findById
	// findByIdAndDelete
	// findByIdAndUpdate
	// findByQuery
	// findMany
	// updateOne
	// deleteOne
	// deleteMany
	// findOne
	// deleteMany
	// findOne
	// getCollection
	// importMany
	// updateMany
	// remove
	// save

	public static void main(String[] args) throws Exception {

		// String path = "/home/dmansilla/dev/salidas/objects/";
		String path = "D:\\dev\\salidas_pruebas\\cendraom";
		String pathJdbc = "D:\\dev\\source\\cendraom\\src\\main\\java\\jdbc.properties";

		args = new String[] { ContextOM.ARG_NAME_SOURCE + ContextOM.SOURCE_PG,
				ContextOM.ARG_NAME_PATH + path,
				ContextOM.ARG_NAME_PATH_JDBC + pathJdbc };

		// -------------------------------------------------------------------------

		Clazz claseA = new Clazz();

		String t = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		claseA.setName("org.cendra.ClaseA");
//		claseA.setName("org.cendra." + t);

//		claseA.addAttributeBoolean(t);
		claseA.addAttributeBoolean("atributo1");
		claseA.addAttributeString("atributo2");
		claseA.addAttributeShort("atributo3");
		claseA.addAttributeArrayShort("atributo3Array");
		claseA.addAttributeInteger("atributo4");
		claseA.addAttributeLong("atributo5");
		claseA.addAttributeFloat("atributo6");
		claseA.addAttributeDouble("atributo7");
		claseA.addAttributeBigDecimal("atributo8");
		claseA.addAttributeDate("atributo9");
		claseA.addAttributeArrayDate("atributo9Array");
		claseA.addAttributeTimestamp("atributo10");
		claseA.addAttributeTime("atributo11");
		claseA.addAttributeObject("atributo12");

		claseA.getAttribute("atributo12").addAttributeBoolean("atributo1");
		claseA.getAttribute("atributo12").addAttributeArrayBoolean(
				"atributo1Array");
		claseA.getAttribute("atributo12").addAttributeString("atributo2");
		claseA.getAttribute("atributo12").addAttributeArrayString(
				"atributo2Array");
		claseA.getAttribute("atributo12").addAttributeShort("atributo3");
		claseA.getAttribute("atributo12").addAttributeInteger("atributo4");
		claseA.getAttribute("atributo12").addAttributeLong("atributo5");
		claseA.getAttribute("atributo12").addAttributeFloat("atributo6");
		claseA.getAttribute("atributo12").addAttributeDouble("atributo7");
		claseA.getAttribute("atributo12").addAttributeBigDecimal("atributo8");
		claseA.getAttribute("atributo12").addAttributeDate("atributo9");
		claseA.getAttribute("atributo12").addAttributeTimestamp("atributo10");
		claseA.getAttribute("atributo12").addAttributeTime("atributo11");
		claseA.getAttribute("atributo12").addAttributeObject("atributo12");

		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeBoolean("atributo1");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeString("atributo2");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeShort("atributo3");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeInteger("atributo4");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeLong("atributo5");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeFloat("atributo6");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeDouble("atributo7");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeBigDecimal("atributo8");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeDate("atributo9");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeTimestamp("atributo10");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeArrayTimestamp("atributo10Array");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeTime("atributo11");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.addAttributeObject("atributo12");

		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeBoolean("atributo1");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeString("atributo2");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeShort("atributo3");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeInteger("atributo4");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeLong("atributo5");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeFloat("atributo6");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeDouble("atributo7");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeBigDecimal("atributo8");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeDate("atributo9");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeTimestamp("atributo10");
		claseA.getAttribute("atributo12").getAttribute("atributo12")
				.getAttribute("atributo12").addAttributeTime("atributo11");

		// --------------------------------------------------------------------------------------

		Clazz claseB = new Clazz();

		claseB.setName("org.cendra.ClaseB");
		claseB.addAttributeClazz("atributo1", claseA);
		// claseB.addAttributeArrayClazz("atributo2Array", claseA);

		// --------------------------------------------------------------------------------------

		Clazz claseC = new Clazz();

		claseC.setName("org.cendra.ClaseC");
		claseC.setExtendsFrom(claseB);

		claseC.addAttributeBoolean("atributo1");
		claseC.addAttributeString("atributo2");
		claseC.addAttributeShort("atributo3");
		claseC.addAttributeInteger("atributo4");
		claseC.addAttributeArrayInteger("atributo4Array");
		claseC.addAttributeLong("atributo5");
		claseC.addAttributeFloat("atributo6");
		claseC.addAttributeDouble("atributo7");
		claseC.addAttributeBigDecimal("atributo8");
		claseC.addAttributeDate("atributo9");
		claseC.addAttributeTimestamp("atributo10");
		claseC.addAttributeTime("atributo11");
		claseC.addAttributeObject("atributo12");

		claseC.getAttribute("atributo12")
				.addAttributeClazz("atributo1", claseA);
		// claseC.getAttribute("atributo12").addAttributeArrayClazz("atributo2Array",
		// claseB);

		// =======================================================================================

		ClazzDAOPg clazzDAO = ContextOM.getInstance(args).buildClazzBO();

		clazzDAO.insertOne(claseA);
		// clazzDAO.insertOne(claseB);
		// clazzDAO.insertOne(claseC);

		// -------------------------------------------------------------------------

	}

	private void x(String[] args) throws Exception {

		XClazzBOX clazzBO = ContextOM.getInstance(args).buildClazzBOx();
		XInterfazeBOX interfazeBO = ContextOM.getInstance(args)
				.buildInterfazeBO();

		// ----------------------------------------------------------

		XInterfazeX codeable = new XInterfazeX();
		codeable.setName("org.cendra.Codeable");
		codeable.addAttString("code"); // ejemplo para unique lower

		codeable = interfazeBO.create(codeable);
		System.out.println(codeable);

		// ----------------------------------------------------------

		XInterfazeX commentable = new XInterfazeX();
		commentable.setName("org.cendra.Commentable");
		commentable.addAttString("comment");

		commentable = interfazeBO.create(commentable);
		System.out.println(codeable);

		// ----------------------------------------------------------

		XInterfazeX domain = new XInterfazeX();
		domain.addExtendInterfaze(codeable);
		domain.addExtendInterfaze(commentable);
		domain.setName("org.cendra.Domain");
		domain.addAttString("name");

		domain = interfazeBO.create(domain);
		System.out.println(domain);

		// ----------------------------------------------------------

		XInterfazeX idType = new XInterfazeX();
		idType.addExtendInterfaze(domain);
		idType.setName("org.cendra.person.IdType");
		idType.addAttString("countryCode");

		idType = interfazeBO.create(idType);
		System.out.println(idType);

		// ----------------------------------------------------------

		// Clazz personAddress = new Clazz();
		// personAddress.setName("org.cendra.person.PersonAddress");
		// personAddress.addAttString("countryCode");
		// personAddress.addAttString("adminAreaLevel1Code");
		// personAddress.addAttString("adminAreaLevel2");
		// personAddress.addAttString("locality");
		// personAddress.addAttString("neighbourhood");
		// personAddress.addAttString("street");
		// personAddress.addAttString("streetNumber");
		// personAddress.addAttString("buildingFloor");
		// personAddress.addAttString("buildingRoom");
		// personAddress.addAttString("building");
		// personAddress.addAttString("postalCode");
		// personAddress.addAttString("comment");
		// personAddress.addAttDouble("lat");
		// personAddress.addAttDouble("lng");
		//
		// // personAddress = (Clazz) clazzBO.create(personAddress);
		// System.out.println(personAddress);

		// ----------------------------------------------------------

		// Clazz personEmail = new Clazz();
		// personEmail.setName("org.cendra.person.PersonEmail");
		// personEmail.addAttString("email");
		// personEmail.addAttString("comment");
		//
		// // personEmail = (Clazz) clazzBO.create(personEmail);
		// System.out.println(personEmail);

		// ----------------------------------------------------------

		// Clazz personPhone = new Clazz();
		// personPhone.setName("org.cendra.person.PersonPhone");
		// personPhone.addAttString("countryCode");
		// personPhone.addAttString("phoneNumber");
		// personPhone.addAttString("comment");
		//
		// // personPhone = (Clazz) clazzBO.create(personPhone);
		// System.out.println(personEmail);

		// ----------------------------------------------------------

		XClazzX person = new XClazzX();
		// person.setVisibility(UtilTypesVisibilityClass.PUBLIC);
		person.setName("org.cendra.person.Person");
		person.addAttString("fullName"); // primer ejemplo de un att calculado
		person.addAttDate("birthDate");
		person.addAttInternalClazzList("emails");
		person.getAttInternalClazz("emails").addAttString("email");
		person.getAttInternalClazz("emails").addAttString("comment");
		person.addAttInternalClazzList("phones");
		person.getAttInternalClazz("phones").addAttString("countryCode");
		person.getAttInternalClazz("phones").addAttString("phoneNumber");
		person.getAttInternalClazz("phones").addAttString("comment");
		person.addAttInternalClazzList("addresses");
		person.getAttInternalClazz("addresses").addAttString("countryCode");
		person.getAttInternalClazz("addresses").addAttString(
				"adminAreaLevel1Code");
		person.getAttInternalClazz("addresses").addAttString("adminAreaLevel2");
		person.getAttInternalClazz("addresses").addAttString("locality");
		person.getAttInternalClazz("addresses").addAttString("neighbourhood");
		person.getAttInternalClazz("addresses").addAttString("street");
		person.getAttInternalClazz("addresses").addAttString("streetNumber");
		person.getAttInternalClazz("addresses").addAttString("buildingFloor");
		person.getAttInternalClazz("addresses").addAttString("buildingRoom");
		person.getAttInternalClazz("addresses").addAttString("building");
		person.getAttInternalClazz("addresses").addAttString("postalCode");
		person.getAttInternalClazz("addresses").addAttString("comment");
		person.getAttInternalClazz("addresses").addAttDouble("lat");
		person.getAttInternalClazz("addresses").addAttDouble("lng");

		person = clazzBO.create(person);
		System.out.println(person);

		// ----------------------------------------------------------

		XClazzX humanIdType = new XClazzX();
		humanIdType.addImplementInterfaze(idType);
		humanIdType.setName("org.cendra.person.human.HumanIdType");
		// humanIdType.addAttString("code");
		// humanIdType.addAttString("name");
		// humanIdType.addAttString("countryCode");
		humanIdType.addAttString("xxx");

		humanIdType = clazzBO.create(humanIdType);
		System.out.println(humanIdType);

		// ----------------------------------------------------------

		XClazzX human = new XClazzX();
		human.setExtendsClazz(person);
		human.setName("org.cendra.person.human.Human");
		human.addAttString("givenName");
		human.addAttString("middleName");
		human.addAttString("familyName");
		human.addAttBoolean("male");
		human.addAttString("urlPhoto");
		human.addAttString("comment");
		human.addAttInternalClazz("birth");
		human.getAttInternalClazz("birth").addAttString("countryCode");
		human.getAttInternalClazz("birth").addAttString("adminAreaLevel1Code");
		human.getAttInternalClazz("birth").addAttString("adminAreaLevel2");
		human.getAttInternalClazz("birth").addAttString("locality");
		human.getAttInternalClazz("birth").addAttDouble("lat");
		human.getAttInternalClazz("birth").addAttDouble("lng");
		human.addAttInternalClazzList("ids");
		human.getAttInternalClazz("ids").addAttString("idNumber");
		human.getAttInternalClazz("ids").addAttString("code");
		human.getAttInternalClazz("ids").addAttString("name");
		human.getAttInternalClazz("ids").addAttString("countryCode");
		human.getAttInternalClazz("ids").addAttString("comment");
		human.getAttInternalClazz("ids").addAttExternalLeftClazz("type",
				humanIdType);
		human.addAttInternalClazzList("nationalities");
		human.getAttInternalClazz("nationalities").addAttString("countryCode");
		human.getAttInternalClazz("nationalities").addAttString("comment");

		human = clazzBO.create(human);
		System.out.println(human);

		// ----------------------------------------------------------

		XClazzX orgIdType = new XClazzX();
		orgIdType.setName("org.cendra.person.org.OrgIdType");
		orgIdType.addAttString("code");
		orgIdType.addAttString("name");
		orgIdType.addAttString("countryCode");
		orgIdType.addAttString("comment");

		orgIdType = clazzBO.create(orgIdType);
		System.out.println(orgIdType);

		// ----------------------------------------------------------

		XClazzX org = new XClazzX();
		org.setExtendsClazz(person);
		org.setName("org.cendra.person.org.Org");
		org.addAttString("businessName");
		org.addAttString("urlPhoto");
		org.addAttString("comment");
		org.addAttInternalClazzList("ids");
		org.getAttInternalClazz("ids").addAttString("idNumber");
		org.getAttInternalClazz("ids").addAttString("code");
		org.getAttInternalClazz("ids").addAttString("name");
		org.getAttInternalClazz("ids").addAttString("countryCode");
		org.getAttInternalClazz("ids").addAttString("comment");
		org.getAttInternalClazz("ids").addAttExternalLeftClazz("type",
				orgIdType);

		org = clazzBO.create(org);
		System.out.println(org);

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

		System.out.println();
		System.out.println();

		JsonObject dmansilla = new JsonObject();
		// dmansilla.addProperty("id", id);
		// dmansilla.addProperty("virtual", true);
		dmansilla.addProperty("fullName", "Diego Pablo Mansilla");
		dmansilla.addProperty("birthDate", LocalDate.now().toString());

		dmansilla.addProperty("givenName", "Diego");
		dmansilla.addProperty("middleName", "Pablo");
		dmansilla.addProperty("familyName", "Mansilla");
		dmansilla.addProperty("male", true);
		// dmansilla.addProperty("urlPhoto", null);
		dmansilla.addProperty("comment", "Comentario de diego mansilla");

		System.out.println(dmansilla);

		ImplClazzInstancePgDAO clazzInstancePgDAO = new ImplClazzInstancePgDAO(
				clazzBO);

		// dmansilla = clazzInstancePgDAO.insert(human.getName(), dmansilla);

		System.out.println();
		System.out.println();

		System.out.println(dmansilla);
	}
}
