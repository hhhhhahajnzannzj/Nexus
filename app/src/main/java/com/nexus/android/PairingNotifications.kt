package com.nexus.android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

object PairingNotifications {
    private const val CHANNEL_ID = "nexus_pairing"
    const val KEY_PAIRING_CODE = "pairing_code"

    fun show(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_ID,
                    "Nexus pairing",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }

        val intent = Intent(context, PairingCodeReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1001,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
        val remoteInput = RemoteInput.Builder(KEY_PAIRING_CODE)
            .setLabel("Six-digit pairing code")
            .build()

        val replyAction = NotificationCompat.Action.Builder(
            android.R.drawable.ic_menu_send,
            "Submit code",
            pendingIntent
        ).addRemoteInput(remoteInput).build()

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .setContentTitle("Nexus pairing code")
            .setContentText("Enter the code shown in Android Wireless debugging settings.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .addAction(replyAction)
            .setAutoCancel(true)
            .build()

        manager.notify(2001, notification)
    }
}
