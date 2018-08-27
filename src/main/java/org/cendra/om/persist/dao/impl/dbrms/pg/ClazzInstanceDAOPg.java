package org.cendra.om.persist.dao.impl.dbrms.pg;

import org.cendra.jdbc.ConnectionWrapper;
import org.cendra.jdbc.DataSourceWrapper;
import org.cendra.om.model.clazz.Clazz;

import com.google.gson.JsonObject;

public class ClazzInstanceDAOPg {

	private DataSourceWrapper dataSourceWrapper;

	public ClazzInstanceDAOPg(DataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}
	
	public JsonObject insertOne(Clazz clazz, JsonObject jsonObject) throws Exception {

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			connectionWrapper.begin();

			// ----------------------------------------------------

			
			
			// ----------------------------------------------------

			connectionWrapper.commit();

		} catch (Exception e) {
			connectionWrapper.rollBack();
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}

		return jsonObject;

	}

}
