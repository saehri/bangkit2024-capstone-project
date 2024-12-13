package com.example.kulit.result

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kulit.R
import com.example.kulit.response.FacialWash
import com.example.kulit.response.Moisturizer
import com.example.kulit.response.Sunscreen

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList: List<Any> = listOf()

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.ivProductImage)
        val productName: TextView = view.findViewById(R.id.tvProductName)
        val productLink: Button = view.findViewById(R.id.btnProductLink)
    }

    fun submitList(products: List<Any>) {
        this.productList = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        when (product) {
            is FacialWash -> {
                holder.productName.text = product.name
                Glide.with(holder.itemView.context).load(product.image).into(holder.productImage)
                holder.productLink.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.link))
                    holder.itemView.context.startActivity(intent)
                }
            }
            is Sunscreen -> {
                holder.productName.text = product.name
                Glide.with(holder.itemView.context).load(product.image).into(holder.productImage)
                holder.productLink.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.link))
                    holder.itemView.context.startActivity(intent)
                }
            }
            is Moisturizer -> {
                holder.productName.text = product.name
                Glide.with(holder.itemView.context).load(product.image).into(holder.productImage)
                holder.productLink.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.link))
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}
