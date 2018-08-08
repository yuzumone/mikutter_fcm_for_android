package net.yuzumone.mikutter.fcm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import net.yuzumone.mikutter.fcm.ui.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val settingFragment = SettingFragment()
            supportFragmentManager.beginTransaction()
                    .add(android.R.id.content, settingFragment).commit()
        }
    }
}
