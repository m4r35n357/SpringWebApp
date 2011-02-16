package uk.me.doitto.mypackage.globals;

public final class Constants {
	public static final String pathSeparator = System.getProperty("path.separator");
	public static final String lineSeparator = System.getProperty("line.separator");
	public static final String fileSeparator = System.getProperty("file.separator");
	
	public static final String userName = System.getProperty("user.name");
	public static final String userHome = System.getProperty("user.home");
	
	public static final String tmpDir = System.getProperty("java.io.tmpdir");
	
	public static final String validChars = "[a-zA-Z0-9\\.\\']";
}
