package org.cendra.om.x.old;

import org.cendra.om.model.clazz.old.XAttributeX;

class ClazzAttPersist {

	private String id;

	private String name;
	private String dataType;
	private String typeCardinality;
	private Integer orderAtt = 0;

	public ClazzAttPersist() {
		super();
	}
	
	public ClazzAttPersist(XAttributeX clazzAtt) {			
		super();
		this.setId(clazzAtt.getId());
		this.setName(clazzAtt.getName());
		this.setDataType(clazzAtt.getDataType().getId());
		this.setTypeCardinality(clazzAtt.getTypeCardinality().getName());
		this.setOrderAtt(clazzAtt.getOrderAtt());
	}

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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getTypeCardinality() {
		return typeCardinality;
	}

	public void setTypeCardinality(String typeCardinality) {
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

}
