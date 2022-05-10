package com.akashi.basicframework.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akashi.basicframework.databinding.ActivityTestNonStickyLiveDataBinding
import com.akashi.common.livedata.LiveDataBus

/**
 * 测试非粘性事件，这个Activity会观察liveData，但期望第一次的值不被接收
 * 由于liveData是静态，第二次打开会被接收
 */
class TestNonStickyLiveDataActivity : AppCompatActivity() {

    lateinit var binding: ActivityTestNonStickyLiveDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestNonStickyLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LiveDataBus.get()
            .with("Event1", String::class.java, false)
            .observe(this) {
                binding.tvBusNonSticky.text = it
            }

        LiveDataBus.get()
            .with("Event2", String::class.java, true)
            .observe(this) {
                binding.tvBusSticky.text = it
            }
    }
}