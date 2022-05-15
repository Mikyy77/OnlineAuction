package serialization;

import users.User;

import java.io.*;
import java.util.ArrayList;

public class Serialize implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    /**
     * Method that writes an array list of any type into the txt file created to store information about the auction
     * @param o The array list of any type to be written into file
     * @return
     * @throws IOException
     */
    public final boolean writeIntoTxt(ArrayList<?> o) throws IOException {
        if(o.get(0) instanceof User) { // TODO RTTI usage
            //TODO relative path
        	String path = new File("src/files/users.txt").getAbsolutePath();
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream stream = new ObjectOutputStream(file);
            stream.writeObject(o);
            file.close();
            stream.close();
            return true;
        }
        return false;
    }
    /**
     * Function to get information from the txt file. Returns the array list with all objects
     * @param help The parameter to decide which type of information you want 
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object getFromTxt(String help) throws IOException, ClassNotFoundException {
        if(help.equals("users")) {
        	String path = new File("src/files/users.txt").getAbsolutePath();
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream stream = new ObjectInputStream(file);
            ArrayList<User> users = (ArrayList<User>)stream.readObject();
            stream.close();
            return users;
        }
        return null;
    }
}
