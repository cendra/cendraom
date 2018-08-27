package org.cendra.om.persist.dao;

import java.util.List;

import org.cendra.om.model.clazz.old.XClazzX;
import org.cendra.om.model.clazz.old.XTypeX;

public interface XClazzDAO {

	public XTypeX create(XTypeX type) throws Exception;

	public boolean ifExists(String name) throws Exception;

	public List<XClazzX> find() throws Exception;

	public XClazzX findById(String id) throws Exception;
	
	public XClazzX findByName(String id) throws Exception;

}
