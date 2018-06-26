package me.abdelrhman.domain.interactor

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import me.abdelrhman.domain.executor.PostExecutionThread

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
abstract class CompletableUseCase<in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open fun excute(observer: DisposableCompletableObserver , params: Params? = null){
        val completable = this.buildUseCaseCompletable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)

        addDisposable(completable.subscribeWith(observer))

    }

    private fun addDisposable(disposable:Disposable){
        disposables.add(disposable)
    }

    fun dispose(){
        disposables.dispose()
    }
}