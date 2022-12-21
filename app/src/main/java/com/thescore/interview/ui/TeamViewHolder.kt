package com.thescore.interview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thescore.interview.data.Team
import com.thescore.interview.databinding.LayoutTeamBinding

class TeamViewHolder(
    parent: ViewGroup,
    private val binding: LayoutTeamBinding = LayoutTeamBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    )
) : RecyclerView.ViewHolder(
    binding.root
) {

    fun bindView(team: Team) {
        binding.fullName.text = team.fullName
        binding.wins.text = team.wins.toString()
        binding.losses.text = team.losses.toString()
    }

}