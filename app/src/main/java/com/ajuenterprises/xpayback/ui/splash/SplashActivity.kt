package com.ajuenterprises.xpayback.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.ajuenterprises.xpayback.base.BaseActivity
import com.ajuenterprises.xpayback.databinding.ActivitySplashBinding
import com.ajuenterprises.xpayback.ui.user.UserActivity


class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun observeViewModel() {
    }

    override fun initViewBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this, UserActivity::class.java))
            finish()
        }, 2000)
    }
}
