package org.cendra.om.x.old;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonObject;

class ImplObjectCreateFileJsonDAO implements ObjectCreateDAO {

	private String path;

	public ImplObjectCreateFileJsonDAO(String path) {
		super();
		this.path = path;

	}

	public JsonObject create(JsonObject jsonObject) throws Exception {

		writeFile(path, jsonObject.get("id").getAsString(),
				jsonObject.toString());

		return jsonObject;

	}

	private void writeFile(String path, String id, String content)
			throws Exception {

		Files.write(Paths.get(path + File.separatorChar + id),
				content.getBytes());
	}

}
