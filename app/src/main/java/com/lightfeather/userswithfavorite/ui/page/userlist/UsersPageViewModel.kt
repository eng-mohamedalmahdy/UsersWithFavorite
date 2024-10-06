package com.lightfeather.userswithfavorite.ui.page.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lightfeather.userswithfavorite.domain.model.DomainResponse
import com.lightfeather.userswithfavorite.domain.usecase.GetAllUsersUseCase
import com.lightfeather.userswithfavorite.domain.usecase.GetFavoriteUsersUseCase
import com.lightfeather.userswithfavorite.domain.usecase.ToggleUserFavoriteUseCase
import com.lightfeather.userswithfavorite.ui.model.toUiUser
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersPageViewModel(
    private val source: UsersPageSource,
    private val toggleUserFavoriteUseCase: ToggleUserFavoriteUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(UsersPageState())
    val state: StateFlow<UsersPageState> = _state.asStateFlow()

    private val _intent = MutableSharedFlow<UsersPageIntent>()
    val intent: SharedFlow<UsersPageIntent> = _intent.asSharedFlow()

    init {
        viewModelScope.launch {
            _intent.collect { intent ->
                when (intent) {
                    is UsersPageIntent.LoadUsers -> loadUsers()
                    is UsersPageIntent.ToggleFavorite -> {
                        toggleUserFavoriteUseCase(intent.user.id)
                        loadUsers()
                    }

                    else -> {}
                }
            }
        }
    }

    fun onIntent(intent: UsersPageIntent) {
        viewModelScope.launch {
            _intent.emit(intent)
        }
    }

    private suspend fun loadUsers() {
        when (source) {
            UsersPageSource.ALL -> {
                when (val response = getAllUsersUseCase()) {
                    is DomainResponse.Success -> {
                        _state.value = _state.value.copy(users = response.data.map { it.toUiUser() })
                    }

                    is DomainResponse.Failure -> {
                        _state.value = _state.value.copy(error = response.e.message)
                    }
                }

            }

            UsersPageSource.FAVORITE -> {
                when (val response = getFavoriteUsersUseCase()) {
                    is DomainResponse.Success -> {
                        _state.value = _state.value.copy(users = response.data.map { it.toUiUser() })
                    }

                    is DomainResponse.Failure -> {
                        _state.value = _state.value.copy(error = response.e.message)
                    }
                }
            }
        }
        onIntent(UsersPageIntent.Init)
    }


}