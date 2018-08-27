package org.cendra.om.model.clazz.interfaze;

import java.util.ArrayList;
import java.util.List;

import org.cendra.om.model.clazz.ClazzAttribute;
import org.cendra.om.model.clazz.TypeVisibilityClass;
import org.cendra.om.util.UtilTypesVisibilityClass;

public class Interfaze {

	private String id;
	private Boolean virtual;

	private TypeVisibilityClass visibility = UtilTypesVisibilityClass.PRIVATE;
	private String name;
	private List<Interfaze> implementations = new ArrayList<Interfaze>();
	private String comment;

	private List<ClazzAttribute> attributes = new ArrayList<ClazzAttribute>();

	private String labelName;
	private String labelComment;

	private List<InterfazLabel> labels = new ArrayList<InterfazLabel>();

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

	public void setAttributes(List<ClazzAttribute> attributes) {
		this.attributes = attributes;
	}

	public boolean addAttribute(ClazzAttribute e) {
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

	public List<InterfazLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<InterfazLabel> labels) {
		this.labels = labels;
	}

}
