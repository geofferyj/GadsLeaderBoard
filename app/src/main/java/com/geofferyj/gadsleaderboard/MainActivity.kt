package com.geofferyj.gadsleaderboard

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val fragments = arrayListOf<Fragment>(LearningFragment(), SkillsFragment())

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragments)

        fragment_view_pager.adapter = adapter

        TabLayoutMediator(tab_layout, fragment_view_pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Learning Leaders"
                1 -> tab.text = "Skill IQ Leaders"
            }
        }.attach()
        val intent = Intent(this, SubmitActivity::class.java)
        btn_submit2.setOnClickListener {
            startActivity(intent)
        }
    }
}