package org.cendra.om.x.old;

import org.cendra.om.model.clazz.old.XTypeX;

class IfExistsClassBO {

	private ClassIfExistsDAO ifExistsClassDAO;

	public IfExistsClassBO(ClassIfExistsDAO ifExistsClassDAO) {
		super();
		this.ifExistsClassDAO = ifExistsClassDAO;
	}

	public boolean ifExistsClass(XTypeX classComponent) throws Exception {

		return ifExistsClassDAO.ifExistsClass(classComponent.getName());
	}

}
