import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'tenjin_plugin_method_channel.dart';

abstract class TenjinPluginPlatform extends PlatformInterface {
  /// Constructs a TenjinPluginPlatform.
  TenjinPluginPlatform() : super(token: _token);

  static final Object _token = Object();

  static TenjinPluginPlatform _instance = MethodChannelTenjinPlugin();

  /// The default instance of [TenjinPluginPlatform] to use.
  ///
  /// Defaults to [MethodChannelTenjinPlugin].
  static TenjinPluginPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [TenjinPluginPlatform] when
  /// they register themselves.
  static set instance(TenjinPluginPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
