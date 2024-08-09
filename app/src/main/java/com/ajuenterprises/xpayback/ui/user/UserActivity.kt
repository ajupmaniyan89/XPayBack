package com.ajuenterprises.xpayback.ui.user

import com.ajuenterprises.xpayback.R
import com.ajuenterprises.xpayback.base.BaseActivity
import com.ajuenterprises.xpayback.databinding.ActivityUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class
UserActivity : BaseActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun observeViewModel() {

    }

    override fun initViewBinding() {
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, UserListFragment())
            .commit()
    }

}