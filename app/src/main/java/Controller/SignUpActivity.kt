package Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.mysource.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_signin.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        btn_sign_up.setOnClickListener {

            CreateAccount()
        }
    }

    private fun CreateAccount() {

        val fullName = full_name_signup.text.toString()
        val userName = username_signup.text.toString()
        val email = email_signup.text.toString()
        val password = password_signup.text.toString()

        when{
            TextUtils.isEmpty(fullName) ->
                Toast.makeText(this, "Name is required", Toast.LENGTH_LONG).show()

            TextUtils.isEmpty(userName) ->
                Toast.makeText(this, "Username is required", Toast.LENGTH_LONG).show()

            TextUtils.isEmpty(email) ->
                Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show()

            TextUtils.isEmpty(password) ->
                Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()
        }
    }
}
