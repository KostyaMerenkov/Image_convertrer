package com.edu.imageconverter

import io.reactivex.rxjava3.core.Completable

interface IConverter {
    fun convert(): Completable
}