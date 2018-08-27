package org.cendra.jdbc;

public class DataSourceMetaData {

	public String url = "unknown";
	public String userName = "unknown";
	public String databaseProductName = "unknown";
	public String databaseProductVersion = "unknown";
	public String driverName = "unknown";
	public String driverVersion = "unknown";
	public int jDBCMajorVersion = -1;
	public int jDBCMinorVersion = -1;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDatabaseProductName() {
		return databaseProductName;
	}

	public void setDatabaseProductName(String databaseProductName) {
		this.databaseProductName = databaseProductName;
	}

	public String getDatabaseProductVersion() {
		return databaseProductVersion;
	}

	public void setDatabaseProductVersion(String databaseProductVersion) {
		this.databaseProductVersion = databaseProductVersion;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	public int getjDBCMajorVersion() {
		return jDBCMajorVersion;
	}

	public void setjDBCMajorVersion(int jDBCMajorVersion) {
		this.jDBCMajorVersion = jDBCMajorVersion;
	}

	public int getjDBCMinorVersion() {
		return jDBCMinorVersion;
	}

	public void setjDBCMinorVersion(int jDBCMinorVersion) {
		this.jDBCMinorVersion = jDBCMinorVersion;
	}

	public String toString() {
		return getDatabaseProductName() + " " + getDatabaseProductVersion()
				+ " " + getDriverName() + " " + getDriverVersion();
	}

	public String toJson() {
		String json = "\n{\n";

		json += "\n\t\"url\":" + buildValue(url) + ",";
		json += "\n\t\"userName\":" + buildValue(userName) + ",";
		json += "\n\t\"databaseProductName\":"
				+ buildValue(databaseProductName) + ",";
		json += "\n\t\"databaseProductVersion\":"
				+ buildValue(databaseProductVersion) + ",";
		json += "\n\t\"driverName\":" + buildValue(driverName) + ",";
		json += "\n\t\"driverVersion\":" + buildValue(driverVersion) + ",";
		json += "\n\t\"jDBCMajorVersion\":" + buildValue(jDBCMajorVersion)
				+ ",";
		json += "\n\t\"jDBCMinorVersion\":" + buildValue(jDBCMinorVersion)
				+ "";

		json += "\n}";

		return json;
	}

	private String buildValue(Object value) {

		if (value == null) {
			return null;
		}
		
		if(value instanceof Number){
			return value.toString();
		}
		
		if(value instanceof Boolean){
			return value.toString();
		}

		return "\"" + value + "\"";

	}

}
