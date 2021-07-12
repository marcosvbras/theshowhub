package com.example.theshowhub.extensions

import android.view.View

fun View.makeItVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeItGone() {
    this.visibility = View.GONE
}