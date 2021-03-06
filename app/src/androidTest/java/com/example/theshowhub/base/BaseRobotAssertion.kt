package com.example.theshowhub.base

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.Visibility

open class BaseRobotAssertion {

    fun checkViewVisible(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    fun checkViewGone(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    fun checkTextExistence(@IdRes viewId: Int, @StringRes textResourceId: Int) {
        onView(withId(viewId)).check(matches(withText(textResourceId)))
    }

}