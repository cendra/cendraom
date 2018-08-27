package org.cendra.om.persist.dao.impl.dbrms.pg;

import java.sql.SQLException;
import java.util.List;

import org.cendra.jdbc.ConnectionWrapper;
import org.cendra.jdbc.DataSourceWrapper;
import org.cendra.jdbc.GenericDAO;
import org.cendra.jdbc.SQLExceptionWrapper;
import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.model.clazz.ClazzAttribute;
import org.cendra.om.model.clazz.DataTypeArray;
import org.cendra.om.model.clazz.DataTypeClazz;
import org.cendra.om.model.clazz.DataTypeSimpleBigDecimal;
import org.cendra.om.model.clazz.DataTypeSimpleBoolean;
import org.cendra.om.model.clazz.DataTypeSimpleDate;
import org.cendra.om.model.clazz.DataTypeSimpleDouble;
import org.cendra.om.model.clazz.DataTypeSimpleFloat;
import org.cendra.om.model.clazz.DataTypeSimpleInteger;
import org.cendra.om.model.clazz.DataTypeSimpleLong;
import org.cendra.om.model.clazz.DataTypeSimpleShort;
import org.cendra.om.model.clazz.DataTypeSimpleString;
import org.cendra.om.model.clazz.DataTypeSimpleTime;
import org.cendra.om.model.clazz.DataTypeSimpleTimestamp;
import org.cendra.om.util.UtilUUID;

public class ClazzDAOPg extends GenericDAO {

	private DataSourceWrapper dataSourceWrapper;

	public ClazzDAOPg(DataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public Clazz insertOne(Clazz clazz) throws Exception {

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			connectionWrapper.begin();

			// ----------------------------------------------------

			insertTableClazz(connectionWrapper, clazz);

			createTableClazz(connectionWrapper, clazz);

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

	private Clazz insertTableClazz(ConnectionWrapper connectionWrapper,
			Clazz clazz) throws Exception {

		clazz.setId(UtilUUID.buildUUID());

		Object id = String.class;
		Object virtual = Boolean.class;
		Object extendable = Boolean.class;
		Object visibility = String.class;
		Object name = String.class;
		Object extendsFrom = String.class;
		Object comment = String.class;
		Object labelName = String.class;
		Object labelComment = String.class;

		if (clazz.getId() != null) {
			id = clazz.getId();
		}
		if (clazz.getVirtual() != null) {
			virtual = clazz.getVirtual();
		}
		if (clazz.getExtendable() != null) {
			extendable = clazz.getExtendable();
		}
		if (clazz.getVisibility() != null
				&& clazz.getVisibility().getName() != null) {
			visibility = clazz.getVisibility().getName();
		}
		if (clazz.getName() != null) {
			name = clazz.getName();
		}
		if (clazz.getExtendsFrom() != null
				&& clazz.getExtendsFrom().getName() != null) {

			String extendsFromId = getIdClazzByName(clazz.getExtendsFrom()
					.getName());
			if (extendsFromId != null) {
				extendsFrom = extendsFromId;
			}

		}
		if (clazz.getComment() != null) {
			comment = clazz.getComment();
		}
		if (clazz.getLabelName() != null) {
			labelName = clazz.getLabelName();
		}
		if (clazz.getLabelComment() != null) {
			labelComment = clazz.getLabelComment();
		}

		String nameTableDB = "cendraom.Clazz";
		String[] nameAtts = { "id", "isVirtual", "isExtendable", "visibility",
				"name", "extendsFrom", "comment", "labelName", "labelComment" };
		Object[] args = { id, virtual, extendable, visibility, name,
				extendsFrom, comment, labelName, labelComment };

		insert(connectionWrapper, nameTableDB, nameAtts, args);

		insertTableClazzAttribute(connectionWrapper, clazz);

		return clazz;

	}

	private void insertTableClazzAttribute(ConnectionWrapper connectionWrapper,
			Clazz clazz) throws Exception {

		for (ClazzAttribute attribute : clazz.getAttributes()) {

			attribute.setId(UtilUUID.buildUUID());

			Object id = String.class;

			Object code = String.class;
			Object orderAtt = Integer.class;
			Object name = String.class;
			Object comment = String.class;
			Object labelName = String.class;
			Object labelComment = String.class;

			if (attribute.getId() != null) {
				id = attribute.getId();
			}
			if (attribute.getCode() != null) {
				code = attribute.getCode();
			}
			if (attribute.getOrderAtt() != null) {
				orderAtt = attribute.getOrderAtt();
			}
			if (attribute.getName() != null) {
				name = attribute.getName();
			}
			if (attribute.getComment() != null) {
				comment = attribute.getComment();
			}
			if (attribute.getLabelName() != null) {
				labelName = attribute.getLabelName();
			}
			if (attribute.getLabelComment() != null) {
				labelComment = attribute.getLabelComment();
			}

			Object memberOfClazz = String.class;

			Object isArray = Boolean.class;

			Object dataTypeBoolean = false;
			Object dataTypeString = false;
			Object dataTypeShort = false;
			Object dataTypeInteger = false;
			Object dataTypeLong = false;
			Object dataTypeFloat = false;
			Object dataTypeDouble = false;
			Object dataTypeBigDecial = false;
			Object dataTypeDate = false;
			Object dataTypeTimestamp = false;
			Object dataTypeTime = false;

			Object dataTypeClazz = String.class;

			if (clazz.getId() != null) {
				memberOfClazz = clazz.getId();
			}

			if (attribute.getDataType() != null) {

				if (attribute.getDataType() instanceof DataTypeArray) {

					isArray = ((DataTypeArray) attribute.getDataType())
							.getArray();
				}

				if (attribute.getDataType() instanceof DataTypeClazz
						&& ((DataTypeClazz) attribute.getDataType()).getClazz() != null
						&& ((DataTypeClazz) attribute.getDataType()).getClazz()
								.getName() != null) {

					String clazzName = ((DataTypeClazz) attribute.getDataType())
							.getClazz().getName();

					String dataTypeClazzId = getIdClazzByName(clazzName);

					if (dataTypeClazzId != null) {
						dataTypeClazz = dataTypeClazzId;
					}

				} else if (attribute.getDataType() instanceof DataTypeSimpleBoolean) {

					dataTypeBoolean = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleString) {

					dataTypeString = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleShort) {

					dataTypeShort = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleInteger) {

					dataTypeInteger = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleLong) {

					dataTypeLong = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleFloat) {

					dataTypeFloat = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleDouble) {

					dataTypeDouble = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleBigDecimal) {

					dataTypeBigDecial = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleDate) {

					dataTypeDate = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleTimestamp) {

					dataTypeTimestamp = true;

				} else if (attribute.getDataType() instanceof DataTypeSimpleTime) {

					dataTypeTime = true;

				}
			}

			String nameTableDB = "cendraom.ClazzAttribute";
			String[] nameAtts = { "id", "code", "orderAtt", "name", "comment",
					"labelName", "labelComment", "memberOfClazz", "isArray",
					"dataTypeBoolean", "dataTypeString", "dataTypeShort",
					"dataTypeInteger", "dataTypeLong", "dataTypeFloat",
					"dataTypeDouble", "dataTypeBigDecial", "dataTypeDate",
					"dataTypeTimestamp", "dataTypeTime", "dataTypeClazz" };
			Object[] args = { id, code, orderAtt, name, comment, labelName,
					labelComment, memberOfClazz, isArray, dataTypeBoolean,
					dataTypeString, dataTypeShort, dataTypeInteger,
					dataTypeLong, dataTypeFloat, dataTypeDouble,
					dataTypeBigDecial, dataTypeDate, dataTypeTimestamp,
					dataTypeTime, dataTypeClazz };

			insert(connectionWrapper, nameTableDB, nameAtts, args);

		}

	}

	private String getIdClazzByName(String name) throws Exception {

		String sql = "SELECT id FROM cendraom.Clazz WHERE name = ?";

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			Object nameArg = String.class;
			if (name != null) {
				nameArg = name.trim();
			}

			Object[][] table = connectionWrapper.findToTable(sql, nameArg);

			if (table.length > 0) {
				if (table[0].length > 0) {
					return table[0][0].toString();
				}
			}

			return null;

		} catch (Exception e) {
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

	private void createTableClazz(ConnectionWrapper connectionWrapper,
			Clazz clazz) throws Exception {

		String sql = "\n";

		String schemaName = clazz.getPackagesName().replace(".", "_");

		sql += "CREATE SCHEMA IF NOT EXISTS " + schemaName + ";";

		connectionWrapper.genericExecute(sql);

		sql = "";

		String tableName = clazz.getSimpleName();

		sql += "CREATE TABLE " + schemaName + "." + tableName;
		sql += "\n(";
		sql += "\n\tid VARCHAR(36) NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4()";

		if (clazz.getExtendsFrom() != null) {

			schemaName = clazz.getExtendsFrom().getPackagesName()
					.replace(".", "_");

			sql += " REFERENCES " + schemaName + "."
					+ clazz.getExtendsFrom().getSimpleName() + " (id)";

		}

		sql += "\n";

		List<ClazzAttribute> attributes = clazz.getAttributes();

		createTable(connectionWrapper, sql, schemaName, clazz.getSimpleName(),
				attributes);

	}

	private void createTable(ConnectionWrapper connectionWrapper, String sql,
			String schemaName, String tableNameSource,
			List<ClazzAttribute> attributes) throws SQLExceptionWrapper,
			SQLException {

		// ===========================================================

		sql += buildAttributesTableDataTypeNotArray(attributes);

		sql += "\n)";

		System.err.println(sql);

		connectionWrapper.genericExecute(sql);

		// ===========================================================

		for (ClazzAttribute attributeItem : attributes) {

			if (attributeItem.getDataType() != null) {

				boolean isArray = false;

				if (attributeItem.getDataType() instanceof DataTypeArray) {

					isArray = ((DataTypeArray) attributeItem.getDataType())
							.getArray();
				}

				if (isArray) {

					createTableArray(connectionWrapper, schemaName,
							tableNameSource, attributeItem);

				}
			}

		} // end for

		// ===========================================================
	}

	private String buildAttributesTableDataTypeNotArray(
			List<ClazzAttribute> attributes) {

		String sql = "";

		for (ClazzAttribute attributeItem : attributes) {

			if (attributeItem.getDataType() != null) {

				boolean isArray = false;

				if (attributeItem.getDataType() instanceof DataTypeArray) {

					isArray = ((DataTypeArray) attributeItem.getDataType())
							.getArray();
				}

				if (isArray == false) {

					String dataType = buildDataTypeNotArray(attributeItem);

					if (dataType.trim().length() > 0) {
						sql += "\n\t, " + attributeItem.getName().replace(".", "_") + " "
								+ dataType + "";
					}

				}

			}

		} // end for

		return sql;
	}

	private String buildDataTypeNotArray(ClazzAttribute attributeItem) {

		String dataType = "";

		if (attributeItem.getDataType() instanceof DataTypeClazz
				&& ((DataTypeClazz) attributeItem.getDataType()).getClazz() != null
				&& ((DataTypeClazz) attributeItem.getDataType()).getClazz()
						.getName() != null) {

			String schemaNameDataTypeClazz = ((DataTypeClazz) attributeItem
					.getDataType()).getClazz().getPackagesName()
					.replace(".", "_");
			String simpleNameDataTypeClazz = ((DataTypeClazz) attributeItem
					.getDataType()).getClazz().getSimpleName();

			dataType = "VARCHAR(36) REFERENCES " + schemaNameDataTypeClazz
					+ "." + simpleNameDataTypeClazz + " (id)";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleBoolean) {

			dataType = "BOOLEAN";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleString) {

			dataType = "VARCHAR";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleShort) {

			dataType = "SMALLINT";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleInteger) {

			dataType = "INTEGER";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleLong) {

			dataType = "BIGINT";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleFloat) {

			dataType = "REAL";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleDouble) {

			dataType = "FLOAT";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleBigDecimal) {

			dataType = "DECIMAL";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleDate) {

			dataType = "DATE";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleTimestamp) {

			dataType = "TIMESTAMP";

		} else if (attributeItem.getDataType() instanceof DataTypeSimpleTime) {

			dataType = "TIME";

		}

		return dataType;
	}

	private void createTableArray(ConnectionWrapper connectionWrapper,
			String schemaName, String tableNameSource, ClazzAttribute attribute)
			throws SQLExceptionWrapper, SQLException {

		String sql = "\n";

		String tableName = attribute.getName().replace(".", "_");

		if (tableName.trim().length() > 63) {
			throw new IllegalStateException(
					"It is not allowed to create an attribute whose Simple Path exceeds the length of 63 characters.\nFull Path: "
							+ "attribute.getPath()"
							+ "\nSimple Path: "
							+ "attribute.getSimplePath()");
		}

		sql += "CREATE TABLE " + schemaName + "." + tableName;
		sql += "\n(";
		sql += "\n\tid VARCHAR(36) NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4()";

		sql += "\n\t, " + tableNameSource
				+ "_id VARCHAR(36) NOT NULL REFERENCES " + schemaName + "."
				+ tableNameSource + " (id)";

		String dataType = buildDataTypeNotArray(attribute);

		sql += "\n";

		if (dataType.trim().length() > 0) {
			sql += "\n\t, " + attribute.getSimpleName() + " " + dataType + "";
		}

		sql += "\n)";

		System.err.println(sql);

		connectionWrapper.genericExecute(sql);

		sql = "CREATE UNIQUE INDEX u_" + schemaName + "_" + tableName + "_id_"
				+ tableNameSource + "_id ON " + schemaName + "." + tableName
				+ " (TRIM(" + tableName + ".id), TRIM(" + tableName + "."
				+ tableNameSource + "_id));";

		connectionWrapper.genericExecute(sql);

		System.err.println(sql);

	}

}
