package sq.mayv.aegyptus.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sq.mayv.aegyptus.network.Api
import sq.mayv.aegyptus.repository.AuthRepository
import sq.mayv.aegyptus.repository.FavoritesRepository
import sq.mayv.aegyptus.repository.PlacesRepository
import sq.mayv.aegyptus.util.PreferenceHelper
import sq.mayv.aegyptus.util.PreferenceHelper.baseUrl
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthRepository(api: Api) = AuthRepository(api)

    @Provides
    fun provideFavoritesRepository(api: Api) = FavoritesRepository(api)

    @Provides
    fun providePlacesRepository(api: Api) = PlacesRepository(api)

    @Provides
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceHelper.getPreference(context)
    }

    @Singleton
    @Provides
    fun provideApi(preferences: SharedPreferences): Api {

        val baseUrl = preferences.baseUrl

        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.MINUTES).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)
    }

}