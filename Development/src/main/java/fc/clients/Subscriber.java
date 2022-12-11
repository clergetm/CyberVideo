package fc.clients;

import java.util.Date;

public class Subscriber extends Client {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Login login;

    public Subscriber(String firstName, String lastName, Date birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
    
	@Override
	public int getSizeCart() {
		return 3;
	}

    public void askCard() {}
    
    public void modifyInfo(String fn, String ln, Date bd){
        setFirstName(fn);
        setLastName(ln);
        setBirthDate(bd);
    }

    public String getFirstName(){
        return  firstName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public Date getBirthDate(){
        return birthDate;
    }
    
    public void setBirthDate(Date birthDate){
        this.birthDate = birthDate;
    }
    
    // TODO
    public Login getLogin(){ return null;}
}
