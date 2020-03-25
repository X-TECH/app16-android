package am.xtech.app16.presentation.ui.create_application

import am.xtech.app16.R
import am.xtech.app16.app.App
import am.xtech.app16.data.model.CreateApplication
import am.xtech.app16.presentation.base.BaseFragment
import am.xtech.app16.presentation.ui.create_application.state.CreateFormStateEvent
import am.xtech.app16.presentation.ui.create_application.state.CreateFormViewState
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class CreateApplicationFragment : BaseFragment() {

    companion object {
        fun newInstance() = CreateApplicationFragment()
    }

    private lateinit var viewModel: CreateApplicationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_form, container, false)

        val buttonCreateApplication = view.findViewById<View>(R.id.btn_create_for_create)

//        val latExitTime = view.findViewById<TextInputLayout>(R.id.lay_create_form_exit_time)
//        val latExitLocation = view.findViewById<TextInputLayout>(R.id.lay_create_form_exit_location)
//        val latReturnTime = view.findViewById<TextInputLayout>(R.id.lay_create_form_return_time)
//        val latVisitingAddress =
//            view.findViewById<TextInputLayout>(R.id.lay_create_form_visiting_address)

//        val latVisitingReason =
//            view.findViewById<TextInputLayout>(R.id.lay_create_form_visiting_reason)

        val etOutTime = view.findViewById<TextInputEditText>(R.id.et_create_form_out_time)
        val etOutLocation = view.findViewById<TextInputEditText>(R.id.et_create_form_out_location)
        val etReturnTime = view.findViewById<TextInputEditText>(R.id.et_create_form_return_time)
        val etVisitingAddress = view.findViewById<TextInputEditText>(R.id.et_create_form_visiting_address)
        val etVisitingReason = view.findViewById<TextInputEditText>(R.id.et_create_form_visiting_reason)

        etOutTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                etOutTime.setText(SimpleDateFormat("HH:mm").format(cal.time))
            }
            TimePickerDialog(
                activity as Context,
                R.style.MyTimePickerDialogTheme,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        etReturnTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                etReturnTime.setText(SimpleDateFormat("HH:mm").format(cal.time))
            }
            TimePickerDialog(
                activity as Context,
                R.style.MyTimePickerDialogTheme,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        buttonCreateApplication.setOnClickListener {
            triggerRegisterApplication(

                CreateApplication(
                    deviceToken = App.sUserManager.getUUID(),
                    firstName = App.sUserManager.getUser()?.firstName,
                    lastName = App.sUserManager.getUser()?.lastName,
                    middleName = App.sUserManager.getUser()?.middleName,
                    outAddress = etOutLocation.text.toString(),
                    outDatetime = "2020-03-25 08:09:01",
                    visitingAddressAndName = etVisitingAddress.text.toString(),
                    visitingReason =etVisitingReason.text.toString(),
                    plannedReturnDatetime = "2020-03-25 16:13:01"
                )
            )
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateApplicationViewModel::class.java)
        subscribeObservers()
    }

    private fun triggerRegisterApplication(application: CreateApplication) {
        viewModel.setStateEvent(CreateFormStateEvent.registerApplication(application))
    }

    fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { dataState ->

            dataState.data?.let { searchViewState ->

                searchViewState.peekContent().let {
                    it.application.let { result ->
                        viewModel.setViewState(CreateFormViewState(result))
                    }
                }
            }

            dataState.message?.let {
                it
            }

            dataState.loading.let {
                mCallback?.onProgressStateChange(it)
            }
        })

//        viewModel.viewState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { viewState ->
//            viewState.apply { }?.let {
//                findNavController().navigate(R.id.navigation_home)
//
//            }
//        })
    }

}
