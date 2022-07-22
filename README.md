package com.fiserv.dps.mobile.zelleplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.fiserv.dps.mobile.sdk.bridge.zelleview.BridgeView;
import com.fiserv.dps.mobile.sdk.interfaces.GenericTag;

/**
 * This class echoes a string called from JavaScript.
 */
public class ZellePlugin extends CordovaPlugin implements GenericTag {
  CallbackContext   callbackContext1;
    @Override
    public boolean execute(String action, JSONArray args,CallbackContext   callbackContext) throws JSONException {
        Context context = cordova.getActivity().getApplicationContext();
                if(action.equals("zelle_activity")) {
                    String  message;
                    String duration;
                  BridgeView.genericTag= this;
                  callbackContext1=callbackContext;
                    try {

                        JSONObject options = args.getJSONObject(0);
                        Intent intent = new Intent(context, ZelleActivity.class);
                        intent.putExtra("ZelleObject", options.toString());

                        this.cordova.startActivityForResult(this,intent,10);
                  // this.coolMethod("ccccccccccccdfewgeeg", callbackContext);

                    } catch (JSONException e) {
                        callbackContext.error("Error encountered: " + e.getMessage());
                        return false;
                    }
                    return true;
                }
                return false;
    }



  public void coolMethod(String message, CallbackContext callbackContext) {

        if (message != null && message.length() > 0) {
            callbackContext1.success(message);
          //Toast.makeText(this.cordova.getActivity(), "kkkk", Toast.LENGTH_SHORT).show();
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

  @Override
  public void sessionTag(@NonNull String s) {
    this.coolMethod("ccccccccccccdfewgeeg", callbackContext1);
  }
}



var success = function(message) {
               console.log(message);
            }
            var failure = function() {
                    alert("Error calling Hello Plugin");
                }


        if (window.cordova && window.cordova.plugins && window.cordova.plugins.HelloWorld) {
          //  window.cordova.plugins.HelloWorld.nativeToast();
            ZellePlugin.zelle_activity(person ,success,failure);
        }

