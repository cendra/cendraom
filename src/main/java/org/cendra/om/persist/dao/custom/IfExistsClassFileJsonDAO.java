package org.cendra.om.persist.dao.custom;

import java.io.File;

import org.cendra.om.util.SerializeObjects;
import org.cendra.om.util.TypesComponents;

import com.google.gson.JsonObject;

public class IfExistsClassFileJsonDAO implements IfExistsClassDAO {

	private String path;

	private SerializeObjects serializeObjects;

	public IfExistsClassFileJsonDAO(String path) {
		super();
		this.path = path;

		serializeObjects = new SerializeObjects();
	}

	public boolean ifExistsClass(String name) throws Exception {

		File folderObjects = new File(path);

		File[] filesObjects = folderObjects.listFiles();

		for (File fileObject : filesObjects) {

			JsonObject jsonObject = serializeObjects.buildJsonObjectByString(fileObject);

			Boolean virtualItem = null;

			if (jsonObject.get("virtual") != null && jsonObject.get("virtual").isJsonNull() == false) {
				virtualItem = jsonObject.get("virtual").getAsBoolean();

			}

			String typeComponentItem = null;

			if (jsonObject.get("typeComponent") != null && jsonObject.get("typeComponent").isJsonNull() == false) {
				typeComponentItem = jsonObject.get("typeComponent").getAsString().trim();
			}

			boolean isClassComponent = false;

			if (typeComponentItem.equals(TypesComponents.CLASS_COMPONENT.getName())) {
				isClassComponent = true;
			}

			if (isClassComponent == true && virtualItem == false) {

				String nameItem = null;

				if (jsonObject.get("name") != null && jsonObject.get("name").isJsonNull() == false) {
					nameItem = jsonObject.get("name").getAsString().trim();
				}

				if (name.trim().equals(nameItem)) {
					return true;
				}
			}

		}

		return false;
	}

}
