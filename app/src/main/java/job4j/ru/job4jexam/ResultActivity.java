package job4j.ru.job4jexam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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
        TextView firstResult = findViewById(R.id.firstResult);
        TextView secondResult = findViewById(R.id.secondResult);
        TextView thirdResult = findViewById(R.id.thirdResult);
        int[] answers = getIntent().getIntArrayExtra(ExamActivity.ANSWER_NUMBER);
        Integer firstAnswer = answers[0];
        Integer secondAnswer = answers[1];
        Integer thirdAnswer = answers[2];
        firstResult.setText(firstAnswer.toString());
        secondResult.setText(secondAnswer.toString());
        thirdResult.setText(thirdAnswer.toString());

    }
}