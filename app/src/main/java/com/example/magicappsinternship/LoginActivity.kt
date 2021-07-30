package com.example.magicappsinternship

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    val username_key = "username"
    val username_default = ""
    val password_key = "password"
    val pasword_default = ""
    val MyPreference = "myprefs"
    var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_save.visibility = View.INVISIBLE
        save_detail_tv.visibility = View.INVISIBLE

        val sharedPreferences : SharedPreferences = getSharedPreferences(MyPreference, MODE_PRIVATE)


        bt_login.setOnClickListener {

            username = et_email.text.toString()
            val password = et_password.text.toString()

            if (username != sharedPreferences.getString(username_key,username_default))
            {
                save_detail_tv.visibility = View.VISIBLE
                bt_save.visibility = View.VISIBLE
                bt_save.setOnClickListener {
                    sharedPreferences.edit().putString(username_key, username).apply()
                    sharedPreferences.edit().putString(password_key, password).apply()
                    gotoWelcomeActivty()
                }

            }
            else if (username == sharedPreferences.getString(username_key,username_default) &&
                password != sharedPreferences.getString(password_key,pasword_default))
            {
                save_detail_tv.visibility = View.INVISIBLE
                bt_save.visibility = View.INVISIBLE
                Toast.makeText(this, "Incorrect Password", Toast.LENGTH_LONG).show()
            }
            else
            {
                gotoWelcomeActivty()
            }
        }
    }

    private fun gotoWelcomeActivty()
    {
        val startWelcomeActivity = Intent(this,WelcomeActivity::class.java)
        startWelcomeActivity.putExtra(username_key,username)
        startActivity(startWelcomeActivity)
    }

    override fun onResume() {
        bt_save.visibility = View.INVISIBLE
        save_detail_tv.visibility = View.INVISIBLE
        super.onResume()
    }

}