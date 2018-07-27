package org.cendra.om.bo.classess;

import org.cendra.om.model.classes.ClassComponent;
import org.cendra.om.persist.dao.custom.IfExistsClassDAO;

public class IfExistsClassBO {

	private IfExistsClassDAO ifExistsClassDAO;

	public IfExistsClassBO(IfExistsClassDAO ifExistsClassDAO) {
		super();
		this.ifExistsClassDAO = ifExistsClassDAO;
	}

	public boolean ifExistsClass(ClassComponent classComponent) throws Exception {

		return ifExistsClassDAO.ifExistsClass(classComponent.getName());
	}

}
