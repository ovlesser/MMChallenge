package ovlesser.mmchallenge.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ovlesser.mmchallenge.MyApplication
import ovlesser.mmchallenge.model.User
import ovlesser.mmchallenge.view.QuoteCalculatorFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class, MyApplicationModule::class])
interface MyApplicationComponent {
    fun inject() : User
}

