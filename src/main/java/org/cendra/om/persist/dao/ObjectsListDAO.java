package org.cendra.om.persist.dao;

import java.util.List;

import com.google.gson.JsonObject;

public interface ObjectsListDAO {

	public List<JsonObject> list() throws Exception;

}
