package am.xtech.app16.presentation.ui.login

import am.xtech.app16.R
import am.xtech.app16.app.App
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _errorList = HashMap<String, Int?>()
    val errorList = MutableLiveData<Map<String, Int?>>()
    val navigateNext = MutableLiveData<Boolean>()

    fun saveUserData(fName: String?, lName: String?, mName: String?) {

        _errorList.clear()
        var flagIsInvalid = false

        if (isInvalid(fName)) {
            flagIsInvalid = true
            _errorList["first_name"] = R.string.field_is_required
        }else{
            _errorList["first_name"] = null
        }

        if (isInvalid(lName)) {
            flagIsInvalid = true
            _errorList["last_name"] = R.string.field_is_required
        }else{
            _errorList["last_name"] =null
        }

        if (isInvalid(mName)) {
            flagIsInvalid = true
            _errorList["middle_name"] = R.string.field_is_required
        }else{
            _errorList["middle_name"] =null
        }

        if (flagIsInvalid) {
            errorList.postValue(_errorList)
            return
        }

        if(fName!= null && lName!= null && mName!= null){
            App.sUserManager.saveUserData(fName,lName,mName)
            navigateNext.postValue(true)
        }
    }

    private fun isInvalid(text: String?): Boolean {
        text ?: return true
        return text.isEmpty() || text.isBlank()
    }


}