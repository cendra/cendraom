package org.cendra.om.model.clazz.old;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.util.UtilDataTypes;

public class XClazzX extends XTypeX {

	private XClazzX extendsClazz;
	private boolean internalClazz;
	private List<XInterfazeX> implementsInterfaze = new ArrayList<XInterfazeX>();

	public XClazzX(boolean internalClazz) {
		super();
		this.internalClazz = internalClazz;
	}

	public XClazzX() {
		super();
	}

	public XClazzX getExtendsClazz() {
		if (internalClazz) {
			throw new IllegalArgumentException(
					"Las clases internas no pueden heredar de otra clase.");
		}
		return extendsClazz;
	}

	public void setExtendsClazz(XClazzX extendsClazz) {
		if (internalClazz) {
			throw new IllegalArgumentException(
					"Las clases internas no pueden heredar de otra clase.");
		}
		this.extendsClazz = extendsClazz;
	}

	public List<XInterfazeX> getImplementsInterfaze() {
		return implementsInterfaze;
	}

	public void setImplementsInterfaze(List<XInterfazeX> extendsInterfaze) {
		this.implementsInterfaze = extendsInterfaze;
	}

	public boolean addImplementInterfaze(XInterfazeX e) {

		return implementsInterfaze.add(e);
	}

	public boolean addAttInternalClazzList(String name) {
		return addAttInternalClazz(name, UtilDataTypes.INTERNAL_OBJECT_LIST);
	}

	public boolean addAttInternalClazz(String name) {
		return addAttInternalClazz(name, UtilDataTypes.INTERNAL_OBJECT);
	}

	public boolean addAttExternalLeftClazz(String name, XClazzX dataType) {
		return addAtt(name, dataType, UtilDataTypes.EXTERNAL_LEFT_OBJECT);
	}

	private boolean addAttInternalClazz(String name,
			TypeCardinality typeCardinality) {

		String className = name.substring(0, 1).toUpperCase()
				+ name.substring(1, name.length());

		XAttributeX attribute = new XAttributeX();
		attribute.setName(name);
		attribute.setDataType(UtilDataTypes.buildInternalClass(
				this.getPackagesName(), this.getSimpleName() + className));
		attribute.setOrderAtt(getAtts().size() + 1);
		attribute.setTypeCardinality(typeCardinality);

		return getAtts().add(attribute);

	}

	public boolean isInternalClazz() {
		return internalClazz;
	}

	public XClazzX getAttInternalClazz(String name) {

		for (XAttributeX attribute : getAtts()) {

			if (attribute.getName().trim().equals(name.trim())
					&& attribute.getDataType() instanceof XClazzX
					&& ((XClazzX) attribute.getDataType()).isInternalClazz() == true) {

				return (XClazzX) attribute.getDataType();

			}
		}

		return null;
	}

}
