package com.example.marsphotos.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

// The primary goal of this test rule is to replace the Main dispatcher with a test dispatcher before a test begins to execute.
class TestDispatcherRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
): TestWatcher(){
    //The starting() function of the TestWatcher class executes before a given test executes.
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }
    //After test execution is finished, reset the Main dispatcher by overriding the finished() method.
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}