package com.example.upintheair.activity_signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import com.example.upintheair.activity_global.GlobalActivity
import com.example.upintheair.loginFilter
import kotlinx.android.synthetic.main.activity_signin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {

    val mViewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        login.filters += loginFilter()

        buttonSingIn.setOnClickListener(clickOnButtonSignIn)
        textLogIn.setOnClickListener {
            openLogIn()
        }
    }

    private val clickOnButtonSignIn = object : View.OnClickListener {
        override fun onClick(v: View?) {
            mViewModel.sendUser(
                login.text.toString(),
                username.text.toString(),
                password.text.toString(),
                repeatPassword.text.toString()
            )

            when (mViewModel.errorLiveData.value) {
                null -> openLogIn()
                "error_with_repeat_password" -> errorText.text =
                    resources.getString(R.string.error_with_repeat_password)
                "error_with_all_edit_text" -> errorText.text =
                    resources.getString(R.string.error_with_all_edit_text)
            }
        }
    }

    fun openLogIn() {
        val intent = Intent(this, GlobalActivity::class.java)
        startActivity(intent)
    }

    fun toastMessage(message: String) {
        Toast.makeText(this@SignInActivity, message, Toast.LENGTH_SHORT).show()
    }
}