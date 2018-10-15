package job4j.ru.job4jexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    /**
     * Method that will take the current position and fill out the question and answered options
     */
    private void fillForm() {
        findViewById(R.id.previous).setEnabled(position != 0);
        findViewById(R.id.next).setEnabled(position != questions.size() - 1);
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
     * Is where you initialize your activity. Most importantly, here you will usually call
     *
     * @param savedInstanceState is a reference to a Bundle object that is passed into the onCreate method of every Android Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        this.fillForm();
        final Button next = findViewById(R.id.next);
        final Button previous = findViewById(R.id.previous);
        final RadioGroup variants = findViewById(R.id.variants);
        next.setEnabled(false);
        previous.setEnabled(false);
        variants.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                next.setEnabled(checkedId != -1 && position != questions.size() - 1);
                previous.setEnabled(checkedId != -1 && position != 0);
            }
        });
        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        answers.add(variants.getCheckedRadioButtonId());
                        showAnswer();
                        position++;
                        fillForm();
                        variants.check(-1);
                    }
                }
        );
        previous.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position--;
                        fillForm();
                    }
                }
        );
    }
}
