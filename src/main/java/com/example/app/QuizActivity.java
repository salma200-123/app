package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    TextView questionText;
    RadioGroup answersGroup;
    RadioButton option1, option2, option3;
    Button nextButton;

    String[] questions = {
            "Quelle est la capitale de la France?",
            "Combien font 2 + 2?",
            "Quelle est la couleur du ciel?"
    };

    String[][] options = {
            {"Paris", "Lyon", "Marseille"},
            {"3", "4", "5"},
            {"Bleu", "Vert", "Rouge"}
    };

    int[] correctAnswers = {0, 1, 0};
    int currentQuestion = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.questionText);
        answersGroup = findViewById(R.id.answersGroup);
        option1 = findViewById(R.id.radioButton1);
        option2 = findViewById(R.id.radioButton2);
        option3 = findViewById(R.id.radioButton3);
        nextButton = findViewById(R.id.buttonNext);

        loadQuestion();

        nextButton.setOnClickListener(v -> {
            int checkedId = answersGroup.getCheckedRadioButtonId();
            if (checkedId == -1) return;

            int answerIndex = answersGroup.indexOfChild(findViewById(checkedId));
            if (answerIndex == correctAnswers[currentQuestion]) score++;

            currentQuestion++;
            if (currentQuestion < questions.length) {
                loadQuestion();
            } else {
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("total", questions.length);
                startActivity(intent);
                finish();
            }
        });
    }

    void loadQuestion() {
        questionText.setText(questions[currentQuestion]);
        option1.setText(options[currentQuestion][0]);
        option2.setText(options[currentQuestion][1]);
        option3.setText(options[currentQuestion][2]);
        answersGroup.clearCheck();
    }
}
