package org.cendra.jdbc;

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

}
