package ovlesser.mmchallenge.di

import dagger.Module
import dagger.Provides
import ovlesser.mmchallenge.model.User
import javax.inject.Singleton

@Module
class UserModule {
    @Provides
    @Singleton
    fun provideUser() : User{
        return User(name="John Doe", mobile = "04778095252", email = "johndow@test.com")
    }
}