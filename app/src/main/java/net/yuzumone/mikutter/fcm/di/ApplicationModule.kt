package net.yuzumone.mikutter.fcm.di

import androidx.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import net.yuzumone.mikutter.fcm.FCMApplication
import net.yuzumone.mikutter.fcm.db.MikutterMessageDatabase
import net.yuzumone.mikutter.fcm.perf.PreferenceStorage
import net.yuzumone.mikutter.fcm.perf.SharedPreferenceStorage
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    fun provideContext(application: FCMApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideDatabase(app: FCMApplication): MikutterMessageDatabase {
        return Room.databaseBuilder(app,
                MikutterMessageDatabase::class.java, "database").build()
    }

    @Singleton
    @Provides
    fun providesPreferenceStorage(context: Context): PreferenceStorage =
            SharedPreferenceStorage(context)
}