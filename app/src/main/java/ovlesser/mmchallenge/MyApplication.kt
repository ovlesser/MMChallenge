package ovlesser.mmchallenge

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import ovlesser.mmchallenge.di.DaggerMyApplicationComponent
import ovlesser.mmchallenge.di.MyApplicationComponent
import ovlesser.mmchallenge.di.MyApplicationModule
import javax.inject.Inject

class MyApplication : Application() {
    lateinit var myApplicationComponent: MyApplicationComponent

    override fun onCreate() {
        super.onCreate()
        myApplicationComponent = DaggerMyApplicationComponent.builder()
            .myApplicationModule( MyApplicationModule(this))
            .build()
    }
}