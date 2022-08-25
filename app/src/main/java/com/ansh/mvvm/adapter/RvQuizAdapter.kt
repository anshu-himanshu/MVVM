package com.ansh.mvvm.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ansh.mvvm.Quote
import com.ansh.mvvm.R

class RvQuizAdapter(
    val context: Context, val List: List<Quote>,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<RvQuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        return QuizViewHolder(
            LayoutInflater.from(context).inflate(R.layout.itemlayout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.question.text = List[position].question
        holder.opt1.text = "A: " + List[position].opt1
        holder.opt2.text = "B: " + List[position].opt2
        holder.opt3.text = "C: " + List[position].opt3
        holder.opt4.text = "D: " + List[position].opt4
        holder.ivText.text = List[position].id.toString()

        holder.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (holder.itemView.findViewById<RadioButton>(checkedId)) {
                holder.rb1 -> {
                    listener.onItemClick(holder.opt1.text.toString(),1,List[position].id)



                }
               holder. rb2 -> {
                    listener.onItemClick(holder.opt2.text.toString(),2,List[position].id)


                }
              holder.  rb3 -> {
                    listener.onItemClick(holder.opt3.text.toString(),3,List[position].id)
                }
              holder.  rb4 -> {
                    listener.onItemClick(holder.opt4.text.toString(),4,List[position].id)
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return List.size
    }

    inner class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val question = itemView.findViewById<TextView>(R.id.tvQuestion)
        val ivText = itemView.findViewById<TextView>(R.id.tvImage)
        val answerState = itemView.findViewById<TextView>(R.id.answeredstate)
        val opt1 = itemView.findViewById<TextView>(R.id.tvOpt1)
        val opt2 = itemView.findViewById<TextView>(R.id.tvOpt2)
        val opt3 = itemView.findViewById<TextView>(R.id.tvOpt3)
        val opt4 = itemView.findViewById<TextView>(R.id.tvOpt4)

        val radioGroup = itemView.findViewById<RadioGroup>(R.id.radioGroup)
        val rb1 = itemView.findViewById<RadioButton>(R.id.rb1)
        val rb2 = itemView.findViewById<RadioButton>(R.id.rb2)
        val rb3 = itemView.findViewById<RadioButton>(R.id.rb3)
        val rb4 = itemView.findViewById<RadioButton>(R.id.rb4)


    }

    interface OnItemClickListener {
        fun onItemClick(answer:String,option: Int , id :Int)
    }

}