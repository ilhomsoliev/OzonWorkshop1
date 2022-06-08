package ru.ozon.route256.workshop1.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ozon.route256.workshop1.R
import ru.ozon.route256.workshop1.presentation.viewObject.ProductInListVO

class Adapter(private val context: Context, val onClick: (String) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    var listOfProducts = listOf<ProductInListVO>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val priceTV: TextView = itemView.findViewById(R.id.priceTV)
        val productIV: ImageView =itemView.findViewById(R.id.productIV)
        val ratingView: RatingBar = itemView.findViewById(R.id.ratingView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listOfProducts[position].also { item ->
            holder.itemView.setOnClickListener {
                onClick(item.guid)
            }
            holder.nameTV.text = item.name
            holder.priceTV.text = item.price
            holder.ratingView.rating = item.rating.toFloat()
            Glide.with(context).load(item.image).into(holder.productIV)
        }
    }

    override fun getItemCount(): Int = listOfProducts.size
}