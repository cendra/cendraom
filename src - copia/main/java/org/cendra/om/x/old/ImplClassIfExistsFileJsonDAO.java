package org.cendra.om.x.old;

import java.io.File;

import org.cendra.om.util.UtilSerializeObjects;
import org.cendra.om.util.UtilTypesComponents;

import com.google.gson.JsonObject;

class ImplClassIfExistsFileJsonDAO implements ClassIfExistsDAO {

	private String path;

	private UtilSerializeObjects utilSerializeObjects;

	public ImplClassIfExistsFileJsonDAO(String path, UtilSerializeObjects utilSerializeObjects) {
		super();
		this.path = path;

		this.utilSerializeObjects = utilSerializeObjects;
	}

	public boolean ifExistsClass(String name) throws Exception {

		File folderObjects = new File(path);

		File[] filesObjects = folderObjects.listFiles();

		for (File fileObject : filesObjects) {

			JsonObject jsonObject = utilSerializeObjects.buildJsonObjectByString(fileObject);

			Boolean virtualItem = null;

			if (jsonObject.get("virtual") != null && jsonObject.get("virtual").isJsonNull() == false) {
				virtualItem = jsonObject.get("virtual").getAsBoolean();

			}

			String typeComponentItem = null;

			if (jsonObject.get("typeComponent") != null && jsonObject.get("typeComponent").isJsonNull() == false) {
				typeComponentItem = jsonObject.get("typeComponent").getAsString().trim();
			}

			boolean isClassComponent = false;

			if (typeComponentItem.equals(UtilTypesComponents.CLASS_COMPONENT.getName())) {
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
