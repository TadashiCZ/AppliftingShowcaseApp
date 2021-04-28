package cz.tadeasvalenta.appliftingshowcaseapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Capsule
import cz.tadeasvalenta.appliftingshowcaseapp.databinding.CapsuleItemBinding

class CapsuleViewHolder(private val binding: CapsuleItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(capsule: Capsule) {
        binding.tvSerial.text = capsule.serial
        binding.tvType.text = capsule.type
        binding.tvLandLandings.text = capsule.landLandings.toString()
        binding.tvLastUpdate.text = capsule.lastUpdate
        binding.tvLaunches.text = capsule.launches.size.toString()
        binding.tvReuse.text = capsule.reuseCount.toString()
        binding.tvWaterLandings.text = capsule.waterLandings.toString()
        binding.tvStatus.text = capsule.status
    }
}
