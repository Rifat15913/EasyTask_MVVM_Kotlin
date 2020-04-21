package com.shajt3ch.todomvvm.viewmodel.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shajt3ch.todomvvm.BuildConfig
import com.shajt3ch.todomvvm.model.remote.Networking
import com.shajt3ch.todomvvm.model.remote.request.auth.RegisterRequest
import com.shajt3ch.todomvvm.model.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers.IO

/**
 * Created by Shakil Ahmed Shaj on 14,April,2020.
 * shakilahmedshaj@gmail.com
 */
class RegisterViewModel : ViewModel() {

    companion object {
        const val TAG = "RegisterViewModel"
    }

    private val networkService = Networking.create(BuildConfig.BASE_URL)
    private val registerRepository = RegisterRepository(networkService)

    val progress: MutableLiveData<Boolean> = MutableLiveData()

    fun register(registerRequest: RegisterRequest) = liveData(IO) {

        progress.postValue(true)
        val data = registerRepository.register(registerRequest)
        emit(data)
        progress.postValue(false)
    }

}