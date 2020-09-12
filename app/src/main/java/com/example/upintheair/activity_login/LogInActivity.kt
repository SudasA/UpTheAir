package com.example.upintheair.activity_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
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
                finish()
            }
            "error_with_all_edit_text" -> text_error.text =
                resources.getString(R.string.error_with_all_edit_text)
            "error_wrong_authorization" -> text_error.text =
                resources.getText(R.string.error_wrong_authorization)
            null -> text_error.text = " "
            else -> {
                Toast.makeText(
                    this,
                    vm.error.value!!,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private val clickOnButtonLogIn = View.OnClickListener {
        vm.authorization(edit_text_login.text.toString(), edit_text_password.text.toString())
    }

    private fun openStartActivity() {
        val intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
    }

    private fun openSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}