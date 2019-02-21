
package com.haka.zplblutooth;

import java.util.Map;
import java.util.HashMap;
import javax.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import com.zebra.sdk.comm.BluetoothConnectionInsecure;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;

import com.zebra.sdk.printer.PrinterLanguage;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;

public class RNZplblutoothModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  private BluetoothConnectionInsecure con;
  private static final boolean D =true;
  private String macAddress;
  private ZebraPrinter printer;

  public RNZplblutoothModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @ReactMethod
  public void checkConnection(String BtMacAddress, final Promise promise)
  {
    con = null;
    macAddress=BtMacAddress;
    sendLog("Trying Connect to "+BtMacAddress);
    con = new BluetoothConnectionInsecure(BtMacAddress, 30000, 15000);
    try{
      int to=con.getMaxTimeoutForRead();
      sendLog("Total Timeout "+ Integer.toString(to));
      con.open();
      boolean ok=con.isConnected();
      WritableMap params=Arguments.createMap();
      params.putBoolean("isConnected",ok);
      if(ok)
      {
        params.putString("macAddress",con.getMACAddress());
        params.putString("friendlyName",con.getFriendlyName());
        sendLog("Success Connect");
        con.close();
      }
      promise.resolve(params);
    }catch(ConnectionException ce){
      promise.reject("CONNECTION_EXCEPTION",ce);
      sendException(ce.getMessage());
    }
  }

  @ReactMethod
  public void sendZplCommand(String ZplData, final Promise promise)
  {
    if((con==null)&&(macAddress==""))
    {
      sendException("Connection Failed");
      promise.reject("CONNECTION_EXCEPTION", "macAddress empty string");
    }
    else if(con==null)
    {
      con = new BluetoothConnectionInsecure(macAddress,300000,15000);
      doWrite(ZplData, promise);
    }
    else{
      doWrite(ZplData, promise);
    }
    
  }

  private void doWrite(String ZplData, final Promise promise)
  {
    try{
      con.open();
      con.write(ZplData.getBytes());
      WritableMap params=Arguments.createMap();
      params.putBoolean("status",true);
      params.putString("Zpldata",ZplData);
      con.close();
      promise.resolve(params);
    }catch(ConnectionException ce){
      sendException(ce.getMessage());
      promise.reject("CONNECTION_EXCEPTION",ce);
    }
  }

  private void sendException(String message)
  {
    WritableMap params=Arguments.createMap();
    params.putString("message",message);
    sendEvent("LIB_EXCEPTION",params);
  }

  private void sendLog(String message)
  {
    WritableMap params=Arguments.createMap();
    params.putString("message",message);
    sendEvent("LIB_LOG",params);
  }

  private void sendEvent(String eventName, @Nullable WritableMap params)
  {
    if(this.reactContext.hasActiveCatalystInstance())
    {
      if(D) Log.d("RNZPLZB", "Sending Event: "+eventName);
      this.reactContext
          .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit(eventName, params);
    }
  }

  @Override
  public String getName() {
    return "RNZplblutooth";
  }
}