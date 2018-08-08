package net.yuzumone.mikutter.fcm.ui.setting

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.fragment_setting.*
import net.yuzumone.mikutter.fcm.R

class SettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val task = FirebaseInstanceId.getInstance().instanceId
        task.addOnSuccessListener { token ->
            textToken.text = token.token
        }
        textToken.setOnClickListener {
            val token = textToken.text
            val clipboard = activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.primaryClip = ClipData.newPlainText("token", token)
            Toast.makeText(activity, "Copy token", Toast.LENGTH_SHORT).show()
        }
    }

}