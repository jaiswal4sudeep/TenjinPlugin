package com.adzvortex.tenjin_plugin;

import android.content.Context;
import androidx.annotation.NonNull;
import com.tenjin.android.TenjinSDK;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TenjinPlugin implements FlutterPlugin, MethodCallHandler {
  private MethodChannel channel;
  private Context context;
  private TenjinSDK instance;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "tenjin_plugin");
    channel.setMethodCallHandler(this);
    context = flutterPluginBinding.getApplicationContext();
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    switch (call.method) {
      case "init":
        init(call, result);
        break;
      case "optIn":
        instance.optIn();
        result.success(null);
        break;
      case "optOut":
        instance.optOut();
        result.success(null);
        break;
      case "optInParams":
        optInParams(call, result);
        break;
      case "optOutParams":
        optOutParams(call, result);
        break;
      case "connect":
        connect(call, result);
        break;
      case "transaction":
        transaction(call, result);
        break;
      case "eventWithName":
        eventWithName(call, result);
        break;
      case "eventWithNameAndValue":
        eventWithNameAndValue(call, result);
        break;
      case "appendAppSubversion":
        appendAppSubversion(call, result);
        break;
      case "setCustomerUserId":
        setCustomerUserId(call, result);
        break;
      case "getCustomerUserId":
        getCustomerUserId(call, result);
        break;
      case "getAnalyticsInstallationId":
        getAnalyticsInstallationId(call, result);
        break;
      default:
        result.notImplemented();
        break;
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

  private void init(MethodCall call, Result result) {
    Map<String, Object> args = call.arguments();
    String apiKey = (String) args.get("apiKey");
    instance = TenjinSDK.getInstance(context, apiKey);
    result.success(null);
  }

  private void connect(MethodCall call, Result result) {
    instance.connect();
    instance.getDeeplink((clickedTenjinLink, isFirstSession, data) -> {
      Map<String, Object> response = new HashMap<>();
      response.put("clickedTenjinLink", clickedTenjinLink);
      response.put("isFirstSession", isFirstSession);
      response.put("data", data);
      channel.invokeMethod("onSucessDeeplink", response);
    });
    result.success(null);
  }

  private void optInParams(MethodCall call, Result result) {
    Map<String, Object> args = call.arguments();
    String[] params = ((List<String>) args.get("params")).toArray(new String[0]);
    instance.optInParams(params);
    result.success(null);
  }

  private void optOutParams(MethodCall call, Result result) {
    Map<String, Object> args = call.arguments();
    String[] params = ((List<String>) args.get("params")).toArray(new String[0]);
    instance.optOutParams(params);
    result.success(null);
  }

  private void transaction(MethodCall call, Result result) {
    Map<String, Object> args = call.arguments();
    String productName = (String) args.get("productName");
    String currencyCode = (String) args.get("currencyCode");
    int quantity = ((Number) args.get("quantity")).intValue();
    double unitPrice = ((Number) args.get("unitPrice")).doubleValue();
    instance.transaction(productName, currencyCode, quantity, unitPrice);
    result.success(null);
  }

  private void eventWithName(MethodCall call, Result result) {
    Map<String, Object> args = call.arguments();
    String name = (String) args.get("name");
    instance.eventWithName(name);
    result.success(null);
  }

  private void eventWithNameAndValue(MethodCall call, Result result) {
    Map<String, Object> args = call.arguments();
    String name = (String) args.get("name");
    int value = ((Number) args.get("value")).intValue();
    instance.eventWithNameAndValue(name, value);
    result.success(null);
  }

  private void appendAppSubversion(MethodCall call, Result result) {
    Map<String, Object> args = call.arguments();
    int value = ((Number) args.get("value")).intValue();
    instance.appendAppSubversion(value);
    result.success(null);
  }

  private void setCustomerUserId(MethodCall call, Result result) {
    String userId = call.argument("userId");
    if (userId != null) {
      instance.setCustomerUserId(userId);
      result.success(null);
    } else {
      result.error("Error", "Invalid or missing 'userId'", null);
    }
  }

  private void getCustomerUserId(MethodCall call, Result result) {
    String userId = instance.getCustomerUserId();
    if (userId != null) {
      result.success(userId);
    } else {
      result.error("Error", "Failed to get 'userId'", null);
    }
  }

  private void getAnalyticsInstallationId(MethodCall call, Result result) {
    String installationId = instance.getAnalyticsInstallationId();
    if (installationId != null) {
      result.success(installationId);
    } else {
      result.error("Error", "Failed to get 'analyticsInstallationId'", null);
    }
  }

  private void eventAdImpression(Map<String, Object> json, String eventType) {
    try {
      JSONObject jsonObject = new JSONObject(json);
      switch (eventType) {
        case "AdMob":
          instance.eventAdImpressionAdMob(jsonObject);
          break;
        case "AppLovin":
          instance.eventAdImpressionAppLovin(jsonObject);
          break;
        case "HyperBid":
          instance.eventAdImpressionHyperBid(jsonObject);
          break;
        case "IronSource":
          instance.eventAdImpressionIronSource(jsonObject);
          break;
        case "TopOn":
          instance.eventAdImpressionTopOn(jsonObject);
          break;
        case "TradPlus":
          instance.eventAdImpressionTradPlus(jsonObject);
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}