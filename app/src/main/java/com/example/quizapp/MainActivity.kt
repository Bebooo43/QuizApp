package com.example.quizapp

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val totalPoints = 100.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Results")
                .setMessage(getAlertMessage())
                .setPositiveButton("OK") { _, _ ->
                    resetQuestions()
                }
                .create()
                .show()
        }
    }

    private fun getAlertMessage(): String {
        val resultPoints = calculateResults()
        val currentDate: String = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
        var msg =
            if (resultPoints == 0.0)
                "Please try again! "
            else
                "Congratulations! "
        msg += "You submitted at ${currentDate}, Your achieved ${(resultPoints / totalPoints * 100).toInt()}%"
        return msg
    }

    private fun calculateResults(): Double {
        var resultPoints = 0.0
        if (isQ1Valid())
            resultPoints += 50
        if (isQ2Valid()) {
            resultPoints += 50
        }
        return resultPoints
    }

    private fun resetQuestions() {
        binding.also {
            it.q1RG.clearCheck()
            it.b1RB.isChecked = false
            it.b2RB.isChecked = false
            it.b3RB.isChecked = false
        }
    }

    private fun isQ2Valid(): Boolean =
        binding.a1RB.isChecked

    private fun isQ1Valid(): Boolean =
        binding.b1RB.isChecked.not() && binding.b2RB.isChecked && binding.b3RB.isChecked

}
