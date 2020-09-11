package com.geofferyj.gadsleaderboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_skills.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SkillsFragment : Fragment(R.layout.fragment_skills) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gadsapi.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val usersApi = retrofit.create(ApiService::class.java)
        usersApi.fetchSkillIQ().enqueue(object : Callback<List<UserInfo>> {
            override fun onResponse(
                call: Call<List<UserInfo>>,
                response: Response<List<UserInfo>>
            ) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {

            }
        })

    }

    private fun showData(userInfo: List<UserInfo>) {
        val adapter = RVAdapter(userInfo, R.drawable.skill)
        rv_skills.adapter = adapter
        rv_skills.layoutManager = LinearLayoutManager(activity)
    }


}

