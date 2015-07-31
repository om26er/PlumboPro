package com.byteshaft.plumbopro;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Helpers.isFreeVersionInstalled()) {
            Helpers.launchApp(getApplicationContext());
            Helpers.hideProIcon();
            finish();
        } else {
            Helpers.showNeedToDownloadFromPlayStoreDialog(this);
        }
    }
}
