package com.example.testprog.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testprog.R
import com.example.testprog.data.*
import com.example.testprog.data.domain.ResultTrigger
import com.google.android.material.textview.MaterialTextView

class ProblemRecyclerViewAdapter :
    RecyclerView.Adapter<ProblemRecyclerViewAdapter.MainViewHolder>() {

    private var problemList: List<ResultTrigger> = listOf()

    fun setData(data: List<ResultTrigger>) {
        problemList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_problem, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(problemList.get(position))
    }

    override fun getItemCount(): Int = problemList.count()

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(problem: ResultTrigger) {
            itemView.apply {
                context?.let {
                    this.findViewById<ImageView>(R.id.item_problem_image_view)
                        .setColorFilter(
                            ContextCompat.getColor(
                                it,
                                getSeverityColor(problem.priority.toInt())
                            )
                        )
                    this.findViewById<ImageView>(R.id.item_problem_image_view)
                        .setImageResource(getSeverityIcon(problem.priority.toInt()))
                    this.findViewById<MaterialTextView>(R.id.item_problem_date).text =
                        clockZabbixToString(problem.lastChange.toInt())
                    this.findViewById<MaterialTextView>(R.id.item_problem_hostname).text =
                        getHostName(problem.hosts)
                    this.findViewById<MaterialTextView>(R.id.item_problem_name).text =
                        problem.description
                    this.findViewById<MaterialTextView>(R.id.item_problem_name)
                        .setBackgroundColor(
                            ContextCompat.getColor(
                                it,
                                getSeverityColor(problem.priority.toInt())
                            )
                        )
                }
            }
        }
    }
}