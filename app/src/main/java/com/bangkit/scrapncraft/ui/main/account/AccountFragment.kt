package com.bangkit.scrapncraft.ui.main.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.scrapncraft.R
import com.bangkit.scrapncraft.databinding.FragmentAccountBinding
import com.bangkit.scrapncraft.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccountFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_account_1
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = Firebase.auth
        val username = binding.itemUser
        val user = Firebase.auth.currentUser

        if (auth != null) {
            username.text = user!!.email
        } else {
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        binding.signOut.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}