package am.xtech.app16.presentation.ui.application_details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import am.xtech.app16.R
import am.xtech.app16.data.model.Application
import am.xtech.app16.presentation.base.BaseFragment
import am.xtech.app16.presentation.ui.application_details.adapter.ApplicationItemAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ApplicationDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance() = ApplicationDetailsFragment()
    }

    private lateinit var viewModel: ApplicationDetailsViewModel
    private val adapter = ApplicationItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =   inflater.inflate(R.layout.fragment_application_details, container, false)

        val rv = view.findViewById<RecyclerView>(R.id.rv_application_details)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter




        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ApplicationDetailsViewModel::class.java)

        val application = arguments?.getSerializable("argument_application") as Application

        val data = ArrayList<ModelDetailItem>()
        data.add(ModelDetailItem(getString(R.string.hint_first_name ), application.firstName))
        data.add(ModelDetailItem(getString(R.string.hint_last_name ), application.lastName))
        data.add(ModelDetailItem(getString(R.string.hint_middle_name ), application.middleName))
        data.add(ModelDetailItem(getString(R.string.hint_exit_address ), application.outAddress))
        data.add(ModelDetailItem(getString(R.string.hint_exit_time ), application.outDatetime))
        data.add(ModelDetailItem(getString(R.string.hint_return_time ), application.plannedReturnDatetime))
        data.add(ModelDetailItem(getString(R.string.hint_visiting_address_name ), application.visitingAddressAndName))
        data.add(ModelDetailItem(getString(R.string.hint_visiting_reason ), application.visitingReason))

        adapter.submitList(data)

    }

}
