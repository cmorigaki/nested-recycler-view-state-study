package br.com.nestedrecyclerviewstate

sealed class Content(val id: String) {
    class Banner(id: String) : Content(id)
    class Carousel(id: String, val list: List<Int>) : Content(id)
}