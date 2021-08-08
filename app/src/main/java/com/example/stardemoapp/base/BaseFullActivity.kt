package com.example.stardemoapp.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stardemoapp.extensions.setSafeClickListener
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFullActivity : AppCompatActivity(), View.OnClickListener {
    val compositeDisposable = CompositeDisposable()


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun setSafeClickListener(vararg ids: View) {
        for (view in ids) view.setSafeClickListener(this)
    }

    override fun onClick(view: View?) {
    }
}