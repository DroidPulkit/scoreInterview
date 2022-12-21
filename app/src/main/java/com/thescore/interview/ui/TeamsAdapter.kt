package com.thescore.interview.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thescore.interview.data.Team

class TeamsAdapter() : RecyclerView.Adapter<TeamViewHolder>() {

    private var listOfTeam: List<Team> = emptyList()

    fun updateData(listOfData: List<Team>)
    {
        listOfTeam = listOfData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(parent)
    }

    override fun getItemCount() = listOfTeam.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindView(listOfTeam[position])
    }

}

