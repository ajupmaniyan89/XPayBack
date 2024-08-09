package com.ajuenterprises.xpayback.ui.user

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajuenterprises.xpayback.model.User
import com.ajuenterprises.xpayback.repository.Repository
import com.ajuenterprises.xpayback.utils.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: Repository
) : ViewModel() {
    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> get() = _users

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _toastMsg = MutableLiveData<String>()
    val toastMsg: LiveData<String> get() = _toastMsg

    private var limit = 20
    private var skip = 0


    fun fetchUsers() {
        if (!AppUtils.isNetworkAvailable(context)) {
            _toastMsg.value = "Internet not available"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            try {
                val userList = repository.getUsers(limit, skip)
                if (userList.users.isNotEmpty()) {
                    val updatedList = _users.value.orEmpty().toMutableList()
                    updatedList.addAll(userList.users)
                    _users.value = updatedList
                    skip += limit

                } else {
                    if (skip == 0) {
                        _toastMsg.value = "No data found"
                    }
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _toastMsg.value = "Error fetching data: ${e.message}"
            }
        }

    }

    fun fetchUserDetail(userId: Int) {
        if (!AppUtils.isNetworkAvailable(context)) {
            _toastMsg.value = "Internet not available"
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val user = repository.getUserDetail(userId)
                _user.value = user
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _toastMsg.value = "Error fetching data: ${e.message}"
            }
        }
    }
}

