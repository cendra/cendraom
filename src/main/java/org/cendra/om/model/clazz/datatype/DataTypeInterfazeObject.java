package org.cendra.om.model.clazz.datatype;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.model.clazz.interfaze.InterfazeAttribute;

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
