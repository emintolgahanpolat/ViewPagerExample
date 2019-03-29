package com.emintolgahanpolat.viewpagerexample


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import android.text.Html
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private var adapter: CustomViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val arrayList: ArrayList<CategoryModel> = ArrayList<CategoryModel>()

        for (i in 1..19) {
            arrayList.add(CategoryModel("Cat $i", R.drawable.ic_favorite_black_24dp))
        }


        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        adapter = CustomViewPagerAdapter(this, 2, 2, arrayList)
        viewPager.adapter = adapter




        addBottomDots(0)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {


            }

            override fun onPageSelected(position: Int) {
                addBottomDots(position)
            }

        })
    }

    private fun addBottomDots(currentPage: Int) {
        var dots = arrayOfNulls<TextView>(adapter!!.count)

        val colorsActive = ContextCompat.getColor(this, R.color.colorAccent);
        val colorsInactive = ContextCompat.getColor(this, R.color.colorPrimary);

        llPageDots.removeAllViews()
        for (i in 0 until adapter!!.count) {
            dots[i] = TextView(this)
            dots[i]!!.setText(Html.fromHtml("&#8226;"))
            dots[i]!!.setTextSize(35F)
            dots[i]!!.setTextColor(colorsInactive)
            llPageDots.addView(dots[i])
        }

        if (adapter!!.count > 0)
            dots[currentPage]!!.setTextColor(colorsActive)
    }


}
