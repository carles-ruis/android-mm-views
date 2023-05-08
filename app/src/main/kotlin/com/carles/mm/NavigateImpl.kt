package com.carles.mm

import androidx.navigation.NavController
import com.carles.common.ui.Navigate
import com.carles.common.ui.extensions.safeNavigate
import com.carles.hyrule.HyruleGraphDirections
import javax.inject.Inject

class NavigateImpl @Inject constructor() : Navigate {

    private lateinit var navController: NavController

    override fun init(navController: NavController) {
        this.navController = navController
    }

    override fun toErrorDialog(message: String, retry: Boolean) {
        navController.safeNavigate {
            HyruleGraphDirections.toErrorDialog(message, retry)
        }
    }

    override fun toMonsterDetail(id: Int) {
        navController.safeNavigate {
            HyruleGraphDirections.toMonsterDetail(id)
        }
    }

    override fun up() {
        navController.navigateUp()
    }
}