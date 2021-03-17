package com.amberfleet.innoventesinter.Base

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amberfleet.innoventesinter.di.module.ViewModelFactory

import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<D : ViewDataBinding, M : ViewModel> : DaggerAppCompatActivity(),
    IBaseView {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding = DataBindingUtil.setContentView<D>(this, getLayoutResId())

        val viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModel())
        binding.setVariable(getBindVariable(), viewModel)
        onCreate(savedInstanceState, binding, viewModel)
        binding.executePendingBindings()
    }


    override fun showStatus(message: String, type: Int) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    protected abstract fun onCreate(instance: Bundle?, binding: D, viewModel: M)

    protected abstract fun getBindVariable(): Int

    protected abstract fun getViewModel(): Class<M>

    @LayoutRes
    protected abstract fun getLayoutResId(): Int


    @RequiresApi(Build.VERSION_CODES.M)
    fun checkGranted(permissions: Array<String>): Boolean {
        for (per in permissions) {
            if (checkSelfPermission(per) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {

        requestPermissions(permissions, requestCode)

    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}