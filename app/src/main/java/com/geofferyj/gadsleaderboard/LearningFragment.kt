package com.geofferyj.gadsleaderboard

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_learning.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LearningFragment : Fragment(R.layout.fragment_learning) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gadsapi.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val usersApi = retrofit.create(ApiService::class.java)
        usersApi.fetchLerners().enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(
                call: Call<List<UserInfo>>,
                response: Response<List<UserInfo>>
            ) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                Log.d("geo", "on Failure")

            }
        })

    }

    private fun showData(userInfo: List<UserInfo>) {
        val adapter = RVAdapter(userInfo, R.drawable.learner)
        rv_learn.adapter = adapter
        rv_learn.layoutManager = LinearLayoutManager(activity)
    }

}