package com.example.beehivedata.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.beehivedata.R
import kotlinx.android.synthetic.main.item_custom_spinner.view.*


class CustomSpinnerAdapter(ctx: Context, beehives: ArrayList<String>) : ArrayAdapter<String>(ctx, 0, beehives) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    private fun createItemView(position: Int, recycledView: View?, parent: ViewGroup):View {
        val beehive = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.item_custom_spinner,
            parent,
            false
        )

        beehive?.let {
            view.spinnerText.text = beehive
        }
        return view
    }
}