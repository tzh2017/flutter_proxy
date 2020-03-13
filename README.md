# flutter_proxy

A flutter plugin to read network proxy info from native. It can be used to set up the network proxy for flutter.  

# Installing

You should add the following to your `pubspec.yaml` file:

```yaml
dependencies:
  flutter_proxy: ^0.1.0
```


# Example

```dart
final settings = await FlutterProxy.proxySetting;
bool enabled = settings.enabled;
String host = settings.host;
int port = settings.port;
```

# Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.dev/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our 
[online documentation](https://flutter.dev/docs), which offers tutorials, 
samples, guidance on mobile development, and a full API reference.
