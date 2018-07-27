package org.cendra.om.persist.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.om.util.SerializeObjects;

import com.google.gson.JsonObject;

public class ListObjectsFileJsonDAO implements ListObjectsDAO {

	private String path;
	
	private SerializeObjects serializeObjects;

	public ListObjectsFileJsonDAO(String path) {
		super();		
		this.path = path;
		
		serializeObjects = new SerializeObjects();
	}

	// public JsonObject create() throws Exception {
	//
	// String id = buildUUID();
	//
	// JsonObject jsonObject = new JsonObject();
	// jsonObject.addProperty("id", id);
	//
	// writeFile(path, id, jsonObject.toString());
	//
	// return jsonObject;
	// }

	public List<JsonObject> list() throws Exception {

		List<JsonObject> listJsonObjects = new ArrayList<JsonObject>();

		File folderObjects = new File(path);

		File[] filesObjects = folderObjects.listFiles();

		for (File fileObject : filesObjects) {

//			BufferedReader br = new BufferedReader(new FileReader(fileObject));
//			StringBuffer fileContent = new StringBuffer();
//			String line = br.readLine();
//			while (line != null) {
//				fileContent.append(line);
//				line = br.readLine();
//			}
//
//			br.close();

			JsonObject jsonObject = serializeObjects.buildJsonObjectByString(fileObject);

			listJsonObjects.add(jsonObject);
		}

		return listJsonObjects;
	}

	

}
