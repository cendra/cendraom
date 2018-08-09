package org.cendra.om.model.clazz;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilTypesVisibilityClass;

public class Clazz {

	private String id;
	private Boolean virtual = false;

	private String name;
	private TypeVisibilityClass visibility = UtilTypesVisibilityClass.PRIVATE;
	private Boolean finalClass = false;
	private Boolean abstractClass = false;
	private List<Clazz> extendsClass = new ArrayList<Clazz>();
	private List<ClazzAtt> atts = new ArrayList<ClazzAtt>();

	public String getId() {
		if (id != null) {
			this.id = id.trim();
		}
		return id;
	}

	public void setId(String id) {
		if (id != null) {
			this.id = id.trim();
		}
		this.id = id;
	}

	public Boolean getVirtual() {
		return virtual;
	}

	public void setVirtual(Boolean virtual) {
		if(virtual == null){
			virtual = false;
		}
		this.virtual = virtual;
	}

	public String getName() {
		if (name != null) {
			this.name = name.trim();
		}
		return name;
	}

	public void setName(String name) {
		if (name != null) {

			this.name = name.trim();

		}

		this.name = getPackagesName().toLowerCase() + "."
				+ this.getSimpleName(this.name);
	}

	public String[] getPackages() {
		if (this.getName() != null) {
			String[] items = this.getName().split("[.]");
			String[] packages = new String[items.length - 1];
			if (packages.length == 1) {
				return null;
			}
			for (int i = 0; i < packages.length; i++) {
				packages[i] = items[i];
			}

			return packages;
		}

		return null;
	}

	public String getPackagesName() {
		if (this.getName() != null) {
			String[] items = this.getName().split("[.]");
			String packagesName = "";
			if (items.length - 1 == 1) {
				return null;
			}
			for (int i = 0; i < items.length - 1; i++) {
				if (i == 0) {
					packagesName = items[i];
				} else {
					packagesName = packagesName + "." + items[i];
				}

			}

			return packagesName;
		}

		return null;
	}

	public String getSimpleName() {

		return getSimpleName(this.getName());
	}

	private String getSimpleName(String name) {

		if (name != null) {

			String[] items = name.split("[.]");

			if (items.length - 1 == 1) {

				return items[0].trim();
			}

			for (int i = items.length - 1; i < items.length;) {

				return items[i].trim();
			}

		}

		return null;
	}

	public TypeVisibilityClass getVisibility() {
		return visibility;
	}

	public void setVisibility(TypeVisibilityClass visibility) {
		this.visibility = visibility;
	}

	public Boolean getFinalClass() {
		if(finalClass == null){
			finalClass = false;
		}
		return finalClass;
	}

	public void setFinalClass(Boolean finalClass) {
		this.finalClass = finalClass;
	}

	public Boolean getAbstractClass() {
		if(abstractClass == null){
			abstractClass = false;
		}
		return abstractClass;
	}

	public void setAbstractClass(Boolean abstractClass) {
		this.abstractClass = abstractClass;
	}

	public List<Clazz> getExtendsClass() {

		return extendsClass;
	}

	public void setExtendsClass(List<Clazz> extendsClass) {

		this.extendsClass = extendsClass;
	}

	public boolean addExtendClass(Clazz e) {

		return extendsClass.add(e);

	}

	public boolean addAtt(String name) {
		return addAtt(name, UtilDataTypes.buildString());
	}

	public boolean addAtt(String name, Clazz dataType) {
		ClazzAtt clazzAtt = new ClazzAtt();
		clazzAtt.setName(name);
		clazzAtt.setDataType(dataType);
		clazzAtt.setOrderAtt(atts.size() + 1);

		return atts.add(clazzAtt);
	}

	public boolean addAtt(ClazzAtt e) {
		return atts.add(e);
	}

	public List<ClazzAtt> getAtts() {
		return atts;
	}

	public void setAtts(List<ClazzAtt> atts) {
		this.atts = atts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if(this.getId() != null){
			result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		} else if(this.getName() != null){
			result = prime * result + ((getName() == null) ? 0 : getName().hashCode());	
		}
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Clazz other = (Clazz) obj;

		if (this.getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!this.getName().equals(other.getName())) {
			return false;
		}
		
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId())) {
			return false;
		} 

		return true;
	}

	@Override
	public String toString() {
		return "Clazz [id=" + id + ", virtual=" + virtual + ", name=" + name
				+ ", visibility=" + visibility + ", finalClass=" + finalClass
				+ ", abstractClass=" + abstractClass + ", extendsClass="
				+ extendsClass + ", atts=" + atts + "]";
	}
	
	

}
