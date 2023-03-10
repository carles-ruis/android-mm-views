package com.carles.common

import androidx.navigation.NavController

interface Navigate {
    fun toMonsterDetail(id: Int, navController: NavController)
    fun toErrorDialog(errorMessage: String?, navController: NavController)
}
