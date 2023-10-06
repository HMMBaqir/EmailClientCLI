import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class saveEmailObjects {
    public static void saveEmail(emailObject object, String filename) {
        File file = new File(filename);
        boolean append = file.exists(); // if file exists then append, otherwise create new
        try (FileOutputStream fout = new FileOutputStream(file, append);
                AppendableObjectOutputStream oout = new AppendableObjectOutputStream(fout, append)) {
            oout.writeObject(object); // replace "..." with serializable object to be written
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}