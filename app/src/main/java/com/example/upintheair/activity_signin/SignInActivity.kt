package com.example.upintheair.activity_signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.upintheair.R
import com.example.upintheair.activity_global.GlobalActivity
import com.example.upintheair.loginFilter
import kotlinx.android.synthetic.main.activity_signin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {

    val mViewModel: SignInViewModel by viewModel()

    private val observerLoading = Observer<Boolean> {
        when (it) {
            false -> {
                progress_bar.visibility = View.GONE
                button_sing_in.visibility = View.VISIBLE

                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            }
            true -> {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )

                button_sing_in.visibility = View.INVISIBLE
                progress_bar.visibility = View.VISIBLE
            }
        }
    }

    private val observerError = Observer<String> {
        when (it) {
            "successes" -> {
                openLogIn()
            }
            "error_with_size_of_password" -> text_error.text =
                resources.getText(R.string.error_with_size_of_password)
            "error_with_repeat_password" -> text_error.text =
                resources.getString(R.string.error_with_repeat_password)
            "error_with_all_edit_text" -> text_error.text =
                resources.getString(R.string.error_with_all_edit_text)
            else -> {
                if (mViewModel != null)
                    Toast.makeText(
                        this@SignInActivity,
                        mViewModel.error.value!!,
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        edit_text_login.filters += loginFilter()

        mViewModel.error.observe(this, observerError)
        mViewModel.loading.observe(this, observerLoading)

        button_sing_in.setOnClickListener(clickOnButtonSignIn)

        text_log_in.setOnClickListener {
            openLogIn()
        }
    }

    private val clickOnButtonSignIn = object : View.OnClickListener {
        override fun onClick(v: View?) {

//                delay(1000)

            mViewModel.createUser(
                edit_text_login.text.toString(),
                edit_text_username.text.toString(),
                edit_text_password.text.toString(),
                edit_text_repeat_password.text.toString()
            )

        }
    }

    fun openLogIn() {
        val intent = Intent(this, GlobalActivity::class.java)
        startActivity(intent)
    }
}