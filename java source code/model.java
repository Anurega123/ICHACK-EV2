package dhanush.com.firestoreapp;

public class model {
    String number;
    String location;

    public model() {

    }

    public model(String number,String location) {

        this.number = number;
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
