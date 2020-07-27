package com.example.upintheair.test_for_viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.upintheair.activity_signin.SignInViewModel
import com.example.upintheair.network.RetrofitRepository
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignInViewModelTest {

    @Mock
    lateinit var repository: RetrofitRepository

    lateinit var viewModel: SignInViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        viewModel = SignInViewModel(repository)
    }


    @Test
    fun viewModelTest() {
        assertNotNull(viewModel)
    }

}