package am.xtech.app16.presentation.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import am.xtech.app16.R
import am.xtech.app16.presentation.base.BaseFragment
import android.content.Intent
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        viewModel.navigate.observe(viewLifecycleOwner, Observer {

            when(it){
                NavConstant.NAVIGATION_LOGIN ->{
                    findNavController().navigate(R.id.action_navigation_splash_to_navigation_login)
                }

                NavConstant.NAVIGATION_HOME ->{
                    findNavController().navigate(R.id.action_navigation_splash_to_navigation_home)
                }
            }
        })

        Handler().postDelayed(Runnable {
            viewModel.getState()
        }, 2000)

    }

}
