package job4j.ru.job4jexam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 15/10/2018
 */
public class HintActivity extends AppCompatActivity {
    @SuppressLint("UseSparseArrays")
    private final Map<Integer, String> answers = new HashMap<>();
    @SuppressLint("UseSparseArrays")
    private final Map<Integer, String> questionAnswers = new HashMap<>();

    public HintActivity() {
        this.answers.put(0, "How many primitive variables does Java have?");
        this.answers.put(1, "What is Java Virtual Machine?");
        this.answers.put(2, "What is happen if we try unboxing null?");
        this.questionAnswers.put(0, "4");
        this.questionAnswers.put(1, "4");
        this.questionAnswers.put(2, "4");
    }

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.hint_activity);
        TextView text = findViewById(R.id.hint);
        TextView answerText = findViewById(R.id.answer);
        int question = getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);
        int answer = getIntent().getIntExtra(ExamActivity.ANSWER_FOR_HINT, 4);
        text.setText(this.answers.get(question));
        answerText.setText(this.questionAnswers.get(answer));
        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());
    }
}
