/*
Copyright 2011-2013 Pieter Pareit

This file is part of SwiFTP.

SwiFTP is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

SwiFTP is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with SwiFTP.  If not, see <http://www.gnu.org/licenses/>.
*/
package be.ppareit.swiftp;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.android.app.common.utils.ApplicationUtils;

import net.vrallev.android.cat.Cat;

import be.ppareit.swiftp.gui.FsWidgetProvider;
import lombok.val;

public class FtpInitializer {

    public static void onCreate() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FsService.ACTION_STARTED);
        intentFilter.addAction(FsService.ACTION_STOPPED);
        intentFilter.addAction(FsService.ACTION_FAILEDTOSTART);

        getAppContext().registerReceiver(new AutoConnect.ServerActionsReceiver(), intentFilter);
        getAppContext().registerReceiver(new NsdService.ServerActionsReceiver(), intentFilter);
        getAppContext().registerReceiver(new FsWidgetProvider(), intentFilter);

        AutoConnect.maybeStartService(getAppContext());
    }

    /**
     * @return the Context of this application
     */
    public static Context getAppContext() {
        return ApplicationUtils.getApplication().getApplicationContext();
    }

    /**
     * @return true if this is the free version
     */
    public static boolean isFreeVersion() {
        try {
            Context context = getAppContext();
            return context.getPackageName().contains("free");
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * @return true if the paid version is installed on this device
     */
    public static boolean isPaidVersionInstalled() {
        return isPackageInstalled("be.ppareit.swiftp");
    }

    /**
     * @param packageName is the name of the package to check
     * @return true if packageName is installed on this device
     */
    public static boolean isPackageInstalled(String packageName) {
        try {
            Context context = getAppContext();
            val packageManager = context.getPackageManager();
            packageManager.getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Get the version from the manifest.
     *
     * @return The version as a String.
     */
    public static String getVersion() {
        Context context = getAppContext();
        String packageName = context.getPackageName();
        try {
            PackageManager pm = context.getPackageManager();
            return pm.getPackageInfo(packageName, 0).versionName;
        } catch (NameNotFoundException e) {
            Cat.e("Unable to find the name " + packageName + " in the package");
            return null;
        }
    }

}