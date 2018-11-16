package job4j.ru.job4jexam.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import job4j.ru.job4jexam.Model.Exam;
import job4j.ru.job4jexam.R;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 02/11/2018
 */
public class ExamsActivity extends AppCompatActivity {
    private List<Exam> exams = new ArrayList<>();
    private RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.recycler = findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exams_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(ExamsActivity.this, "ADD", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_item:
                Toast.makeText(ExamsActivity.this, "DELETE", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ExamAdapter extends RecyclerView.Adapter<ExamHolder> {
        private final List<Exam> exams;

        public ExamAdapter(List<Exam> exams) {
            this.exams = exams;
        }

        /**
         * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
         *
         * @param parent   ViewGroup: The ViewGroup into which the new View will be added after it is bound to an adapter position.
         * @param viewType int: The view type of the new View.
         * @return A new ViewHolder that holds a View of the given view type.
         */
        @NonNull
        @Override
        public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.info_exam, parent, false);
            return new ExamHolder(view);
        }

        /**
         * Called by RecyclerView to display the data at the specified position.
         * This method should update the contents of the itemView to reflect the item at the given position.
         *
         * @param holder   VH: The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
         * @param position int: The position of the item within the adapter's data set.
         */
        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ExamHolder holder, int position) {
            Exam exam = this.exams.get(position);

            TextView text = holder.view.findViewById(R.id.info);
            TextView result = holder.view.findViewById(R.id.result);
            TextView date = holder.view.findViewById(R.id.date);

            text.setText(exam.getName());
            text.setOnClickListener(v -> {
                Toast.makeText(
                        getApplicationContext(), "You select " + exam,
                        Toast.LENGTH_SHORT
                ).show();
                Intent intent = new Intent(ExamsActivity.this, ExamActivity.class);
                startActivity(intent);
            });
            int resultActivityPercent = getIntent().getIntExtra(ResultActivity.ANSWER_RIGHT_PERCENT_FROM_RESULT, 0);
            String currentTime = getIntent().getStringExtra(ResultActivity.CURRENT_TIME_OF_EXAM);
            String currentDate = getIntent().getStringExtra(ResultActivity.CURRENT_DATE_OF_EXAM);
            date.setText(currentTime + " " + currentDate);
            exam.setResult(resultActivityPercent);
            result.setText(String.valueOf(exam.getResult()));
        }

        @Override
        public int getItemCount() {
            return this.exams.size();
        }
    }

    public class ExamHolder extends RecyclerView.ViewHolder {
        private View view;

        public ExamHolder(@NonNull View view) {
            super(view);
            this.view = itemView;
        }
    }

    /**
     * Method that update list of exams
     */
    private void updateUI() {
        exams.add(new Exam(1, String.format("Exam %s", 1), System.currentTimeMillis(), 0));
        exams.add(new Exam(2, String.format("Exam %s", 2), System.currentTimeMillis(), 0));
        this.recycler.setAdapter(new ExamAdapter(exams));
    }
}
