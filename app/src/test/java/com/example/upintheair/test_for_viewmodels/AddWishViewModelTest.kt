package com.example.upintheair.test_for_viewmodels

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.upintheair.LocalRepository
import com.example.upintheair.fragment_addwish.AddWishViewModel
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class AddWishViewModelTest {

    @Mock
    lateinit var repository: LocalRepository

    lateinit var vm: AddWishViewModel

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        try {
            vm = AddWishViewModel(repository)
        } catch (e: Exception) {
            Log.e("ERROR", e.message)
            print(e.message)
        }
    }

    @Test
    fun vmTest(){
        assertNotNull(vm)
    }
}