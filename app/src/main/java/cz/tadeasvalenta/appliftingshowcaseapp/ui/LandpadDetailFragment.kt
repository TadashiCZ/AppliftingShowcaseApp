package cz.tadeasvalenta.appliftingshowcaseapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Landpad
import cz.tadeasvalenta.appliftingshowcaseapp.databinding.FragmentLandpadDetailBinding
import cz.tadeasvalenta.appliftingshowcaseapp.ui.viewModel.LandpadViewModel
import cz.tadeasvalenta.appliftingshowcaseapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandpadDetailFragment : Fragment() {
    private val viewModel: LandpadViewModel by viewModels()
    private lateinit var binding: FragmentLandpadDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandpadDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.landpad.observe(
            viewLifecycleOwner,
            {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        bindLandpad(it.data!!)
                        binding.progressBar.visibility = View.GONE
                        binding.clLandpad.visibility = View.VISIBLE
                    }

                    Resource.Status.ERROR ->
                        Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                    Resource.Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.clLandpad.visibility = View.GONE
                    }
                }
            }
        )
    }

    fun bindLandpad(landpad: Landpad) {
        binding.tvLocality.text = landpad.locality
        binding.tvRegion.text = landpad.region
        binding.tvType.text = landpad.type
        binding.tvName.text = landpad.name
        binding.tvFullName.text = landpad.fullName
        binding.tvLaunches.text = landpad.launches.size.toString()
        binding.tvLandingAttempts.text = landpad.landingAttempts.toString()
        binding.tvLandingSuccess.text = landpad.landingSuccesses.toString()
        binding.tvStatus.text = landpad.status
        binding.tvDescription.text = landpad.details
        binding.tvWikipedia.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(landpad.wikipedia))
            startActivity(browserIntent)
        }
    }
}
