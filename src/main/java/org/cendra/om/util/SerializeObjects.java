package org.cendra.om.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SerializeObjects {
	
	public String toJsonByGson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	public String toJsonByJackson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	public JsonObject buildJsonObjectByString(String json) throws Exception {

		JsonParser parser = new JsonParser();

		JsonElement e = parser.parse(json);

		if (e.isJsonObject()) {

			JsonObject jsonObject = (JsonObject) e;

			return jsonObject;
		}

		throw new IllegalArgumentException("No es un objeto JSON válido. " + json);
	}

	public JsonObject buildJsonObjectByString(File fileObject) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(fileObject));
		StringBuffer fileContent = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			fileContent.append(line);
			line = br.readLine();
		}

		br.close();

		JsonObject jsonObject = buildJsonObjectByString(fileContent.toString());

		return jsonObject;
	}

}
