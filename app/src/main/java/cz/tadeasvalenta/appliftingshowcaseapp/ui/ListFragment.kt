package cz.tadeasvalenta.appliftingshowcaseapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cz.tadeasvalenta.appliftingshowcaseapp.R
import cz.tadeasvalenta.appliftingshowcaseapp.data.Type
import cz.tadeasvalenta.appliftingshowcaseapp.databinding.FragmentListBinding
import cz.tadeasvalenta.appliftingshowcaseapp.ui.adapter.CapsuleAdapter
import cz.tadeasvalenta.appliftingshowcaseapp.ui.adapter.LandpadAdapter
import cz.tadeasvalenta.appliftingshowcaseapp.ui.viewModel.CapsulesViewModel
import cz.tadeasvalenta.appliftingshowcaseapp.ui.viewModel.LandpadViewModel
import cz.tadeasvalenta.appliftingshowcaseapp.ui.viewModel.StateViewModel
import cz.tadeasvalenta.appliftingshowcaseapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ListFragment :
    Fragment(),
    LandpadAdapter.LandpadItemListener,
    SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private val landPadViewModel: LandpadViewModel by viewModels()
    private val capsulesViewModel: CapsulesViewModel by viewModels()
    private val stateViewModel: StateViewModel by viewModels {
        SavedStateViewModelFactory(activity?.application, this)
    }

    private lateinit var capsuleAdapter: CapsuleAdapter
    private lateinit var landpadAdapter: LandpadAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setType(stateViewModel.getChosenList())

        binding.btnCapsules.setOnClickListener {
            setType(Type.CAPSULES)
            binding.svFilter.onActionViewCollapsed()
        }
        binding.btnLandpads.setOnClickListener {
            setType(Type.LANDPADS)
            binding.svFilter.onActionViewCollapsed()
        }

        binding.svFilter.setOnQueryTextListener(this)
    }

    private fun setupRecyclerView(type: Type) {
        if (type == Type.CAPSULES) {
            capsuleAdapter = CapsuleAdapter()
            binding.rvList.layoutManager = LinearLayoutManager(requireContext())
            binding.rvList.adapter = capsuleAdapter
        } else {
            landpadAdapter = LandpadAdapter(this)
            binding.rvList.layoutManager = LinearLayoutManager(requireContext())
            binding.rvList.adapter = landpadAdapter
        }
    }

    private fun setupObservers(type: Type) {
        if (type == Type.LANDPADS) {
            landPadViewModel.landpads.observe(
                viewLifecycleOwner,
                {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            binding.pbLoader.visibility = View.GONE
                            if (!it.data.isNullOrEmpty()) {
                                landpadAdapter.setItems(ArrayList(it.data))
                            }
                        }
                        Resource.Status.ERROR ->
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                        Resource.Status.LOADING ->
                            binding.pbLoader.visibility = View.VISIBLE
                    }
                }
            )
        } else {
            capsulesViewModel.capsules.observe(
                viewLifecycleOwner,
                {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            binding.pbLoader.visibility = View.GONE
                            if (!it.data.isNullOrEmpty()) capsuleAdapter.setItems(ArrayList(it.data))
                        }
                        Resource.Status.ERROR ->
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                        Resource.Status.LOADING ->
                            binding.pbLoader.visibility = View.VISIBLE
                    }
                }
            )
        }
    }

    override fun onClickedLandpad(id: String) {
        findNavController().navigate(
            R.id.action_listFragment_to_landpadDetailFragment,
            bundleOf("id" to id)
        )
    }

    private fun setType(type: Type) {
        setupRecyclerView(type)
        setupObservers(type)
        if (type == Type.CAPSULES) {
            binding.btnLandpads.setBackgroundColor(resources.getColor(android.R.color.holo_blue_dark))
            binding.btnCapsules.setBackgroundColor(resources.getColor(R.color.design_default_color_primary))
            stateViewModel.saveChosenList(Type.CAPSULES)
        } else {
            binding.btnCapsules.setBackgroundColor(resources.getColor(android.R.color.holo_blue_dark))
            binding.btnLandpads.setBackgroundColor(resources.getColor(R.color.design_default_color_primary))
            stateViewModel.saveChosenList(Type.LANDPADS)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val query = newText?.lowercase(Locale.ROOT) ?: ""
        stateViewModel.saveFilter(query)
        if (stateViewModel.getChosenList() == Type.CAPSULES) {
            capsuleAdapter.filter.filter(query)
            capsuleAdapter.notifyDataSetChanged()
        } else {
            landpadAdapter.filter.filter(query)
            landpadAdapter.notifyDataSetChanged()
        }

        return true
    }
}
