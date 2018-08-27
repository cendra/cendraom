package org.cendra.om.model.clazz.old;

import java.util.ArrayList;
import java.util.List;

public class XInterfazeX extends XTypeX {

	private List<XInterfazeX> extendsInterfaze = new ArrayList<XInterfazeX>();

	public List<XInterfazeX> getExtendsInterfaze() {
		return extendsInterfaze;
	}

	public void setExtendsInterfaze(List<XInterfazeX> extendsInterfaze) {
		this.extendsInterfaze = extendsInterfaze;
	}

	public boolean addExtendInterfaze(XInterfazeX e) {

		return extendsInterfaze.add(e);
	}

}
