package net.yuzumone.mikutter.fcm

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val task = FirebaseInstanceId.getInstance().instanceId
        task.addOnSuccessListener { token ->
            textToken.text = token.token
        }
        textToken.setOnClickListener {
            val token = textToken.text
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.primaryClip = ClipData.newPlainText("token", token)
            Toast.makeText(this, "Copy token", Toast.LENGTH_SHORT).show()
        }
    }
}
