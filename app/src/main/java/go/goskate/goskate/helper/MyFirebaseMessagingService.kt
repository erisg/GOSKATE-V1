package go.goskate.goskate.helper

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMassage: RemoteMessage) {
        Looper.prepare()
        Handler().post {
            Toast.makeText(baseContext, remoteMassage.notification?.title, Toast.LENGTH_LONG).show()
        }
        Looper.loop()
    }
}