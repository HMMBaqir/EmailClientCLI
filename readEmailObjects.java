import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.EOFException;

import java.io.ObjectInputStream;
import java.util.ArrayList;

public class readEmailObjects {
    public static ArrayList<emailObject> readEmail(String filename) {
        ArrayList<emailObject> emailArray = new ArrayList<>();
        boolean check = true;
        try (FileInputStream fileinput = new FileInputStream(filename)) {
            ObjectInputStream objectstream = new ObjectInputStream(fileinput);
            while (check) {
                emailObject emailRead = (emailObject) objectstream.readObject();
                emailArray.add(emailRead);
            }
            objectstream.close();
        } catch (EOFException e) {
            check = false;
        } catch (FileNotFoundException e) {
            return emailArray;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return emailArray;
    }
}