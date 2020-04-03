package com.cespaul.arent.base

import com.cespaul.arent.di.components.DaggerPresenterInjector
import com.cespaul.arent.di.components.PresenterInjector
import com.cespaul.arent.di.modules.ContextModule
import com.cespaul.arent.ui.rent.RentPresenter

/**
 * Обеспечивает основу для конкретных презентеров.
 *
 * @param V Конкретная реализация базовой View.
 * @property view Конкретная реализация базовой View.
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    /**
     * Подключение Dagger2 для внедрения зависимостей (DI).
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .build()

    init {
        inject()
    }

    open fun onViewCreated() {}

    open fun onViewDestroyed() {}

    /**
     * Позволяет внедрить зависимость.
     *
     */
    private fun inject() {
        when (this) {
            is RentPresenter -> injector.inject(this)
        }
    }
}