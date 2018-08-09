package org.cendra.om.x.old;

import java.util.List;

import org.cendra.om.model.clazz.TypeComponent;

import com.google.gson.JsonObject;

interface ObjectsFindDAO {

	public List<JsonObject> find(TypeComponent typeComponent) throws Exception;
	
	public JsonObject findById(TypeComponent typeComponent, String id) throws Exception;

}
