package com.carles.common.domain

import io.reactivex.Scheduler
import javax.inject.Inject

class AppSchedulers @Inject constructor(val io: Scheduler, val ui: Scheduler, val new: Scheduler)