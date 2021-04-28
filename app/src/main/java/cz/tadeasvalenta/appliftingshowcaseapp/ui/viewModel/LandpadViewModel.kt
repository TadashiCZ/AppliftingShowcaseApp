package cz.tadeasvalenta.appliftingshowcaseapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import cz.tadeasvalenta.appliftingshowcaseapp.data.entity.Landpad
import cz.tadeasvalenta.appliftingshowcaseapp.data.repository.LandpadRepository
import cz.tadeasvalenta.appliftingshowcaseapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandpadViewModel @Inject constructor(
    private val repository: LandpadRepository
) : ViewModel() {
    private val _id = MutableLiveData<String>()

    private val _landpad = _id.switchMap { id ->
        repository.getLandpad(id)
    }

    val landpad: LiveData<Resource<Landpad>> = _landpad
    val landpads = repository.getLandpads()

    fun start(id: String) {
        _id.value = id
    }
}
