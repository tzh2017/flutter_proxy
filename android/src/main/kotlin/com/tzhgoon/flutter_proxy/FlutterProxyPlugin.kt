package com.tzhgoon.flutter_proxy

import android.content.Context
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.util.*

/** FlutterProxyPlugin */
public class FlutterProxyPlugin : FlutterPlugin, MethodCallHandler {

    var context: Context? = null

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_proxy")
            val plugin = FlutterProxyPlugin()
            plugin.context = registrar.context()
            channel.setMethodCallHandler(FlutterProxyPlugin())
        }
    }

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        context = binding.applicationContext
        val channel = MethodChannel(binding.getFlutterEngine().getDartExecutor(), "flutter_proxy")
        channel.setMethodCallHandler(FlutterProxyPlugin())
    }


    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getProxySetting") {
            result.success(getProxySetting(context))
        } else {
            result.notImplemented()
        }
    }

    fun getProxySetting(context: Context?): Any? {
        val host: Any?
        val port: Any?
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            host = android.net.Proxy.getHost(context)
            port = android.net.Proxy.getPort(context)
        } else {
            host = System.getProperty("http.proxyHost")
            port = System.getProperty("http.proxyPort")
        }
        val map = LinkedHashMap<String, Any?>()
        map["host"] = host
        map["port"] = port
        return map
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        context = null
    }
}
