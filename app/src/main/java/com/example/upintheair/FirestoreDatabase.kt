package com.example.upintheair

import android.util.Log
import com.example.upintheair.entity.Wish
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDatabase {

    val db = FirebaseFirestore.getInstance()

    fun createWish(wish: Wish) {
        db.collection("wishes")
            .add(wish)
            .addOnSuccessListener {
                Log.d("SUCCESS", it.id)
            }
            .addOnFailureListener {
                Log.e("ERROR", it.message)
            }
    }

}