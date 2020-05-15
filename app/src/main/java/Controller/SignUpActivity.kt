package Controller

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.mysource.R
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth? = null
    private val mDatabase: DatabaseReference? = null

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

        when {
            TextUtils.isEmpty(fullName) ->
                Toast.makeText(this, "Name is required", Toast.LENGTH_LONG).show()

            TextUtils.isEmpty(userName) ->
                Toast.makeText(this, "Username is required", Toast.LENGTH_LONG).show()

            TextUtils.isEmpty(email) ->
                Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show()

            TextUtils.isEmpty(password) ->
                Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()

            else -> {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Register")
                progressDialog.setMessage("Pleae wait, this might take a while")
                progressDialog.setCanceledOnTouchOutside(false)
                progressDialog.show()

                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUserInfo(fullName, userName, email)

                        } else {

                            val message = task.exception!!.toString()
                            Toast.makeText(this, "Error$message", Toast.LENGTH_LONG ).show()
                            progressDialog.dismiss()

                        }

                    }
            }

        }
    }

    private fun saveUserInfo(fullName: String, userName: String, email: String) {
            TODO("Not yet implemented")
    }
}
