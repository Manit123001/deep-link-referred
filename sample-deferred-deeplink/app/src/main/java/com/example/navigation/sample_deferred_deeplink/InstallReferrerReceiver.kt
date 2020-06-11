package com.example.navigation.sample_deferred_deeplink

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.google.android.gms.analytics.CampaignTrackingReceiver
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class InstallReferrerReceiver : BroadcastReceiver() {
    private val TAG = javaClass.name
    private val REFERRER = "referrer"
    private val RECEIVER_ACTION = "com.android.vending.INSTALL_REFERRER"

    override fun onReceive(context: Context, intent: Intent) {

        if (TextUtils.equals(intent.action, RECEIVER_ACTION)) {
            val referrer = intent.getStringExtra(REFERRER)
            Log.d(TAG, "referrer $referrer")

            if (!referrer.isNullOrEmpty()) {
                try {
                    val decodedReferrer = URLDecoder.decode(referrer, StandardCharsets.UTF_8.name())
                    Log.d(TAG, "decodedReferrer $decodedReferrer")

                    if (decodedReferrer.isNotEmpty()) {

                        // get value from key
//                        if (decodedReferrer.contains(APP_LINK)) {
//                        }

                        // TODO check Terms and conditions

                        // TODO target for launches Product Activity (JET, C4C etc.)
                        val target = Intent(context, MainActivity::class.java)

                        target.action = Intent.ACTION_VIEW
                        target.putExtra(DEEPLINK, decodedReferrer)

                        Log.d(TAG, "Deferred deep-link referrer: $decodedReferrer")
                        target.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)

                        context.startActivity(target)

                    }

                } catch (e: UnsupportedEncodingException) {
                    Log.e(TAG, "Unable to decode referrer. Deferred deep-link url is not correct: ", e)
                }

            }
            CampaignTrackingReceiver().onReceive(context, intent)
        }
    }

    companion object {
        const val DEEPLINK = "query"
    }
}
