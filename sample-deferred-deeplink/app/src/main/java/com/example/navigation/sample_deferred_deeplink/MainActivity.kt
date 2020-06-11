package com.example.navigation.sample_deferred_deeplink

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.navigation.sample_deferred_deeplink.InstallReferrerReceiver.Companion.DEEPLINK
import kotlinx.android.synthetic.main.activity_main.*
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val action = intent.action

        if (Intent.ACTION_MAIN == action) run {
            // user launches the app from app icon or widget// do your normal logic here
            Toast.makeText(this, "Launch by tap", Toast.LENGTH_SHORT).show()
        } else if (Intent.ACTION_VIEW == action) {

            parseIntent()
        }


    }

    @SuppressLint("SetTextI18n")
    private fun parseIntent() {
        val uri = getUri()
        val mQuery = getDeepLinkParams(uri)

        tvDeepLink.text = "Hello $mQuery"
        Toast.makeText(this, "deeplink query params $mQuery", Toast.LENGTH_LONG).show()
    }

    private fun getUri(): Uri? {
        val uri = intent.data
        return uri ?: if (intent.hasExtra(DEEPLINK))
            Uri.parse(intent.extras?.getString(DEEPLINK))
        else
            null
    }

    private fun getDeepLinkParams(uri: Uri?): HashMap<String, String?> {
        val deepLinkingParams = HashMap<String, String?>()
        if (uri != null) {
            val paramNames = uri.queryParameterNames
            for (name in paramNames) {
                deepLinkingParams[name] = uri.getQueryParameter(name)
            }
        }
        return deepLinkingParams
    }


}