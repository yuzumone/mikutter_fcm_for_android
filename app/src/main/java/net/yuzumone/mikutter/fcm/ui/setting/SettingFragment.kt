package net.yuzumone.mikutter.fcm.ui.setting

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_setting.*
import net.yuzumone.mikutter.fcm.R
import javax.inject.Inject

class SettingFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: SettingViewModel? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

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
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SettingViewModel::class.java)
        viewModel!!.getMessageCount().observe(this, Observer {
            when (it) {
                100 -> {
                    spinnerCount.setSelection(0)
                }
                200 -> {
                    spinnerCount.setSelection(1)
                }
                500 -> {
                    spinnerCount.setSelection(2)
                }
                1000 -> {
                    spinnerCount.setSelection(3)
                }
                2000 -> {
                    spinnerCount.setSelection(4)
                }
            }
        })
        val spinnerAdapter = ArrayAdapter.createFromResource(activity,
                R.array.message_count, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCount.adapter = spinnerAdapter
        spinnerCount.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val count = parent!!.getItemAtPosition(position).toString().toInt()
                viewModel!!.setMessageCount(count)
            }
        }
    }

}