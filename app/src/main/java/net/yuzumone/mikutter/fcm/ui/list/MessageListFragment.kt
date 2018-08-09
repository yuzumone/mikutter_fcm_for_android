package net.yuzumone.mikutter.fcm.ui.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import net.yuzumone.mikutter.fcm.R
import net.yuzumone.mikutter.fcm.ui.setting.SettingFragment

class MessageListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.fragment_message_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_setting -> {
                val fragment = SettingFragment()
                fragmentManager!!.beginTransaction().replace(android.R.id.content, fragment)
                        .addToBackStack(null).commit()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}