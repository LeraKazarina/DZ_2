import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.io.*;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress gameProgress = new GameProgress(2,3,4,5);
        GameProgress gameProgress1 = new GameProgress(3, 10,32,2);
        GameProgress gameProgress2 = new GameProgress(5,11,32,4);

        saveGame("C://Workspace//13.1.2//myFile.dat", gameProgress);
        saveGame("C://Workspace//13.1.2//myFile2.dat", gameProgress1);
        saveGame("C://Workspace//13.1.2//myFile3.dat", gameProgress2);

        ArrayList<String> list= new ArrayList();
        list.add("C://Workspace//13.1.2//myFile.dat");
        list.add("C://Workspace//13.1.2//myFile2.dat");
        list.add("C://Workspace//13.1.2//myFile3.dat");

        zipFiles("C://Workspace//13.1.2//zip_output.zip", list);
        }

    private static void saveGame(String file, GameProgress gameProgress) {

        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void zipFiles(String archive, ArrayList<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archive))) {
            try (FileInputStream fis = new FileInputStream(String.valueOf(arrayList))) {
                ZipEntry entry = new ZipEntry("packed_notes.txt");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

