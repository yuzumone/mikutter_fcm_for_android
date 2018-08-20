package net.yuzumone.mikutter.fcm.di

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.yuzumone.mikutter.fcm.ui.list.MessageListViewModel
import net.yuzumone.mikutter.fcm.ui.setting.SettingViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MessageListViewModel::class)
    abstract fun bindMainViewModel(viewModel: MessageListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(viewModel: SettingViewModel): ViewModel
}