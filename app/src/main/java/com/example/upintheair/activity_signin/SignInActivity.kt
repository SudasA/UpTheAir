package com.example.upintheair.activity_signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import com.example.upintheair.entity.UserRequest
import com.example.upintheair.loginFilter
import com.example.upintheair.md5
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

        login.filters += loginFilter()

        buttonSingIn.setOnClickListener(clickOnButtonSignIn)
    }

    val clickOnButtonSignIn = object : View.OnClickListener {
        override fun onClick(v: View?) {

            if (login.text.toString() != "" && username.text.toString() != "" && password.text.toString() != "" && repeatPassword.text.toString() != "") {
                if (checkPassword(password.text.toString(), repeatPassword.text.toString())) {
//                    CoroutineScope(Dispatchers.Main).launch {
                        mViewModel.postUser(
                            UserRequest(
                                login.text.toString(),
                                username.text.toString(),
                                password.text.toString().md5()
                            )
                        )
//                    }
                } else {
                    errorText.text = "Вы неправильно повторили пароль"
                }
            } else {
                errorText.text = "Все поля должны быть заполнены"
            }
        }
    }

    fun toastMessage(message: String) {
        Toast.makeText(this@SignInActivity, message, Toast.LENGTH_SHORT).show()
    }

    fun checkPassword(p1: String, p2: String): Boolean = p1 == p2

}