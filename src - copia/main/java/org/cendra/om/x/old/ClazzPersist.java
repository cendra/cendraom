package org.cendra.om.x.old;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.model.clazz.old.XAttributeX;
import org.cendra.om.model.clazz.old.XTypeX;

class ClazzPersist {

	private String id;
	private Boolean virtual;
	private String typeComponent;

	private String name;
	private String visibility;
	private Boolean finalClass;
	private Boolean abstractClass;
	private List<String> extendsClass = new ArrayList<String>();
	private List<ClazzAttPersist> atts = new ArrayList<ClazzAttPersist>();

	public ClazzPersist(XTypeX classComponent) {
		id = classComponent.getId();
		virtual = classComponent.getVirtual();
		name = classComponent.getName();
		if (classComponent.getVisibility() != null) {
			visibility = classComponent.getVisibility().getName();
		}
		finalClass = classComponent.getFinalType();
//		abstractClass = classComponent.getAbstractClass();
//		if (classComponent.getExtendsClass() != null) {
//			for (Clazz extendClass : classComponent.getExtendsClass()) {
//				extendsClass.add(extendClass.getId());
//			}
//		}		
		if (classComponent.getAtts() != null) {
			for (XAttributeX clazzAtt : classComponent.getAtts()) {
				atts.add(new ClazzAttPersist(clazzAtt));
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getVirtual() {
		return virtual;
	}

	public void setVirtual(Boolean virtual) {
		this.virtual = virtual;
	}

	public String getTypeComponent() {
		return typeComponent;
	}

	public void setTypeComponent(String typeComponent) {
		this.typeComponent = typeComponent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Boolean getFinalClass() {
		return finalClass;
	}

	public void setFinalClass(Boolean finalClass) {
		this.finalClass = finalClass;
	}

	public Boolean getAbstractClass() {
		return abstractClass;
	}

	public void setAbstractClass(Boolean abstractClass) {
		this.abstractClass = abstractClass;
	}

	public List<String> getExtendsClass() {
		return extendsClass;
	}

	public void setExtendsClass(List<String> extendsClass) {
		this.extendsClass = extendsClass;
	}

	public List<ClazzAttPersist> getAtts() {
		return atts;
	}

	public void setAtts(List<ClazzAttPersist> atts) {
		this.atts = atts;
	}

}
