package org.cendra.om.x.old;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.cendra.om.model.TypeComponent;
import org.cendra.om.util.UtilSerializeObjects;

import com.google.gson.JsonObject;

class ImplObjectsFindFileJsonDAO implements ObjectsFindDAO {

	private String path;
	
	private UtilSerializeObjects serializeObjects;

	public ImplObjectsFindFileJsonDAO(String path) {
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

	public List<JsonObject> find() throws Exception {

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

	public List<JsonObject> find(TypeComponent typeComponent) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public JsonObject findById(TypeComponent typeComponent, String id)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	

}
