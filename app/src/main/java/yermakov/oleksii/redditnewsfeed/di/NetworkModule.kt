package yermakov.oleksii.redditnewsfeed.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yermakov.oleksii.redditnewsfeed.client.RedditApi
import javax.inject.Singleton

private const val BASE_REDDIT_URL = "https://www.reddit.com/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRedditApi(): RedditApi {
        return Retrofit.Builder()
            .baseUrl(BASE_REDDIT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RedditApi::class.java)
    }
}