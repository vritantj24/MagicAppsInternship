package com.example.magicappsinternship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment(username: String?,fragmentManager: FragmentManager) : Fragment() {

    private val mUsername = username
    private val mfragmentManager = fragmentManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val welcomeMessage = "Welcome $mUsername"
        welcome_tv.text=welcomeMessage
        my_task_button.setOnClickListener {

           val fragmentTransaction : FragmentTransaction = mfragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container_view,MyTaskFragment()).commit()
        }
    }
}