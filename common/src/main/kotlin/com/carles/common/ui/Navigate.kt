package com.carles.common.ui

import androidx.navigation.NavController

interface Navigate {

    fun init(navController: NavController)
    fun toErrorDialog(message: String, retry: Boolean = false)
    fun toMonsterDetail(id: Int)
    fun up()
}
