package com.gsyx.logcat;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ADRTLogCatReader implements Runnable {
    private static Context context;

    public static void onContext(Context context2, String str) {

        String str2 = str;
        if (context == null) {
            context = context2.getApplicationContext();
            if (0 != (context2.getApplicationInfo().flags & 2)) {
                try {
                    PackageInfo packageInfo = context2.getPackageManager().getPackageInfo(str2, 128);
                    ADRTSender.onContext(context, str2);
                    new Thread((Runnable)new ADRTLogCatReader(), "LogCat").start();
                } catch (PackageManager.NameNotFoundException e) {
                    return;
                }
            }
        }
    }

//
//    @Override
//    public void run() {
//        BufferedReader bufferedReader;
//        Reader reader;
//        try {
//            bufferedReader = new BufferedReader((Reader)new InputStreamReader(Runtime.getRuntime().exec("logcat -v threadtime").getInputStream()), 20);          
//            BufferedReader bufferedReader2 = bufferedReader;
//            Object obj = "";
//            while (true) {
//                String readLine = bufferedReader2.readLine();
//                String str = readLine;
//                if (readLine != null) {
//                    ADRTSender.sendLogcatLines(new String[]{str});
//                } else {
//                    return;
//                }
//            }
//        } catch (IOException e) {
//            IOException iOException = e;
//        }
//    }
    
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader((Reader)new InputStreamReader(Runtime.getRuntime().exec("logcat -v threadtime").getInputStream()), 20);
            do {
                String string2;
                if ((string2 = bufferedReader.readLine()) == null) {
                    return;
                }
                ADRTSender.sendLogcatLines(new String[]{string2});
            } while (true);
        }
        catch (IOException iOException) {
            return;
        }
    }


}
