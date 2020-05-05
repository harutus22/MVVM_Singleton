package com.example.mvvmsingleton

import com.example.mvvmsingleton.models.User

object ExampleSingleton {
    val singletonUser: User by lazy {
        User("harutus22@yahoo.com", "harut", "image.png")
    }
}