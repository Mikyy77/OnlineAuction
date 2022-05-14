package serialization;

import users.User;

import java.io.*;
import java.util.ArrayList;

public class Serialize implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    public final boolean writeIntoTxt(ArrayList<?> o) throws IOException {
        if(o.get(0) instanceof User) { // TODO RTTI usage
            //TODO relative path
            FileOutputStream file = new FileOutputStream("C:\\Users\\micha\\IdeaProjects\\RealEstateAuction\\src\\files\\users.txt");
            //FileOutputStream file = new FileOutputStream("/files/users.txt"); // relative path
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(o);
            file.close();
            stream.close();
            return true;
        }
        return false;
    }
    public Object getFromTxt(String help) throws IOException, ClassNotFoundException {
        if(help.equals("users")) {
            FileInputStream file = new FileInputStream("C:\\Users\\micha\\IdeaProjects\\RealEstateAuction\\src\\files\\users.txt");
            //FileInputStream file = new FileInputStream("/files/users.txt");
            ObjectInputStream stream = new ObjectInputStream(file);
            ArrayList<User> users = (ArrayList<User>)stream.readObject();
            stream.close();
            return users;
        }
        return null;
    }
}
