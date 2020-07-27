package com.example.upintheair.activity_start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import com.example.upintheair.activity_global.GlobalActivity
import com.example.upintheair.activity_signin.SignInActivity
import kotlinx.coroutines.NonCancellable.start
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.KoinContextHandler.start

class StartActivity : AppCompatActivity() {
    val mViewModel: StartViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_start)

        val user = true
        routeToAppropriatePage(user)
        finish()
    }

    private fun routeToAppropriatePage(user: Boolean) {
        when {
            user == true -> startActivity(Intent(this, GlobalActivity::class.java))
            user == false -> startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}