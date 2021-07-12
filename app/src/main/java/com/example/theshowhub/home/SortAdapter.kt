package com.example.theshowhub.home

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.theshowhub.databinding.ItemOptionBinding

class SortAdapter(
    context: Activity, resourceId: Int, private val sortOptions: List<SortOption>
): ArrayAdapter<SortOption>(context, resourceId, 0, sortOptions) {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        createItemView(position, convertView)

    override fun getItem(position: Int): SortOption = sortOptions[position]

    override fun getDropDownView(
        position: Int, convertView: View?, parent: ViewGroup?
    ): View = createItemView(position, convertView)

    private fun createItemView(position: Int, convertView: View?): View {
        val sortOption = sortOptions[position]

        val itemOptionBinding = if (convertView != null)
            ItemOptionBinding.bind(convertView)
        else
            ItemOptionBinding.inflate(layoutInflater)

        itemOptionBinding.labelTextView.text = context.getString(sortOption.label)

        return itemOptionBinding.root
    }

}