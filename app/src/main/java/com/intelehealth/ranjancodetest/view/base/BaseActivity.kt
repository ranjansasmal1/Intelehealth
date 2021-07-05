package com.intelehealth.ranjancodetest.view.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import dagger.android.AndroidInjection
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
  protected lateinit var binding: B
  protected lateinit var viewModel: VM


  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
      window.statusBarColor = Color.TRANSPARENT
    }
    super.onCreate(savedInstanceState)
    bindContentView(layoutId())
  }

  private fun bindContentView(layoutId: Int) {
    binding = DataBindingUtil.setContentView(this, layoutId)
    viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    //binding.setVariable(BR.viewModel, viewModel)
  }

  abstract fun getViewModelClass(): Class<VM>

  @LayoutRes protected abstract fun layoutId(): Int


   fun toggleStatusBarTextColor(light: Boolean) {
    // clear any existing flags
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
       if (light)
         window.insetsController?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
       else
         window.insetsController?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
     }else {
       window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
       if (light) {
         window.decorView.systemUiVisibility =
           (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
       } else {
         window.decorView.systemUiVisibility =
           (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
       }
     }
  }
}
