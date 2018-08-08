package net.yuzumone.mikutter.fcm.di

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import net.yuzumone.mikutter.fcm.FCMApplication
import net.yuzumone.mikutter.fcm.db.MikutterMessageDatabase
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(app: FCMApplication): MikutterMessageDatabase {
        return Room.databaseBuilder(app,
                MikutterMessageDatabase::class.java, "database").build()
    }
}