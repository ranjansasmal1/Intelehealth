package com.intelehealth.ranjancodetest.utils

import android.R.attr.spacing
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EqualSpacingItemDecoration constructor(val space: Int, display: Int = -1): RecyclerView.ItemDecoration() {
    private var displayMode:Int = display

    companion object{
        const val HORIZONTAL = 0
        const val VERTICAL = 1
        const val GRID = 2
        const val BOTH = 3
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

//        val position = parent.getChildViewHolder(view).adapterPosition
//        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager
        layoutManager?.let {
            when (displayMode) {
                HORIZONTAL -> {
                    if (parent.getChildAdapterPosition(view) == 0) {
                        outRect.set(space, 0, space, 0);
                    } else {
                        outRect.set(0, 0, space, 0);
                    }
                }
                VERTICAL -> {
                    /*if (parent.getChildAdapterPosition(view) == 0) {
                        outRect.set(0, 0, 0, 0);
                    } else {*/
                        outRect.set(0, 0, 0, space);
                   // }
                }
                BOTH ->{
                    if (parent.getChildAdapterPosition(view) == 0) {
                        outRect.set(space/2, space/4, space/2, space);
                    } else {
                        outRect.set(space/2, 0, space/2, space);
                    }
                }
            }
        }
    }

    private fun setSpacingForDirection(
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager,
        position: Int,
        itemCount: Int
    ) {

        // Resolve display mode automatically
        if (displayMode == -1) {
            displayMode = resolveDisplayMode(layoutManager)
        }
        when (displayMode) {
            HORIZONTAL -> {
                outRect.left = spacing
                outRect.right = if (position == itemCount - 1) spacing else 0
                outRect.top = spacing
                outRect.bottom = spacing
            }
            VERTICAL -> {
                outRect.left = spacing
                outRect.right = spacing
                outRect.top = spacing
                outRect.bottom = if (position == itemCount - 1) spacing else 0
            }
            GRID -> if (layoutManager is GridLayoutManager) {
                val cols = layoutManager.spanCount
                val rows = itemCount / cols
                outRect.left = spacing
                outRect.right = if (position % cols == cols - 1) spacing else 0
                outRect.top = spacing
                outRect.bottom = if (position / cols == rows - 1) spacing else 0
            }
        }
    }

    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager): Int {
        if (layoutManager is GridLayoutManager) return GRID
        return if (layoutManager.canScrollHorizontally()) HORIZONTAL else VERTICAL
    }

}