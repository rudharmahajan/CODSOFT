package com.quizapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.quizapp.R;


public class QuizActivity extends AppCompatActivity {

    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button submitBtn;
    private TextView feedbackText;
    private TextView scoreText;
    private TextView correctAnswersText;
    private TextView incorrectAnswersText;

    private int currentQuestion = 0;
    private int score = 0;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;

    private Question[] questions = new Question[] {
            new Question("What is the capital of France?", new String[] {"Paris", "London", "Berlin", "Rome"}, 0),
            new Question("What is the largest planet in our solar system?", new String[] {"Earth", "Saturn", "Jupiter", "Uranus"}, 2),
            // Add more questions here
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionText = findViewById(R.id.question_text);
        optionsGroup = findViewById(R.id.options_group);
        submitBtn = findViewById(R.id.submit_btn);
        feedbackText = findViewById(R.id.feedback_text);
        scoreText = findViewById(R.id.score_text);
        correctAnswersText = findViewById(R.id.correct_answers_text);
        incorrectAnswersText = findViewById(R.id.incorrect_answers_text);

        renderQuestion();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void renderQuestion() {
        Question currentQuestionObj = questions[currentQuestion];
        questionText.setText(currentQuestionObj.getQuestion());
        optionsGroup.clearCheck();

        for (int i = 0; i < currentQuestionObj.getOptions().length; i++) {
            RadioButton radioButton = (RadioButton)optionsGroup.getChildAt(i);
            radioButton.setText(currentQuestionObj.getOptions()[i]);
        }
    }

    private void checkAnswer() {
        int selectedOption = optionsGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedOption);

        if (selectedRadioButton != null) {
            int correctAnswer = questions[currentQuestion].getCorrectAnswer();
            if (selectedRadioButton.getText().equals(questions[currentQuestion].getOptions()[correctAnswer])) {
                score++;
                correctAnswers++;
                feedbackText.setText("Correct!");
                feedbackText.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                incorrectAnswers++;
                feedbackText.setText("Incorrect. The correct answer is " + questions[currentQuestion].getOptions()[correctAnswer] + ".");
                feedbackText.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            }
            currentQuestion++;
            if (currentQuestion < questions.length) {
                renderQuestion();
            } else {
                feedbackText.setText("You scored " + score + " out of " + questions.length + ".");
                scoreText.setText("Score: " + score + "/" + questions.length);
                correctAnswersText.setText("Correct answers: " + correctAnswers);
                incorrectAnswersText.setText("Incorrect answers: " + incorrectAnswers);
            }
        } else {
            feedbackText.setText("Please select an answer.");
        }
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}