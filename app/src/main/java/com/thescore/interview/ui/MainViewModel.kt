package com.thescore.interview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thescore.interview.data.Team
import com.thescore.interview.repositories.TeamService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val team: TeamService) : ViewModel()
{
    init {
        getDataFromServer()
    }

    private val _fetchData: MutableLiveData<Result<List<Team>>> = MutableLiveData<Result<List<Team>>>()
    val fetchData: LiveData<Result<List<Team>>> = _fetchData

    private fun getDataFromServer() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            try {
                val data = team.getTeams()
                _fetchData.postValue(processTeamsData(data))
            } catch (execption: Exception)
            {
                //for now sending this
                _fetchData.postValue(Result.failure(NullPointerException()))
            }
        }
    }

    private fun processTeamsData(listOfTeams: List<Team>) : Result<List<Team>>
    {
        return if (listOfTeams.isEmpty()) Result.failure(NullPointerException())
        else Result.success(listOfTeams)
    }

}

