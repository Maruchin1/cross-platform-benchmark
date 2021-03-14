package com.example.android_app

import android.app.Activity

fun Activity.setStatusBarColor(colorResId: Int) {
    window.statusBarColor = getColor(colorResId)
}

fun Activity.setNavigationBarColor(colorResId: Int) {
    window.navigationBarColor = getColor(colorResId)
}