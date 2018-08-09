package org.cendra.om.x.old;

import java.util.List;

import org.cendra.om.model.clazz.TypeComponent;

import com.google.gson.JsonObject;

class FindObjectsBO {

	private ObjectsFindDAO objectsFindDAO;

	public FindObjectsBO(ObjectsFindDAO listObjectsDAO) {
		super();
		this.objectsFindDAO = listObjectsDAO;
	}

	public List<JsonObject> find(TypeComponent typeComponent) {
		try {
			return objectsFindDAO.find(typeComponent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JsonObject findById(TypeComponent typeComponent, String id) {
		try {
			return objectsFindDAO.findById(typeComponent, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
