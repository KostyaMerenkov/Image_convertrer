package com.edu.imageconverter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.rxjava3.core.Completable
import java.io.File
import java.io.FileOutputStream


class MainModel(private val context: Context) : IConverter {
    private val TAG = "MainModel"

    override fun convert(): Completable = Completable.create { emitter ->
        conversion().let {
            if (it) {
                emitter.onComplete()
            } else {
                emitter.onError(RuntimeException("Error"))
                return@create
            }
        }
    }

    private fun conversion(): Boolean {
        val bmp: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.test)
        val out = FileOutputStream(File(context.filesDir, "new_image.png"))
        //Log.d(TAG, context.filesDir.toString() + "/new_image.png")
        bmp.compress(Bitmap.CompressFormat.PNG, 100, out) //100-best quality
        out.close()
        return true
    }
}