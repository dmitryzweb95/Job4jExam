package job4j.ru.job4jexam.activity;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import job4j.ru.dialogs.ConfirmHintDialogFragment;
import job4j.ru.job4jexam.model.Option;
import job4j.ru.job4jexam.model.Question;
import job4j.ru.job4jexam.R;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 14/10/2018
 */
public class ExamActivity extends AppCompatActivity implements ConfirmHintDialogFragment.ConfirmHintDialogListener {

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
    private int[] answers = new int[questions.size()];
    private int percent = 0;
    public static final String HINT_FOR = "hint_for";
    public static final String ANSWER_FOR_HINT = "answer_for_hint";
    private static final String TAG = "ExamActivity";
    public static final String ANSWER_NUMBER = "answer_number";
    public static final String ANSWER_RIGHT_PERCENT = "answer_right_percent";

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
     * Shows you answer
     */
    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.questions.get(this.position);
        Toast.makeText(this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Set information to intent by clicking next button
     */
    private void onNextClickSetIntent() {
        final Button next = findViewById(R.id.next);
        final RadioGroup variants = findViewById(R.id.variants);
        Question question = this.questions.get(position);

        next.setOnClickListener(
                v -> {
                    if (position == questions.size() - 1) {
                        answers[position] = variants.getCheckedRadioButtonId();
                        if (question.getAnswer() == variants.getCheckedRadioButtonId()) {
                            percent += 33;
                        }
                        Intent intent = new Intent(ExamActivity.this, ResultActivity.class);
                        intent.putExtra(ANSWER_RIGHT_PERCENT, percent);
                        intent.putExtra(ANSWER_NUMBER, answers);
                        startActivity(intent);
                    } else {
                        answers[position] = variants.getCheckedRadioButtonId();
                        if (question.getAnswer() == variants.getCheckedRadioButtonId()) {
                            percent += 33;
                        }
                        showAnswer();
                        position++;
                        fillForm();
                        variants.check(-1);
                    }
                }
        );
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
        Question question = this.questions.get(position);
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position");
            answers = savedInstanceState.getIntArray("answers");
        }
        Log.d(TAG, "onCreate");
        this.fillForm();

        final Button next = findViewById(R.id.next);
        final Button previous = findViewById(R.id.previous);
        final Button examsList = findViewById(R.id.examsList);
        final RadioGroup variants = findViewById(R.id.variants);

        Button hint = findViewById(R.id.hint);
        next.setEnabled(false);
        previous.setEnabled(false);
        variants.setOnCheckedChangeListener((group, checkedId) -> {
            next.setEnabled(checkedId != -1);
        });
        onNextClickSetIntent();
        previous.setOnClickListener(
                v -> {
                    position--;
                    fillForm();
                }
        );
        hint.setOnClickListener(v -> {
            DialogFragment dialog = new ConfirmHintDialogFragment();
            dialog.show(getSupportFragmentManager(), "dialog_tag");
        });
        examsList.setOnClickListener(v -> {
            Intent intent = new Intent(ExamActivity.this, ExamsActivity.class);
            intent.putExtra(ANSWER_RIGHT_PERCENT, percent);
            startActivity(intent);
        });
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
     * Called before onStop() method to save data
     *
     * @param outState saved data
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
        outState.putIntArray("answers", answers);
        Log.d(TAG, "onSaveInstanceState");
    }

    /**
     * Called before the operation is destroyed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        Intent intent = new Intent(ExamActivity.this, HintActivity.class);
        intent.putExtra(HINT_FOR, position);
        intent.putExtra(ANSWER_FOR_HINT, position);
        startActivity(intent);
    }

    @Override
    public void onNegativeDialogClick(DialogFragment dialog) {
        Toast.makeText(this, "Right way!", Toast.LENGTH_SHORT).show();
    }
}
