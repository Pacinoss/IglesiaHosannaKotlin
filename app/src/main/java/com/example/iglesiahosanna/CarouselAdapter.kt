package com.example.iglesiahosanna

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import coil.load

class CarouselAdapter(private val imageList: List<String>) : RecyclerView.Adapter<CarouselAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.carousel_image_view)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageList[position]
        holder.progressBar.visibility = View.VISIBLE
        holder.imageView.load(imageUrl) {
            placeholder(R.drawable.anuncio_placeholder)
            error(R.drawable.anuncio_placeholder)
            listener(
                onSuccess = { _, _ ->
                    holder.progressBar.visibility = View.GONE
                },
                onError = { _, _ ->
                    holder.progressBar.visibility = View.GONE
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}