package org.cendra.om.bo.object;

import java.util.List;

import org.cendra.om.persist.dao.ObjectsListDAO;

import com.google.gson.JsonObject;

public class ListObjectsBO {

	private ObjectsListDAO listObjectsDAO;

	public ListObjectsBO(ObjectsListDAO listObjectsDAO) {
		super();
		this.listObjectsDAO = listObjectsDAO;
	}

	public List<JsonObject> list() {
		try {
			return listObjectsDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
