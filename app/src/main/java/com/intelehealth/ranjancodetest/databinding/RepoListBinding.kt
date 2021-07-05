package com.intelehealth.ranjancodetest.databinding

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.intelehealth.ranjancodetest.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class RepoListBinding {

    companion object{
        @BindingAdapter("repoImage")
        @JvmStatic
        fun bindRepositoryImage(imageView: AppCompatImageView, image:String) {
            Glide.with(imageView.context).load(image)
                    .placeholder(R.drawable.icon_repo)
                    .error(R.drawable.icon_repo).into(imageView)
        }


        @BindingAdapter("repoUpdatedOn")
        @JvmStatic
        fun getUTCTime(apptextView: AppCompatTextView, dateStr: String?) {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val dt: Date
            try {
                dt = sdf.parse(dateStr)
                val calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault())
                calendar.time = dt
                calendar[Calendar.HOUR_OF_DAY] = dt.hours
                val times = calendar.time
                val df = SimpleDateFormat("MMM dd ',' yyyy", Locale.UK)
                apptextView.text =  df.format(times)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
    }
}