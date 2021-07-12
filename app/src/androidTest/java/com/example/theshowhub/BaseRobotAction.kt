package com.example.theshowhub

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

open class BaseRobotAction {

    fun clickOnComponent(@IdRes resourceId: Int) {
        onView((withId(resourceId))).perform(click())
    }

    fun clickOnText(@StringRes text: Int) {
        onView(withText(text)).perform(click())
    }

}