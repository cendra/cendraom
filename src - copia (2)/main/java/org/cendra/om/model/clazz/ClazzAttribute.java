package org.cendra.om.model.clazz;

import java.util.ArrayList;
import java.util.List;

public class ClazzAttribute {

	private String id;

	private String code;
	private Integer orderAtt;
	private String name;
	private String comment;

	private DataTypeForClazz dataType;

	private String labelName;
	private String labelComment;

	private List<ClazzAttributeLabel> labels = new ArrayList<ClazzAttributeLabel>();

	protected Clazz _clazz;
	protected ClazzAttribute _source;

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

	public String getCode() {

		if (code != null) {

			code = code.trim().toLowerCase();

			if (code.length() == 0) {
				code = null;
			}
		}

		return code;
	}

	public void setCode(String code) {

		if (code != null) {

			code = code.trim().toLowerCase();

			if (code.length() == 0) {
				code = null;
			}
		}

		this.code = code;
	}

	public Integer getOrderAtt() {
		if (orderAtt == null) {
			orderAtt = 0;
		}

		return orderAtt;
	}

	public void setOrderAtt(Integer orderAtt) {
		if (orderAtt == null) {
			orderAtt = 0;
		}

		this.orderAtt = orderAtt;
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

	public DataTypeForClazz getDataType() {
		return dataType;
	}

	public void setDataType(DataTypeForClazz dataType) {
		this.dataType = dataType;

		if (this.dataType instanceof DataTypeClazz) {

			((DataTypeClazz) dataType)._source = this;

		}
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

	public List<ClazzAttributeLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<ClazzAttributeLabel> labels) {
		this.labels = labels;
	}

	public boolean addLabel(ClazzAttributeLabel e) {
		return labels.add(e);
	}

	// -------------------------------------------------------
	
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

} // END
