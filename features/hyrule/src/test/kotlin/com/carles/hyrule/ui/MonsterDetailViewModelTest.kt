package com.carles.hyrule.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.carles.common.ui.ERROR
import com.carles.common.ui.Navigate
import com.carles.common.ui.SUCCESS
import com.carles.hyrule.MonsterDetail
import com.carles.hyrule.domain.GetMonsterDetail
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MonsterDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getMonsterDetail: GetMonsterDetail = mockk()
    private val navigate: Navigate = mockk()
    private val state = SavedStateHandle().apply {
        set(MonsterDetailFragment.EXTRA_ID, 1)
    }

    private val monsterDetail = MonsterDetail(1, "Monster", "here", "big monster", "https://url")
    private val errorMessage = "some error"

    private lateinit var viewModel: MonsterDetailViewModel

    @Test
    fun `given initialization, when monster is obtained successfully, then update monsterDetail properly`() {
        every { getMonsterDetail.execute(any()) } returns Single.just(monsterDetail)

        viewModel = MonsterDetailViewModel(state, getMonsterDetail, navigate)

        verify { getMonsterDetail.execute(1) }
        val result = viewModel.monsterDetail.value!!
        assertTrue(result.state is SUCCESS)
        assertEquals(result.data, monsterDetail)
        assertNull(result.message)
    }

    @Test
    fun `given initialization, when there is an error obtaining monster, then update monsterDetail properly`() {
        every { getMonsterDetail.execute(any()) } returns Single.error(Exception(errorMessage))

        viewModel = MonsterDetailViewModel(state, getMonsterDetail, navigate)

        verify { getMonsterDetail.execute(1) }
        val result = viewModel.monsterDetail.value!!
        assertTrue(result.state is ERROR)
        assertNull(result.data)
        assertEquals(result.message, errorMessage)
    }

    @Test
    fun `given retry, when called, then refresh monster`() {
        every { getMonsterDetail.execute(any()) } returns Single.just(monsterDetail)

        viewModel = MonsterDetailViewModel(state, getMonsterDetail, navigate)
        viewModel.retry()

        verify(exactly = 2) { getMonsterDetail.execute(1) }
    }

    @Test
    fun `given onErrorEvent, when called, then navigate to error dialog`() {
        every { getMonsterDetail.execute(any()) } returns Single.just(monsterDetail)
        every { navigate.toErrorDialog(any()) } just Runs

        viewModel = MonsterDetailViewModel(state, getMonsterDetail, navigate)
        viewModel.onErrorDisplayed(errorMessage)

        verify { navigate.toErrorDialog(errorMessage) }
    }
}