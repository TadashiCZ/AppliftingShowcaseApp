package cz.tadeasvalenta.appliftingshowcaseapp.ui.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cz.tadeasvalenta.appliftingshowcaseapp.data.Type

class StateViewModel(private val state: SavedStateHandle) : ViewModel() {

    fun saveChosenList(type: Type) {
        state.set(CHOSEN_LIST, type)
    }

    fun getChosenList(): Type {
        return state.get<Type>(CHOSEN_LIST) ?: Type.CAPSULES
    }

    fun saveFilter(filter: String) {
        state.set(FILTER, filter)
    }

    companion object {
        const val CHOSEN_LIST = "ChosenList"
        const val FILTER = "Filter"
    }
}
