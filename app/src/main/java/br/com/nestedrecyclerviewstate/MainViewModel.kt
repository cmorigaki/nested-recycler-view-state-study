package br.com.nestedrecyclerviewstate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _content = MutableLiveData<List<Content>>()
    val content : LiveData<List<Content>> = _content

    init {
        generateContent()
    }

    private fun generateContent() {
        val cards = listOf(1..8).flatten()
        _content.value = listOf(
            Content.Carousel("100", cards),
            Content.Banner("1"),
            Content.Carousel("101", cards),
            Content.Carousel("102", cards),
            Content.Carousel("103", cards),
            Content.Banner("2"),
            Content.Carousel("104", cards),
            Content.Carousel("105", cards),
            Content.Carousel("106", cards),
            Content.Banner("3"),
        )
    }
}
