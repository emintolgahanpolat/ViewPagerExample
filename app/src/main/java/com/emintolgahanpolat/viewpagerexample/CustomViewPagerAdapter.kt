package com.emintolgahanpolat.viewpagerexample

import android.content.Context
import android.graphics.Color
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.widget.*
import kotlinx.android.synthetic.main.item_card.view.*
import java.io.*

class CustomViewPagerAdapter(
    private val mContext: Context,
    val row: Int,
    val col: Int,
    val arrayList: ArrayList<CategoryModel> = ArrayList<CategoryModel>()
) : PagerAdapter(), View.OnClickListener {
    override fun onClick(view: View?) {

        Toast.makeText(mContext, "id: ${view?.id}", Toast.LENGTH_SHORT).show()

    }

    var padding_in_px =mContext.resources.getDimension(R.dimen.card_margin).toInt()

    //mContext.resources.getDimension(R.dimen.dots_font_size).toInt()
    private val count: Int = Math.ceil((arrayList.size).toDouble() / (row * col)).toInt()


    override fun instantiateItem(collection: ViewGroup, position: Int): Any {


        val rootView = LinearLayout(mContext)
        rootView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        rootView.weightSum=2F
        rootView.setPadding(padding_in_px, padding_in_px, padding_in_px, padding_in_px)

        rootView.orientation = LinearLayout.VERTICAL

        var itemPosition = 0
        var itemIndex = 0
        for (i in 0 until row) {

            if (itemIndex < arrayList.size - 1) {

                val rowView = LinearLayout(mContext)
                rowView.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                   0,1F
                )
                rowView.weightSum=2F
                rowView.orientation = LinearLayout.HORIZONTAL
                rowView.layoutParams.apply { (this as LinearLayout.LayoutParams).weight = 1f }



                for (j in 0 until col) {
                    itemIndex = itemPosition + (position * col * row)
                    if (itemIndex < arrayList.size) {


                        if (j > 0) {
                            val vSeperator = View(mContext)
                            vSeperator.layoutParams = LinearLayout.LayoutParams(
                                1,
                                ViewGroup.LayoutParams.MATCH_PARENT

                            )
                            vSeperator.setBackgroundColor(Color.parseColor("#B3B3B3"))
                            rowView.addView(vSeperator)

                        }

                        val modelObject = arrayList[itemIndex]

                        val itemView = FrameLayout(mContext)
                        itemView.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT,1F
                        )

                        itemView.id=j
                        itemView.setOnClickListener(this)
                        val view = LayoutInflater.from(mContext).inflate( R.layout.item_card,collection, false)

                        view.ivIcon.setImageResource(modelObject.icon)
                        view.tvTitle.text=modelObject.name

                        itemView.addView(view)
                        rowView.addView(itemView)



                        itemPosition += 1
                    }

                }

                if (i > 0) {
                    val vSeperator = View(mContext)
                    vSeperator.layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1

                    )
                    vSeperator.setBackgroundColor(Color.parseColor("#B3B3B3"))
                    rootView.addView(vSeperator)
                }

                rootView.addView(rowView)
            }
        }



        collection.addView(rootView)
        return rootView
    }


    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {

        collection.removeView(view as View)
    }

    override fun getCount(): Int {

        return count
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }


    private fun saveToInternalStorage(bitmapImage: Bitmap) {


        val mypath = File(mContext.filesDir, "profile.jpg")

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }
}



