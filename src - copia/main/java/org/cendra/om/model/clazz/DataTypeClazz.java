package org.cendra.om.model.clazz;

public class DataTypeClazz extends DataType implements DataTypeForInterfaze, DataTypeForClazz {
	
	protected ClazzAttribute _source;

	private Clazz clazz;

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;		
	}

}
