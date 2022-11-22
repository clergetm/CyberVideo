package fc.Client;

class Administrator{
    private Login login;

    public Administrator(String login, String password){
        login = new Login(login, password);
    }

    public getLogin(){
        return login;
    }

}