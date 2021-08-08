package com.example.stardemoapp.main.user.view

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stardemoapp.Injection
import com.example.stardemoapp.R
import com.example.stardemoapp.base.BaseFragment
import com.example.stardemoapp.databinding.FragmentUserBinding
import com.example.stardemoapp.extensions.getViewModel
import com.example.stardemoapp.extensions.viewBinding
import com.example.stardemoapp.main.user.adapter.UserAdapter
import com.example.stardemoapp.main.user.viewmodel.UserViewModel
import com.example.stardemoapp.core.rest.Status
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class UserFragment : BaseFragment() {
    lateinit var adapter: UserAdapter
    private val binding by viewBinding(FragmentUserBinding::bind)
    private val viewModel: UserViewModel by lazy { getViewModel{ Injection.provideUserViewModel()}}

    override fun setLayoutId(): Int = R.layout.fragment_user

    override fun initView() {
        initData()

    }

    override fun onClick(view: View?) {
        when(view) {
            binding.btnSyncUser -> {
                viewModel.syncUser()
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun initData() {
        setSafeClickListener(binding.btnSyncUser)
        adapter = UserAdapter()
        binding.rvListUser.adapter = adapter
        binding.rvListUser.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListUser.setHasFixedSize(true)

        dataObserver()
        viewModel.getAllUser()

        binding.searchBox.textChanges()
            .skip(1)
            .map { it.toString() }
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("EliottDoo", it.toString())
                viewModel.getUserByName(it)
            }, {
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
            })
    }

    private fun dataObserver() {
        viewModel.listUserLiveData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoading(false)
                    it.data?.let { it1 ->
                        adapter.updateData(it1)
                        Log.d("EliottDo", "update data success - ${it1.size}")
                    }
                }
                Status.ERROR -> {
                    Log.d("EliottDo", "synd data error - ${it.status} --- ${it.message}")
                    showLoading(false)
                }
                Status.LOADING -> {
                    Log.d("EliottDo", "loading state when sync")
                }
            }
        })
    }

}