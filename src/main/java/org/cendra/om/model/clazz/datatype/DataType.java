package org.cendra.om.model.clazz.datatype;

public abstract class DataType implements DataTypeArray {

	private Boolean array;

	public Boolean getArray() {
		if (array == null) {
			array = false;
		}
		return array;
	}

	public void setArray(Boolean array) {
		if (array == null) {
			array = false;
		}
		this.array = array;
	}

}
