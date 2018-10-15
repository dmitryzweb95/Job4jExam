package job4j.ru.job4jexam;

/**
 * TODO: comment
 *
 * @author dmitryzweb
 * @since 14/10/2018
 */
public class Option {
    private int id;
    private String text;

    public Option(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
