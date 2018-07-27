package org.cendra.om.bo.clazz.model.persist;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.bo.clazz.model.ClassComponent;

public class ClassComponentPersist {

	private String id;
	private Boolean virtual;
	private String typeComponent;

	private String name;
	private String visibility;
	private Boolean finalClass;
	private Boolean abstractClass;
	private List<String> extendsClass = new ArrayList<String>();

	public ClassComponentPersist(ClassComponent classComponent) {
		id = classComponent.getId();
		virtual = classComponent.getVirtual();
		name = classComponent.getName();
		if (classComponent.getVisibility() != null) {
			visibility = classComponent.getVisibility().getName();
		}
		finalClass = classComponent.getFinalClass();
		abstractClass = classComponent.getAbstractClass();
		if (classComponent.getExtendsClass() != null) {
			for (ClassComponent extendClass : classComponent.getExtendsClass()) {
				extendsClass.add(extendClass.getId());
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

}
