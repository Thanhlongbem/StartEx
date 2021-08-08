package com.example.stardemoapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stardemoapp.extensions.setSafeClickListener
import com.example.stardemoapp.utils.LoadingDialog
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment () : Fragment(), View.OnClickListener{
    val compositeDisposable = CompositeDisposable()
    private var loadingDialog: LoadingDialog? = null
    abstract fun setLayoutId(): Int

    abstract fun initView()

    override fun onClick(v: View?) {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setLayoutId(), container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun setSafeClickListener(vararg ids: View) {
        for (view in ids) view.setSafeClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun showLoading(show: Boolean) {
        if(loadingDialog == null){
            loadingDialog = LoadingDialog()
        }
        if (show) {
            activity?.supportFragmentManager?.let {
                loadingDialog?.show(it, null)
                loadingDialog?.isCancelable = false
            }
        } else {
            if (loadingDialog?.isVisible == true) loadingDialog?.dismiss()
        }
    }


}