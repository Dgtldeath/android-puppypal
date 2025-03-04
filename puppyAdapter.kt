package com.adamgumm.puppypal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adamgumm.puppypal.databinding.ItemPuppyBinding

class PuppyAdapter(private val puppies: List<Puppy>) :
    RecyclerView.Adapter<PuppyAdapter.PuppyViewHolder>() {

    class PuppyViewHolder(val binding: ItemPuppyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuppyViewHolder {
        val binding = ItemPuppyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PuppyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PuppyViewHolder, position: Int) {
        val puppy = puppies[position]
        holder.binding.puppyName.text = puppy.name
        holder.binding.puppyDetails.text = buildString {
            append("${puppy.breed}, ${puppy.age} years")
        }

        holder.binding.puppyDistance.text = buildString {
            // TODO: using user location: "miles from you" v2
            puppy.distance?.let { distance ->
                append("${String.format("%.1f", distance)} miles from Columbus, Ohio")
            } ?: append(" - Distance calculation failed")
        }
    }

    override fun getItemCount() = puppies.size
}
