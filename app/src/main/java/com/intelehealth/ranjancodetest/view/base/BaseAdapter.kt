package com.intelehealth.ranjancodetest.view.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : RecyclerView.ViewHolder, D> : RecyclerView.Adapter<T>() {
    abstract fun setData(data: List<D>)

    abstract fun setAddedData(addedData: List<D>)

    abstract fun clearList()
}