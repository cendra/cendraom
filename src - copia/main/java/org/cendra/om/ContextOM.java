package org.cendra.om;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.cendra.jdbc.DataSourceProperties;
import org.cendra.jdbc.DataSourceWrapper;
import org.cendra.om.bo.XClazzBOX;
import org.cendra.om.bo.XInterfazeBOX;
import org.cendra.om.persist.dao.impl.dbrms.pg.ClazzDAOPg;
import org.cendra.om.persist.dao.impl.dbrms.pg.XImplClassPgDAO;

public class ContextOM {

	public static final String ARG_NAME_PATH = "-PATH";
	public static final String ARG_NAME_PATH_JDBC = "-JDBC";
	public static final String ARG_NAME_SOURCE = "-SOURCE";

	public final static int SOURCE_PG = 1;

	private static ContextOM instance;

	private int source;
	private String path;
	private String pathJDBC;

	private DataSourceProperties dataSourceProperties;
	private DataSource dataSource;
	private DataSourceWrapper dataSourceWrapper;

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

				if (arg != null && arg.trim().startsWith(ARG_NAME_PATH)) {
					String[] tmp = arg.trim().split(ARG_NAME_PATH);
					if (tmp.length > 1) {
						String val = tmp[1].trim();
						if (val.length() > 0) {
							this.path = val;
						}
					}
				} else if (arg != null
						&& arg.trim().startsWith(ARG_NAME_PATH_JDBC)) {
					String[] tmp = arg.trim().split(ARG_NAME_PATH_JDBC);
					if (tmp.length > 1) {
						String val = tmp[1].trim();
						if (val.length() > 0) {
							this.pathJDBC = val;
						}
					}
				} else if (arg != null
						&& arg.trim().startsWith(ARG_NAME_SOURCE)) {
					String[] tmp = arg.trim().split(ARG_NAME_SOURCE);
					if (tmp.length > 1) {
						String val = tmp[1].trim();
						if (val.length() > 0) {
							this.source = Integer.valueOf(val);
						}
					}
				}
			}

			if (source == SOURCE_PG && pathJDBC != null) {

				Properties properties = loadProperties(pathJDBC);
				initContextDbPostgreSql(properties);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	private Properties loadProperties(String path) {

		String sep = "\n\n=================================================================================\n\n";

		Properties properties = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(path);

			properties.load(input);

			String json = "\n{\n";

			for (Enumeration<Object> e = properties.keys(); e.hasMoreElements();) {
				Object obj = e.nextElement();
				json += "\n\t\"" + obj + "\":"
						+ buildValue(properties.getProperty(obj.toString()))
						+ ",";
			}

			json = json.substring(0, json.length() - 1);

			json += "\n}";

			System.out.println(sep
					+ "[OK] Lectura de archivo de propiedades\n\n" + path
					+ "\n\nContenido:\n\n" + json + sep);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return properties;

	}

	private String buildValue(Object value) {

		if (value == null) {
			return null;
		}

		if (value instanceof Number) {
			return value.toString();
		}

		if (value instanceof Boolean) {
			return value.toString();
		}

		return "\"" + value + "\"";

	}

	private void initContextDbPostgreSql(Properties properties)
			throws Exception {

		String path = "jdbc.";

		dataSourceProperties = new DataSourceProperties();

		dataSourceProperties.setDriverClassName(properties.getProperty(path
				+ "driverClassName"));
		dataSourceProperties.setUrl(properties.getProperty(path + "url"));
		dataSourceProperties.setUserName(properties.getProperty(path
				+ "userName"));
		dataSourceProperties.setUserPassword(properties.getProperty(path
				+ "userPassword"));
		dataSourceProperties.setInitialSize(new Integer(properties
				.getProperty(path + "initialSize")));
		dataSourceProperties.setMaxActive(new Integer(properties
				.getProperty(path + "maxActive")));
		dataSourceProperties.setMaxIdle((new Integer(properties
				.getProperty(path + "maxIdle"))));
		dataSourceProperties.setValidationQuery(properties.getProperty(path
				+ "validationQuery"));
		dataSourceProperties.setVerbose((new Boolean(properties
				.getProperty(path + "verbose"))));

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(dataSourceProperties
				.getDriverClassName());
		basicDataSource.setUrl(dataSourceProperties.getUrl());
		basicDataSource.setUsername(dataSourceProperties.getUserName());
		basicDataSource.setPassword(dataSourceProperties.getUserPassword());
		basicDataSource.setInitialSize(dataSourceProperties.getInitialSize());
		basicDataSource.setMaxActive(dataSourceProperties.getMaxActive());
		basicDataSource.setMaxIdle(dataSourceProperties.getMaxIdle());
		basicDataSource.setValidationQuery(dataSourceProperties
				.getValidationQuery());

		dataSource = basicDataSource;

		dataSourceWrapper = new DataSourceWrapper(dataSource,
				dataSourceProperties);

	}

	// ---------------------------------------------------------

	// public IfExistsClassBO buildIfExistsClassBO() {
	//
	// if (source == SOURCE_PG) {
	// return new IfExistsClassBO(new ImplClassIfExistsPgDAO(
	// dataSourceWrapper));
	// }
	//
	// return new IfExistsClassBO(new ImplClassIfExistsFileJsonDAO(path,
	// new UtilSerializeObjects()));
	// }

	// public CreateClassBO buildCreateClassComponentBO() {
	// return new CreateClassBO(buildCreateObjectBO(), buildIfExistsClassBO(),
	// new UtilSerializeObjects());
	// }
	
	public ClazzDAOPg buildClazzBO() {
		return new ClazzDAOPg(dataSourceWrapper);
	}

	public XClazzBOX buildClazzBOx() {
		return new XClazzBOX(new XImplClassPgDAO(dataSourceWrapper));
	}
	
	public XInterfazeBOX buildInterfazeBO() {
		return new XInterfazeBOX(new XImplClassPgDAO(dataSourceWrapper));
	}

	// public FindClassBO buildFindClassBO() {
	// return new FindClassBO(buildFindObjectsBO());
	// }

	// ---------------------------------------------------------

	// public CreateObjectBO buildCreateObjectBO() {
	//
	// if (source == SOURCE_PG) {
	// return new CreateObjectBO(new ImplObjectCreatePgDAO(
	// dataSourceWrapper), new UtilSerializeObjects());
	// }
	//
	// return new CreateObjectBO(new ImplObjectCreateFileJsonDAO(path),
	// new UtilSerializeObjects());
	//
	// }

	// public FindObjectsBO buildFindObjectsBO() {
	//
	// if (source == SOURCE_PG) {
	// return new FindObjectsBO(new ImplObjectsFindDAO(dataSourceWrapper));
	// }
	//
	// return new FindObjectsBO(new ImplObjectsFindFileJsonDAO(path));
	// }

}
