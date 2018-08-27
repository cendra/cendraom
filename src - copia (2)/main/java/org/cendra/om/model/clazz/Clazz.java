package org.cendra.om.model.clazz;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.util.UtilTypesVisibilityClass;

public class Clazz {

	private String id;
	private Boolean virtual;

	private Boolean extendable;
	private TypeVisibilityClass visibility = UtilTypesVisibilityClass.PRIVATE;
	private String name;
	private Clazz extendsFrom;
	private List<Interfaze> implementations = new ArrayList<Interfaze>();
	private String comment;

	private List<ClazzAttribute> attributes = new ArrayList<ClazzAttribute>();

	private String labelName;
	private String labelComment;

	private List<ClazzLabel> labels = new ArrayList<ClazzLabel>();

	public String getId() {

		if (id != null) {

			id = id.trim();

			if (id.length() == 0) {
				id = null;
			}
		}

		return id;
	}

	public void setId(String id) {

		if (id != null) {

			id = id.trim();

			if (id.length() == 0) {
				id = null;
			}
		}

		this.id = id;
	}

	public Boolean getVirtual() {
		if (virtual == null) {
			virtual = false;
		}
		return virtual;
	}

	public void setVirtual(Boolean virtual) {
		if (virtual == null) {
			virtual = false;
		}
		this.virtual = virtual;
	}

	public Boolean getExtendable() {
		if (extendable == null) {
			extendable = false;
		}
		return extendable;
	}

	public void setExtendable(Boolean extendable) {
		if (extendable == null) {
			extendable = false;
		}
		this.extendable = extendable;
	}

	public TypeVisibilityClass getVisibility() {
		return visibility;
	}

	public void setVisibility(TypeVisibilityClass visibility) {
		this.visibility = visibility;
	}

	public String getName() {

		if (name != null) {

			name = name.trim();

			if (name.length() == 0) {
				name = null;
			}
		}

		return name;
	}

	public void setName(String name) {

		if (name != null) {

			name = name.trim();

			if (name.length() == 0) {
				name = null;
			}
		}

		this.name = name;

		this.name = getPackagesName().toLowerCase() + "."
				+ this.getSimpleName(this.name);
	}

	public Clazz getExtendsFrom() {
		return extendsFrom;
	}

	public void setExtendsFrom(Clazz extendsFrom) {
		this.extendsFrom = extendsFrom;
	}

	public List<Interfaze> getImplementations() {
		return implementations;
	}

	public void setImplementations(List<Interfaze> implementations) {
		this.implementations = implementations;
	}

	public void addImplementation(Interfaze element) {
		implementations.add(element);
	}

	public String getComment() {

		if (comment != null) {

			comment = comment.trim();

			if (comment.length() == 0) {
				comment = null;
			}
		}

		return comment;
	}

	public void setComment(String comment) {

		if (comment != null) {

			comment = comment.trim();

			if (comment.length() == 0) {
				comment = null;
			}
		}

		this.comment = comment;
	}

	public List<ClazzAttribute> getAttributes() {
		return attributes;
	}

	// public void setAttributes(List<ClazzAttribute> attributes) {
	// this.attributes = attributes;
	// }

	public boolean addAttribute(ClazzAttribute e) {
		e._clazz = this;
		return attributes.add(e);
	}

	public String getLabelName() {

		if (labelName != null) {

			labelName = labelName.trim();

			if (labelName.length() == 0) {
				labelName = null;
			}
		}

		return labelName;
	}

	public void setLabelName(String labelName) {

		if (labelName != null) {

			labelName = labelName.trim();

			if (labelName.length() == 0) {
				labelName = null;
			}
		}

		this.labelName = labelName;
	}

	public String getLabelComment() {

		if (labelComment != null) {

			labelComment = labelComment.trim();

			if (labelComment.length() == 0) {
				labelComment = null;
			}
		}

		return labelComment;
	}

	public void setLabelComment(String labelComment) {

		if (labelComment != null) {

			labelComment = labelComment.trim();

			if (labelComment.length() == 0) {
				labelComment = null;
			}
		}

		this.labelComment = labelComment;
	}

	public List<ClazzLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<ClazzLabel> labels) {
		this.labels = labels;
	}

	// -------------------------------------------------------

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

	// -------------------------------------------------------

	public boolean addAttributeBoolean(String name) {
		return addAttribute(name, new DataTypeSimpleBoolean());
	}

	public boolean addAttributeArrayBoolean(String name) {
		DataTypeForClazz dt = new DataTypeSimpleBoolean();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeString(String name) {
		return addAttribute(name, new DataTypeSimpleString());
	}

	public boolean addAttributeArrayString(String name) {
		DataTypeForClazz dt = new DataTypeSimpleString();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeShort(String name) {
		return addAttribute(name, new DataTypeSimpleShort());
	}

	public boolean addAttributeArrayShort(String name) {
		DataTypeForClazz dt = new DataTypeSimpleShort();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeInteger(String name) {
		return addAttribute(name, new DataTypeSimpleInteger());
	}

	public boolean addAttributeArrayInteger(String name) {
		DataTypeForClazz dt = new DataTypeSimpleInteger();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeLong(String name) {
		return addAttribute(name, new DataTypeSimpleLong());
	}

	public boolean addAttributeArrayLong(String name) {
		DataTypeForClazz dt = new DataTypeSimpleLong();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeFloat(String name) {
		return addAttribute(name, new DataTypeSimpleFloat());
	}

	public boolean addAttributeArrayFloat(String name) {
		DataTypeForClazz dt = new DataTypeSimpleFloat();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeDouble(String name) {
		return addAttribute(name, new DataTypeSimpleDouble());
	}

	public boolean addAttributeArrayDouble(String name) {
		DataTypeForClazz dt = new DataTypeSimpleDouble();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeBigDecimal(String name) {
		return addAttribute(name, new DataTypeSimpleBigDecimal());
	}

	public boolean addAttributeArrayBigDecimal(String name) {
		DataTypeForClazz dt = new DataTypeSimpleBigDecimal();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeDate(String name) {
		return addAttribute(name, new DataTypeSimpleDate());
	}

	public boolean addAttributeArrayDate(String name) {
		DataTypeForClazz dt = new DataTypeSimpleDate();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeTimestamp(String name) {
		return addAttribute(name, new DataTypeSimpleTimestamp());
	}

	public boolean addAttributeArrayTimestamp(String name) {
		DataTypeForClazz dt = new DataTypeSimpleTimestamp();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeTime(String name) {
		return addAttribute(name, new DataTypeSimpleTime());
	}

	public boolean addAttributeArrayTime(String name) {
		DataTypeForClazz dt = new DataTypeSimpleTime();
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	public boolean addAttributeClazz(String name, Clazz clazz) {
		DataTypeClazz dt = new DataTypeClazz();
		dt.setClazz(clazz);

		return addAttribute(name, dt);
	}

	public boolean addAttributeArrayClazz(String name, Clazz clazz) {
		DataTypeClazz dt = new DataTypeClazz();
		dt.setClazz(clazz);
		dt.setArray(true);

		return addAttribute(name, dt);
	}

	private boolean addAttribute(String name, DataTypeForClazz dataType) {

		ClazzAttribute attribute = new ClazzAttribute();
		attribute.setName(name);
		attribute.setDataType(dataType);
		attribute.setOrderAtt(attributes.size() + 1);

		return this.addAttribute(attribute);
	}

	public ClazzAttribute getAttribute(String name) {

		if (name == null) {
			throw new IllegalArgumentException("name is null.");
		}
		name = name.trim();
		if (name.length() == 0) {
			throw new IllegalArgumentException("name is empty.");
		}

		for (ClazzAttribute attribute : this.getAttributes()) {

			if (attribute.getName().equals(name)) {

				return attribute;

			}
		}

		return null;
	}

} // END
