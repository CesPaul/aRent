package com.cespaul.arent.di.components

import com.cespaul.arent.base.BaseView
import com.cespaul.arent.di.modules.ContextModule
import com.cespaul.arent.di.modules.DatabaseModule
import com.cespaul.arent.ui.rent.RentPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Компонент для внедрения зависимостей.
 *
 */
@Singleton
@Component(
    modules = [
        (ContextModule::class),
        (DatabaseModule::class)
    ]
)

/**
 * Управление внедрением зависимостей.
 *
 */
interface PresenterInjector {

    /**
     * Метод для внедрения зависимостей
     *
     * @param rentPresenter Презентер для новостей.
     */
    fun inject(rentPresenter: RentPresenter)

    /**
     * Управление созданием зависимостей.
     *
     */
    @Component.Builder
    interface Builder {

        /**
         * Создание зависимости.
         *
         * @return Injector.
         */
        fun build(): PresenterInjector

        /**
         * Модуль для контекста.
         *
         * @param contextModule Модуль контекста.
         * @return Builder.
         */
        fun contextModule(contextModule: ContextModule): Builder

        fun databaseModule(databaseModule: DatabaseModule): Builder

        /**
         * Привязка к конкретной реализации базовой View.
         *
         * @param baseView Базовая View.
         * @return Builder.
         */
        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }

}