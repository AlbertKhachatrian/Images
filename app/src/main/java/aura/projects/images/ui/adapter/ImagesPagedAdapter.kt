package aura.projects.images.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import aura.projects.domain.model.Image
import aura.projects.images.R
import aura.projects.images.databinding.ItemImageBinding
import com.bumptech.glide.Glide
import java.util.*


class ImagesPagedAdapter : PagingDataAdapter<Image, ImagesPagedAdapter.ImagesViewHolder>(diffCallback) {

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

    inner class ImagesViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Image){
            val array: Array<String> = binding.root.context.resources.getStringArray(R.array.rainbow)
            val randomStr = array[Random().nextInt(array.size)]

            Glide.with(binding.root.context).load(item.url).placeholder(ColorDrawable(Color.parseColor(randomStr))).into(binding.root)
        }
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        return ImagesViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}