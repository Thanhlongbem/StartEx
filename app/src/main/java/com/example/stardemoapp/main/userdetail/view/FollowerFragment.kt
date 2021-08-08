package com.example.stardemoapp.main.userdetail.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stardemoapp.R
import com.example.stardemoapp.base.BaseFragment
import com.example.stardemoapp.core.rest.Status
import com.example.stardemoapp.databinding.FragmentFollowerBinding
import com.example.stardemoapp.extensions.viewBinding
import com.example.stardemoapp.main.userdetail.adapter.FollowerAdapter
import com.example.stardemoapp.main.userdetail.viewmodel.UserDetailViewModel

class FollowerFragment(private val user: String?):  BaseFragment() {
    private val viewModel: UserDetailViewModel by activityViewModels()
    private val binding by viewBinding(FragmentFollowerBinding::bind)
    lateinit var followerAdapter: FollowerAdapter


    override fun setLayoutId(): Int = R.layout.fragment_follower

    override fun initView() {
        initData()
        user?.let { viewModel.getFollowers(it) }
        dataObserver()
    }


    private fun dataObserver() {
        viewModel.followerData.observe(this, {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.apply {
                        followerAdapter.updateData(this)
                    }
                    binding.loadingProgressbar.visibility = View.GONE
                }
                Status.LOADING -> {
                    binding.loadingProgressbar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.loadingProgressbar.visibility = View.GONE
                }
            }
        })
    }

    private fun initData(){
        followerAdapter = FollowerAdapter()
        binding.rvFollower.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollower.adapter = followerAdapter
        binding.rvFollower.setHasFixedSize(true)
    }

}