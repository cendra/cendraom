package org.cendra.om.x.old;

import org.cendra.om.model.clazz.TypeComponent;
import org.cendra.om.util.UtilSerializeObjects;
import org.cendra.om.util.UtilUUID;

import com.google.gson.JsonObject;

class CreateObjectBO {

	private ObjectCreateDAO createObjectDAO;

	private UtilSerializeObjects serializeObjects;

	public CreateObjectBO(ObjectCreateDAO createObjectDAO, UtilSerializeObjects serializeObjects) {
		super();
		this.createObjectDAO = createObjectDAO;
		this.serializeObjects = serializeObjects;
	}

	public JsonObject create(TypeComponent typeComponent) throws Exception {

		String id = UtilUUID.buildUUID();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", id);
		jsonObject.addProperty("virtual", true);
		jsonObject.addProperty("typeComponent", typeComponent.getName());

		return createObjectDAO.create(jsonObject);

	}

	public JsonObject create(String jsonString, TypeComponent typeComponent) throws Exception {

		JsonObject jsonObject = serializeObjects.buildJsonObjectByString(jsonString);

		return create(jsonObject, typeComponent);

	}

	public JsonObject create(JsonObject jsonObject, TypeComponent typeComponent) throws Exception {

		String id = UtilUUID.buildUUID();

		jsonObject.addProperty("id", id);
		jsonObject.addProperty("virtual", false);
		jsonObject.addProperty("typeComponent", typeComponent.getName());

		return createObjectDAO.create(jsonObject);

	}

	
}
