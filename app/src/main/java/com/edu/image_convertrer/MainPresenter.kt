package com.edu.imageconverter

import android.util.Log
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter(private val converter: IConverter, private val mainView: MainView) {
    private val TAG: String = "MainPresenter"
    private val compositeDisposable = CompositeDisposable()


    fun buttonClicked() {
        mainView.showSnackbar("Начало преобразования...")
        compositeDisposable.add(converter.convert().subscribeOn(Schedulers.io()).subscribe({
            mainView.showSnackbar("Преобразование успешно!")
            Log.d(TAG, "onComplete")
        }, {
            mainView.showSnackbar("Преобразование не удалось!")
            Log.d(TAG, "onError: ${it.message}")
        }))
    }

    fun onDestroy() {
        compositeDisposable.clear()
        Log.d(TAG, "onDestroy()")
    }
}