package org.cendra.om.persist.dao.impl.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.om.persist.dao.ObjectsListDAO;
import org.cendra.om.util.UtilSerializeObjects;

import com.google.gson.JsonObject;

public class ImplObjectsListFileJsonDAO implements ObjectsListDAO {

	private String path;
	
	private UtilSerializeObjects serializeObjects;

	public ImplObjectsListFileJsonDAO(String path) {
		super();		
		this.path = path;
		
		serializeObjects = new UtilSerializeObjects();
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
