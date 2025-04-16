# 📊 TenjinPlugin (Flutter)

A full-featured Flutter plugin for integrating the **Tenjin** mobile marketing analytics SDK. This plugin gives you full control to track events, manage user privacy preferences, handle transactions, and send ad impression data.

---

## ✅ Features

- Initialize Tenjin SDK
- Opt-in / Opt-out for privacy compliance
- Send custom events (with/without value)
- Record transactions (with or without receipt)
- Append app subversion
- Get attribution and analytics info
- Manage Google DMA settings
- Send ad impression data (supports multiple ad networks)

---

## 📦 Installation

Add the plugin to your `pubspec.yaml`:

```yaml
dependencies:
  tenjin_plugin:
    path: ./path_to_your_plugin_directory
```

> Replace the path according to your local setup.

---

## ⚙️ Android Setup

1. Add required Tenjin SDK dependencies in your `build.gradle`.

2. Add necessary permissions and metadata in your `AndroidManifest.xml`.

3. Follow Tenjin's official documentation to complete SDK configuration.

---

## 🧑‍💻 Usage

### 1️⃣ Initialize SDK

```dart
TenjinPlugin.instance.init(apiKey: 'your_api_key');
TenjinPlugin.instance.connect();
```

---

### 2️⃣ Track Events

```dart
TenjinPlugin.instance.eventWithName('level_completed');

TenjinPlugin.instance.eventWithNameAndValue('purchase_amount', 100);
```

---

### 3️⃣ Opt-In / Opt-Out

```dart
TenjinPlugin.instance.optIn();
TenjinPlugin.instance.optOut();
TenjinPlugin.instance.optInParams(['email']);
TenjinPlugin.instance.optOutParams(['location']);
TenjinPlugin.instance.optInOutUsingCMP();
TenjinPlugin.instance.optInGoogleDMA();
TenjinPlugin.instance.optOutGoogleDMA();
```

---

### 4️⃣ Transactions

```dart
TenjinPlugin.instance.transaction(
  'Gold Pack',
  'USD',
  1,
  4.99,
);

// OR with receipt info
TenjinPlugin.instance.transactionWithReceipt(
  productId: 'com.example.gold_pack',
  currencyCode: 'USD',
  unitPrice: 4.99,
  quantity: 1,
  androidPurchaseData: androidData,
  androidDataSignature: androidSignature,
);
```

---

### 5️⃣ Attribution & Analytics

```dart
final info = await TenjinPlugin.instance.getAttributionInfo();
final userId = await TenjinPlugin.instance.getCustomerUserId();
final installId = await TenjinPlugin.instance.getAnalyticsInstallationId();
```

---

### 6️⃣ Set Identifiers

```dart
TenjinPlugin.instance.setCustomerUserId('user_123');
TenjinPlugin.instance.appendAppSubversion(5);
TenjinPlugin.instance.setCacheEventSetting(true);
TenjinPlugin.instance.setGoogleDMAParameters(true, false);
```

---

### 7️⃣ Ad Impression Events

```dart
TenjinPlugin.instance.eventAdImpressionAdMob({
  'ad_unit_id': 'admob_unit_01',
  'network_name': 'AdMob',
  'revenue': 0.01,
});

TenjinPlugin.instance.eventAdImpressionTradPlusAdInfo(yourJsonData);
```

> `eventAdImpressionTradPlusAdInfo()` will auto-convert fields based on platform (Android/iOS).

---

## 📄 Method Summary

| Method                          | Description                                |
|--------------------------------|--------------------------------------------|
| `init(apiKey)`                 | Initializes Tenjin SDK                     |
| `connect()`                    | Connects to Tenjin                        |
| `optIn()` / `optOut()`         | User privacy handling                      |
| `eventWithName(name)`         | Send basic event                           |
| `eventWithNameAndValue(name, value)` | Send event with value               |
| `transaction(...)`            | Basic transaction                          |
| `transactionWithReceipt(...)` | Transaction with purchase receipt          |
| `getAttributionInfo()`        | Fetch attribution data                     |
| `getAnalyticsInstallationId()`| Get installation ID                        |
| `eventAdImpressionAdMob(json)`| Track AdMob impression                     |
| `eventAdImpressionTradPlusAdInfo(json)` | TradPlus impression tracking     |

---

## 🧪 Testing Tips

- Use a real device for all SDK features.
- Make sure you pass valid receipt and signature for transactions.
- Use print/debug logs to confirm event success.

---

## 📃 License

MIT License — use, modify, distribute freely.