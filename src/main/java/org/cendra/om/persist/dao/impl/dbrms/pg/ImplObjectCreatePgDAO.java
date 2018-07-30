package org.cendra.om.persist.dao.impl.dbrms.pg;

import java.util.ArrayList;
import java.util.List;

import org.cendra.jdbc.ConnectionWrapper;
import org.cendra.jdbc.DataSourceWrapper;
import org.cendra.jdbc.GenericDAO;
import org.cendra.om.persist.dao.ObjectCreateDAO;
import org.cendra.om.util.UtilAtts;
import org.cendra.om.util.UtilTypesComponents;
import org.cendra.om.util.UtilUUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ImplObjectCreatePgDAO extends GenericDAO implements
		ObjectCreateDAO {

	protected DataSourceWrapper dataSourceWrapper;

	public ImplObjectCreatePgDAO(DataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}

	public JsonObject create(JsonObject jsonObject) throws Exception {

		if (isClass(jsonObject) == true) {
			return createClass(jsonObject);
		}

		return jsonObject;
	}

	private boolean isClass(JsonObject jsonObject) {

		return (jsonObject.get(UtilAtts.typeComponent).getAsString()
				.equals(UtilTypesComponents.CLASS_COMPONENT.getName()));
	}

	private JsonObject createClass(JsonObject jsonObject) throws Exception {

		String id = jsonObject.get(UtilAtts.id).getAsString();
		Boolean virtual = jsonObject.get(UtilAtts.virtual).getAsBoolean();
		String typeComponent = jsonObject.get(UtilAtts.typeComponent)
				.getAsString();

		String name = jsonObject.get(UtilAtts.name).getAsString();
		String visibility = jsonObject.get(UtilAtts.visibility).getAsString();
		Boolean finalClass = jsonObject.get(UtilAtts.finalClass).getAsBoolean();
		Boolean abstractClass = jsonObject.get(UtilAtts.abstractClass)
				.getAsBoolean();

		JsonArray extendsClassJson = jsonObject.get(UtilAtts.extendsClass)
				.getAsJsonArray();
		List<String> extendsClass = new ArrayList<String>();
		for (int i = 0; i < extendsClassJson.size(); i++) {
			extendsClass.add(extendsClassJson.getAsString());
		}

		JsonArray attsJson = jsonObject.get(UtilAtts.atts).getAsJsonArray();
		List<JsonObject> atts = new ArrayList<JsonObject>();
		for (int i = 0; i < attsJson.size(); i++) {
			atts.add(attsJson.get(i).getAsJsonObject());
		}

		// -----------------------------------------------------

		Object idArg = String.class;
		Object virtualArg = Boolean.class;
		Object typeComponentArg = String.class;

		Object nameArg = String.class;
		Object visibilityArg = String.class;
		Object finalClassArg = Boolean.class;
		Object abstractClassArg = Boolean.class;

		if (id != null) {
			idArg = id;
		}
		if (id != null) {
			virtualArg = virtual;
		}
		if (id != null) {
			typeComponentArg = typeComponent;
		}
		if (id != null) {
			nameArg = name;
		}
		if (id != null) {
			visibilityArg = visibility;
		}
		if (id != null) {
			finalClassArg = finalClass;
		}
		if (id != null) {
			abstractClassArg = abstractClass;
		}

		String nameTableDB = "cendraom.Clazz";

		String[] nameAtts = { UtilAtts.id, UtilAtts.virtual,
				UtilAtts.typeComponent, UtilAtts.name, UtilAtts.visibility,
				UtilAtts.finalClass, UtilAtts.abstractClass };

		Object[] args = { idArg, virtualArg, typeComponentArg, nameArg,
				visibilityArg, finalClassArg, abstractClassArg };

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			connectionWrapper.begin();

			insert(connectionWrapper, nameTableDB, nameAtts, args);

			for (String classExtends : extendsClass) {

				Object idArg2 = UtilUUID.buildUUID();
				Object classArg = String.class;
				Object classExtendsArg = String.class;

				if (idArg != null) {
					classArg = idArg;
				}
				if (classExtends != null) {
					classExtendsArg = classExtends;
				}

				String nameTableDB2 = "cendraom.ClazzExtends";

				String[] nameAtts2 = { UtilAtts.id, "clazz", "clazzExtends" };

				Object[] args2 = { idArg2, classArg, classExtendsArg };

				insert(connectionWrapper, nameTableDB2, nameAtts2, args2);
			}

			for (JsonObject att : atts) {
				

				String nameAtt = att.get(UtilAtts.name).getAsString();
				String dataType = att.get(UtilAtts.dataType).getAsString();
				String typeCardinality = att.get(UtilAtts.typeCardinality)
						.getAsString();
				Integer order = att.get(UtilAtts.orderAtt).getAsInt();

				Object idAttArg = UtilUUID.buildUUID();
				Object classArg = String.class;
				Object nameAttArg = String.class;
				Object dataTypeArg = String.class;
				Object typeCardinalityArg = String.class;
				Object orderArg = String.class;

				if (idArg != null) {
					classArg = idArg;
				}
				if (nameAtt != null) {
					nameAttArg = nameAtt;
				}
				if (dataType != null) {
					dataTypeArg = dataType;
				}
				if (typeCardinality != null) {
					typeCardinalityArg = typeCardinality;
				}
				if (order != null) {
					orderArg = order;
				}

				String nameTableDB3 = "cendraom.ClazzAtt";

				String[] nameAtts3 = { UtilAtts.id, "clazz", UtilAtts.name,
						UtilAtts.dataType, UtilAtts.typeCardinality,
						UtilAtts.orderAtt };

				Object[] args3 = { idAttArg, classArg, nameAttArg, dataTypeArg,
						typeCardinalityArg, orderArg };

				insert(connectionWrapper, nameTableDB3, nameAtts3, args3);
			}

			connectionWrapper.commit();

		} catch (Exception e) {
			connectionWrapper.rollBack();
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}

		return jsonObject;
	}
}
