package org.cendra.om.model.classes;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.util.TypesVisibilityClass;

public class ClassComponent {

	private String id;
	private Boolean virtual = false;

	private String name;
	private TypeVisibilityClass visibility = TypesVisibilityClass.PRIVATE;
	private Boolean finalClass = false;
	private Boolean abstractClass = false;
	private List<ClassComponent> extendsClass = new ArrayList<ClassComponent>();

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

		this.name = getPackagesName().toLowerCase() + "." + this.getSimpleName(this.name);
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

	public List<ClassComponent> getExtendsClass() {

		return extendsClass;
	}

	public void setExtendsClass(List<ClassComponent> extendsClass) {

		this.extendsClass = extendsClass;
	}

	public boolean addExtendClass(ClassComponent e) {

		return extendsClass.add(e);

	}

	@Override
	public String toString() {
		return "ClassComponent [id=" + id + ", virtual=" + virtual + ", name=" + name + ", visibility=" + visibility
				+ ", finalClass=" + finalClass + ", abstractClass=" + abstractClass + ", extendsClass=" + extendsClass
				+ "]";
	}

}
