package br.fk.admin.entity;

public final class AppConfiguration {

	private static final String appName = "Sistema - TFC";
	private static final String appNickName = "TFC";
	private static final String[] MODULES = { "DOCUMENTOS", "EVENTOS", "FREQUENCIAS", "MENSAGENS", "PROJETOS",
			"SEMESTRES", "USUARIOS" };

	public static String getAppName() {
		return appName;
	}

	public static String getAppname() {
		return appName;
	}

	public static String getAppnickname() {
		return appNickName;
	}

	public static String[] getModules() {
		return MODULES;
	}

}
