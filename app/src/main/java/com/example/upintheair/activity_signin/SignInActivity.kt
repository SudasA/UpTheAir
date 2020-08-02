package com.example.upintheair.activity_signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.upintheair.R
import com.example.upintheair.activity_global.GlobalActivity
import com.example.upintheair.loginFilter
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {

    val mViewModel: SignInViewModel by viewModel()
//    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        edit_text_login.filters += loginFilter()

        progress_bar.visibility = ProgressBar.INVISIBLE

        button_sing_in.setOnClickListener(clickOnButtonSignIn)
        text_log_in.setOnClickListener {
            openLogIn()
        }
    }

    private val clickOnButtonSignIn = object : View.OnClickListener {
        override fun onClick(v: View?) {
            CoroutineScope(Dispatchers.Main).launch {
                progress_bar.visibility = View.VISIBLE

                //проверка веденных данных и содание нового пользователя
                mViewModel.sendUser(
                    edit_text_login.text.toString(),
                    edit_text_username.text.toString(),
                    edit_text_password.text.toString(),
                    edit_text_repeat_password.text.toString()
                )

                if (mViewModel.toastMessageLiveData.value != null) {
                    toastMessage(mViewModel.toastMessageLiveData.value!!)
                }

                //вывод ошибка или переход на другую активити
                when (mViewModel.errorLiveData.value) {
                    null -> {
                        progress_bar.visibility = View.INVISIBLE
                        openLogIn()
                    }
                    "error_with_size_of_password" -> text_error.text =
                        resources.getText(R.string.error_with_size_of_password)
                    "error_with_repeat_password" -> text_error.text =
                        resources.getString(R.string.error_with_repeat_password)
                    "error_with_all_edit_text" -> text_error.text =
                        resources.getString(R.string.error_with_all_edit_text)
                    else -> text_error.text =
                        mViewModel.errorLiveData.value
                }

                progress_bar.visibility = View.INVISIBLE
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