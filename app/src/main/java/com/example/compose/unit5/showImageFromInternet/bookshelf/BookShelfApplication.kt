package com.example.compose.unit5.showImageFromInternet.bookshelf
import AppContainer
import BookShelfDefaultContainer
import android.app.Application

class BookShelfApplication: Application() {
    lateinit var bookShelfDefaultContainer: AppContainer


    override fun onCreate() {
        super.onCreate()
        bookShelfDefaultContainer = BookShelfDefaultContainer()
    }

}

