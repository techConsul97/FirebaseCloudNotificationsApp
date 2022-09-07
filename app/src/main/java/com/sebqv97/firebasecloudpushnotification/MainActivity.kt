package com.sebqv97.firebasecloudpushnotification

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.sebqv97.firebasecloudpushnotification.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //INIT
        binding = ActivityMainBinding.inflate(layoutInflater)



        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FIREBASE_NOTIFICATION", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = "My Token : $token"
            Log.d("FIREBASE_NOTIFICATION", msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
        //ONCLICK LISTENER
        binding.showAnalysis.setOnClickListener { showAnalytics() }

        setContentView(binding.root)
}

    private fun showAnalytics() {
        analytics  =Firebase.analytics
        analytics.logEvent(
            FirebaseAnalytics.Event.SELECT_CONTENT,
            bundleOf(
                Pair(FirebaseAnalytics.Param.ITEM_ID, "MAIN ACTIVITY"),
                Pair(FirebaseAnalytics.Param.ITEM_NAME, "NAME"),
                Pair(FirebaseAnalytics.Param.CONTENT_TYPE, "SEE"))
        )
    }
}