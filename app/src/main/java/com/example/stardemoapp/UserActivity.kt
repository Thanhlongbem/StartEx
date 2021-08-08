package com.example.stardemoapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stardemoapp.base.BaseFullActivity
import com.example.stardemoapp.core.rest.Constants
import com.example.stardemoapp.core.rest.Status
import com.example.stardemoapp.databinding.ActivityUserBinding
import com.example.stardemoapp.extensions.getViewModel
import com.example.stardemoapp.extensions.viewBinding
import com.example.stardemoapp.main.user.adapter.UserAdapter
import com.example.stardemoapp.main.user.viewmodel.UserViewModel
import com.example.stardemoapp.main.userdetail.view.UserDetailActivity
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class UserActivity : BaseFullActivity() {
    lateinit var adapter: UserAdapter
    private val binding by viewBinding(ActivityUserBinding::inflate)
    private val viewModel: UserViewModel by lazy { getViewModel{ Injection.provideUserViewModel()}}

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initData()
        binding.searchBox.textChanges()
            .skip(1)
            .map { it.toString() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewModel.getUserByName(it)
            }, {
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            })
    }

    override fun onClick(view: View?) {
        when(view) {
            binding.btnSyncUser -> {
                viewModel.syncUser()
            }
        }
    }

    private fun initData() {
        setSafeClickListener(binding.btnSyncUser)
        adapter = UserAdapter()
        binding.rvListUser.adapter = adapter
        binding.rvListUser.setHasFixedSize(true)
        binding.rvListUser.layoutManager = LinearLayoutManager(this)
        adapter.onUserClick = {
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra(Constants.USER_TRANFER, it)
            startActivity(intent)
        }
        dataObserver()
        viewModel.getAllUser()
    }

    private fun dataObserver() {
        viewModel.listUserLiveData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        adapter.updateData(it1)
                        Log.d("EliottDo", "update data success - ${it1.size}")
                    }
                }
                Status.ERROR -> {
                    Log.d("EliottDo", "synd data error - ${it.status} --- ${it.message}")
                }
                Status.LOADING -> {
                    Log.d("EliottDo", "loading state when sync")
                }
            }
        })
    }
}