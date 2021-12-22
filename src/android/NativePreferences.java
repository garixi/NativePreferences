package com.cuoriilabs.nativepreferences;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;


/**
 * This class echoes a string called from JavaScript.
 */
public class NativePreferences extends CordovaPlugin {

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }else if (action.equals("read")) {
            String message = args.getString(0);
            this.read(message, callbackContext);
            return true;
        }
        else if (action.equals("removeKey")) {
            String message = args.getString(0);
            this.removeKey(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            Context context = cordova.getActivity().getApplicationContext();
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            String userData = settings.getString("app_user", "");

            callbackContext.success(userData);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void read(String key, CallbackContext callbackContext) {
        if (key != null && key.length() > 0) {

            Context context = cordova.getActivity().getApplicationContext();
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            String userData = settings.getString(key, "");

            callbackContext.success(userData);            
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void removeKey(String key, CallbackContext callbackContext) {
        if (key != null && key.length() > 0) {
            Context context = cordova.getActivity().getApplicationContext();
            SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.remove(key);
            editor.commit();

            callbackContext.success(key);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
