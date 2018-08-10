package org.cendra.om.persist.dao.impl.dbrms.pg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cendra.jdbc.ConnectionWrapper;
import org.cendra.jdbc.DataSourceWrapper;
import org.cendra.jdbc.GenericDAO;
import org.cendra.jdbc.SQLExceptionWrapper;
import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.model.clazz.ClazzAtt;
import org.cendra.om.model.clazz.TypeCardinality;
import org.cendra.om.model.clazz.TypeVisibilityClass;
import org.cendra.om.persist.dao.ClazzDAO;
import org.cendra.om.util.UtilAtts;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilUUID;

public class ImplClassPgDAO extends GenericDAO implements ClazzDAO {

	protected DataSourceWrapper dataSourceWrapper;

	public ImplClassPgDAO(DataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public Clazz create(Clazz clazz) throws Exception {

		clazz.setId(UtilUUID.buildUUID());

		Object idArg = String.class;
		Object virtualArg = Boolean.class;

		Object nameArg = String.class;
		Object visibilityArg = String.class;
		Object finalClassArg = Boolean.class;
		Object abstractClassArg = Boolean.class;

		if (clazz.getId() != null) {
			idArg = clazz.getId();
		}
		if (clazz.getVirtual() != null) {
			virtualArg = clazz.getVirtual();
		}
		if (clazz.getName() != null) {
			nameArg = clazz.getName();
		}
		if (clazz.getVisibility().getName() != null) {
			visibilityArg = clazz.getVisibility().getName();
		}
		if (clazz.getFinalClass() != null) {
			finalClassArg = clazz.getFinalClass();
		}
		if (clazz.getAbstractClass() != null) {
			abstractClassArg = clazz.getAbstractClass();
		}

		String nameTableDB = "cendraom.Clazz";

		String[] nameAtts = { UtilAtts.id, UtilAtts.virtual, UtilAtts.name,
				UtilAtts.visibility, UtilAtts.finalClass,
				UtilAtts.abstractClass };

		Object[] args = { idArg, virtualArg, nameArg, visibilityArg,
				finalClassArg, abstractClassArg };

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			connectionWrapper.begin();

			insert(connectionWrapper, nameTableDB, nameAtts, args);

			for (Clazz extendsClass : clazz.getExtendsClass()) {

				// extendsClass.setId(UtilUUID.buildUUID());

				Object idArg2 = UtilUUID.buildUUID();
				Object classArg = String.class;
				Object classExtendsArg = String.class;

				// if (extendsClass.getId() != null) {
				// idArg2 = extendsClass.getId();
				// }
				if (idArg != null) {
					classArg = idArg;
				}
				if (extendsClass != null) {
					classExtendsArg = extendsClass.getId();
				}

				String nameTableDB2 = "cendraom.ClazzExtends";

				String[] nameAtts2 = { UtilAtts.id, "clazz", "clazzExtends" };

				Object[] args2 = { idArg2, classArg, classExtendsArg };

				insert(connectionWrapper, nameTableDB2, nameAtts2, args2);
			}

			for (ClazzAtt clazzAtt : clazz.getAtts()) {

				Object idAttArg = UtilUUID.buildUUID();
				Object classArg = String.class;
				Object nameAttArg = String.class;
				Object dataTypeArg = String.class;
				Object typeCardinalityArg = String.class;
				Object orderArg = Integer.class;

				if (idArg != null) {
					classArg = idArg;
				}
				if (clazzAtt.getName() != null) {
					nameAttArg = clazzAtt.getName();
				}
				if (clazzAtt.getDataType() != null) {
					dataTypeArg = clazzAtt.getDataType().getId();
				}
				if (clazzAtt.getTypeCardinality() != null) {
					typeCardinalityArg = clazzAtt.getTypeCardinality()
							.getName();
				}
				if (clazzAtt.getOrderAtt() != null) {
					orderArg = clazzAtt.getOrderAtt();
				}

				String nameTableDB3 = "cendraom.ClazzAtt";

				String[] nameAtts3 = { UtilAtts.id, "clazz", UtilAtts.name,
						UtilAtts.dataType, UtilAtts.typeCardinality,
						UtilAtts.orderAtt };

				Object[] args3 = { idAttArg, classArg, nameAttArg, dataTypeArg,
						typeCardinalityArg, orderArg };

				insert(connectionWrapper, nameTableDB3, nameAtts3, args3);
			}

			// ----------------------------------------------------

			String sql = buildDMLCreateSchema(clazz);

			connectionWrapper.genericExecute(sql);

			sql = buildDMLCreateTable(clazz);

			connectionWrapper.genericExecute(sql);

			buildDMLCreateTableInternalObject(connectionWrapper, clazz);

			// ----------------------------------------------------

			connectionWrapper.commit();

		} catch (Exception e) {
			connectionWrapper.rollBack();
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}

		return clazz;
	}

	private String buildDMLCreateSchema(Clazz clazz) {
		String sql = "";

		String schemaName = clazz.getPackagesName().replace(".", "_");

		sql += "CREATE SCHEMA IF NOT EXISTS " + schemaName + ";";

		return sql;
	}

	private String buildDMLCreateTable(Clazz clazz) {
		String sql = "";

		String schemaName = clazz.getPackagesName().replace(".", "_");
		String tableName = clazz.getSimpleName().replace(".", "_");

		sql += "CREATE TABLE " + schemaName + "." + tableName;
		sql += "\n(";
		sql += "\n\tid VARCHAR(36) PRIMARY KEY DEFAULT uuid_generate_v4()\n";

		for (Clazz extendsClass : clazz.getExtendsClass()) {
			schemaName = extendsClass.getPackagesName().replace(".", "_");

			String dataType = "VARCHAR(36) NOT NULL REFERENCES " + schemaName + "."
					+ extendsClass.getSimpleName().replace(".", "_") + " (id)";

			sql += "\n\t, extends" + extendsClass.getSimpleName() + "_id" + " "
					+ dataType + "";
		}

		for (ClazzAtt att : clazz.getAtts()) {
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

		return sql;
	}

	private void buildDMLCreateTableInternalObject(
			ConnectionWrapper connectionWrapper, Clazz clazz)
			throws SQLExceptionWrapper, SQLException {

		for (ClazzAtt att : clazz.getAtts()) {

			if (UtilDataTypes.isPrimitiveType(att.getDataType()) == false) {

				if (att.getTypeCardinality().equals(
						UtilDataTypes.INTERNAL_OBJECT_LIST)) {

					String schemaName = att.getDataType().getPackagesName()
							.replace(".", "_");

					String sql = "ALTER TABLE " + schemaName + "."
							+ att.getDataType().getSimpleName()
							+ " ADD COLUMN " + att.getName()
							+ clazz.getSimpleName() + "_id" + " VARCHAR(36) NOT NULL REFERENCES "
							+ schemaName + "." + clazz.getSimpleName()
							+ " (id);";

					connectionWrapper.genericExecute(sql);

				} else if (att.getTypeCardinality().equals(
						UtilDataTypes.INTERNAL_OBJECT)) {

					String schemaName = att.getDataType().getPackagesName()
							.replace(".", "_");

					String sql = "ALTER TABLE " + schemaName + "."
							+ att.getDataType().getSimpleName()
							+ " ADD COLUMN " + att.getName()
							+ clazz.getSimpleName() + "_id"
							+ " VARCHAR(36) NOT NULL UNIQUE REFERENCES " + schemaName + "."
							+ clazz.getSimpleName() + " (id);";

					connectionWrapper.genericExecute(sql);

				}

			}

		}

	}

	public boolean ifExists(String name) throws Exception {

		String sql = "SELECT COUNT(*) FROM cendraom.clazz WHERE name = ?";

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

	public Clazz findById(String id) throws Exception {

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

			List<Clazz> list = mapper(connectionWrapper, table);
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

	public List<Clazz> find() throws Exception {

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

	private List<Clazz> mapper(ConnectionWrapper connectionWrapper,
			Object[][] table) throws Exception {

		List<Clazz> list = new ArrayList<Clazz>();

		for (int i = 0; i < table.length; i++) {

			Clazz clazz = new Clazz();

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
				clazz.setFinalClass((Boolean) table[i][4]);
			}
			if (table[i][5] != null) {
				clazz.setAbstractClass((Boolean) table[i][5]);
			}

			// -------------------------------------------------------------

			List<Clazz> extendsClass = new ArrayList<Clazz>();

			String sql = "SELECT cendraom.Clazz.id, cendraom.Clazz.name FROM cendraom.ClazzExtends JOIN cendraom.Clazz ON cendraom.ClazzExtends.clazzExtends = cendraom.Clazz.id WHERE cendraom.ClazzExtends.clazz = ?";

			Object[][] tableClazzExtends = find(connectionWrapper, sql,
					clazz.getId());

			for (int x = 0; x < tableClazzExtends.length; x++) {

				Clazz clazzExtend = new Clazz();
				clazzExtend.setId((String) tableClazzExtends[x][0]);
				if (tableClazzExtends[0][1] != null) {
					clazzExtend.setName((String) tableClazzExtends[0][1]);
				}
				extendsClass.add(clazzExtend);

			}

			clazz.setExtendsClass(extendsClass);

			// -------------------------------------------------------------

			List<ClazzAtt> atts = new ArrayList<ClazzAtt>();

			String sql2 = "SELECT ClazzAtt.*, cendraom.Clazz.name AS clazzDataTypeName FROM cendraom.ClazzAtt LEFT JOIN cendraom.Clazz ON cendraom.ClazzAtt.dataType = cendraom.Clazz.id WHERE cendraom.ClazzAtt.clazz = ?";

			Object[] argsAtts = { clazz.getId() };

			Object[][] tableAtts = find(connectionWrapper, sql2, argsAtts);

			for (int x = 0; x < tableAtts.length; x++) {

				ClazzAtt att = new ClazzAtt();

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
						Clazz dataTypeClazz = new Clazz();
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
