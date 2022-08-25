package com.ansh.mvvm

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.ansh.mvvm.adapter.RvQuizAdapter
import com.ansh.mvvm.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity(), RvQuizAdapter.OnItemClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    private var totalItems = 0
    private var answers = Array<String?>(5){null}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val database = QuizDatabase.getDatabase(applicationContext)
        val dao = database.quizDao()
        val repository = QuoteRepository(dao)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]


        // adding data

        if (!databaseFileExists()) {
            val quote = Quote(
                0,
                "What was Meta Platforms Inc formerly known as?",
                "facebook",
                "google",
                "zomato",
                "ayurvaid house",
                "",
                "facebook"
            )
            val quote2 = Quote(
                1,
                "Which English city is known as the Steel City?",
                "faridabad",
                "mirzapur",
                "Sheffield",
                "antartica","",
                "Sheffield"
            )
            val quote3 = Quote(
                2,
                "Which former British colony was given back to China in 1997?",
                "shri lanka",
                "Hong Kong",
                "pakistan",
                "bombay",
                "",
                "Hong Kong"
            )
            val quote4 = Quote(
                3,
                "The logo for luxury car maker Porsche features which animal?",
                "lion",
                "dog",
                "mosquito",
                "Horse",
                "",
                "Horse"
            )
            mainViewModel.insertQuiz(quote)
            mainViewModel.insertQuiz(quote2)
            mainViewModel.insertQuiz(quote3)
            mainViewModel.insertQuiz(quote4)

        }


        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvQuestions)


        mainViewModel.getQuiz().observe(this, Observer {

            binding.rvQuestions.adapter = RvQuizAdapter(this, it, this@MainActivity)
            totalItems = it.size
            binding.rvQuestions.scrollToPosition(1)
            binding.rvQuestions.scrollToPosition(0)

            answers=Array<String?>(it.size){null}




        })
        mainViewModel.position.observe(this, Observer {
            if (it == 0) {
                binding.btnPrev.visibility = GONE
            } else if (it == totalItems) {
                binding.btnNext.visibility = GONE
            } else {
                binding.btnPrev.visibility = VISIBLE
                binding.btnNext.visibility = VISIBLE

            }

        })
        binding.btnNext.setOnClickListener {
            val pos = mainViewModel.position.value!!
            binding.rvQuestions.scrollToPosition(pos + 1)
            mainViewModel.position.value = pos + 1
        }

        binding.btnPrev.setOnClickListener {
            val pos = mainViewModel.position.value!!
            binding.rvQuestions.scrollToPosition(pos - 1)
            mainViewModel.position.value = pos - 1

        }

        binding.btnSubmit.setOnClickListener {
            showDialog()
        }



    }


    override fun onItemClick(answer: String,option:Int,id:Int) {

        val a = AnswersModel(answer)
        answers[id-1] = answer
        Toast.makeText(this, "" + answers[id-1].toString()+answers.size, Toast.LENGTH_SHORT).show()


    }


        private fun databaseFileExists(): Boolean {
            return try {
                File(getDatabasePath("quiz_database").absolutePath).exists()
            }catch (e: Exception){
                false
            }
        }

    private fun showDialog() {

        val customLayout: View = layoutInflater.inflate(R.layout.dialog, null)

        val tv =customLayout.findViewById<TextView>(R.id.tvAnswers)


        val builder = AlertDialog.Builder(this)

        var strNames: String=""

        for (i in answers.indices) {
            strNames += "${i+1} = "+answers[i].toString()+"\n"
        }

        tv.text=strNames.toString()



        builder.setView(customLayout)


        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.white)

        dialog.show()



    }


}