package org.cendra.om.model.clazz;

import org.cendra.om.util.UtilDataTypes;

public class ClazzAtt {

	private String id;

	private String name;
	private Clazz dataType = UtilDataTypes.buildString();
	private TypeCardinality typeCardinality = UtilDataTypes.CARDINALITY_1_1;
	private Integer orderAtt = 0;

	public String getId() {
		if (id != null) {
			this.id = id.trim();
		}
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = id.trim();
		}
		this.id = id;
	}

	public String getName() {
		if (name != null) {
			this.name = name.trim();
		}
		return name;
	}

	public void setName(String name) {
		if (name != null) {

			this.name = name.trim();

		}

		this.name = name;
	}

	public Clazz getDataType() {
		return dataType;
	}

	public void setDataType(Clazz dataType) {
		this.dataType = dataType;
	}

	public TypeCardinality getTypeCardinality() {
		return typeCardinality;
	}

	public void setTypeCardinality(TypeCardinality typeCardinality) {
		this.typeCardinality = typeCardinality;
	}

	public Integer getOrderAtt() {
		if (orderAtt == null) {
			this.orderAtt = 0;
		}
		return orderAtt;
	}

	public void setOrderAtt(Integer orderAtt) {
		if (orderAtt == null) {
			this.orderAtt = 0;
		}
		this.orderAtt = orderAtt;
	}

	@Override
	public String toString() {
		return "ClazzAtt [id=" + id + ", name=" + name + ", dataType="
				+ dataType + ", typeCardinality=" + typeCardinality
				+ ", orderAtt=" + orderAtt + "]";
	}

}
