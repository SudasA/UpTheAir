package com.example.upintheair.activity_signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.upintheair.R
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.network.RetrofitRepository
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    lateinit var viewModel: SignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        viewModel = ViewModelProvider(
            this,
            SignInViewModelFactory(RetrofitRepository)
        ).get(SignInViewModel::class.java)

        buttonSingIn.setOnClickListener(clickOnButtonSignIn)
    }

    val clickOnButtonSignIn = object : View.OnClickListener{
        override fun onClick(v: View?) {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.postUser(UserRequest(login.text.toString(), username.text.toString(), password.text.toString()))
            }
        }
    }

    fun toastMessage(message: String) {
        Toast.makeText(this@SignInActivity, message, Toast.LENGTH_SHORT).show()
    }
}