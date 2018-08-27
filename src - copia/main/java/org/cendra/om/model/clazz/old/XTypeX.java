package org.cendra.om.model.clazz.old;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.model.clazz.TypeVisibilityClass;
import org.cendra.om.util.UtilDataTypes;
import org.cendra.om.util.UtilTypesVisibilityClass;

public abstract class XTypeX {

	private String id;
	private Boolean virtual = false;

	private String name;
	private TypeVisibilityClass visibility = UtilTypesVisibilityClass.PRIVATE;
	private Boolean finalType = false;
	// private Boolean internalClass = false;
	// private Boolean abstractClass = false;
	// private List<Clazz> extendsClass = new ArrayList<Clazz>();
	private List<XAttributeX> atts = new ArrayList<XAttributeX>();

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
		if (virtual == null) {
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

	public Boolean getFinalType() {
		if (finalType == null) {
			finalType = false;
		}
		return finalType;
	}

	public void setFinalType(Boolean finalType) {
		this.finalType = finalType;
	}

	public boolean addAttBoolean(String name) {
		return addAtt(name, UtilDataTypes.buildBoolean(),
				UtilDataTypes.INTERNAL_OBJECT);
	}

	public boolean addAttString(String name) {
		return addAtt(name, UtilDataTypes.buildString(),
				UtilDataTypes.INTERNAL_OBJECT);
	}

	public boolean addAttDouble(String name) {
		return addAtt(name, UtilDataTypes.buildDouble(),
				UtilDataTypes.INTERNAL_OBJECT);
	}

	public boolean addAttDate(String name) {
		return addAtt(name, UtilDataTypes.buildDate(),
				UtilDataTypes.INTERNAL_OBJECT);
	}	

	protected boolean addAtt(String name, XTypeX dataType,
			TypeCardinality typeCardinality) {

		XAttributeX attribute = new XAttributeX();
		attribute.setName(name);
		attribute.setDataType(dataType);
		attribute.setOrderAtt(atts.size() + 1);
		attribute.setTypeCardinality(typeCardinality);

		return atts.add(attribute);
	}

	public List<XAttributeX> getAtts() {
		return atts;
	}

	public void setAtts(List<XAttributeX> atts) {
		this.atts = atts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this.getId() != null) {
			result = prime * result
					+ ((getId() == null) ? 0 : getId().hashCode());
		} else if (this.getName() != null) {
			result = prime * result
					+ ((getName() == null) ? 0 : getName().hashCode());
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

		XTypeX other = (XTypeX) obj;

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
		return "Type [id=" + id + ", virtual=" + virtual + ", name=" + name
				+ ", visibility=" + visibility + ", finalType=" + finalType
				+ ", atts=" + atts + "]";
	}

}
