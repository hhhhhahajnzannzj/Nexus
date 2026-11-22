package com.nexus.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.RemoteInput

class PairingCodeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val input = RemoteInput.getResultsFromIntent(intent)
        val code = input?.getCharSequence(PairingNotifications.KEY_PAIRING_CODE)?.toString().orEmpty()
        val validFormat = code.length == 6 && code.all(Char::isDigit)

        // Do not log or persist pairing codes. Actual ADB pairing is not implemented yet.
        Toast.makeText(
            context,
            if (validFormat) "Code received. ADB pairing is not implemented yet."
            else "Please enter a six-digit code.",
            Toast.LENGTH_LONG
        ).show()
    }
}
