package fc;

import java.util.Date;

public class Subscriber {
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Subscriber(String firstName, String lastName, Date birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void modifyInfo(String fn, String ln, Date bd){
        setFirstName(fn);
        setLastName(ln);
        setBirthDate(bd);
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getFirstName(){
        return  firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }

    public Date getBirthDate(){
        return birthDate;
    }

    public void askCard(){}

    public Login getLogin(){ return null;}
}
