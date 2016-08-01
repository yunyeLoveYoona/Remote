package com.yun.remote;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dell on 2016/8/1.
 */
public class RemoteActivity extends Activity {
    private Activity remote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        remote = (Activity) Remote.get(getIntent().getStringExtra("remote"));
        if (remote != null) {
            attachRemote();
            remote.setTheme(R.style.AppTheme);
            createRemote(savedInstanceState);
            setContentView(getRootView(remote));
        }

    }

    private void createRemote(Bundle bundle) {
        Class thisClass = null;
        try {
            thisClass = Class.forName("android.app.Activity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Method method = thisClass.getDeclaredMethod("onCreate", Bundle.class);
            method.invoke(remote, bundle);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void attachRemote() {
        Class thisClass = null;
        try {
            thisClass = Class.forName("android.app.Activity");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object aThread = null;
        Instrumentation instr = null;
        IBinder token = null;
        int ident = 0;
        Application application = null;
        Intent intent = null;
        ActivityInfo info = null;
        CharSequence title = null;
        Activity parent = null;
        String id = null;
        Object lastNonConfigurationInstances = null;
        Configuration config = null;
        String referrer = null;
        Object voiceInteractor = null;
        Field[] fields = thisClass.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("mMainThread")) {
                    aThread = field.get(this);
                } else if (field.getName().equals("mInstrumentation")) {
                    instr = (Instrumentation) field.get(this);
                } else if (field.getName().equals("mToken")) {
                    token = (IBinder) field.get(this);
                } else if (field.getName().equals("mIdent")) {
                    ident = (int) field.get(this);
                } else if (field.getName().equals("mApplication")) {
                    application = (Application) field.get(this);
                } else if (field.getName().equals("mIntent")) {
                    intent = (Intent) field.get(this);
                } else if (field.getName().equals("mReferrer")) {
                    referrer = (String) field.get(this);
                } else if (field.getName().equals("mActivityInfo")) {
                    info = (ActivityInfo) field.get(this);
                } else if (field.getName().equals("mTitle")) {
                    title = (CharSequence) field.get(this);
                } else if (field.getName().equals("mParent")) {
                    parent = (Activity) field.get(this);
                } else if (field.getName().equals("mEmbeddedID")) {
                    id = (String) field.get(this);
                } else if (field.getName().equals("mLastNonConfigurationInstances")) {
                    lastNonConfigurationInstances = (Object) field.get(this);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Method method = null;
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                method = thisClass.getDeclaredMethod("attach", new Class[]{Context.class, Class.forName("android.app.ActivityThread"), Instrumentation.class,
                        IBinder.class, int.class, Application.class, Intent.class, ActivityInfo.class, CharSequence.class
                        , Activity.class, String.class, Class.forName("android.app.Activity$NonConfigurationInstances"), Configuration.class,
                        String.class, Class.forName("com.android.internal.app.IVoiceInteractor")});
                method.setAccessible(true);
                method.invoke(remote, getBaseContext(), aThread, instr, token, ident, application, intent, info, title, parent, id, lastNonConfigurationInstances, config, referrer, voiceInteractor);
            } else {
                method = thisClass.getDeclaredMethod("attach", new Class[]{Context.class, Class.forName("android.app.ActivityThread"), Instrumentation.class,
                        IBinder.class, int.class, Application.class, Intent.class, ActivityInfo.class, CharSequence.class
                        , Activity.class, String.class, Class.forName("android.app.Activity$NonConfigurationInstances"), Configuration.class,
                        Class.forName("com.android.internal.app.IVoiceInteractor")});
                method.setAccessible(true);
                method.invoke(remote, getBaseContext(), aThread, instr, token, ident, application, intent, info, title, parent, id, lastNonConfigurationInstances, config, voiceInteractor);
            }


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getRootView();
    }

    @Override
    protected void onDestroy() {
        if (remote != null) {
            Class thisClass = null;
            try {
                thisClass = Class.forName("android.app.Activity");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Method method = thisClass.getDeclaredMethod("onDestroy");
                method.invoke(remote);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

}
