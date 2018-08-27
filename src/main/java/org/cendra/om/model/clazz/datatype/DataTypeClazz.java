package org.cendra.om.model.clazz.datatype;

import org.cendra.om.model.clazz.Clazz;
import org.cendra.om.model.clazz.ClazzAttribute;

public class DataTypeClazz extends DataType implements DataTypeForInterfaze, DataTypeForClazz {
	
	public ClazzAttribute _source;

	private Clazz clazz;

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;		
	}

}
