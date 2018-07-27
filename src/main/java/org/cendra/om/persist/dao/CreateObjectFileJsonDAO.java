package org.cendra.om.persist.dao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.cendra.om.model.classes.TypeComponent;

import com.google.gson.JsonObject;

public class CreateObjectFileJsonDAO implements CreateObjectDAO {

	private String path;

	public CreateObjectFileJsonDAO(String path) {
		super();
		this.path = path;

	}

	public JsonObject create(JsonObject jsonObject, TypeComponent typeComponent) throws Exception {

		writeFile(path, jsonObject.get("id").toString(), jsonObject.toString());

		return jsonObject;

	}

	private void writeFile(String path, String id, String content) throws Exception {

		Files.write(Paths.get(path + File.separatorChar + id), content.getBytes());
	}

}
