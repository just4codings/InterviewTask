package com.amberfleet.innoventesinter


import android.os.Bundle
import android.view.View
import com.amberfleet.innoventesinter.Base.BaseActivity
import com.amberfleet.innoventesinter.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {


    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(
        instance: Bundle?,
        binding: ActivityMainBinding,
        viewModel: MainViewModel
    ) {
        activityMainBinding = binding
        activityMainBinding.mainViewModel = viewModel

        mainViewModel = viewModel

    }

    override fun getBindVariable(): Int = BR.mainViewModel

    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_main


}