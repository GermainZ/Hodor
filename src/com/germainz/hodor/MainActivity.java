package com.germainz.hodor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class MainActivity extends PreferenceActivity {

	public static Context c;
	public static final String PREF = "pref";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		c = this.getApplicationContext();
		if (savedInstanceState == null)
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, new PrefFragment()).commit();
	}

	public static class PrefFragment extends PreferenceFragment implements
			OnSharedPreferenceChangeListener {
		@SuppressWarnings("deprecation")
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.getPreferenceManager().setSharedPreferencesName(PREF);
			this.getPreferenceManager().setSharedPreferencesMode(
					Context.MODE_WORLD_READABLE);
			addPreferencesFromResource(R.xml.pref);
			getPreferenceScreen().getSharedPreferences()
					.registerOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onSharedPreferenceChanged(
				SharedPreferences sharedPreferences, String key) {
			killApps(true);// maybe an option in the future?
		}
	}

	public static void killApps(boolean systemUI) {
		List<ApplicationInfo> packages;
		PackageManager pm;
		pm = c.getPackageManager();
		packages = pm.getInstalledApplications(0);

		ActivityManager mActivityManager = (ActivityManager) c
				.getSystemService(Context.ACTIVITY_SERVICE);

		for (ApplicationInfo packageInfo : packages)
			mActivityManager.killBackgroundProcesses(packageInfo.packageName);

		if (systemUI) {
			Process su = null;
			try {
				su = Runtime.getRuntime().exec("su");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (su != null) {
				try {
					DataOutputStream os = new DataOutputStream(
							su.getOutputStream());
					os.writeBytes("pkill com.android.systemui \n");
					os.flush();
					os.writeBytes("exit\n");
					os.flush();
					su.waitFor();

				} catch (IOException e) {
					e.printStackTrace();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
