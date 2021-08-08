package com.example.stardemoapp.extensions

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.stardemoapp.R
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


fun <T> Single<T>.applyOn(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.applyOn(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Disposable.disposedBy(compositeDisposable: CompositeDisposable){
    compositeDisposable.add(this)
}

fun loadImageFromURL(context: Context, url: String?, imageView: ImageView, enableCache: Boolean = false, defaultImg: Int = R.drawable.icon_image_default) {
    Glide.with(context)
        .load(url)
        .placeholder(defaultImg)
        .diskCacheStrategy(if (enableCache) DiskCacheStrategy.ALL else DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .error(defaultImg)
        .into(imageView)
}

private var lastClickedTime = 0L

fun View.setSafeClickListener(onViewClick: View.OnClickListener) {
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickedTime > 500){
            lastClickedTime = SystemClock.elapsedRealtime()
            onViewClick.onClick(it)
        }
    }
}

fun Fragment.addFragmentToActivity(
    fragmentManager: FragmentManager,
    fragment: Fragment, frameId: Int, onBackstack: Boolean, tag: String? = null
) {
    val currentFr = fragmentManager.findFragmentById(frameId)
    val transaction = fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    if (currentFr != null) transaction.hide(currentFr)
    transaction.add(frameId, fragment, tag)
    if (onBackstack) {
        transaction.addToBackStack(tag)
    }
    transaction.commit()
}

fun Fragment.replaceFragmentToActivity(
    fragmentManager: FragmentManager,
    fragment: Fragment, frameId: Int, onBackStack: Boolean = false, tag: String? = null
) {
    val transaction = fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    //transaction.setCustomAnimations(R.anim.attack_fragment,R.anim.detack_fragment,R.anim.backstack_in,R.anim.backstack_out);
    transaction.replace(frameId, fragment, tag)
    if (onBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}