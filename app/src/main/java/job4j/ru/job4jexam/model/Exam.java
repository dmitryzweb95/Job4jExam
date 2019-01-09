package job4j.ru.job4jexam.model;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Objects;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 02/11/2018
 */
public class Exam {
    private Integer id;
    private String name;
    private long time;
    private int result;

    public Exam(Integer id, String name, long time, int result) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id.equals(exam.id);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @NonNull
    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", result=" + result +
                '}';
    }
}
