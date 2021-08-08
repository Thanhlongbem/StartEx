package com.example.stardemoapp.main.userdetail.view

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stardemoapp.R
import com.example.stardemoapp.base.BaseFragment
import com.example.stardemoapp.core.rest.Status
import com.example.stardemoapp.databinding.FragmentReposBinding
import com.example.stardemoapp.extensions.viewBinding
import com.example.stardemoapp.main.userdetail.adapter.RepoAdapter
import com.example.stardemoapp.main.userdetail.viewmodel.UserDetailViewModel

class ReposFragment(private val user: String?):  BaseFragment() {
    lateinit var repoAdapter: RepoAdapter
    private val binding by viewBinding(FragmentReposBinding::bind)
    private val viewModel: UserDetailViewModel by activityViewModels()

    override fun setLayoutId(): Int = R.layout.fragment_repos

    override fun initView() {
        initData()
        if (user != null) {
            viewModel.getRepos(user)
        }
        dataObserver()
    }

    private fun dataObserver() {
        viewModel.reposData.observe(this, {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.apply {
                        repoAdapter.updateData(this)
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
        repoAdapter = RepoAdapter()
        binding.rvRepos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRepos.adapter = repoAdapter
        binding.rvRepos.setHasFixedSize(true)
    }
}