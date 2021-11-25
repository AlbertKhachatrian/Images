package aura.projects.images.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import aura.projects.core.BaseAdapter
import aura.projects.core.BaseViewHolder
import aura.projects.domain.model.Image
import aura.projects.images.R
import aura.projects.images.databinding.ItemImageBinding
import com.bumptech.glide.Glide
import java.lang.NullPointerException
import java.util.*


class ImagesPagedAdapter : BaseAdapter<ItemImageBinding, Image, ImagesPagedAdapter.ImagesViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Image>() {

            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }

    inner class ImagesViewHolder(private val binding: ItemImageBinding) : BaseViewHolder<Image, ItemImageBinding>(binding) {

        override fun bind(item: Image){
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))


            try {
                Glide.with(binding.root.context)
                    .load("http://static.wizl.itech-mobile.ru" + item.url)
                    .placeholder(ColorDrawable(color)).into(binding.image)
            }catch (e: NullPointerException){
                e.printStackTrace()
            }
        }
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}