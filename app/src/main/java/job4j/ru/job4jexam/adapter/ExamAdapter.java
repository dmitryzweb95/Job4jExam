package job4j.ru.job4jexam.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import job4j.ru.job4jexam.R;
import job4j.ru.job4jexam.activity.ExamActivity;
import job4j.ru.job4jexam.activity.ExamsActivity;
import job4j.ru.job4jexam.activity.ResultActivity;
import job4j.ru.job4jexam.model.Exam;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamHolder> {
    private final List<Exam> exams = new ArrayList<>();

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     *
     * @param parent   ViewGroup: The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType int: The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ExamAdapter.ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.info_exam, parent, false);
        return new ExamAdapter.ExamHolder(view);
    }

    /**
     * Set items to list
     *
     * @param exams
     */
    public void setItems(List<Exam> exams) {
        this.exams.clear();
        this.exams.addAll(exams);
        notifyDataSetChanged();
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
    public void onBindViewHolder(@NonNull ExamAdapter.ExamHolder holder, int position) {
        Exam exam = this.exams.get(position);
        TextView text = holder.itemView.findViewById(R.id.info);
        TextView result = holder.itemView.findViewById(R.id.result);
        TextView date = holder.itemView.findViewById(R.id.date);
        text.setText(exam.getName());
        text.setOnClickListener(v -> {
            Toast.makeText(
                    holder.itemView.getContext(), "You select " + exam,
                    Toast.LENGTH_SHORT
            ).show();
            Intent intent = new Intent(holder.itemView.getContext(), ExamActivity.class);
            holder.itemView.getContext().startActivity(intent);
        });
        Intent intent = ((ExamsActivity) holder.itemView.getContext()).getIntent();
        int resultActivityPercent = intent.getIntExtra(ResultActivity.ANSWER_RIGHT_PERCENT_FROM_RESULT, 0);
        String currentTime = intent.getStringExtra(ResultActivity.CURRENT_TIME_OF_EXAM);
        String currentDate = intent.getStringExtra(ResultActivity.CURRENT_DATE_OF_EXAM);
        date.setText(currentTime + " " + currentDate);
        exam.setResult(resultActivityPercent);
        result.setText(String.valueOf(exam.getResult()));
    }

    @Override
    public int getItemCount() {
        return this.exams.size();
    }

    public class ExamHolder extends RecyclerView.ViewHolder {
        public ExamHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
