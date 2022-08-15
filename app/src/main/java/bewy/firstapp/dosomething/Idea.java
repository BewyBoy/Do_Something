package bewy.firstapp.dosomething;

public class Idea {
    private String title;
    private String description;
    private int pic;
    private int id;

    public Idea(String title, String description, int pic, int id) {
        this.title = title;
        this.description = description;
        this.pic = pic;
        this.id = id;
    }

    public Idea(String title, int pic, int id) {
        this.title = title;
        this.description = "description";
        this.pic = pic;
        this.id = id;
    }

    public Idea(String title, int id) {
        this.title = title;
        this.description = "description";
        this.pic = R.mipmap.kite2;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
