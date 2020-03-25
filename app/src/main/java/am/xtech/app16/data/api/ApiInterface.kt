package am.xtech.app16.data.api

import am.xtech.app16.data.model.Application
import am.xtech.app16.data.model.BaseDataModel
import am.xtech.app16.data.model.CreateApplication
import am.xtech.app16.utils.GenericApiResponse
import androidx.lifecycle.LiveData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("v1/applications")
    fun registerApplication(@Body application: CreateApplication): LiveData<GenericApiResponse<BaseDataModel<Application>>>

    @GET("v1/applications/current")
    fun getCurrentApplication(@Query("device_token") deviceId: String): LiveData<GenericApiResponse<BaseDataModel<Application>>>

    @POST("v1/applications/finish")
    fun finishApplication(@Query("device_token") deviceId: String):LiveData<GenericApiResponse<BaseDataModel<Application>>>

    @GET("v1/applications")
    fun getHistory(@Query("device_token") deviceId: String):LiveData<GenericApiResponse<BaseDataModel<List<Application>>>>


//    @GET("")
//    fun finishApplication(): LiveData<GenericApiResponse<List<Any>>>
//
//    @GET("")
//    fun finishApplication(): LiveData<GenericApiResponse<List<Any>>>
//
//    @GET("")
//    fun finishApplication(): LiveData<GenericApiResponse<List<Any>>>
}