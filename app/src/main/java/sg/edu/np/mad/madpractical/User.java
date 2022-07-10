package sg.edu.np.mad.madpractical;

public class User {
    public String name;
    public String description;
    public Integer id;
    public Boolean followed;

    public User() {
        this.name = "test";
        this.description = "test";
        this.id = 0;
        this.followed = true;
    }

    public User(String name, String description, Integer id, Boolean followed) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }
}
