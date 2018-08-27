package org.cendra.om;

import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.persist.dao.impl.dbrms.pg.ClazzDAOPg;

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

		// --------------------------------------------------------------------------------------

		Clazz claseX = new Clazz();

		claseX.setName("org.cendra.ClaseX");
		claseX.addAttributeBoolean("atributo1");
		claseX.addAttributeString("atributo2");
		claseX.addAttributeShort("atributo3");

		// --------------------------------------------------------------------------------------

		Clazz claseY = new Clazz();

		claseY.setName("org.cendra.ClaseY");
		claseY.addAttributeBoolean("atributo1");
		claseY.addAttributeString("atributo2");
		claseY.addAttributeShort("atributo3");

		// --------------------------------------------------------------------------------------

		Clazz claseZ = new Clazz();

		claseZ.setName("org.cendra.ClaseZ");
		claseZ.addAttributeBoolean("atributo1");
		claseZ.addAttributeString("atributo2");
		claseZ.addAttributeShort("atributo3");

		// -------------------------------------------------------------------------

		Clazz claseA = new Clazz();

		claseA.setName("org.cendra.ClaseA");

		claseA.addAttributeBoolean("atributo1");
		claseA.addAttributeString("atributo2");
		claseA.addAttributeShort("atributo3");
		claseA.addAttributeInteger("atributo4");
		claseA.addAttributeLong("atributo5");
		claseA.addAttributeFloat("atributo6");
		claseA.addAttributeDouble("atributo7");
		claseA.addAttributeBigDecimal("atributo8");
		claseA.addAttributeDate("atributo9");
		claseA.addAttributeTimestamp("atributo10");
		claseA.addAttributeTime("atributo11");

		claseA.addAttributeObject("atributo12");
		claseA.getAttribute("atributo12").addAttributeBoolean("atributo1");
		claseA.getAttribute("atributo12").addAttributeShort("atributo2");

		claseA.addAttributeArrayObject("atributo13");
		claseA.getAttribute("atributo13").addAttributeInteger("atributo1");
		claseA.getAttribute("atributo13").addAttributeTimestamp("atributo2");

		claseA.getAttribute("atributo13").addAttributeObject("atributo3");
		claseA.getAttribute("atributo13").getAttribute("atributo3")
				.addAttributeBoolean("atributo1");
		claseA.getAttribute("atributo13").getAttribute("atributo3")
				.addAttributeShort("atributo2");

		claseA.getAttribute("atributo13").getAttribute("atributo3")
				.addAttributeArrayObject("atributo3");
		claseA.getAttribute("atributo13").getAttribute("atributo3")
				.getAttribute("atributo3").addAttributeBoolean("atributo1");

		claseA.getAttribute("atributo13").addAttributeArrayObject("atributo4");
		claseA.getAttribute("atributo13").getAttribute("atributo4")
				.addAttributeBoolean("atributo1");
		claseA.getAttribute("atributo13").getAttribute("atributo4")
				.addAttributeShort("atributo2");
		claseA.getAttribute("atributo13").getAttribute("atributo4")
				.addAttributeArrayObject("atributo3");
		claseA.getAttribute("atributo13").getAttribute("atributo4")
				.getAttribute("atributo3").addAttributeTime("atributo1");
		claseA.getAttribute("atributo13").getAttribute("atributo4")
				.getAttribute("atributo3").addAttributeTime("atributo2");
		claseA.getAttribute("atributo13").getAttribute("atributo4")
				.getAttribute("atributo3")
				.addAttributeClazz("atributo3", claseZ);
		claseA.getAttribute("atributo13").getAttribute("atributo4")
				.getAttribute("atributo3")
				.addAttributeArrayClazz("atributo4", claseZ);

		claseA.getAttribute("atributo13").addAttributeArrayString("atributo5");

		claseA.addAttributeArrayString("atributo14");

		claseA.addAttributeClazz("atributo15", claseX);

		claseA.addAttributeArrayClazz("atributo16", claseY);

		// --------------------------------------------------------------------------------------

		Clazz claseB = new Clazz();

		claseB.setExtendsFrom(claseA);
		claseB.setName("org.cendra.ClaseB");
		claseB.addAttributeBoolean("atributo1");
		claseB.addAttributeString("atributo2");
		claseB.addAttributeShort("atributo3");
		// claseB.addAttributeClazz("atributo1", claseA);
		// claseB.addAttributeArrayClazz("atributo2Array", claseA);

		// --------------------------------------------------------------------------------------

		Clazz claseC = new Clazz();

		claseC.setName("org.cendra.ClaseC");
		claseC.setExtendsFrom(claseB);

		claseC.addAttributeBoolean("atributo1");
		claseC.addAttributeString("atributo2");
		claseC.addAttributeObject("atributo3");

		// claseC.getAttribute("atributo3").addAttributeClazz("atributo1",
		// claseA);
		// claseC.getAttribute("atributo12").addAttributeArrayClazz("atributo2Array",
		// claseB);

		// =======================================================================================

		ClazzDAOPg clazzDAO = ContextOM.getInstance(args).buildClazzBO();

		clazzDAO.insertOne(claseX);
		clazzDAO.insertOne(claseY);
		clazzDAO.insertOne(claseZ);

		clazzDAO.insertOne(claseA);
		clazzDAO.insertOne(claseB);
		clazzDAO.insertOne(claseC);

		// -------------------------------------------------------------------------

	}

}
