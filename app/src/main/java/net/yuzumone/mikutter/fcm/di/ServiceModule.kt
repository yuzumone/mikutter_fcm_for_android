package net.yuzumone.mikutter.fcm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.yuzumone.mikutter.fcm.MikutterMessagingService

@Module
abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract fun contributeMikutterMessagingService(): MikutterMessagingService
}