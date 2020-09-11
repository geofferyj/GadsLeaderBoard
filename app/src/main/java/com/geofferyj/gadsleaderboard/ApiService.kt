package com.geofferyj.gadsleaderboard

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/api/hours")
    fun fetchLerners(): Call<List<UserInfo>>

    @GET("/api/skilliq")
    fun fetchSkillIQ(): Call<List<UserInfo>>


    @POST("/forms/u/0/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submit(
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.1824927963") email: String,
        @Field("entry.284483984") github: String
    ): Call<String>

}