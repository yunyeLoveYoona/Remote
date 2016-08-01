package com.yun.remote;

import android.app.ActivityManager;
import android.content.Intent;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * @author yunye
 */
public class AppException implements UncaughtExceptionHandler {
	public static final String TAG = "CrashHandler";
	private static AppException instance;
	private UncaughtExceptionHandler mDefaultHandler;

	private AppException() {
		init();
	}

	public static AppException getInstance() {
		if (instance == null) {
			instance = new AppException();
		}
		return instance;
	}

	private void init() {
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		handleException(ex);
	}

	public static void exit() {
		int currentVersion = android.os.Build.VERSION.SDK_INT;
		if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.addCategory(Intent.CATEGORY_HOME);
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			MainApplication.getContext().startActivity(startMain);
			System.exit(0);
		} else {// android2.1
			ActivityManager am = (ActivityManager) MainApplication.getContext()
					.getSystemService(
							MainApplication.getContext().ACTIVITY_SERVICE);
			am.restartPackage(MainApplication.getContext().getPackageName());
		}
	}

	private boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		return true;
	}
}