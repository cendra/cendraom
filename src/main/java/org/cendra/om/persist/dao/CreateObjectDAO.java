package org.cendra.om.persist.dao;

import org.cendra.om.model.classes.TypeComponent;

import com.google.gson.JsonObject;

public interface CreateObjectDAO {

	public JsonObject create(JsonObject jsonObject, TypeComponent typeComponent) throws Exception;
}
