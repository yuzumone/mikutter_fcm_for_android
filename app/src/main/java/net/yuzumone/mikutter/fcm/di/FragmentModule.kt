package net.yuzumone.mikutter.fcm.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.yuzumone.mikutter.fcm.ui.list.MessageListFragment

@Module
abstract class FragmentModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeMessageListFragment(): MessageListFragment
}