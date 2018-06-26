package me.abdelrhman.domain.interactor

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import me.abdelrhman.domain.executor.PostExecutionThread

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
abstract class ObservableUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun excute(observer: DisposableObserver<T> , params: Params? = null){
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)

        addDisposable(observable.subscribeWith(observer))

    }

    private fun addDisposable(disposable:Disposable){
        disposables.add(disposable)
    }

    fun dispose(){
        disposables.dispose()
    }
}