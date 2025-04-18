import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'tenjin_plugin_platform_interface.dart';

/// An implementation of [TenjinPluginPlatform] that uses method channels.
class MethodChannelTenjinPlugin extends TenjinPluginPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('tenjin_plugin');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
