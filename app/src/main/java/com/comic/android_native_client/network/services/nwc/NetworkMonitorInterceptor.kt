//package com.comic.android_native_client.network.services.nwc
//
//import android.content.Context
//import android.widget.Toast
//import dagger.hilt.android.qualifiers.ApplicationContext
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.Response
//import javax.inject.Inject
//
//class NetworkMonitorInterceptor @Inject constructor(
//    @ApplicationContext private val context: Context,
//    private val liveNetworkMonitor: NetworkMonitor
//) : Interceptor {
//    private var _queue = mutableListOf<Request>()
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        if (liveNetworkMonitor.isConnected()) {
//            println("test")
//            return chain.proceed(request)
//        } else {
//            _queue.add(request)
//            Toast.makeText(
//                context,
//                "No internet connection",
//                Toast.LENGTH_SHORT
//            ).show()
//            throw NoNetworkException("No network")
//        }
//    }
//
//    fun retryRequests(client: OkHttpClient) {
//        val iterator = _queue.iterator()
//        while (iterator.hasNext()) {
//            val request = iterator.next()
//            try {
//                val call = client.newCall(request)
//                val response = call.execute()
//                iterator.remove()
//            } catch (e: Exception) {
//
//            }
//        }
//    }
//}
