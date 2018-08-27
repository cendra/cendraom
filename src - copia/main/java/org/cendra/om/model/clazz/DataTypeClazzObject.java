package org.cendra.om.model.clazz;

import java.util.ArrayList;
import java.util.List;

public class DataTypeClazzObject extends DataType implements DataTypeForClazz {

	private List<ClazzAttribute> attributes = new ArrayList<ClazzAttribute>();

	public List<ClazzAttribute> getAttributes() {
		return attributes;
	}

	// public void setAttributes(List<ClazzAttribute> attributes) {
	// this.attributes = attributes;
	// }

	public boolean addAttribute(ClazzAttribute source, ClazzAttribute e) {
		e._source = source;
		return attributes.add(e);
	}

	// -------------------------------------------------------

	public boolean addAttributeBoolean(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleBoolean());
	}

	public boolean addAttributeArrayBoolean(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleBoolean();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeString(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleString());
	}

	public boolean addAttributeArrayString(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleString();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeShort(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleShort());
	}

	public boolean addAttributeArrayShort(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleShort();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeInteger(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleInteger());
	}

	public boolean addAttributeArrayInteger(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleInteger();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeLong(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleLong());
	}

	public boolean addAttributeArrayLong(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleLong();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeFloat(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleFloat());
	}

	public boolean addAttributeArrayFloat(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleFloat();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeDouble(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleDouble());
	}

	public boolean addAttributeArrayDouble(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleDouble();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeBigDecimal(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleBigDecimal());
	}

	public boolean addAttributeArrayBigDecimal(ClazzAttribute source,
			String name) {
		DataTypeForClazz dt = new DataTypeSimpleBigDecimal();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeDate(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleDate());
	}

	public boolean addAttributeArrayDate(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleDate();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttTimestamp(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleTimestamp());
	}

	public boolean addAttributeArrayTimestamp(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleTimestamp();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeTime(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeSimpleTime());
	}

	public boolean addAttributeArrayTime(ClazzAttribute source, String name) {
		DataTypeForClazz dt = new DataTypeSimpleTime();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeObject(ClazzAttribute source, String name) {
		return addAttribute(source, name, new DataTypeClazzObject());
	}

	public boolean addAttributeArrayObject(ClazzAttribute source, String name) {
		DataTypeClazzObject dt = new DataTypeClazzObject();
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeClazz(ClazzAttribute source, String name,
			Clazz clazz) {
		DataTypeClazz dt = new DataTypeClazz();
		dt.setClazz(clazz);

		return addAttribute(source, name, dt);
	}

	public boolean addAttributeArrayClazz(ClazzAttribute source, String name,
			Clazz clazz) {
		DataTypeClazz dt = new DataTypeClazz();
		dt.setClazz(clazz);
		dt.setArray(true);

		return addAttribute(source, name, dt);
	}

	private boolean addAttribute(ClazzAttribute source, String name,
			DataTypeForClazz dataType) {

		ClazzAttribute attribute = new ClazzAttribute();
		attribute.setName(name);
		attribute.setDataType(dataType);
		attribute.setOrderAtt(attributes.size() + 1);

		return this.addAttribute(source, attribute);
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
