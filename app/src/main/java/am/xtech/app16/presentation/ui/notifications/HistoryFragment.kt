package am.xtech.app16.presentation.ui.notifications

import am.xtech.app16.R
import am.xtech.app16.app.App
import am.xtech.app16.presentation.base.BaseFragment
import am.xtech.app16.presentation.ui.notifications.adapter.HistoryItemAdapter
import am.xtech.app16.presentation.ui.notifications.state.HistoryStateEvent
import am.xtech.app16.presentation.ui.notifications.state.HistoryViewState
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment : BaseFragment() {

    private lateinit var viewModel: HistoryViewModel
    private lateinit var adapter: HistoryItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)

        adapter = HistoryItemAdapter()
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
                adapter.submitList(applicationList ?: ArrayList())
            }
        })
    }

}
