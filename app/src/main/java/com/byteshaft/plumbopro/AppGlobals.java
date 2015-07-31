package com.byteshaft.plumbopro;

import android.app.Application;
import android.content.Context;

public class AppGlobals extends Application {

    public static final String PLUMBO_FREE = "com.byteshaft.callnote";
    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
