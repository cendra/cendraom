package org.cendra.om.persist.dao;

import java.util.List;

import org.cendra.om.model.clazz.Clazz;

public interface ClazzDAO {

	public Clazz create(Clazz clazz) throws Exception;

	public boolean ifExists(String name) throws Exception;

	public List<Clazz> find() throws Exception;

	public Clazz findById(String id) throws Exception;

}
