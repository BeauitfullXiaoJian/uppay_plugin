package com.plugin;

import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.unionpay.UPPayAssistEx;

public class UPPayPlugin extends CordovaPlugin {

    private static final String TAG = "UPPayPlugin";
    private CallbackContext mActiveCallbackContext;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        mActiveCallbackContext = callbackContext;
        if (action.equals("pay")) {
            Log.d(TAG, "调用支付方法");
            startPay(args.getString(0), "00");
        }
        if (action.equals("payTest")) {
            Log.d(TAG, "调用支付测试方法-不会发生真的交易");
            startPay(args.getString(0), "01");
        }
        return true;
    }

    private void startPay(String paySn, String serverMode) {
        // “00” – 银联正式环境
        // “01” – 银联测试环境，该环境中不发生真实交易
        cordova.setActivityResultCallback(this);
        UPPayAssistEx.startPay(cordova.getActivity(), null, null, paySn, serverMode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("payResult", intent.getStringExtra("pay_result"));
            jsonObject.put("resultData", intent.hasExtra("pay_result") ? intent.getStringExtra("pay_result") : "");
            mActiveCallbackContext.success(jsonObject);
            Log.d(TAG, jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "数据解析异常");
            mActiveCallbackContext.error("数据解析异常");
        }
    }
}