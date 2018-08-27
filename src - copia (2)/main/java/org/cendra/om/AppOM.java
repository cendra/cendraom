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

		// -------------------------------------------------------------------------

		Clazz claseA = new Clazz();

		claseA.setName("org.cendra.ClaseA");

		claseA.addAttributeBoolean("a1");
		claseA.addAttributeString("a2");
		claseA.addAttributeShort("a3");		
		claseA.addAttributeInteger("a4");
		claseA.addAttributeLong("a5");
		claseA.addAttributeFloat("a6");
		claseA.addAttributeDouble("a7");
		claseA.addAttributeBigDecimal("a8");
		claseA.addAttributeDate("a9");		
		claseA.addAttributeTimestamp("a10");
		claseA.addAttributeTime("a11");
		claseA.addAttributeTime("a12.b12");
		claseA.addAttributeTime("a12.b13");
		claseA.addAttributeTime("a12.b14");
		claseA.addAttributeTime("a12.b15");
		claseA.addAttributeTime("a12.b15.c15");
		claseA.addAttributeTime("a12.b15.c15.d15");
		claseA.addAttributeTime("a12.b16");

//		claseA.addAttributeBoolean("atributo12.atributo1");
//		claseA.addAttributeArrayBoolean("atributo12.atributo1Array");
//		claseA.addAttributeString("atributo12.atributo2");
		
//		claseA.addAttributeArrayString("atributo12.atributo2Array");
//		claseA.addAttributeString("atributo12.atributo2Array.atributoX");
//		claseA.addAttributeString("atributo12.atributo2Array.atributoY");
//		claseA.addAttributeString("atributo12.atributo2Array.atributoZ");
//		claseA.addAttributeString("atributo12.atributo2Array.atributoZ.atributoAlfa");
//		claseA.addAttributeArrayString("atributo12.atributo2Array.atributoZ.atributoBeta");
//		claseA.addAttributeString("atributo12.atributo2Array.atributoZ.atributoGama");
//		
//		claseA.addAttributeShort("atributo12.atributo3");
//		claseA.addAttributeInteger("atributo12.atributo4");
//		claseA.addAttributeLong("atributo12.atributo5");
//		claseA.addAttributeFloat("atributo12.atributo6");
//		claseA.addAttributeDouble("atributo12.atributo7");
//		claseA.addAttributeBigDecimal("atributo12.atributo8");
//		claseA.addAttributeDate("atributo12.atributo9");
//		claseA.addAttributeTimestamp("atributo12.atributo10");
//		claseA.addAttributeTime("atributo12.atributo11");
//
//		claseA.addAttributeBoolean("atributo12.atributo12.atributo1");
//		claseA.addAttributeString("atributo12.atributo12.atributo2");
//		claseA.addAttributeShort("atributo12.atributo12.atributo3");
//		claseA.addAttributeInteger("atributo12.atributo12.atributo4");
//		claseA.addAttributeLong("atributo12.atributo12.atributo5");
//		claseA.addAttributeFloat("atributo12.atributo12.atributo6");
//		claseA.addAttributeDouble("atributo12.atributo12.atributo7");
//		claseA.addAttributeBigDecimal("atributo12.atributo12.atributo8");
//		claseA.addAttributeDate("atributo12.atributo12.atributo9");
//		claseA.addAttributeTimestamp("atributo12.atributo12.atributo10");
//		claseA.addAttributeArrayTimestamp("atributo12.atributo12.atributo10Array");
//		claseA.addAttributeTime("atributo12.atributo12.atributo11");
//
//		claseA.addAttributeBoolean("atributo12.atributo12.atributo12.atributo1");
//		claseA.addAttributeString("atributo12.atributo12.atributo12.atributo2");
//		claseA.addAttributeShort("atributo12.atributo12.atributo12.atributo3");
//		claseA.addAttributeInteger("atributo12.atributo12.atributo12.atributo4");
//		claseA.addAttributeLong("atributo12.atributo12.atributo12.atributo5");
//		claseA.addAttributeFloat("atributo12.atributo12.atributo12.atributo6");
//		claseA.addAttributeDouble("atributo12.atributo12.atributo12.atributo7");
//		claseA.addAttributeBigDecimal("atributo12.atributo12.atributo12.atributo8");
//		claseA.addAttributeDate("atributo12.atributo12.atributo12.atributo9");
//		claseA.addAttributeTimestamp("atributo12.atributo12.atributo12.atributo10");
//		claseA.addAttributeTime("atributo12.atributo12.atributo12.atributo11");

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

		claseC.addAttributeClazz("atributo12.atributo1", claseA);
		// claseC.addAttributeArrayClazz("atributo12.atributo2Array", claseB);

		// =======================================================================================

		ClazzDAOPg clazzDAO = ContextOM.getInstance(args).buildClazzBO();

		clazzDAO.insertOne(claseA);
		// clazzDAO.insertOne(claseB);
		// clazzDAO.insertOne(claseC);

		// -------------------------------------------------------------------------

	}

}
