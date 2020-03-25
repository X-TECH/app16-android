package am.xtech.app16.presentation.ui.splash

import am.xtech.app16.app.App
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    val navigate = MutableLiveData<String>()

    fun getState(){

        if(App.sUserManager.getUser() == null){
            navigate.postValue(NavConstant.NAVIGATION_LOGIN)
        }else{
            navigate.postValue(NavConstant.NAVIGATION_HOME)
        }
    }





}
