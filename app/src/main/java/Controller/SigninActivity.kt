package Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.mysource.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth? = null
    private val mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        btn_signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))

        }

        btn_login.setOnClickListener {
            loginUser();

        }
    }

    private fun loginUser() {
        val email = email_login.text.toString()
        val password = password_login.text.toString()
        when {

            TextUtils.isEmpty(email) ->
                Toast.makeText(this, "Email is required", Toast.LENGTH_LONG).show()

            TextUtils.isEmpty(password) ->
                Toast.makeText(this, "Password is required", Toast.LENGTH_LONG).show()

            else -> {


                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()

                        } else {

                            val mesdage = task.exception!!.toString()
                            Toast.makeText(this, "Error$mesdage", Toast.LENGTH_LONG).show()
                            FirebaseAuth.getInstance().signOut()

                        }

                    }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}
