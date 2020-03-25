package am.xtech.app16.presentation.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){

    var mCallback:FragmentEventListener?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mCallback = activity as FragmentEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                activity.toString() + " must implement OnReviewSelectedListener"
            )
        }
    }

    override fun onDetach() {
        mCallback = null
        super.onDetach()
    }

    interface FragmentEventListener {
        fun onProgressStateChange(show: Boolean)
    }
}