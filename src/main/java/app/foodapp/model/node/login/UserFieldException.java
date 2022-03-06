package app.foodapp.model.node.login;

public class UserFieldException extends Exception{
    public void UsernameFailed() {
        System.out.println("Username doesn't exist");
    }
}

