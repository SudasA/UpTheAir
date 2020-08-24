package com.example.upintheair.test_for_viewmodels

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.upintheair.FirestoreDatabase
import com.example.upintheair.room.WishesDatabase
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
    lateinit var localRepository: WishesDatabase

    @Mock
    lateinit var removeRepository: FirestoreDatabase

    @Mock
    lateinit var context: Context

    lateinit var vm: AddWishViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        try {
            vm = AddWishViewModel(localRepository, removeRepository, context)
        } catch (e: Exception) {
            print(e.message)
        }
    }

    @Test
    fun vmTest(){
        assertNotNull(vm)
    }
}