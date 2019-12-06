package com.cespaul.arent.base

abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    open fun onViewCreated(){ }

    open fun onViewDestroyed(){ }

}