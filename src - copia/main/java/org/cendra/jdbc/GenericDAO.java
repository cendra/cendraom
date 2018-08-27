package org.cendra.jdbc;

import java.util.List;

public class GenericDAO {

	public boolean insert(ConnectionWrapper connectionWrapper,
			String tableName, String[] nameAtts, Object[] args)
			throws Exception {

		if (args == null) {
			args = new Object[0];
		}

		String sql = buildSQLInsert(tableName, nameAtts, args);

		int rows = connectionWrapper.insert(sql, args);

		if (rows != 1) {
			throw new Exception(
					"La sentencia debería afectar un registro, la sentencia afecto "
							+ rows + " registros.");
		}

		return true;

	}

	public boolean insert(DataSourceWrapper dataSourceWrapper,
			String tableName, String[] nameAtts, Object[] args)
			throws Exception {

		if (args == null) {
			args = new Object[0];
		}

		String sql = buildSQLInsert(tableName, nameAtts, args);

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			connectionWrapper.begin();

			int rows = connectionWrapper.insert(sql, args);

			if (rows != 1) {
				throw new Exception(
						"La sentencia debería afectar un registro, la sentencia afecto "
								+ rows + " registros.");
			}

			connectionWrapper.commit();

			return true;

		} catch (Exception e) {
			connectionWrapper.rollBack();
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

	public String buildSQLInsert(String tableName, String[] nameAtts,
			Object[] args) throws Exception {

		String sql = "INSERT INTO " + tableName + " (";

		for (int i = 0; i < nameAtts.length; i++) {
			sql += nameAtts[i];
			if (i < nameAtts.length - 1) {
				sql += ", ";
			}
		}

		sql += ") VALUES (";

		for (int i = 0; i < args.length; i++) {
			sql += "?";
			if (i < args.length - 1) {
				sql += ", ";
			}
		}

		sql += ")";

		return sql;
	}

	@SuppressWarnings("rawtypes")
	protected List findTLessPaged(ConnectionWrapper connectionWrapper,
			String viewName, String orderBy, String where, int limit,
			int offset, Object... args) throws Exception {

		if (args == null) {
			args = new Object[0];
		}

		String sql = "SELECT * FROM " + viewName;

		if (where != null && where.trim().length() > 0) {
			sql += " WHERE " + where;
		}

		// if (orderBy == null || orderBy.trim().length() == 0) {
		// throw new Exception("orderBy not found.");
		// }

		if (orderBy != null && orderBy.trim().length() > 0) {
			sql += " ORDER BY " + orderBy;
		} else {
			sql += " ORDER BY " + 1;
		}

		if (offset > -1 && limit > -1) {

			sql += " OFFSET " + offset + " LIMIT " + limit;
		}

		sql += ";";

		// ConnectionWrapper connectionWrapper =
		// dataSourceWrapper.getConnectionWrapper();

		try {

			List list = null;

			if (args.length == 0) {
				list = connectionWrapper.findToListByCendraConvention(sql);
			} else {
				list = connectionWrapper
						.findToListByCendraConvention(sql, args);
			}

			// for (Object item : list) {
			// if (item instanceof Valuable) {
			// ((Valuable) item).validate();
			// }
			// }

			return list;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}

	}

	protected Object[][] findPaged(ConnectionWrapper connectionWrapper,
			String viewName, String orderBy, String where, int limit,
			int offset, Object... args) throws Exception {

		if (args == null) {
			args = new Object[0];
		}

		String sql = "SELECT * FROM " + viewName;

		if (where != null && where.trim().length() > 0) {
			sql += " WHERE " + where;
		}

		// if (orderBy == null || orderBy.trim().length() == 0) {
		// throw new Exception("orderBy not found.");
		// }

		if (orderBy != null && orderBy.trim().length() > 0) {
			sql += " ORDER BY " + orderBy;
		} else {
			sql += " ORDER BY " + 1;
		}

		if (offset > -1 && limit > -1) {

			sql += " OFFSET " + offset + " LIMIT " + limit;
		}

		sql += ";";

		// ConnectionWrapper connectionWrapper =
		// dataSourceWrapper.getConnectionWrapper();

		try {

			Object[][] table = null;

			if (args.length == 0) {
				table = connectionWrapper.findToTable(sql);
			} else {
				table = connectionWrapper.findToTable(sql, args);
			}

			// for (Object item : list) {
			// if (item instanceof Valuable) {
			// ((Valuable) item).validate();
			// }
			// }

			return table;

		} catch (Exception e) {
			// e.printStackTrace();
			throw e;
		} finally {
			// connectionWrapper.close(connectionWrapper);
		}

	}

	protected Object[][] find(ConnectionWrapper connectionWrapper, String sql,
			Object... args) throws Exception {

		if (args == null) {
			args = new Object[0];
		}	

		// ConnectionWrapper connectionWrapper =
		// dataSourceWrapper.getConnectionWrapper();

		try {

			Object[][] table = null;

			if (args.length == 0) {
				table = connectionWrapper.findToTable(sql);
			} else {
				table = connectionWrapper.findToTable(sql, args);
			}

			// for (Object item : list) {
			// if (item instanceof Valuable) {
			// ((Valuable) item).validate();
			// }
			// }

			return table;

		} catch (Exception e) {
			// e.printStackTrace();
			throw e;
		} finally {
			// connectionWrapper.close(connectionWrapper);
		}

	}

}
