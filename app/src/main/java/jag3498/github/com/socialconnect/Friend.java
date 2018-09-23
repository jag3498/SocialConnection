package jag3498.github.com.socialconnect;


public class Friend {

    private String firstname;
    private String lastname;
    private String birthday;
    private String startDate;
    private String notes;

    public Friend(String firstname, String lastname, String birthday, String startDate, String notes) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.startDate = startDate;
        this.notes = notes;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getstartDate() {
        return startDate;
    }

    public void setstartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", startDate='" + startDate + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
