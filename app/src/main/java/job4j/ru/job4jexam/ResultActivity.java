package job4j.ru.job4jexam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Arrays;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 15/10/2018
 */
public class ResultActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.result_activity);
        TextView result = findViewById(R.id.results);
        int[] answers = getIntent().getIntArrayExtra(ExamActivity.ANSWER_NUMBER);
        result.setText(Arrays.toString(answers));
    }
}