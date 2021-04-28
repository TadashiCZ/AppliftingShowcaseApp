package cz.tadeasvalenta.appliftingshowcaseapp.ui.viewModel

import androidx.lifecycle.ViewModel
import cz.tadeasvalenta.appliftingshowcaseapp.data.repository.CapsuleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CapsulesViewModel @Inject constructor(
    private val repository: CapsuleRepository
) : ViewModel() {
    val capsules = repository.getCapsules()
}
