package com.germainz.hodor;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import android.graphics.Paint;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;

public class Hodor implements IXposedHookZygoteInit {

	public static final String HODOOOR = "enabled";
	private static final String HODOR = "Hodor";
	private static final String HODOR_HODOR = "\\w+";
	private static final String HODOR_HODOR_HODOR = "setText";
	private static final String HODOR_HODOR_HODOR_HODOR = "android.view.GLES20Canvas";
	private static final String HODOR_HODOR_HODOR_HODOR_HODOR = "drawText";
	private static final String HODOR_HODOR_HODOR_HOOODOOOOOR = "setHint";
	private static final int HOOODOOOR = 0;
	private static final String HODOR_HODOR_HODOR_HODOR_HODOR_HODOR = "com.germainz.hodor";

	XSharedPreferences pref;

	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		pref = new XSharedPreferences(HODOR_HODOR_HODOR_HODOR_HODOR_HODOR,
				MainActivity.PREF);

		XC_MethodHook hodorHodor = new XC_MethodHook() {

			@Override
			protected void beforeHookedMethod(MethodHookParam hodorHodorHODOR)
					throws Throwable {
				pref.reload();
				if (pref.getBoolean(Hodor.HODOOOR, false)) {
					CharSequence hodorHodorHodor = (CharSequence) hodorHodorHODOR.args[HOOODOOOR];
					if (hodorHodorHodor != null) {
						String hodor = hodorHodorHodor.toString().replaceAll(
								HODOR_HODOR, HODOR);
						hodorHodorHODOR.args[HOOODOOOR] = hodor;
					}
				}
			}
		};

		findAndHookMethod(TextView.class, HODOR_HODOR_HODOR,
				CharSequence.class, TextView.BufferType.class, boolean.class,
				int.class, hodorHodor);
		findAndHookMethod(TextView.class, HODOR_HODOR_HODOR_HOOODOOOOOR,
				CharSequence.class, hodorHodor);
		findAndHookMethod(HODOR_HODOR_HODOR_HODOR, null,
				HODOR_HODOR_HODOR_HODOR_HODOR, String.class, float.class,
				float.class, Paint.class, hodorHodor);
	}

}
