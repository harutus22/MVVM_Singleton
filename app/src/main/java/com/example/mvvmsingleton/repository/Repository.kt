package com.example.mvvmsingleton.repository

import androidx.lifecycle.LiveData
import com.example.mvvmsingleton.api.RetrofitBuilder
import com.example.mvvmsingleton.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {
    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User>{
        job = Job()
        return object : LiveData<User>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = RetrofitBuilder.apiService.getUser(userId)
                        withContext(Main){
                            value = user
                            theJob.complete()
                        }

                    }

                }
            }
        }
    }

    fun cancelJob(){
        job?.cancel()
    }
}