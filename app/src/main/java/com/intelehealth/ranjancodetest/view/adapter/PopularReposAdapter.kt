package com.intelehealth.ranjancodetest.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.intelehealth.ranjancodetest.data.remote.model.RepositoryListResponseItem
import com.intelehealth.ranjancodetest.databinding.LayoutPopularItemBinding
import com.intelehealth.ranjancodetest.view.base.BaseAdapter
import com.intelehealth.ranjancodetest.view.base.BaseViewHolder
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PopularReposAdapter(val context: Context, val callBack: (item: RepositoryListResponseItem) -> Unit) :
    BaseAdapter<PopularReposAdapter.PopularRepositoryViewHolder, RepositoryListResponseItem>() {

    private var adapterDataList: ArrayList<RepositoryListResponseItem> = ArrayList()
    private var lastIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularRepositoryViewHolder {
        val binding = LayoutPopularItemBinding.inflate(
                LayoutInflater.from(parent.context),
                null,
                false
        )
        return PopularRepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularRepositoryViewHolder, position: Int) {
        holder.bind(adapterDataList[position])
    }

    override fun getItemCount(): Int = adapterDataList.size

    override fun setData(data: List<RepositoryListResponseItem>) {
        this.adapterDataList.clear()
        this.adapterDataList.addAll(data)
        notifyDataSetChanged()
    }

    override fun setAddedData(addedData: List<RepositoryListResponseItem>) {
        addAll(addedData)
    }

    fun addAll(mcList: List<RepositoryListResponseItem>) {
        for (mc in mcList) {
            add(mc)
        }
    }

    fun removeItem(item: Int){
        this.adapterDataList.removeAt(item)
        notifyItemRemoved(item)

    }

    fun removeItem(item: RepositoryListResponseItem){
        this.adapterDataList.remove(item)
        notifyDataSetChanged()
    }

    fun add(mc: RepositoryListResponseItem) {
        try {
            adapterDataList.add(mc)
            notifyItemInserted(adapterDataList.size - 1)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getItemSize():Int{
        return adapterDataList.size
    }

    override fun clearList() {
        adapterDataList.clear();
    }

    inner class PopularRepositoryViewHolder(val bindingView: LayoutPopularItemBinding) :
        BaseViewHolder<RepositoryListResponseItem>(
                bindingView.root
        ) {

        override fun bind(data: RepositoryListResponseItem) {
            bindingView.repo = data
            itemView.setOnClickListener {  callBack(data) }
        }
    }
}