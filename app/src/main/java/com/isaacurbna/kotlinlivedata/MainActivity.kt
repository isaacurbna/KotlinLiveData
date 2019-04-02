package com.isaacurbna.kotlinlivedata

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var myViewModel: MyViewModel

    private val myObserver = Observer<Long> {
        Log.i(TAG, "LiveData update, new value: " + it.toString())
        myTextView.text = it.toString()
    }

    private val timeObservable = Observable.interval(1000L, TimeUnit.MILLISECONDS)
        .timeInterval()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { time -> myViewModel.myResource.value = time.value() }

    // region lifecycle callbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewModel = ViewModelProviders.of(this)
            .get(MyViewModel::class.java)

        myViewModel.myResource.observe(this, myObserver)
    }

    override fun onResume() {
        Log.i(TAG, "onResume")
        super.onResume()
    }

    override fun onStop() {
        Log.i(TAG, "onStop")
        super.onStop()
    }
    // endregion
}
