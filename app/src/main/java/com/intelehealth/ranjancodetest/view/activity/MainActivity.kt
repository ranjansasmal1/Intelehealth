package com.intelehealth.ranjancodetest.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.intelehealth.ranjancodetest.R
import com.intelehealth.ranjancodetest.data.remote.NetworkResult
import com.intelehealth.ranjancodetest.databinding.ActivityMainBinding
import com.intelehealth.ranjancodetest.utils.EqualSpacingItemDecoration
import com.intelehealth.ranjancodetest.view.adapter.PopularReposAdapter
import com.intelehealth.ranjancodetest.view.base.BaseActivity
import com.intelehealth.ranjancodetest.viewModel.HomeViewModel

class MainActivity : BaseActivity<ActivityMainBinding,HomeViewModel>() {
    lateinit var popularReposAdapter: PopularReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toggleStatusBarTextColor(false)
        initializeRecyclerAdapter()
        observePopularRepositories()
    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun layoutId(): Int = R.layout.activity_main

    private fun initializeRecyclerAdapter(){
        popularReposAdapter = PopularReposAdapter(this){item->
            val detailsActivity = Intent(this,DetailsActivity::class.java)
            detailsActivity.putExtra(DetailsActivity.REPOSITORY_ITEM,item)
            startActivity(detailsActivity)
        }
        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvRepos.apply {
            addItemDecoration(
                EqualSpacingItemDecoration(
                    20,
                    EqualSpacingItemDecoration.BOTH
                )
            )
            layoutManager = layoutManager2
            itemAnimator =
                DefaultItemAnimator().apply { addDuration = 500;removeDuration = 200 }
            setHasFixedSize(true)
            adapter = popularReposAdapter
        }


    }

    private fun observePopularRepositories(){
        viewModel.getPopularRepos(popularRepo).observe(this,{ response->
            when(response){

                is NetworkResult.Loading->{
                    binding.pLoader.visibility = View.VISIBLE
                }
                is NetworkResult.Success->{
                    binding.pLoader.visibility = View.GONE
                    popularReposAdapter.setData(response.body)
                }
                is NetworkResult.Failure->{
                    binding.pLoader.visibility = View.GONE
                }
            }

        })
    }


    companion object{
        const val popularRepo = "Medium"
    }
}