package job4j.ru.job4jexam;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 03/11/2018
 */
public class DateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_layout);

        Button button = (Button) findViewById(R.id.dateTime);
        button.setOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");
            datePicker.show(getSupportFragmentManager(), "date picker");
        });
    }

    /**
     * Called when the user is done setting a new date and the dialog has closed.
     *
     * @param view
     * @param year
     * @param month
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView date = (TextView) findViewById(R.id.dateResult);
        date.setText(currentDate);
    }

    /**
     * Called when the user is done setting a new time and the dialog has closed.
     *
     * @param view
     * @param hourOfDay
     * @param minute
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView date = (TextView) findViewById(R.id.timeResult);
        date.setText(hourOfDay + ":" + minute);
    }
}
