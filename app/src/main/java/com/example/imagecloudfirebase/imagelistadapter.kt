package com.example.imagecloudfirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagecloudfirebase.databinding.EachItemBinding

class imagelistadapter(private var mlist: List<String>) :
    RecyclerView.Adapter<imagelistadapter.ImageViewHolder>() {

    inner class ImageViewHolder(var binding: EachItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =EachItemBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mlist.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        with(holder.binding){
            with(mlist[position]){


            }
        }
    }
}