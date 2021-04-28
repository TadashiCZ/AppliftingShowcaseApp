package cz.tadeasvalenta.appliftingshowcaseapp.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Landpad
import cz.tadeasvalenta.appliftingshowcaseapp.databinding.LandpadItemBinding

class LandpadViewHolder(
    private val binding: LandpadItemBinding,
    private val listener: LandpadAdapter.LandpadItemListener
) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var landpad: Landpad

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(landpad: Landpad) {
        this.landpad = landpad
        binding.tvLandingAttempts.text = landpad.landingAttempts.toString()
        binding.tvLandingSuccess.text = landpad.landingSuccesses.toString()
        binding.tvLaunches.text = landpad.launches.size.toString()
        binding.tvTitle.text = landpad.name
    }

    override fun onClick(v: View?) {
        listener.onClickedLandpad(landpad.id)
    }
}
