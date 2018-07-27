package org.cendra.om;

import org.cendra.om.bo.classess.CreateClassBO;
import org.cendra.om.bo.classess.IfExistsClassBO;
import org.cendra.om.bo.core.CreateObjectBO;
import org.cendra.om.bo.core.ListObjectsBO;
import org.cendra.om.persist.dao.CreateObjectFileJsonDAO;
import org.cendra.om.persist.dao.ListObjectsFileJsonDAO;
import org.cendra.om.persist.dao.custom.IfExistsClassFileJsonDAO;
import org.cendra.om.util.SerializeObjects;

public class ContextOM {

	private static ContextOM instance;

	private String path;

	public static ContextOM getInstance(String[] args) {
		if (instance == null) {
			instance = new ContextOM(args);
		}

		return instance;
	}

	public static ContextOM getInstance() {

		return instance;
	}

	private ContextOM(String[] args) {
		super();

		try {

			for (String arg : args) {
				if (arg != null && arg.trim().startsWith("-PATH")) {
					String[] tmp = arg.trim().split("-PATH");
					if (tmp.length > 1) {
						String path = tmp[1].trim();
						if (path.length() > 0) {
							this.path = path;
							break;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public CreateObjectBO buildCreateObjectBO() {

		return new CreateObjectBO(new CreateObjectFileJsonDAO(path), new SerializeObjects());

	}

	public ListObjectsBO buildListObjectsBO() {
		return new ListObjectsBO(new ListObjectsFileJsonDAO(path));
	}

	public IfExistsClassBO buildIfExistsClassBO() {
		return new IfExistsClassBO(new IfExistsClassFileJsonDAO(path));
	}

	public CreateClassBO buildCreateClassComponentBO() {
		return new CreateClassBO(buildCreateObjectBO(), buildIfExistsClassBO(), new SerializeObjects());
	}

}
