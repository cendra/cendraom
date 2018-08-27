package org.cendra.om.model.clazz.datatype;

import org.cendra.om.model.clazz.interfaze.Interfaze;

public class DataTypeInterfaze extends DataType implements
		DataTypeForInterfaze, DataTypeForClazz {

	private Interfaze interfaze;

	public Interfaze getInterfaze() {
		return interfaze;
	}

	public void setInterfaze(Interfaze interfaze) {
		this.interfaze = interfaze;
	}

}
