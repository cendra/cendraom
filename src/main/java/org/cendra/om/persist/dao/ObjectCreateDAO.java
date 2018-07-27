package org.cendra.om.persist.dao;

import com.google.gson.JsonObject;

public interface ObjectCreateDAO {

	public JsonObject create(JsonObject jsonObject) throws Exception;
}
