package org.cendra.om.x.old;

import java.util.ArrayList;
import java.util.List;

import org.cendra.jdbc.ConnectionWrapper;
import org.cendra.jdbc.DataSourceWrapper;
import org.cendra.jdbc.GenericDAO;
import org.cendra.om.model.clazz.TypeComponent;
import org.cendra.om.util.UtilAtts;
import org.cendra.om.util.UtilTypesComponents;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

class ImplObjectsFindDAO extends GenericDAO implements ObjectsFindDAO {

	protected DataSourceWrapper dataSourceWrapper;

	public ImplObjectsFindDAO(DataSourceWrapper dataSourceWrapper) {
		super();
		this.dataSourceWrapper = dataSourceWrapper;
	}
	
	public boolean ifExistsClass(String name) throws Exception {

		String sql = "SELECT COUNT(*) FROM cendraom.clazz WHERE name = ?";

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			Object nameArg = String.class;
			if (name != null) {
				nameArg = name.trim();
			}

			Object[][] table = connectionWrapper.findToTable(sql, nameArg);

			return (Long) table[0][0] > 0;

		} catch (Exception e) {
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

	public List<JsonObject> find(TypeComponent typeComponent) throws Exception {

		if (isClass(typeComponent)) {
			return findClass();
		}

		return null;
	}
	
	public JsonObject findById(TypeComponent typeComponent, String id) throws Exception {

		if (isClass(typeComponent)) {
			return findClassById(id);
		}

		return null;
	}

	private boolean isClass(TypeComponent typeComponent) {

		return (typeComponent.equals(UtilTypesComponents.CLASS_COMPONENT));
	}

	public JsonObject findClassById(String id) throws Exception {

		String nameTableDB = "cendraom.Clazz";
		String orderBy = "name";
		String where = "id = ?";
		int limit = -1;
		int offset = -1;
		Object[] args = { id };

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			Object[][] table = findPaged(connectionWrapper, nameTableDB,
					orderBy, where, limit, offset, args);

			List<JsonObject> list = mapper(connectionWrapper, table);
			if (list.size() > 0) {
				return list.get(0);
			}

			return null;

		} catch (Exception e) {
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}
	}

	private List<JsonObject> findClass() throws Exception {

		String nameTableDB = "cendraom.Clazz";
		String orderBy = "name";
		String where = null;
		int limit = -1;
		int offset = -1;
		Object[] args = new Object[0];

		ConnectionWrapper connectionWrapper = dataSourceWrapper
				.getConnectionWrapper();

		try {

			Object[][] table = findPaged(connectionWrapper, nameTableDB,
					orderBy, where, limit, offset, args);

			return mapper(connectionWrapper, table);

		} catch (Exception e) {
			throw e;
		} finally {
			connectionWrapper.close(connectionWrapper);
		}

	}

	private List<JsonObject> mapper(ConnectionWrapper connectionWrapper,
			Object[][] table) throws Exception {

		List<JsonObject> list = new ArrayList<JsonObject>();

		for (int i = 0; i < table.length; i++) {

			String id = null;
			Boolean virtual = null;
			String typeComponent = null;

			String name = null;
			String visibility = null;
			Boolean finalClass = null;
			Boolean abstractClass = null;

			if (table[i][0] != null) {
				id = (String) table[i][0];
			}
			if (table[i][1] != null) {
				virtual = (Boolean) table[i][1];
			}
			if (table[i][2] != null) {
				typeComponent = (String) table[i][2];
			}
			if (table[i][3] != null) {
				name = (String) table[i][3];
			}
			if (table[i][4] != null) {
				visibility = (String) table[i][4];
			}
			if (table[i][5] != null) {
				finalClass = (Boolean) table[i][5];
			}
			if (table[i][6] != null) {
				abstractClass = (Boolean) table[i][6];
			}

			JsonObject jsonObject = new JsonObject();

			jsonObject.addProperty(UtilAtts.id, id);
			jsonObject.addProperty(UtilAtts.virtual, virtual);
			jsonObject.addProperty(UtilAtts.typeComponent, typeComponent);
			jsonObject.addProperty(UtilAtts.name, name);
			jsonObject.addProperty(UtilAtts.visibility, visibility);
			jsonObject.addProperty(UtilAtts.finalClass, finalClass);
			jsonObject.addProperty(UtilAtts.abstractClass, abstractClass);

			// -------------------------------------------------------------

			JsonArray jsonArrayExtends = new JsonArray();

			String nameTableDBExtends = "cendraom.ClazzExtends";
			String orderByExtends = "id";
			String whereExtends = "clazz = ?";
			int limitExtends = -1;
			int offsetExtends = -1;
			Object[] argsExtends = { id };

			Object[][] tableExtends = findPaged(connectionWrapper,
					nameTableDBExtends, orderByExtends, whereExtends,
					limitExtends, offsetExtends, argsExtends);

			for (int x = 0; x < tableExtends.length; x++) {

				String idExtends = null;
				// Boolean virtualExtends = null;
				// String typeComponentExtends = null;
				//
				// String nameExtends = null;
				// String visibilityExtends = null;
				// Boolean finalClassExtends = null;
				// Boolean abstractClassExtends = null;

				if (tableExtends[x][2] != null) {
					idExtends = (String) tableExtends[x][2];
				}

				// String nameTableDBExtends2 = "cendraom.Clazz";
				// String orderByExtends2 = null;
				// String whereExtends2 = "id = ?";
				// int limitExtends2 = -1;
				// int offsetExtends2 = -1;
				// Object[] argsExtends2 = { idExtends };
				//
				// Object[][] tableExtends2 = findPaged(connectionWrapper,
				// nameTableDBExtends2, orderByExtends2,
				// whereExtends2, limitExtends2, offsetExtends2,
				// argsExtends2);
				//
				// if (tableExtends2[0][1] != null) {
				// virtualExtends = (Boolean) tableExtends2[0][1];
				// }
				// if (tableExtends2[0][2] != null) {
				// typeComponentExtends = (String) tableExtends2[0][2];
				// }
				// if (tableExtends2[0][3] != null) {
				// nameExtends = (String) tableExtends2[0][3];
				// }
				// if (tableExtends2[0][4] != null) {
				// visibilityExtends = (String) tableExtends2[0][4];
				// }
				// if (tableExtends2[0][5] != null) {
				// finalClassExtends = (Boolean) tableExtends2[0][5];
				// }
				// if (tableExtends2[0][6] != null) {
				// abstractClassExtends = (Boolean) tableExtends2[0][6];
				// }
				//
				// JsonObject jsonObjectExtends = new JsonObject();
				//
				// jsonObjectExtends.addProperty(UtilAtts.id, idExtends);
				// jsonObjectExtends.addProperty(UtilAtts.virtual,
				// virtualExtends);
				// jsonObjectExtends.addProperty(UtilAtts.typeComponent,
				// typeComponentExtends);
				// jsonObjectExtends.addProperty(UtilAtts.name,
				// nameExtends);
				// jsonObjectExtends.addProperty(UtilAtts.visibility,
				// visibilityExtends);
				// jsonObjectExtends.addProperty(UtilAtts.finalClass,
				// finalClassExtends);
				// jsonObjectExtends.addProperty(UtilAtts.abstractClass,
				// abstractClassExtends);

				jsonArrayExtends.add(idExtends);

			}

			jsonObject.add(UtilAtts.extendsClass, jsonArrayExtends);

			// -------------------------------------------------------------

			JsonArray jsonArrayAtts = new JsonArray();

			String nameTableDBAtts = "cendraom.ClazzAtt";
			String orderByAtts = "orderAtt";
			String whereAtts = "clazz = ?";
			int limitAtts = -1;
			int offsetAtts = -1;
			Object[] argsAtts = { id };

			Object[][] tableAtts = findPaged(connectionWrapper,
					nameTableDBAtts, orderByAtts, whereAtts, limitAtts,
					offsetAtts, argsAtts);

			for (int x = 0; x < tableAtts.length; x++) {

				String idAtt = null;
				String nameAtt = null;
				String dataTypeAtt = null;
				String typeCardinalityAtt = null;
				Integer orderAtt = null;

				if (tableAtts[x][0] != null) {
					idAtt = (String) tableAtts[x][0];
				}
				if (tableAtts[x][2] != null) {
					nameAtt = (String) tableAtts[x][2];
				}
				if (tableAtts[x][3] != null) {
					dataTypeAtt = (String) tableAtts[x][3];
				}
				if (tableAtts[x][4] != null) {
					typeCardinalityAtt = (String) tableAtts[x][4];
				}
				if (tableAtts[x][5] != null) {
					orderAtt = (Integer) tableAtts[x][5];
				}

				JsonObject jsonObjectAtt = new JsonObject();

				jsonObjectAtt.addProperty(UtilAtts.id, idAtt);
				jsonObjectAtt.addProperty(UtilAtts.name, nameAtt);
				jsonObjectAtt.addProperty(UtilAtts.dataType, dataTypeAtt);
				jsonObjectAtt.addProperty(UtilAtts.typeCardinality,
						typeCardinalityAtt);
				jsonObjectAtt.addProperty(UtilAtts.orderAtt, orderAtt);

				jsonArrayAtts.add(jsonObjectAtt);

			}

			jsonObject.add(UtilAtts.atts, jsonArrayAtts);

			// -------------------------------------------------------------

			list.add(jsonObject);
		}

		return list;
	}

}
