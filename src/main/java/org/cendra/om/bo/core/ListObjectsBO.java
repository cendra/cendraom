package org.cendra.om.bo.core;

import java.util.List;

import org.cendra.om.persist.dao.ListObjectsDAO;

import com.google.gson.JsonObject;

public class ListObjectsBO {

	private ListObjectsDAO listObjectsDAO;

	public ListObjectsBO(ListObjectsDAO listObjectsDAO) {
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
