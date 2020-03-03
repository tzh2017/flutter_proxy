import 'dart:async';

import 'package:flutter/services.dart';

class FlutterProxy {
  static const MethodChannel _channel = const MethodChannel('flutter_proxy');

  static Future<ProxySetting> get proxySetting async {
    return _channel //
        .invokeMapMethod<String, dynamic>('getProxySetting')
        .then((e) => ProxySetting._fromMap(e));
  }
}

class ProxySetting {
  String host;
  int port;

  ProxySetting._({
    this.host,
    this.port,
  });

  factory ProxySetting._fromMap(Map<String, dynamic> map) {
    map ??= {};
    return ProxySetting._(
      host: map['host'],
      port: map['port'] != null //
          ? int.parse(map['port'].toString())
          : null,
    );
  }

  bool get enabled =>
      (host?.isNotEmpty ?? false) && //
      (port != null && port > 0);
}
