package com.example.upintheair.activity_signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.loginFilter
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {

    val mViewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

//        viewModel = ViewModelProvider(
//            this,
//            SignInViewModelFactory(RetrofitRepository)
//        ).get(SignInViewModel::class.java)

//        login.addTextChangedListener(filterForLogin)
        login.filters += loginFilter()

        buttonSingIn.setOnClickListener(clickOnButtonSignIn)
    }

    val clickOnButtonSignIn = object : View.OnClickListener {
        override fun onClick(v: View?) {
            CoroutineScope(Dispatchers.Main).launch {

                if (checkPassword(password.text.toString(), repeatPassword.text.toString()))
                    mViewModel.postUser(
                        UserRequest(
                            login.text.toString(),
                            username.text.toString(),
                            password.text.toString()
                        )
                    )
            }
        }
    }

    fun toastMessage(message: String) {
        Toast.makeText(this@SignInActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun checkPassword(p1: String, p2: String): Boolean = p1 == p2

}