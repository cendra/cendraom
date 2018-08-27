package org.cendra.om.persist.dao.impl.dbrms.pg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cendra.jdbc.ConnectionWrapper;
import org.cendra.jdbc.DataSourceWrapper;
import org.cendra.jdbc.GenericDAO;
import org.cendra.jdbc.SQLExceptionWrapper;
import org.cendra.om.model.clazz.TypeVisibilityClass;
import org.cendra.om.model.clazz.old.TypeCardinality;
import org.cendra.om.model.clazz.old.XAttributeX;
import org.cendra.om.model.clazz.old.XClazzX;
import org.cendra.om.model.clazz.old.XInterfazeX;
import org.cendra.om.model.clazz.old.XTypeX;
import org.cendra.om.persist.dao.XClazzDAO;
import org.cendra.om.util.UtilAtts;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilUUID;

public class XImplClassPgDAO extends GenericDAO implements XClazzDAO {

	protected DataSourceWrapper dataSourceWrapper;

	public XImplClassPgDAO(DataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public XTypeX create(XTypeX type) throws Exception {

		type.setId(UtilUUID.buildUUID());

		Object idArg = String.class;
		Object virtualArg = Boolean.class;

		Object nameArg = String.class;
		Object visibilityArg = String.class;
		Object finalTypeArg = Boolean.class;
		Object classExtendsArg = String.class;

		if (type.getId() != null) {
			idArg = type.getId();
		}
		if (type.getVirtual() != null) {
			virtualArg = type.getVirtual();
		}
		if (type.getName() != null) {
			nameArg = type.getName();
		}
		if (type.getVisibility() != null
				&& type.getVisibility().getName() != null) {
			visibilityArg = type.getVisibility().getName();
		}
		if (type.getFinalType() != null) {
			finalTypeArg = type.getFinalType();
		}

		String nameTableDB = "cendraom.DataType";
		String[] nameAtts = null;
		Object[] args = null;

		if (type instanceof XClazzX) {

			if (((XClazzX) type).isInternalClazz() == false
					&& ((XClazzX) type).getExtendsClazz() != null) {
				classExtendsArg = ((XClazzX) type).getExtendsClazz().getId();
			}

			nameAtts = new String[] { UtilAtts.id, UtilAtts.virtual,
					UtilAtts.name, UtilAtts.visibility, UtilAtts.finalType,
					UtilAtts.typeExtends, "isClass" };
			args = new Object[] { idArg, virtualArg, nameArg, visibilityArg,
					finalTypeArg, classExtendsArg, true };

		} else if (type instanceof XInterfazeX) {

			nameAtts = new String[] { UtilAtts.id, UtilAtts.virtual,
					UtilAtts.name, UtilAtts.visibility, UtilAtts.finalType,
					"isClass" };
			args = new Object[] { idArg, virtualArg, nameArg, visibilityArg,
					finalTypeArg, false };

		}

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			connectionWrapper.begin();

			insert(connectionWrapper, nameTableDB, nameAtts, args);

			for (XAttributeX attribute : type.getAtts()) {

				if (attribute.getDataType() != null
						&& attribute.getDataType().getId() == null) {

					create(attribute.getDataType());
				}

				Object idAttArg = UtilUUID.buildUUID();
				Object memberOfTypeArg = String.class;
				Object nameAttArg = String.class;
				Object dataTypeArg = String.class;
				Object typeCardinalityArg = String.class;
				Object orderArg = Integer.class;

				if (idArg != null) {
					memberOfTypeArg = idArg;
				}
				if (attribute.getName() != null) {
					nameAttArg = attribute.getName();
				}
				if (attribute.getDataType() != null
						&& attribute.getDataType().getId() != null) {
					dataTypeArg = attribute.getDataType().getId();
				}
				if (attribute.getTypeCardinality() != null
						&& attribute.getTypeCardinality().getName() != null) {
					typeCardinalityArg = attribute.getTypeCardinality()
							.getName();
				}
				if (attribute.getOrderAtt() != null) {
					orderArg = attribute.getOrderAtt();
				}

				nameTableDB = "cendraom.TypeAttribute";
				nameAtts = new String[] { UtilAtts.id, "memberOfType",
						UtilAtts.name, UtilAtts.dataType,
						UtilAtts.typeCardinality, UtilAtts.orderAtt };
				args = new Object[] { idAttArg, memberOfTypeArg, nameAttArg,
						dataTypeArg, typeCardinalityArg, orderArg };

				insert(connectionWrapper, nameTableDB, nameAtts, args);
			}

			if (type instanceof XClazzX) {

				XClazzX clazz = (XClazzX) type;

				for (XInterfazeX extendsInterfaze : clazz
						.getImplementsInterfaze()) {

					Object idArg2 = UtilUUID.buildUUID();
					Object childTypeArg = String.class;
					Object fatherTypeArg = String.class;

					if (idArg != null) {
						childTypeArg = idArg;
					}
					if (extendsInterfaze.getId() != null) {
						fatherTypeArg = extendsInterfaze.getId();
					}

					nameTableDB = "cendraom.TypesExtends";
					nameAtts = new String[] { UtilAtts.id, "childType",
							"fatherType" };

					args = new Object[] { idArg2, childTypeArg, fatherTypeArg };

					insert(connectionWrapper, nameTableDB, nameAtts, args);

				}

			} else if (type instanceof XInterfazeX) {

				XInterfazeX interfaze = (XInterfazeX) type;

				for (XInterfazeX extendsInterfaze : interfaze
						.getExtendsInterfaze()) {

					Object idArg2 = UtilUUID.buildUUID();
					Object childTypeArg = String.class;
					Object fatherTypeArg = String.class;

					if (idArg != null) {
						childTypeArg = idArg;
					}
					if (extendsInterfaze.getId() != null) {
						fatherTypeArg = extendsInterfaze.getId();
					}

					nameTableDB = "cendraom.TypesExtends";
					nameAtts = new String[] { UtilAtts.id, "childType",
							"fatherType" };

					args = new Object[] { idArg2, childTypeArg, fatherTypeArg };

					insert(connectionWrapper, nameTableDB, nameAtts, args);

				}
			}

			// ----------------------------------------------------

			if (type instanceof XClazzX) {
				buildDMLCreateTable(connectionWrapper, (XClazzX) type);
			}

			// ----------------------------------------------------

			connectionWrapper.commit();

		} catch (Exception e) {
			connectionWrapper.rollBack();
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}

		return type;
	}

	private void buildDMLCreateTable(ConnectionWrapper connectionWrapper,
			XClazzX clazz) throws SQLExceptionWrapper, SQLException {

		String sql = "";

		String schemaName = clazz.getPackagesName().replace(".", "_");

		sql += "CREATE SCHEMA IF NOT EXISTS " + schemaName + ";";

		connectionWrapper.genericExecute(sql);

		sql = "";

		String tableName = clazz.getSimpleName();
		if (clazz.isInternalClazz() == true) {
			tableName = clazz.getSimpleName() + "_ic";
		}

		sql += "CREATE TABLE " + schemaName + "." + tableName;
		sql += "\n(";
		sql += "\n\tid VARCHAR(36) NOT NULL  PRIMARY KEY DEFAULT uuid_generate_v4()";

		if (clazz.isInternalClazz() == false && clazz.getExtendsClazz() != null) {

			schemaName = clazz.getExtendsClazz().getPackagesName()
					.replace(".", "_");

			sql += "REFERENCES " + schemaName + "."
					+ clazz.getExtendsClazz().getSimpleName().replace(".", "_")
					+ " (id)";

		}

		// for (Clazz implementsInterfaze : clazz.getImplementsInterfaze()) {
		//
		// schemaName = implementsInterfaze.getPackagesName()
		// .replace(".", "_");
		//
		// // String dataType = "VARCHAR(36) NOT NULL REFERENCES " +
		// // schemaName
		// // + "."
		// // + clazz.getExtendsClass().getSimpleName().replace(".", "_")
		// // + " (id)";
		//
		// sql += "REFERENCES " + schemaName + "."
		// + clazz.getExtendsClass().getSimpleName().replace(".", "_")
		// + " (id)";
		//
		// // sql += "\n\t, extends" +
		// // clazz.getExtendsClass().getSimpleName()
		// // + "_id" + " " + dataType + "";
		//
		// }
		sql += "\n";

		for (XAttributeX att : clazz.getAtts()) {
			String dataType = "";

			if (UtilDataTypes.isPrimitiveType(att.getDataType())) {

				if (UtilDataTypes.isBooleanType(att.getDataType())) {
					dataType = "BOOLEAN";
				} else if (UtilDataTypes.isStringType(att.getDataType())) {
					dataType = "VARCHAR";
				} else if (UtilDataTypes.isShortType(att.getDataType())) {
					dataType = "SMALLINT";
				} else if (UtilDataTypes.isIntegerType(att.getDataType())) {
					dataType = "INTEGER";
				} else if (UtilDataTypes.isLongType(att.getDataType())) {
					dataType = "BIGINT";
				} else if (UtilDataTypes.isFloatType(att.getDataType())) {
					dataType = "REAL";
				} else if (UtilDataTypes.isDoubleType(att.getDataType())) {
					dataType = "FLOAT";
				} else if (UtilDataTypes.isDateType(att.getDataType())) {
					dataType = "DATE";
				}

			} else if (att.getTypeCardinality().equals(
					UtilDataTypes.EXTERNAL_LEFT_OBJECT)) {

				schemaName = att.getDataType().getPackagesName()
						.replace(".", "_");

				dataType = "VARCHAR(36) REFERENCES " + schemaName + "."
						+ att.getDataType().getSimpleName() + " (id)";

			}

			if (dataType.trim().length() > 0) {
				sql += "\n\t, " + att.getName() + " " + dataType + "";
			}

		}

		sql += "\n)";

		connectionWrapper.genericExecute(sql);

		buildDMLCreateTableInternalObject(connectionWrapper, clazz);
	}

	private void buildDMLCreateTableInternalObject(
			ConnectionWrapper connectionWrapper, XClazzX clazz)
			throws SQLExceptionWrapper, SQLException {

		for (XAttributeX att : clazz.getAtts()) {

			if (UtilDataTypes.isPrimitiveType(att.getDataType()) == false) {

				if (att.getTypeCardinality().equals(
						UtilDataTypes.INTERNAL_OBJECT_LIST)) {

					String schemaName = att.getDataType().getPackagesName()
							.replace(".", "_");

					String tableName = att.getDataType().getSimpleName();
					if (att.getDataType() instanceof XClazzX
							&& ((XClazzX) att.getDataType()).isInternalClazz() == true) {

						tableName += "_ic";
					}

					String sql = "ALTER TABLE " + schemaName + "." + tableName
							+ " ADD COLUMN " + att.getName()
							+ clazz.getSimpleName() + "_id"
							+ " VARCHAR(36) NOT NULL REFERENCES " + schemaName
							+ "." + clazz.getSimpleName() + " (id);";

					connectionWrapper.genericExecute(sql);

				} else if (att.getTypeCardinality().equals(
						UtilDataTypes.INTERNAL_OBJECT)) {

					String schemaName = att.getDataType().getPackagesName()
							.replace(".", "_");

					String tableName = att.getDataType().getSimpleName();
					if (att.getDataType() instanceof XClazzX
							&& ((XClazzX) att.getDataType()).isInternalClazz() == true) {

						tableName += "_ic";
					}

					String fkName = "fk_" + tableName + "_" + schemaName + "_"
							+ clazz.getSimpleName();

					String sql = "ALTER TABLE " + schemaName + "." + tableName
							+ " ADD CONSTRAINT " + fkName
							+ " FOREIGN KEY (id) REFERENCES " + schemaName
							+ "." + clazz.getSimpleName() + " (id);";

					connectionWrapper.genericExecute(sql);

				}

			}

		}

	}

	public boolean ifExists(String name) throws Exception {

		String sql = "SELECT COUNT(*) FROM cendraom.DataType WHERE name = ?";

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			Object nameArg = String.class;
			if (name != null) {
				nameArg = name.trim();
			}

			Object[][] table = connectionWrapper.findToTable(sql, nameArg);

			return (Long) table[0][0] > 0;

		} catch (Exception e) {
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

	public XClazzX findById(String id) throws Exception {

		String nameTableDB = "cendraom.Clazz";
		String orderBy = "name";
		String where = "id = ?";
		int limit = -1;
		int offset = -1;
		Object[] args = { id };

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			Object[][] table = findPaged(connectionWrapper, nameTableDB,
					orderBy, where, limit, offset, args);

			List<XClazzX> list = mapper(connectionWrapper, table);
			if (list.size() > 0) {
				return list.get(0);
			}

			return null;

		} catch (Exception e) {
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

	public XClazzX findByName(String name) throws Exception {

		String nameTableDB = "cendraom.Clazz";
		String orderBy = "name";
		String where = "name = ?";
		int limit = -1;
		int offset = -1;
		Object[] args = { name };

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			Object[][] table = findPaged(connectionWrapper, nameTableDB,
					orderBy, where, limit, offset, args);

			List<XClazzX> list = mapper(connectionWrapper, table);
			if (list.size() > 0) {
				return list.get(0);
			}

			return null;

		} catch (Exception e) {
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

	public List<XClazzX> find() throws Exception {

		String nameTableDB = "cendraom.Clazz";
		String orderBy = "name";
		String where = null;
		int limit = -1;
		int offset = -1;
		Object[] args = new Object[0];

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			Object[][] table = findPaged(connectionWrapper, nameTableDB,
					orderBy, where, limit, offset, args);

			return mapper(connectionWrapper, table);

		} catch (Exception e) {
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}

	}

	private List<XClazzX> mapper(ConnectionWrapper connectionWrapper,
			Object[][] table) throws Exception {

		List<XClazzX> list = new ArrayList<XClazzX>();

		for (int i = 0; i < table.length; i++) {

			XClazzX clazz = new XClazzX();

			if (table[i][0] != null) {
				clazz.setId((String) table[i][0]);
			}
			if (table[i][1] != null) {
				clazz.setVirtual((Boolean) table[i][1]);
			}
			if (table[i][2] != null) {
				clazz.setName((String) table[i][2]);
			}
			if (table[i][3] != null) {
				clazz.setVisibility(new TypeVisibilityClass(
						(String) table[i][3]));
			}
			if (table[i][4] != null) {
				clazz.setFinalType((Boolean) table[i][4]);
			}
			// if (table[i][5] != null) {
			// clazz.setAbstractClass((Boolean) table[i][5]);
			// }

			// -------------------------------------------------------------

			// List<Clazz> extendsClass = new ArrayList<Clazz>(); 777
			//
			// String sql =
			// "SELECT cendraom.Clazz.id, cendraom.Clazz.name FROM cendraom.ClazzExtends JOIN cendraom.Clazz ON cendraom.ClazzExtends.clazzExtends = cendraom.Clazz.id WHERE cendraom.ClazzExtends.clazz = ?";
			//
			// Object[][] tableClazzExtends = find(connectionWrapper, sql,
			// clazz.getId());
			//
			// for (int x = 0; x < tableClazzExtends.length; x++) {
			//
			// Clazz clazzExtend = new Clazz();
			// clazzExtend.setId((String) tableClazzExtends[x][0]);
			// if (tableClazzExtends[0][1] != null) {
			// clazzExtend.setName((String) tableClazzExtends[0][1]);
			// }
			// extendsClass.add(clazzExtend);
			//
			// }
			//
			// clazz.setExtendsClass(extendsClass);

			// -------------------------------------------------------------

			List<XAttributeX> atts = new ArrayList<XAttributeX>();

			String sql2 = "SELECT ClazzAtt.*, cendraom.Clazz.name AS clazzDataTypeName FROM cendraom.ClazzAtt LEFT JOIN cendraom.Clazz ON cendraom.ClazzAtt.dataType = cendraom.Clazz.id WHERE cendraom.ClazzAtt.clazz = ?";

			Object[] argsAtts = { clazz.getId() };

			Object[][] tableAtts = find(connectionWrapper, sql2, argsAtts);

			for (int x = 0; x < tableAtts.length; x++) {

				XAttributeX att = new XAttributeX();

				if (tableAtts[x][0] != null) {
					att.setId((String) tableAtts[x][0]);
				}
				if (tableAtts[x][2] != null) {
					att.setName((String) tableAtts[x][2]);

				}
				if (tableAtts[x][3] != null) {

					if (UtilDataTypes.isPrimitiveType((String) tableAtts[x][3])) {
						att.setDataType(UtilDataTypes
								.getPrimitiveType((String) tableAtts[x][3]));
					} else {
						XTypeX dataTypeClazz = new XClazzX();
						dataTypeClazz.setId((String) tableAtts[x][3]);
						dataTypeClazz.setName((String) tableAtts[x][6]);

						att.setDataType(dataTypeClazz);
					}
				}
				if (tableAtts[x][4] != null) {
					att.setTypeCardinality(new TypeCardinality(
							(String) tableAtts[x][4]));
				}
				if (tableAtts[x][5] != null) {
					att.setOrderAtt((Integer) tableAtts[x][5]);
				}

				atts.add(att);

			}

			clazz.setAtts(atts);

			// -------------------------------------------------------------

			list.add(clazz);
		}

		return list;
	}

}
