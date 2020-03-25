package am.xtech.app16.presentation.ui.history

import am.xtech.app16.R
import am.xtech.app16.app.App
import am.xtech.app16.data.model.Application
import am.xtech.app16.presentation.base.BaseFragment
import am.xtech.app16.presentation.ui.history.adapter.HistoryItemAdapter
import am.xtech.app16.presentation.ui.history.state.HistoryStateEvent
import am.xtech.app16.presentation.ui.history.state.HistoryViewState
import android.os.Bundle
import android.service.voice.VoiceInteractionService
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment : BaseFragment(), HistoryItemAdapter.EventListener {

    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: HistoryItemAdapter
    private lateinit var textEmpty: TextView
   // val application, Application

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)

        adapter = HistoryItemAdapter(this)
        textEmpty= root.findViewById(R.id.tv_history_empty_text)
        val rv: RecyclerView = root.findViewById(R.id.rv_history)
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        subscribeObservers()
        triggerSearchData()
    }

    private fun triggerSearchData() {
        viewModel.setStateEvent(HistoryStateEvent.getHistory(App.sUserManager.getUUID()))
    }

    fun subscribeObservers() {

        viewModel.dataState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { dataState ->

            dataState.data?.let { viewstate ->
                viewstate.peekContent().let {
                    viewModel.setViewState(HistoryViewState(it.applicationList))
                }
            }

            dataState.message?.let {

            }

            dataState.loading.let {
                mCallback?.onProgressStateChange(it)
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, androidx.lifecycle.Observer { viewState ->
            viewState.apply {

                if(this.applicationList.isNullOrEmpty() ){
                    textEmpty.visibility =  View.VISIBLE
                }else{
                    textEmpty.visibility =  View.GONE
                }

                adapter.submitList(applicationList ?: ArrayList())
            }
        })
    }

    override fun onItemClicked(item: Application) {
        val  data = bundleOf("argument_application" to item)
        findNavController().navigate(R.id.action_navigation_history_to_navigation_application_details,data)    }

}
