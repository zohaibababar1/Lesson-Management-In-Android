package com.example.project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.models.Lesson

class LessonAdapter(
    private val lessons: List<Lesson>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onMarkCompleteClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.bind(lesson)
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lessonNumberTextView: TextView = itemView.findViewById(R.id.lessonNumberTextView)
        private val lessonNameTextView: TextView = itemView.findViewById(R.id.lessonNameTextView)
        private val lessonLengthTextView: TextView = itemView.findViewById(R.id.lessonLengthTextView)
        private val lessonStatusTextView: TextView = itemView.findViewById(R.id.lessonStatusTextView)
        private val markCompleteButton: ImageView = itemView.findViewById(R.id.markCompleteButton)

        fun bind(lesson: Lesson) {
            lessonNumberTextView.text = "Lesson ${adapterPosition + 1}"
            lessonNameTextView.text = lesson.name
            lessonLengthTextView.text = "Length: ${lesson.length} "
            lessonStatusTextView.text = if (lesson.completed) "Completed" else "Not Completed "

           if(lesson.completed){
               markCompleteButton.setImageResource(R.drawable.checkmark)

           }else{
               markCompleteButton.setImageResource(R.drawable.incomplete)
           }

            markCompleteButton.setOnClickListener {

                listener.onMarkCompleteClick(adapterPosition)
            }

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}