package me.abdelrhman.domain.executor

import io.reactivex.Scheduler

/**
 * Created by Abdelrhman
 * on 6/26/18.
 */
interface PostExecutionThread {

    val scheduler: Scheduler
}