package persistence;

import drawing.domain.Drawing;

import java.io.*;
import java.util.Properties;

/*
 * Created by max1_ on 07/03/2017.
 * Handles serialising a drawing object
 */
public class SerializationMediator implements PersistencyMediator {

    private Properties props;

    @Override
    public Drawing load(String nameDrawing) {

        Drawing drawing;
        System.out.println("Loading file at" + System.getProperty("user.dir") + "\\saves\\drawing.ser");
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "\\saves\\" + nameDrawing + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            drawing = (Drawing) in.readObject();
            in.close();
            fileIn.close();
            drawing.setName(nameDrawing);
            return drawing;
        }catch(IOException i) {
            i.printStackTrace();
            return null;
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        } catch(NullPointerException n) {
            n.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Drawing drawing) {
        try {
            // Create a new file to serialise to
            // If the file already exists nothing will happen
            File outFile = new File(System.getProperty("user.dir") + "\\saves\\" + drawing.getName() + ".ser");
            outFile.getParentFile().createNewFile();
            outFile.createNewFile();
            System.out.println("File at: " +  outFile.getAbsolutePath());

            FileOutputStream fileOut = new FileOutputStream(outFile, false);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(drawing);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + outFile.getAbsolutePath());
            return true;
        }catch(IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean init(Properties props) {
        return false;
    }
}
