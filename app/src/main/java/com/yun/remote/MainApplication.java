package com.yun.remote;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
	private static MainApplication appInstance;
	public boolean isFinsh = false;

	@Override
	public void onCreate() {
		super.onCreate();
		setAppInstance(this);
		AppException.getInstance();

	}

	

	public static MainApplication getAppInstance() {
		return appInstance;
	}

	public static Context getContext() {
		return getAppInstance().getApplicationContext();
	}

	private static void setAppInstance(MainApplication appInstance) {
		MainApplication.appInstance = appInstance;
	}

}
