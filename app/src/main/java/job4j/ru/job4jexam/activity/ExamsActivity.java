package job4j.ru.job4jexam.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import job4j.ru.dialogs.DeleteDialogFragment;
import job4j.ru.job4jexam.adapter.ExamAdapter;
import job4j.ru.job4jexam.model.Exam;
import job4j.ru.job4jexam.R;
import job4j.ru.job4jexam.store.ExamBaseHelper;
import job4j.ru.job4jexam.store.ExamDbSchema;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 02/11/2018
 */
public class ExamsActivity extends AppCompatActivity implements DeleteDialogFragment.DeleteDialogFragmentListener {
    private RecyclerView recycler;
    private SQLiteDatabase store;
    private ExamAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.store = new ExamBaseHelper(this.getApplicationContext()).getWritableDatabase();
        initRecyclerView();
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
                startActivity(new Intent(this, ExamUpdateActivity.class));
                return true;
            case R.id.delete_item:
                DialogFragment dialogFragment = new DeleteDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "dialog_tag");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPositiveDialogClick(DialogFragment dialog) {
        store.delete(ExamDbSchema.ExamTable.NAME, null, null);
        initRecyclerView();
    }

    @Override
    public void onNegativeDialogClick(DialogFragment dialog) {
        Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
    }

    /**
     * Initialise recycler
     */
    private void initRecyclerView() {
       recycler = findViewById(R.id.exams);
       recycler.setLayoutManager(new LinearLayoutManager(this));
       adapter = new ExamAdapter();
       adapter.setItems(getExams());
       recycler.setAdapter(adapter);
    }

    /**
     * Method getExams generate exams list
     * @return
     */
    private List<Exam> getExams() {
        List<Exam> exams = new ArrayList<>();
        Cursor cursor = this.store.query(
                ExamDbSchema.ExamTable.NAME, null,null,
                null,null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            exams.add(new Exam(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("title")),
                    System.currentTimeMillis(),
                    100
            ));
            cursor.moveToNext();
        }
        cursor.close();
        return exams;
    }
}
