package org.cendra.om.bo.core;

import java.util.UUID;

import org.cendra.om.model.classes.TypeComponent;
import org.cendra.om.persist.dao.CreateObjectDAO;
import org.cendra.om.util.SerializeObjects;

import com.google.gson.JsonObject;

public class CreateObjectBO {

	private CreateObjectDAO createObjectDAO;

	private SerializeObjects serializeObjects;

	public CreateObjectBO(CreateObjectDAO createObjectDAO, SerializeObjects serializeObjects) {
		super();
		this.createObjectDAO = createObjectDAO;
		this.serializeObjects = serializeObjects;
	}

	public JsonObject create(TypeComponent typeComponent) throws Exception {

		String id = buildUUID();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("id", id);
		jsonObject.addProperty("virtual", true);
		jsonObject.addProperty("typeComponent", typeComponent.getName());

		return createObjectDAO.create(jsonObject, typeComponent);

	}

	public JsonObject create(String jsonString, TypeComponent typeComponent) throws Exception {

		JsonObject jsonObject = serializeObjects.buildJsonObjectByString(jsonString);

		return create(jsonObject, typeComponent);

	}

	public JsonObject create(JsonObject jsonObject, TypeComponent typeComponent) throws Exception {

		String id = buildUUID();

		jsonObject.addProperty("id", id);
		jsonObject.addProperty("virtual", false);
		jsonObject.addProperty("typeComponent", typeComponent.getName());

		return createObjectDAO.create(jsonObject, typeComponent);

	}

	private String buildUUID() {
		return UUID.randomUUID().toString();
	}
}
