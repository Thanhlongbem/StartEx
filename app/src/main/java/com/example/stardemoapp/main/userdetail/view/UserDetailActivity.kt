package com.example.stardemoapp.main.userdetail.view

import android.os.Bundle
import com.example.stardemoapp.Injection
import com.example.stardemoapp.base.BaseFullActivity
import com.example.stardemoapp.core.rest.Constants
import com.example.stardemoapp.core.rest.Status
import com.example.stardemoapp.databinding.ActivityUserDetailBinding
import com.example.stardemoapp.extensions.getViewModel
import com.example.stardemoapp.extensions.loadImageFromURL
import com.example.stardemoapp.extensions.viewBinding
import com.example.stardemoapp.main.userdetail.adapter.PagerAdapter
import com.example.stardemoapp.main.userdetail.viewmodel.UserDetailViewModel
import com.google.android.material.tabs.TabLayoutMediator

class UserDetailActivity : BaseFullActivity() {
    private val binding by viewBinding(ActivityUserDetailBinding::inflate)
    private lateinit var pagerAdapter: PagerAdapter
    private val viewModel: UserDetailViewModel by lazy { getViewModel{ Injection.provideUserDetailViewModel()}}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dataObserve()
        viewModel.getUerData(intent.getStringExtra(Constants.USER_TRANFER)?: "")
        initPagerAdapter()
    }


    private fun dataObserve(){
        viewModel.userData.observe(this, {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.apply {
                        loadImageFromURL(applicationContext, this.avatarUrl, binding.imgAvatar, true)
                        binding.tvName.text = this.login
                        binding.tvNumOfFollower.text = "${this.followers} followers"
                        binding.tvNumOfPublicRepo.text =  "${this.publicRepos} public repos"
                    }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        })
    }

    private fun initPagerAdapter(){
        val user = intent.getStringExtra(Constants.USER_TRANFER)
        pagerAdapter = PagerAdapter(this, listFragments = listOf(FollowerFragment(user), ReposFragment(user)))
        val listTitles  = listOf("Followers", "Repos")
        binding.viewpager.adapter = pagerAdapter
        binding.viewpager.offscreenPageLimit = 2
        binding.viewpager.isUserInputEnabled = false

        TabLayoutMediator(binding.tab, binding.viewpager) { tab, position ->
            tab.text = listTitles[position]
            binding.viewpager.setCurrentItem(tab.position, true)
        }.attach()
    }
}