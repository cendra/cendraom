package org.cendra.om.model.clazz;

import java.util.ArrayList;
import java.util.List;

public class DataTypeInterfazeObject extends DataType implements
		DataTypeForInterfaze {

	private List<InterfazeAttribute> attributes = new ArrayList<InterfazeAttribute>();

	public List<InterfazeAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<InterfazeAttribute> attributes) {
		this.attributes = attributes;
	}

	public boolean addAttribute(InterfazeAttribute arg0) {
		return attributes.add(arg0);
	}

}
