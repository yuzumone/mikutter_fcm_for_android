package net.yuzumone.mikutter.fcm.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_message_list.*
import net.yuzumone.mikutter.fcm.R
import net.yuzumone.mikutter.fcm.ui.common.MikutterMessageAdapter
import net.yuzumone.mikutter.fcm.ui.setting.SettingFragment
import javax.inject.Inject

class MessageListFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private var viewModel: MessageListViewModel? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MessageListViewModel::class.java)
        val adapter = MikutterMessageAdapter { message ->
            // TODO
        }
        listMessage.adapter = adapter
        listMessage.layoutManager = LinearLayoutManager(activity)
        val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        listMessage.addItemDecoration(divider)
        viewModel!!.getMessagesOrderDescLimit(200).observe(this, Observer {
            adapter.update(it)
        })
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