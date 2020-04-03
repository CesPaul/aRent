package com.cespaul.arent.di.modules

import android.content.Context
import androidx.room.Room
import com.cespaul.arent.model.db.RentDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
object DatabaseModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideDatabase(context: Context): RentDatabase =
        Room.databaseBuilder(
                context,
                RentDatabase::class.java,
                "rent.db"
            )
            .allowMainThreadQueries()
            .build()
}