package com.thescore.interview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thescore.interview.data.Team
import com.thescore.interview.repositories.TeamService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito

class MainViewModelTest {

    lateinit var teamService: TeamService
    lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSuccessStateOfTeamData() = runBlockingTest {
        teamService = Mockito.mock(TeamService::class.java)
        Mockito.`when`(teamService.getTeams()).then {
            getFakeListOfTeamData()
        }
        mainViewModel = MainViewModel(teamService)
        mainViewModel.fetchData.observeForever {
            assert(it.isSuccess)
            val listOfData = it.getOrNull() ?: getFakeListOfTeamDataEmpty()
            val isSameList: Boolean = listOfData == getFakeListOfTeamData()
            assert(isSameList)
        }
    }

    @Test
    fun testEmptyStateOfTeamData() = runBlockingTest {
        teamService = Mockito.mock(TeamService::class.java)
        Mockito.`when`(teamService.getTeams()).then {
            throw NullPointerException()
        }

        mainViewModel = MainViewModel(teamService)

        mainViewModel.fetchData.observeForever {
            assert(it.isFailure)
            val throwableData = it.getOrThrow()
            //here we assert that the throwable is same we are expecting or not
            //assertThat()
        }
    }

    private fun getFakeListOfTeamData(): List<Team>
    {
        return listOf(Team(1, 5, "Pulkit", 1),
            Team(3, 5, "Ram", 2),
            Team(4, 5, "Sham", 4))
    }

    private fun getFakeListOfTeamDataEmpty() = listOf<Team>()

}