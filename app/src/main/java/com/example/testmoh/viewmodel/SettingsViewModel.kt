package com.example.testmoh.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmoh.data.datasource.LocalDataSource
import com.example.testmoh.data.models.SettingsOption
import com.example.testmoh.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: AppRepository = AppRepository(LocalDataSource)
) : ViewModel() {

    private val _settingsOptions = MutableStateFlow<List<SettingsOption>>(emptyList())
    val settingsOptions: StateFlow<List<SettingsOption>> = _settingsOptions

    init {
        loadSettingsOptions()
    }

    private fun loadSettingsOptions() {
        viewModelScope.launch {
            _settingsOptions.value = LocalDataSource.getMockSettingsOptions()
        }
    }
}
