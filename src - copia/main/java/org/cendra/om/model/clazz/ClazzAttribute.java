package org.cendra.om.model.clazz;

import java.util.ArrayList;
import java.util.List;

public class ClazzAttribute {

	private String id;

	private String code;
	private Integer orderAtt;
	private String name;
	private String comment;

	private DataTypeForClazz dataType;

	private String labelName;
	private String labelComment;

	private List<ClazzAttributeLabel> labels = new ArrayList<ClazzAttributeLabel>();

	protected Clazz _clazz;
	protected ClazzAttribute _source;

	public String getId() {

		if (id != null) {

			id = id.trim();

			if (id.length() == 0) {
				id = null;
			}
		}

		return id;
	}

	public void setId(String id) {

		if (id != null) {

			id = id.trim();

			if (id.length() == 0) {
				id = null;
			}
		}

		this.id = id;
	}

	public String getCode() {

		if (code != null) {

			code = code.trim().toLowerCase();

			if (code.length() == 0) {
				code = null;
			}
		}

		return code;
	}

	public void setCode(String code) {

		if (code != null) {

			code = code.trim().toLowerCase();

			if (code.length() == 0) {
				code = null;
			}
		}

		this.code = code;
	}

	public Integer getOrderAtt() {
		if (orderAtt == null) {
			orderAtt = 0;
		}

		return orderAtt;
	}

	public void setOrderAtt(Integer orderAtt) {
		if (orderAtt == null) {
			orderAtt = 0;
		}

		this.orderAtt = orderAtt;
	}

	public String getName() {

		if (name != null) {

			name = name.trim();

			if (name.length() == 0) {
				name = null;
			}
		}

		return name;
	}

	public void setName(String name) {

		if (name != null) {

			name = name.trim();

			if (name.length() == 0) {
				name = null;
			}
		}

		this.name = name;
	}

	public String getComment() {

		if (comment != null) {

			comment = comment.trim();

			if (comment.length() == 0) {
				comment = null;
			}
		}

		return comment;
	}

	public void setComment(String comment) {

		if (comment != null) {

			comment = comment.trim();

			if (comment.length() == 0) {
				comment = null;
			}
		}

		this.comment = comment;
	}

	public DataTypeForClazz getDataType() {
		return dataType;
	}

	public void setDataType(DataTypeForClazz dataType) {
		this.dataType = dataType;

		if (this.dataType instanceof DataTypeClazz) {

			((DataTypeClazz) dataType)._source = this;

		}
	}

	public String getLabelName() {

		if (labelName != null) {

			labelName = labelName.trim();

			if (labelName.length() == 0) {
				labelName = null;
			}
		}

		return labelName;
	}

	public void setLabelName(String labelName) {

		if (labelName != null) {

			labelName = labelName.trim();

			if (labelName.length() == 0) {
				labelName = null;
			}
		}

		this.labelName = labelName;
	}

	public String getLabelComment() {

		if (labelComment != null) {

			labelComment = labelComment.trim();

			if (labelComment.length() == 0) {
				labelComment = null;
			}
		}

		return labelComment;
	}

	public void setLabelComment(String labelComment) {

		if (labelComment != null) {

			labelComment = labelComment.trim();

			if (labelComment.length() == 0) {
				labelComment = null;
			}
		}

		this.labelComment = labelComment;
	}

	public List<ClazzAttributeLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<ClazzAttributeLabel> labels) {
		this.labels = labels;
	}

	public boolean addLabel(ClazzAttributeLabel e) {
		return labels.add(e);
	}

	// -------------------------------------------------------

	public String getPath() {

		if (this._source != null) {
			return this._source.getPath() + "." + name;
		}

		return _clazz.getName() + "." + name;
	}
	
	public String getSimplePath() {

		if (this._source != null) {
			return this._source.getSimplePath() + "." + name;
		}

		return _clazz.getSimpleName() + "." + name;
	}

	// -------------------------------------------------------

	public boolean addAttributeBoolean(String name) {

		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeBoolean(this, name);
	}

	public boolean addAttributeArrayBoolean(String name) {

		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayBoolean(this, name);
	}

	public boolean addAttributeString(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeString(this, name);
	}
	
	public boolean addAttributeArrayString(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayString(this, name);
	}

	public boolean addAttributeShort(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeShort(this, name);
	}
	
	public boolean addAttributeArrayShort(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayShort(this, name);
	}

	public boolean addAttributeInteger(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeInteger(this, name);
	}
	
	public boolean addAttributeArrayInteger(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayInteger(this, name);
	}

	public boolean addAttributeLong(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeLong(this, name);
	}
	
	public boolean addAttributeArrayLong(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayLong(this, name);
	}

	public boolean addAttributeFloat(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeFloat(this, name);
	}
	
	public boolean addAttributeArrayFloat(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayFloat(this, name);
	}

	public boolean addAttributeDouble(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeDouble(this, name);
	}
	
	public boolean addAttributeArrayDouble(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayDouble(this, name);
	}

	public boolean addAttributeBigDecimal(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeBigDecimal(this, name);
	}
	
	public boolean addAttributeArrayBigDecimal(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayBigDecimal(this, name);
	}

	public boolean addAttributeDate(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeDate(this, name);
	}
	
	public boolean addAttributeArrayDate(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayDate(this, name);
	}

	public boolean addAttributeTimestamp(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttTimestamp(this, name);
	}
	
	public boolean addAttributeArrayTimestamp(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayTimestamp(this, name);
	}

	public boolean addAttributeTime(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeTime(this, name);
	}
	
	public boolean addAttributeArrayTime(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayTime(this, name);
	}

	public boolean addAttributeObject(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeObject(this, name);
	}

	public boolean addAttributeArrayObject(String name) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayObject(this, name);
	}

	public boolean addAttributeClazz(String name, Clazz clazz) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeClazz(this, name, clazz);
	}

	public boolean addAttributeArrayClazz(String name, Clazz clazz) {
		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.addAttributeArrayClazz(this, name, clazz);
	}

	public ClazzAttribute getAttribute(String name) {

		if (dataType instanceof DataTypeClazzObject == false) {
			throw new IllegalStateException(
					"Se requiere que el tipo de dato del atributo sea "
							+ DataTypeClazzObject.class.getName()
							+ " para poder agregar sub-attributes.");
		}

		DataTypeClazzObject dt = (DataTypeClazzObject) this.getDataType();

		return dt.getAttribute(name);
	}

} // END
