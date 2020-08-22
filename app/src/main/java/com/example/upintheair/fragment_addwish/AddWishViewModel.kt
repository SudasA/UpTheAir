package com.example.upintheair.fragment_addwish

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.upintheair.LocalRepository
import com.example.upintheair.entity.Wish
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class AddWishViewModel(
    private val repository: LocalRepository
) : ViewModel(), CoroutineScope {

    private lateinit var remove: FirebaseFirestore

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    val loading = MutableLiveData<Boolean>(false)
    val result = MutableLiveData<String>()

    fun sendWish(name: String, description: String, context: Context) {
        val wish = Wish(null, name, description)

        try {
            remove = FirebaseFirestore.getInstance(FirebaseApp.initializeApp(context)!!)
        } catch (e: Exception) {
            Log.e("ERROR", e.message)
        }

        CoroutineScope(coroutineContext).launch {
            loading.postValue(true)
            if (checkWish(wish)) {
                addWishInDatabase(wish)

                addWishInRemoveRepository(wish)

                result.postValue("successes")
            } else {
                result.postValue("error_with_name_wish")
            }
            loading.postValue(false)
        }
    }

    fun checkWish(wish: Wish): Boolean {
        if (wish.name != null && wish.name != "") return true
        return false
    }

    suspend fun addWishInDatabase(wish: Wish) {
        CoroutineScope(coroutineContext).async {
            repository.createWish(wish)
        }.await()
    }

    suspend fun addWishInRemoveRepository(wish: Wish) = CoroutineScope(coroutineContext).async {
        remove.collection("Wishes")
            .add(wish)
            .addOnSuccessListener {
                Log.d("SUCCESS", it.id)
            }
            .addOnFailureListener {
                Log.e("ERROR", it.message)
            }
    }.await()
}