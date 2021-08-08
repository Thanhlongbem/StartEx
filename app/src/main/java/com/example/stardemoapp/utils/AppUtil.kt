package com.example.stardemoapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

open class AppUtil {
    companion object {
        fun replaceFragmentToActivity(
            fragmentManager: FragmentManager,
            fragment: Fragment, frameId: Int, onBackStack: Boolean = false, tag: String? = null
        ) {
            val transaction = fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.replace(frameId, fragment, tag)
            if (onBackStack) {
                transaction.addToBackStack(null)
            }
            transaction.commit()
        }

        fun addFragmentToActivity(
            fragmentManager: FragmentManager,
            fragment: Fragment, frameId: Int, onBackstack: Boolean, tag: String? = null
        ) {
            val currentFr = fragmentManager.findFragmentById(frameId)
            val transaction = fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            if (currentFr != null) transaction.hide(currentFr)
            transaction.add(frameId, fragment, tag)
            if (onBackstack) {
                transaction.addToBackStack(tag)
            }
            transaction.commit()
        }
    }
}