package org.cendra.om.persist.dao.impl.dbrms.pg;

import org.cendra.jdbc.DataSourceWrapper;
import org.cendra.om.persist.dao.ObjectCreateDAO;

import com.google.gson.JsonObject;

public class ImplObjectCreatePgDAO implements ObjectCreateDAO {

	protected DataSourceWrapper dataSourceWrapper;

	public ImplObjectCreatePgDAO(DataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public JsonObject create(JsonObject jsonObject) throws Exception {

		return jsonObject;
	}

}
