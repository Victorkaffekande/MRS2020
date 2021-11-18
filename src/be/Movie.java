package be;

public class Movie {

    private int id;
    private String title;
    private int year;

    public Movie(int id, int year, String title) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;

        if (obj instanceof Movie){
            Movie other = (Movie) obj;
            return other.getId() == this.id;
        } else{
            return super.equals(obj);
        }
    }


    @Override
    public String toString()
    {
        return id + ": " + title + " ("+year+")";
    }
}
