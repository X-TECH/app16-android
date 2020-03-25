package am.xtech.app16.presentation.ui.home

import am.xtech.app16.R
import am.xtech.app16.app.App
import am.xtech.app16.presentation.base.BaseFragment
import am.xtech.app16.presentation.ui.home.state.HomeStateEvent
import am.xtech.app16.presentation.ui.home.state.HomeViewState
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel

    lateinit var imageQr: ImageView
    lateinit var layotutCreateApplication: View
    lateinit var layoutQr: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val btnCreateApplication = root.findViewById<View>(R.id.btn_home_add_new)
        val btnFinishApplication = root.findViewById<View>(R.id.btn_home_finish)
        val btnHistoryApplication = root.findViewById<View>(R.id.btn_home_history)




        val textWelcome: TextView = root.findViewById(R.id.tv_home_title)
        val text = getString(R.string.text_welcome)  +" "+ App.sUserManager.getUser()?.getFullName()
        textWelcome.text = text

        imageQr = root.findViewById(R.id.iv_home_qr)
        layoutQr = root.findViewById(R.id.lay_home_qr)
        layotutCreateApplication = root.findViewById(R.id.lay_home_create_layout)

        btnCreateApplication.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_create_form)
        }

        btnFinishApplication.setOnClickListener {
            triggerFinishApplication()
        }

        btnHistoryApplication.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_history)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        subscribeObservers()
        triggerGetRegisteredApplication()
    }

    private fun triggerGetRegisteredApplication() {
        viewModel.setStateEvent(HomeStateEvent.getCurrentApplication(App.sUserManager.getUUID()))
    }

    private fun triggerFinishApplication() {
        viewModel.setStateEvent(HomeStateEvent.finishApplication(App.sUserManager.getUUID()))
    }

    fun subscribeObservers() {

        viewModel.dataState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { dataState ->

            dataState.data?.let { viewstate ->

                viewstate.peekContent().let {
                    it.isCreated.let { result ->

                        if (result != null) {
                            viewModel.setViewState(HomeViewState(result))
                            showQRLayout()
                        }
                    }


                    it.isFinished.let { result ->

                        if (result != null) {
                            viewModel.setViewState(HomeViewState(result))
                            showCreateApplicationLayout()
                        }
                    }
                }
            }

            dataState.code?.let {

                if (it == 404) {
                    showCreateApplicationLayout()
                }
            }

            dataState.message?.let {
            }

            dataState.loading.let {
                mCallback?.onProgressStateChange(it)
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { viewState ->
            viewState.apply { }?.let {

            }
        })
    }

    private fun showQRLayout() {

            layoutQr.visibility = VISIBLE
            layotutCreateApplication.visibility = GONE

            val imageURL =
                "http://app16.x-tech.am/api/v1/applications/qr_code?device_token=" + App.sUserManager.getUUID()
            Glide.with(this)
                .load(imageURL)
                .centerCrop()
                .into(imageQr)

    }

    private fun showCreateApplicationLayout() {
        layoutQr.visibility = GONE
        layotutCreateApplication.visibility = VISIBLE

    }


}
