
package com.example.android.marsphotos

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.network.model.Fruit
import com.example.android.marsphotos.overview.FruitApiStatus
import com.example.android.marsphotos.overview.FruitsAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Fruit>?) {
    val adapter = recyclerView.adapter as FruitsAdapter
    adapter.submitList(data)
}

@BindingAdapter("fruitApiStatus")
fun bindStatus(statusImageView: ImageView, status: FruitApiStatus) {
    when (status) {
        FruitApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FruitApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FruitApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
