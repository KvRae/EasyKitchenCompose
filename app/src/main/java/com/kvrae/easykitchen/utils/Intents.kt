package com.kvrae.easykitchen.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.net.Uri
import android.widget.Toast

inline fun openYoutube(
    uri: String,
    context: Context,
    errorText: String = "Youtube not installed"
) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.google.android.youtube")
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            errorText,
            Toast.LENGTH_SHORT
        ).show()
    }
}

