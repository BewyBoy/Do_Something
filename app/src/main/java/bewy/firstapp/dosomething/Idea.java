package bewy.firstapp.dosomething;


public class Idea {
    private String title;
    private String description;
    private int pic;

    public Idea() {
        this.title = "zzz";
        this.description = "description";
        this.pic = R.mipmap.kite2;
    }

    public Idea(String title) {
        this.title = title;
    }

    public Idea(String title, int pic) {
        this.title = title;
        this.pic = pic;
    }
    public Idea(String title, String description, int pic) {
        this.title = title;
        this.description = description;
        this.pic = pic;
    }

    public void setDescriptionAndPic(String description, int pic) {
        this.description = description;
        this.pic = pic;
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

    @Override
    public String toString() {
        return "\t" + title + "\t" + description;
    }
}
