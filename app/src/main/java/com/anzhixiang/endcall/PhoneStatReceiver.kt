package com.anzhixiang.endcall

import android.annotation.SuppressLint
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import com.android.internal.telephony.ITelephony

class PhoneStatReceiver : BroadcastReceiver() {

    private var incomingFlag = false

    private var number: String? = ""

    private var tm: TelephonyManager? = null


    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i("onReceive", "广播启动....")
        tm = p0?.getSystemService(Service.TELEPHONY_SERVICE) as TelephonyManager
        when (tm?.callState) {
            TelephonyManager.CALL_STATE_RINGING -> {
                //来电
                incomingFlag = true
                number = p1?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                Log.i("onReceive", "来电号码：$number")
                if ("15806193165" == number) {
                    endCall()
                    Log.i("onReceive", "拦截来电 $number")
                }
            }
            else -> {

            }
        }


    }

    fun endCall() {
        val c: Class<TelephonyManager> = TelephonyManager::class.java
        try {
            val method = c.getDeclaredMethod("getITelephony", null)
            method.isAccessible = true
            val iTelephony = method.invoke(tm, null) as ITelephony
            iTelephony.endCall()
        } catch (e: Exception) {
            Log.i("PhoneStatReceiver", "Fail to answer ring call.", e)
        }
    }
}