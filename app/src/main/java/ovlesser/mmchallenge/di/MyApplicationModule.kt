package ovlesser.mmchallenge.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ovlesser.mmchallenge.MainActivity
import ovlesser.mmchallenge.MyApplication
import javax.inject.Singleton

@Module
class MyApplicationModule(private val application: Application) {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return application
    }
}