package am.xtech.app16.app

import am.xtech.app16.data.api.APIService
import am.xtech.app16.data.api.ApiInterface
import android.app.Application

class App : Application() {

    companion object {
        lateinit var sApiService: ApiInterface
        lateinit var sUserManager: UserManager
    }

    override fun onCreate() {
        super.onCreate()
        sUserManager = UserManager(this)
        sApiService = APIService.getInstance()

    }
}