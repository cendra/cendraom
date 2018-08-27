package org.cendra.om.bo;

import org.cendra.om.model.clazz.old.XInterfazeX;
import org.cendra.om.persist.dao.XClazzDAO;

public class XInterfazeBOX extends XTypeBO {

	// public Type create() throws Exception {
	//
	// Clazz clazz = new Clazz();
	// clazz = clazzDAO.create(clazz);
	//
	// return clazz;
	//
	// }

	public XInterfazeBOX(XClazzDAO clazzDAO) {
		super(clazzDAO);		
	}

	public XInterfazeX create(XInterfazeX interfaze) throws Exception {

		return (XInterfazeX) super.create(interfaze);
	}

	public XInterfazeX findById(String id) throws Exception {
		return (XInterfazeX) super.findById(id);
	}

	public XInterfazeX findByName(String name) throws Exception {
		return (XInterfazeX) super.findByName(name);
	}

//	public List<Interfaze> find() throws Exception {
//		return super.find();
//	}

}