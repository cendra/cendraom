package org.cendra.om.bo;

import java.util.List;

import org.cendra.om.model.clazz.old.XClazzX;
import org.cendra.om.persist.dao.XClazzDAO;

public class XClazzBOX extends XTypeBO {

	// public Type create() throws Exception {
	//
	// Clazz clazz = new Clazz();
	// clazz = clazzDAO.create(clazz);
	//
	// return clazz;
	//
	// }

	public XClazzBOX(XClazzDAO clazzDAO) {
		super(clazzDAO);		
	}

	public XClazzX create(XClazzX clazz) throws Exception {

		return (XClazzX) super.create(clazz);
	}

	public XClazzX findById(String id) throws Exception {
		return (XClazzX) super.findById(id);
	}

	public XClazzX findByName(String name) throws Exception {
		return (XClazzX) super.findByName(name);
	}

	public List<XClazzX> find() throws Exception {
		return super.find();
	}

}