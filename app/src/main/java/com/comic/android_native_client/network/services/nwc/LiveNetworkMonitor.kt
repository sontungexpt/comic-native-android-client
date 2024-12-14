//package com.comic.android_native_client.network.services.nwc
//
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.net.ConnectivityManager
//import android.net.Network
//import android.net.NetworkCapabilities
//import android.net.NetworkRequest
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import javax.inject.Inject
//
//interface NetworkMonitor {
//    fun isConnected(): Boolean
//}
//
//class LiveNetworkMonitor @Inject constructor(
//    private val context: Context
//) : NetworkMonitor {
//    private val _networkState = MutableLiveData<Boolean>()
//    val networkState: LiveData<Boolean> get() = _networkState
//    val networkRequest = NetworkRequest.Builder()
//        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//        .build()
//
//    private val networkCallback =
//        object : ConnectivityManager.NetworkCallback() {
//
//            // Network capabilities have changed for the network
//            override fun onCapabilitiesChanged(
//                network: Network,
//                networkCapabilities: NetworkCapabilities
//            ) {
//                super.onCapabilitiesChanged(network, networkCapabilities)
//                val unmetered =
//                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
//            }
//
//        }
//
//    init {
//        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//
//        context.registerReceiver(object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                val cm =
//                    context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//                val activeNetwork = cm.activeNetwork
//
//                _networkState.value = activeNetwork != null
//            }
//
//        }, filter)
//    }
//
//    override fun isConnected(): Boolean {
//        return _networkState.value ?: false
//    }
//
////    fun proceedRequest(request: Request): Response {
////        val client = OkHttpClient()
////        val call = client.newCall(request)
////        return call.execute()
////    }
//}