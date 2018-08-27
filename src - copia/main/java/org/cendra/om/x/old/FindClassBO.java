package org.cendra.om.x.old;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.model.clazz.TypeVisibilityClass;
import org.cendra.om.model.clazz.old.TypeCardinality;
import org.cendra.om.model.clazz.old.XAttributeX;
import org.cendra.om.model.clazz.old.XClazzX;
import org.cendra.om.model.clazz.old.XTypeX;
import org.cendra.om.util.UtilAtts;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilTypesComponents;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

class FindClassBO {

	private FindObjectsBO findObjectsBO;

	public FindClassBO(FindObjectsBO findObjectsBO) {
		super();
		this.findObjectsBO = findObjectsBO;
	}

	public List<XTypeX> find() {
		try {
			List<JsonObject> list = findObjectsBO
					.find(UtilTypesComponents.CLASS_COMPONENT);
			List<XTypeX> listClazz = new ArrayList<XTypeX>();

			for (JsonObject jsonObjectClazz : list) {

				listClazz.add(mapper(jsonObjectClazz, true));

			}

			return listClazz;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private XTypeX mapper(JsonObject jsonObjectClazz, boolean full) {

		XTypeX clazz = new XClazzX();

		clazz.setId(jsonObjectClazz.get(UtilAtts.id).getAsString());
		clazz.setVirtual(jsonObjectClazz.get(UtilAtts.virtual).getAsBoolean());
		clazz.setName(jsonObjectClazz.get(UtilAtts.name).getAsString());
		clazz.setVisibility(new TypeVisibilityClass(jsonObjectClazz.get(
				UtilAtts.visibility).getAsString()));
		clazz.setFinalType(jsonObjectClazz.get(UtilAtts.finalType)
				.getAsBoolean());
//		clazz.setAbstractClass(jsonObjectClazz.get(UtilAtts.abstractClass)
//				.getAsBoolean());

		if (full) {
			JsonArray jsonArrayExtends = jsonObjectClazz
					.getAsJsonArray(UtilAtts.extendsClass);
			List<XTypeX> listClazzExtends = new ArrayList<XTypeX>();

			for (int i = 0; i < jsonArrayExtends.size(); i++) {
				JsonObject jsonObjectExtendsClazz = findObjectsBO.findById(
						UtilTypesComponents.CLASS_COMPONENT, jsonArrayExtends
								.get(i).getAsString());
				listClazzExtends.add(mapper(jsonObjectExtendsClazz, false));
			}

//			clazz.setExtendsClass(listClazzExtends);
		}

		JsonArray jsonArrayAtts = jsonObjectClazz.getAsJsonArray(UtilAtts.atts);
		List<XAttributeX> listClazzAtt = new ArrayList<XAttributeX>();

		for (int i = 0; i < jsonArrayAtts.size(); i++) {

			XAttributeX clazzAtt = new XAttributeX();

			clazzAtt.setId(jsonArrayAtts.get(i).getAsJsonObject()
					.get(UtilAtts.id).getAsString());
			clazzAtt.setName(jsonArrayAtts.get(i).getAsJsonObject()
					.get(UtilAtts.name).getAsString());
			String dataType = jsonArrayAtts.get(i).getAsJsonObject()
					.get(UtilAtts.dataType).getAsString();
			if (UtilDataTypes.isPrimitiveType(dataType)) {
				clazzAtt.setDataType(UtilDataTypes.getPrimitiveType(dataType));
			} else {
				JsonObject jsonObjectDataTypeClazz = findObjectsBO.findById(
						UtilTypesComponents.CLASS_COMPONENT, dataType);
				clazzAtt.setDataType(mapper(jsonObjectDataTypeClazz, false));
			}
			clazzAtt.setTypeCardinality(new TypeCardinality(jsonArrayAtts
					.get(i).getAsJsonObject().get(UtilAtts.typeCardinality)
					.getAsString()));
			clazzAtt.setOrderAtt(jsonArrayAtts.get(i).getAsJsonObject()
					.get(UtilAtts.orderAtt).getAsInt());

			listClazzAtt.add(clazzAtt);
		}

		clazz.setAtts(listClazzAtt);

		return clazz;
	}

}
