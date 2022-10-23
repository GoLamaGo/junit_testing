package serialize;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int health;
    private final int weapons;
    private final int lvl;
    private final double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    public static void saveGame(String file, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String zipPath, String... files) {

        try (ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(zipPath))) {

            for (String file : files) {
                File fileName = new File(file);
                try (FileInputStream fis = new FileInputStream(file)) {

                    ZipEntry zipEntry = new ZipEntry(fileName.getName());
                    zipStream.putNextEntry(zipEntry);

                    byte[] buffer = fis.readAllBytes();

                    zipStream.write(buffer);
                    zipStream.closeEntry();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < files.length; i++) {
            try {
                File fileToDelete = new File(files[i]);
                fileToDelete.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void openZip(String filePath, String destinationPath) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath))) {
            ZipEntry zipEntry;
            File fileName;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                fileName = new File(destinationPath, zipEntry.getName());
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                for (int i = zipInputStream.read(); i != -1; i = zipInputStream.read()) {
                    fileOutputStream.write(i);
                }
                fileOutputStream.flush();
                fileOutputStream.close();

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameProgress openProgress(String savedFile) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(savedFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return gameProgress;
    }


    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

}