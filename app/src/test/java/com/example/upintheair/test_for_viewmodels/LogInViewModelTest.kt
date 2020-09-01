package com.example.upintheair.test_for_viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.upintheair.SharedPreferences.UserPreferences
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
    lateinit var removeRepository: RetrofitRepository

    @Mock
    lateinit var localRepository:UserPreferences

    lateinit var vm: LogInViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        vm = LogInViewModel(removeRepository, localRepository)
    }

    @Test
    fun viewModelTest(){
        assertNotNull(vm)

//        vm.authorization("", "")
//        assertEquals(vm.error.value, "error_with_all_edit_text")

//        vm.authorization(null, null)
//        assertEquals(vm.error.value, "error_with_all_edit_text")


    }
}