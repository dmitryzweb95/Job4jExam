package job4j.ru.job4jexam.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import job4j.ru.job4jexam.R;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 15/10/2018
 */
public class ResultActivity extends AppCompatActivity {
    public static final String ANSWER_RIGHT_PERCENT_FROM_RESULT = "answer_right_percent_from_result";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.result_activity);
        TextView result = findViewById(R.id.results);
        int[] answers = getIntent().getIntArrayExtra(ExamActivity.ANSWER_NUMBER);
        result.setText(Arrays.toString(answers));
        final Button examsList = findViewById(R.id.examsList);
        int percent = getIntent().getIntExtra(ExamActivity.ANSWER_RIGHT_PERCENT, 0);
        examsList.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, ExamsActivity.class);
            intent.putExtra(ANSWER_RIGHT_PERCENT_FROM_RESULT, percent);
            startActivity(intent);
        });
    }
}