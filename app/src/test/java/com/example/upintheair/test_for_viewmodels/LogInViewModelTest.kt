package com.example.upintheair.test_for_viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.upintheair.activity_login.LogInViewModel
import com.example.upintheair.network.RetrofitRepository
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LogInViewModelTest {

    @Mock
    lateinit var repository: RetrofitRepository

    lateinit var vm: LogInViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        vm = LogInViewModel(repository)
    }

    @Test
    fun viewModelTest(){
        assertNotNull(vm)
    }
}