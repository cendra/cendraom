package org.cendra.om.x.old;

import org.cendra.om.model.clazz.Clazz;

class IfExistsClassBO {

	private ClassIfExistsDAO ifExistsClassDAO;

	public IfExistsClassBO(ClassIfExistsDAO ifExistsClassDAO) {
		super();
		this.ifExistsClassDAO = ifExistsClassDAO;
	}

	public boolean ifExistsClass(Clazz classComponent) throws Exception {

		return ifExistsClassDAO.ifExistsClass(classComponent.getName());
	}

}
