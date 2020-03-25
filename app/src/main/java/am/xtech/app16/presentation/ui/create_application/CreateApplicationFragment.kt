package am.xtech.app16.presentation.ui.create_application

import am.xtech.app16.R
import am.xtech.app16.app.App
import am.xtech.app16.data.model.CreateApplication
import am.xtech.app16.data.model.error.ResponseError
import am.xtech.app16.presentation.base.BaseFragment
import am.xtech.app16.presentation.ui.create_application.state.CreateFormStateEvent
import am.xtech.app16.presentation.ui.create_application.state.CreateFormViewState
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class CreateApplicationFragment : BaseFragment() {

    companion object {
        fun newInstance() = CreateApplicationFragment()
    }

    private lateinit var viewModel: CreateApplicationViewModel
    private lateinit var latExitTime: TextInputLayout
    private lateinit var latExitLocation: TextInputLayout
    private lateinit var latReturnTime: TextInputLayout
    private lateinit var latVisitingAddress: TextInputLayout
    private lateinit var latVisitingReason: TextInputLayout

    private var startTime: String? = null
    private var endTime: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_form, container, false)

        val buttonCreateApplication = view.findViewById<View>(R.id.btn_create_for_create)

        latExitTime = view.findViewById<TextInputLayout>(R.id.lay_create_form_exit_time)
        latExitLocation = view.findViewById<TextInputLayout>(R.id.lay_create_form_exit_location)
        latReturnTime = view.findViewById<TextInputLayout>(R.id.lay_create_form_return_time)
        latVisitingAddress =
            view.findViewById<TextInputLayout>(R.id.lay_create_form_visiting_address)
        latVisitingReason = view.findViewById<TextInputLayout>(R.id.lay_create_form_visiting_reason)

        val etOutTime = view.findViewById<TextInputEditText>(R.id.et_create_form_out_time)
        val etOutLocation = view.findViewById<TextInputEditText>(R.id.et_create_form_out_location)
        val etReturnTime = view.findViewById<TextInputEditText>(R.id.et_create_form_return_time)
        val etVisitingAddress =
            view.findViewById<TextInputEditText>(R.id.et_create_form_visiting_address)
        val etVisitingReason =
            view.findViewById<TextInputEditText>(R.id.et_create_form_visiting_reason)

        etOutTime.removeErrorIfTyping(latExitTime)
        etOutLocation.removeErrorIfTyping(latExitLocation)
        etReturnTime.removeErrorIfTyping(latReturnTime)
        etVisitingAddress.removeErrorIfTyping(latVisitingAddress)
        etVisitingReason.removeErrorIfTyping(latVisitingReason)

        etOutTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->

                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                etOutTime.setText(SimpleDateFormat("HH:mm").format(cal.time))
                startTime = SimpleDateFormat(am.xtech.app16.utils.DateUtils.datePattern).format(cal.time)

            }
            TimePickerDialog(
                activity as Context,
                R.style.ThemeDialog,
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
                endTime =
                    SimpleDateFormat(am.xtech.app16.utils.DateUtils.datePattern).format(cal.time)

            }
            TimePickerDialog(
                activity as Context,
                R.style.ThemeDialog,
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
                    outDatetime = startTime,
                    visitingAddressAndName = etVisitingAddress.text.toString(),
                    visitingReason = etVisitingReason.text.toString(),
                    plannedReturnDatetime = endTime
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

                try {


                    val errorResponse = Gson().fromJson(it.peekContent(), ResponseError::class.java)
                    errorResponse?.error?.fields?.forEach { item ->
                        item.key ?: return@forEach
                        when (item.key) {

                            "out_datetime" -> {
                                latExitTime.error = getErrorMessage(item.messages)
                            }

                            "out_address" -> {
                                latExitLocation.error = getErrorMessage(item.messages)
                            }

                            "planned_return_datetime" -> {
                                latReturnTime.error = getErrorMessage(item.messages)
                            }
                            "visiting_address_and_name" -> {
                                latVisitingAddress.error = getErrorMessage(item.messages)
                            }

                            "visiting_reason" -> {
                                latVisitingReason.error = getErrorMessage(item.messages)
                            }
                        }
                    }
                } catch (e: Exception) {

                }
            }

            dataState.loading.let {
                mCallback?.onProgressStateChange(it)
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { viewState ->
            viewState.apply { }?.let {

                findNavController().popBackStack()


            }
        })
    }

    fun EditText.removeErrorIfTyping(textInputLayout: TextInputLayout) {

        this.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textInputLayout.error = null
            }
        })
    }

    private fun getErrorMessage(messages: List<String>?): String {

        var text = ""
        messages?.forEach {
            text = "$text $it"
        }

        return text
    }

}
