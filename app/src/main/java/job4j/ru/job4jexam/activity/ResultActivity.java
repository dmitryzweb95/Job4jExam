package job4j.ru.job4jexam.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;

import job4j.ru.job4jexam.R;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 15/10/2018
 */
public class ResultActivity extends AppCompatActivity {
    public static final String CURRENT_TIME_OF_EXAM = "current_time_of_exam";
    public static final String CURRENT_DATE_OF_EXAM = "current_date_of_exam";
    public static final String ANSWER_RIGHT_PERCENT_FROM_RESULT = "answer_right_percent_from_result";

    /**
     * For date and time of exam
     */
    private Calendar calendar = Calendar.getInstance();
    private String currentTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
    private String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.result_activity);

        TextView result = findViewById(R.id.results);
        final Button examsList = findViewById(R.id.examsList);

        int[] answers = getIntent().getIntArrayExtra(ExamActivity.ANSWER_NUMBER);
        result.setText(Arrays.toString(answers));
        int percent = getIntent().getIntExtra(ExamActivity.ANSWER_RIGHT_PERCENT, 0);
        examsList.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, ExamsActivity.class);
            intent.putExtra(ANSWER_RIGHT_PERCENT_FROM_RESULT, percent);
            intent.putExtra(CURRENT_TIME_OF_EXAM, currentTime);
            intent.putExtra(CURRENT_DATE_OF_EXAM, currentDate);
            startActivity(intent);
        });
    }
}