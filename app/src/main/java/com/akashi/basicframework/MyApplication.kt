package com.akashi.basicframework

import android.app.Application
import android.widget.Toast
import com.akashi.basicframework.test.hook.LoginActivity
import com.akashi.common.actions.initLog
import com.akashi.common.actions.logE
import com.akashi.common.hook.HookUtil
import com.akashi.common.watchers.registerOurLifecycleCallback

class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // 日志工具
        initLog()

        // 监听Activity生命周期
        this.registerOurLifecycleCallback()

        // hook工具 —— hook AMS
        try {
            HookUtil.getInstance()
                .with(this)
                .hookTo(LoginActivity::class.java)
                .hookAMS()
        } catch (e: Throwable) {
            logE(e)
        }
    }
}

fun toast(str: String) {
    Toast.makeText(MyApplication.instance, str, Toast.LENGTH_SHORT).show()
}