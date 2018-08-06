package net.yuzumone.mikutter.fcm

import android.app.Application
import android.arch.persistence.room.Room
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.analytics.FirebaseAnalytics
import io.fabric.sdk.android.Fabric
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import net.yuzumone.mikutter.fcm.db.MikutterMessageDatabase

class FCMApplication : Application() {

    companion object {
        lateinit var database: MikutterMessageDatabase
    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this, FirebaseOptions.Builder()
                .setApplicationId(BuildConfig.APP_ID)
                .setApiKey(BuildConfig.API_KEY)
                .build(), getString(R.string.app_name))
        FirebaseAnalytics.getInstance(this)
        val core = CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG).build()
        val crashlytics = Crashlytics.Builder()
                .core(core).build()
        Fabric.with(this, crashlytics)
        database = Room.databaseBuilder(this,
                MikutterMessageDatabase::class.java, "database").build()
    }
}