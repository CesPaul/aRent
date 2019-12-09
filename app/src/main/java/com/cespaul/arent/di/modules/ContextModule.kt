package com.cespaul.arent.di.modules

import android.app.Application
import android.content.Context
import com.cespaul.arent.base.BaseView
import dagger.Module
import dagger.Provides

@Module

@Suppress("unused")

/**
 * Модуль для контекста.
 */
object ContextModule {

    /**
     * Для предоставления контекста.
     *
     * @param baseView Базовая View.ю
     * @return Контекст.
     */
    @Provides
    @JvmStatic
    internal fun provideContext(baseView: BaseView): Context {
        return baseView.getContext()
    }

    /**
     * Для предоставления контекста Application.
     *
     * @param context Контекст.
     * @return Контекст Application.
     */
    @Provides
    @JvmStatic
    internal fun provideApplication(context: Context): Application {
        return context.applicationContext as Application
    }
}