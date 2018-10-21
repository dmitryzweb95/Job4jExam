package job4j.ru.job4jexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 14/10/2018
 */
public class ExamActivity extends AppCompatActivity {
    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"), new Option(2, "1.2"),
                            new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );
    private int position = 0;
    private List<Integer> answers = new ArrayList<>();
    public static final String HINT_FOR = "hint_for";
    public static final String ANSWER_FOR_HINT = "answer_for_hint";
    private static final String TAG = "ExamActivity";
    private Integer counter = 0;
    private static final String KEY_NUMBER = "number";

    /**
     * Method that will take the current position and fill out the question and answered options
     */
    private void fillForm() {
        findViewById(R.id.previous).setEnabled(position != 0);
        final TextView textView = findViewById(R.id.question);
        Question question = this.questions.get(this.position);
        textView.setText(question.getText());
        RadioGroup variants = findViewById(R.id.variants);
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
    }

    /**
     * Shows you answer, obviously
     */
    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
        Toast.makeText(this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Called before onStop() method to save data
     *
     * @param outState saved data
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUMBER, counter);
        Log.d(TAG, "onSaveInstanceState");
    }

    /**
     * Restore saved data
     *
     * @param savedInstanceState saved date
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter = savedInstanceState.getInt(KEY_NUMBER);
        Log.d(TAG, "onRestoreInstanceState");
    }

    /**
     * Is where you initialize your activity. Most importantly, here you will usually call
     *
     * @param savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Log.d(TAG, "onCreate");
        Log.d(KEY_NUMBER, counter.toString());
        this.fillForm();
        final Button next = findViewById(R.id.next);
        final Button previous = findViewById(R.id.previous);
        final RadioGroup variants = findViewById(R.id.variants);
        Button hint = findViewById(R.id.hint);
        next.setEnabled(false);
        previous.setEnabled(false);
        variants.setOnCheckedChangeListener((group, checkedId) -> {
            next.setEnabled(checkedId != -1);
            previous.setEnabled(checkedId != -1 && position != 0);
        });
        next.setOnClickListener(
                v -> {
                    answers.add(variants.getCheckedRadioButtonId());
                    showAnswer();
                    position++;
                    fillForm();
                    variants.check(-1);
                }
        );
        hint.setOnClickListener(v -> {
            Intent intent = new Intent(ExamActivity.this, HintActivity.class);
            intent.putExtra(HINT_FOR, position);
            intent.putExtra(ANSWER_FOR_HINT, position);
            startActivity(intent);
        });
        previous.setOnClickListener(
                v -> {
                    position--;
                    fillForm();
                }
        );
    }

    /**
     * Activity become visible
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    /**
     * User can act with activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    /**
     * Called, when the system is about to resume another operation
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    /**
     * Called, when the operation is no longer displayed to the user
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    /**
     * Called before the operation is destroyed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void onClick(View view) {
        Toast.makeText(this, "FlipCount = " + ++counter, Toast.LENGTH_SHORT).show();
    }
}
