package net.yuzumone.mikutter.fcm

import android.app.Application
import android.app.Service
import android.support.v4.app.Fragment
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.analytics.FirebaseAnalytics
import io.fabric.sdk.android.Fabric
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import net.yuzumone.mikutter.fcm.di.DaggerApplicationComponent
import javax.inject.Inject

class FCMApplication : Application(), HasServiceInjector, HasSupportFragmentInjector {

    @Inject lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>
    @Inject lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

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
        DaggerApplicationComponent.builder()
                .create(this)
                .inject(this)
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentInjector
    }

}