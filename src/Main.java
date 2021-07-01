import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress gameProgress = new GameProgress(2,3,4,5);
        GameProgress gameProgress1 = new GameProgress(3, 10,32,2);
        GameProgress gameProgress2 = new GameProgress(5,11,32,4);


        saveGame("C://Workspace//13.1.2//myFile1", gameProgress);
        saveGame("C://Workspace//13.1.2//myFile2", gameProgress1);
        saveGame("C://Workspace//13.1.2//myFile3", gameProgress2);

        zipFiles("C://Workspace//13.1.2//zip_output.zip", "C://Workspace//13.1.2//myFile1");
        zipFiles("C://Workspace//13.1.2//zip_output.zip", "C://Workspace//13.1.2//myFile2");
        zipFiles("C://Workspace//13.1.2//zip_output.zip", "C://Workspace//13.1.2//myFile3");

        }

    private static void saveGame(String file, GameProgress gameProgress) {

        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void zipFiles(String archive, String file) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("zip_output.zip"));
            FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry = new ZipEntry("myFile.txt");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

