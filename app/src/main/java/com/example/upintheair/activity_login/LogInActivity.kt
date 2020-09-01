package com.example.upintheair.activity_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.upintheair.R
import com.example.upintheair.activity_signin.SignInActivity
import com.example.upintheair.activity_start.StartActivity
import com.example.upintheair.loginFilter
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInActivity : AppCompatActivity() {

    val vm: LogInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edit_text_login.filters += loginFilter()

        vm.loading.observe(this, observerLoading)
        vm.error.observe(this, observerError)

        button_log_in.setOnClickListener(clickOnButtonLogIn)
        text_sign_in.setOnClickListener {
            openSignInActivity()
        }
    }

    private val observerLoading = Observer<Boolean> {
        when (it) {
            false -> {
                progress_bar.visibility = View.GONE
                button_log_in.visibility = View.VISIBLE

                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
            true -> {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )

                button_log_in.visibility = View.INVISIBLE
                progress_bar.visibility = View.VISIBLE
            }
        }
    }

    private val observerError = Observer<String> {
        when (it) {
            "success" -> {
                openStartActivity()
            }
            "error_wrong_authorization" -> text_error.text =
                resources.getText(R.string.error_wrong_authorization)
            null -> text_error.text = " "
        }
    }

    private val clickOnButtonLogIn = object : View.OnClickListener {
        override fun onClick(v: View?) {
            openStartActivity()
        }
    }

    fun openStartActivity() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    fun openSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}