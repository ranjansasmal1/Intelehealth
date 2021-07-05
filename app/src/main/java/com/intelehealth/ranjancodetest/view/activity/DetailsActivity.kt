package com.intelehealth.ranjancodetest.view.activity

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.intelehealth.ranjancodetest.R
import com.intelehealth.ranjancodetest.data.remote.NetworkResult
import com.intelehealth.ranjancodetest.data.remote.model.RepositoryListResponseItem
import com.intelehealth.ranjancodetest.databinding.ActivityDetailsBinding
import com.intelehealth.ranjancodetest.databinding.ActivityMainBinding
import com.intelehealth.ranjancodetest.utils.CustomTypefaceSpan
import com.intelehealth.ranjancodetest.utils.EqualSpacingItemDecoration
import com.intelehealth.ranjancodetest.view.adapter.PopularReposAdapter
import com.intelehealth.ranjancodetest.view.base.BaseActivity
import com.intelehealth.ranjancodetest.viewModel.HomeViewModel

class DetailsActivity : BaseActivity<ActivityDetailsBinding,HomeViewModel>() {
    lateinit var popularReposAdapter: PopularReposAdapter
    var repositoryListResponseItem:RepositoryListResponseItem?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toggleStatusBarTextColor(false)
        repositoryListResponseItem = intent?.getParcelableExtra(REPOSITORY_ITEM) as? RepositoryListResponseItem
        binding.ivBack.setOnClickListener { onBackPressed() }
        repositoryListResponseItem?.let { setDetails(it) }
    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun layoutId(): Int = R.layout.activity_details

    private fun setDetails(repositoryListResponseItem:RepositoryListResponseItem){
        binding.tvTitle.text = repositoryListResponseItem.name
        var spanData = getString(R.string.fullname).plus(" ").plus(repositoryListResponseItem.full_name)
        binding.tvFullName.text = getBuilderText(spanData,getString(R.string.fullname),this)
        binding.tvDesc.text = repositoryListResponseItem.description
        spanData = getString(R.string.watcher).plus(" ").plus(repositoryListResponseItem.watchers_count)
        binding.watcherCount.text = getBuilderText(spanData,getString(R.string.watcher),this)
        spanData = getString(R.string.fork).plus(" ").plus(repositoryListResponseItem.forks_count)
        binding.forkCount.text = getBuilderText(spanData,getString(R.string.fork),this)
        spanData = getString(R.string.language).plus(" ").plus(repositoryListResponseItem.language)
        binding.language.text = getBuilderText(spanData,getString(R.string.language),this)
    }


    companion object{
        const val REPOSITORY_ITEM = "REPOSITORY_ITEM"

        fun getBuilderText(data: String, spannable: String,context:Context): SpannableStringBuilder {
            val builder = SpannableStringBuilder(data)
            builder.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(context, R.color.black)),
                    data.indexOf(spannable),
                    data.indexOf(spannable) + spannable.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            var tf = ResourcesCompat.getFont(context, R.font.open_sans_semibold)
            builder.setSpan(
                    CustomTypefaceSpan(tf!!), data.indexOf(spannable),
                    data.indexOf(spannable) + spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            return builder
        }
    }
}