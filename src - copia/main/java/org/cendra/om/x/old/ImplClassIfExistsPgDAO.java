package org.cendra.om.x.old;

import org.cendra.jdbc.ConnectionWrapper;
import org.cendra.jdbc.DataSourceWrapper;

class ImplClassIfExistsPgDAO implements ClassIfExistsDAO {

	protected DataSourceWrapper dataSourceWrapper;

	public ImplClassIfExistsPgDAO(DataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public boolean ifExistsClass(String name) throws Exception {

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

}
