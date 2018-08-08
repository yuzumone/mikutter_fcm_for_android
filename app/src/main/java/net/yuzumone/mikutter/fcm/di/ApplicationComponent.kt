package net.yuzumone.mikutter.fcm.di

import dagger.Component
import dagger.android.AndroidInjector
import net.yuzumone.mikutter.fcm.FCMApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    ServiceModule::class
])
interface ApplicationComponent : AndroidInjector<FCMApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<FCMApplication>()
}