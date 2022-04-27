package com.anzhixiang.endcall

import android.R
import android.app.ListActivity
import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter


class MainActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val phoneNumSP = getSharedPreferences("in_phone_num", Context.MODE_PRIVATE)
        val map: Map<*, *> = phoneNumSP.all
        val array = map.keys.toTypedArray()
        Log.v("tag", map.toString() + map.size)
        val adapter: ArrayAdapter<*> = ArrayAdapter(this, R.layout.simple_list_item_1, array)

        setListAdapter(adapter)
    }


    override fun onResume() {
        super.onResume()
        // 注册广播接受者

        // 注册广播接受者
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.PHONE_STATE") //要接收的广播
        registerReceiver(PhoneStatReceiver(), intentFilter) //注册接收者

    }
}