package com.example.actividad3a.data.remotes

import com.example.actividad3a.data.models.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiManager {
    fun addUser(userData: UserRequest, onResult: (UserRequest?) -> Unit) {
        val retrofit = ApiRest.buildService(ApiService::class.java)
        retrofit.addUser(userData).enqueue(
            object : Callback<UserRequest> {
                override fun onFailure(call: Call<UserRequest>, t: Throwable) {
                    onResult(null)
                }

                override fun onResponse(call: Call<UserRequest>, response: Response<UserRequest>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}