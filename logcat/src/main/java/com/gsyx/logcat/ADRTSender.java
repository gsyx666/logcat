package com.gsyx.logcat;
import android.content.Context;
import android.content.Intent;

public class ADRTSender {

    private static Context context;
    private static String debuggerPackageName;

    public static void onContext(Context context2, String str) {
        context = context2;
        debuggerPackageName = str;
    }

    public static void sendLogcatLines(String[] strArr) {

        Intent intent = new Intent();
        intent.setPackage(debuggerPackageName);
        intent.setAction("com.adrt.LOGCAT_ENTRIES");
        intent.putExtra("lines", strArr);
        context.sendBroadcast(intent);
    }

}
