package com.byteshaft.plumbopro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import static com.byteshaft.plumbopro.AppGlobals.PLUMBO_FREE;

public class Helpers {

    public static boolean isFreeVersionInstalled() {
        PackageManager packageManager = AppGlobals.getContext().getPackageManager();
        try {
            packageManager.getPackageInfo(PLUMBO_FREE, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static void launchApp(Context context) {
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(PLUMBO_FREE);
        context.startActivity(launchIntent);
    }

    private static void openPlayStoreToInstallFreeVersion(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.format("market://details?id=%s", PLUMBO_FREE)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void hideProIcon() {
        Context context = AppGlobals.getContext();
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, MainActivity.class);
        packageManager.setComponentEnabledSetting(
                componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
        );
    }

    public static void showNeedToDownloadFromPlayStoreDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Finish installation");
        builder.setMessage("For Plumbo to actually work," +
                " you need to have the free version installed. Press Ok to install.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openPlayStoreToInstallFreeVersion(activity);
                dialog.dismiss();
                activity.finish();
            }
        });
        builder.create();
        builder.show();
    }
}
