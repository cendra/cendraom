package org.cendra.om.bo.clazz;

import org.cendra.om.bo.clazz.model.ClassComponent;
import org.cendra.om.persist.dao.ClassIfExistsDAO;

public class IfExistsClassBO {

	private ClassIfExistsDAO ifExistsClassDAO;

	public IfExistsClassBO(ClassIfExistsDAO ifExistsClassDAO) {
		super();
		this.ifExistsClassDAO = ifExistsClassDAO;
	}

	public boolean ifExistsClass(ClassComponent classComponent) throws Exception {

		return ifExistsClassDAO.ifExistsClass(classComponent.getName());
	}

}
