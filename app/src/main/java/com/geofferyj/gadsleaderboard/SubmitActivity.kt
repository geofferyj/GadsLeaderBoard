package com.geofferyj.gadsleaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.dialog_confirm.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class SubmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val firstName = firstname.text.toString()
        val lastName = lastname.text.toString()
        val email = email.text.toString()
        val github = github.text.toString()


        btn_submit2.setOnClickListener {

            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_confirm, null)

            val dialogBuilder = AlertDialog.Builder(this)
                .setView(dialogView)

            val dialog = dialogBuilder.show()

            dialogView.dialog_submit_btn.setOnClickListener {

                dialog.dismiss()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://docs.google.com/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()

                val postForm = retrofit.create(ApiService::class.java)

                postForm.submit(firstName, lastName, email, github)
                    .enqueue(object : Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {

                            if (response.isSuccessful) {

                                mShowDialog(R.layout.dialog_success)

                            } else {

                                mShowDialog(R.layout.dialog_fail)

                            }

                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {

                            mShowDialog(R.layout.dialog_fail)

                        }

                    })


            }

            dialogView.dialog_cancel_btn.setOnClickListener {

                dialog.dismiss()
            }
        }


    }

    private fun mShowDialog(layout: Int) {
        val dialogView = LayoutInflater.from(this).inflate(layout, null)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)

        dialogBuilder.show()

    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true

    }


}


