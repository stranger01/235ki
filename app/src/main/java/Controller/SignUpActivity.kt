package Controller

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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

                val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUserInfo(fullName, userName, email)

                        } else {

                            val message = task.exception!!.toString()
                            Toast.makeText(this, "Error$message", Toast.LENGTH_LONG).show()
                            mAuth.signOut()

                        }

                    }
            }

        }
    }

    private fun saveUserInfo(fullName: String, userName: String, email: String) {
        val currentUserID = FirebaseAuth.getInstance().currentUser!!.uid
        val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")

        val userMap = HashMap<String, Any>()
        userMap["uid"] = currentUserID
        userMap["fullname"] = fullName
        userMap["username"] = userName
        userMap["email"] = email
        userMap["bio"] = "Im using this awesome platform"
        userMap["image"] =
            "https://firebasestorage.googleapis.com/v0/b/mysource-2bb9a.appspot.com/o/Default%20Image%2Fprofile.png?alt=media&token=7ff3e996-7b06-4a1a-bc90-3958574ce78e"

        userRef.child(currentUserID).setValue(userMap)
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
