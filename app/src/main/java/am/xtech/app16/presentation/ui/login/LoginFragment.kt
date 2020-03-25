package am.xtech.app16.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import am.xtech.app16.R
import am.xtech.app16.presentation.base.BaseFragment
import android.os.BaseBundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : BaseFragment() {

    private lateinit var homeViewModel: LoginViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val layFirstName: TextInputLayout = root.findViewById(R.id.lay_login_first_name)
        val layLastName: TextInputLayout = root.findViewById(R.id.lay_login_last_name)
        val layMiddleName: TextInputLayout = root.findViewById(R.id.lay_login_middle_name)

        val etFirstName: TextInputEditText = root.findViewById(R.id.et_login_first_name)
        val etLastName: TextInputEditText = root.findViewById(R.id.et_login_last_name)
        val etMiddleName: TextInputEditText = root.findViewById(R.id.et_login_middle_name)

        val btnNext: View = root.findViewById(R.id.btn_login_next)

        btnNext.setOnClickListener {
            homeViewModel.saveUserData(etFirstName.text.toString(),etLastName.text.toString(),etMiddleName.text.toString())
        }

        homeViewModel.errorList.observe(viewLifecycleOwner, Observer {errorList->

            val fNameResId = errorList["first_name"]
            val lNameResId = errorList["last_name"]
            val mNameResId = errorList["middle_name"]

            if(fNameResId!= null){
                layFirstName.error = getString(fNameResId)
            }else{
                layFirstName.error = ""
            }

            if(lNameResId!= null){
                layLastName.error = getString(lNameResId)
            }else{
                layLastName.error=""
            }

            if(mNameResId!= null){
                layMiddleName.error = getString(mNameResId)
            }else{
                layMiddleName.error = ""
            }

        })

        homeViewModel.navigateNext.observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(R.id.action_navigation_login_to_navigation_home)
            }
        })

        return root
    }



}
