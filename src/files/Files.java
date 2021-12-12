package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Files {

    public static void createFiles() {
        File mainFolder = new File("mainFolder");
        File gamesDir = new File("mainFolder/games");
        File srcDir = new File("mainFolder/games/src");
        File mainDir = new File("mainFolder/games/src/main");
        File mainFile = new File(mainDir, "Main.java");
        File utilsFile = new File(mainDir, "Utils.java");
        File testDir = new File("mainFolder/games/src/test");
        File resDir = new File("mainFolder/games/res");
        File drawablesDir = new File("mainFolder/games/res/drawables");
        File vectorsDir = new File("mainFolder/games/res/vectors");
        File iconsDir = new File("mainFolder/games/res/icons");
        File savegamesDir = new File("mainFolder/games/savegames");
        File tempDir = new File("mainFolder/games/temp");
        File tempFile = new File(tempDir, "temp.txt");

        StringBuilder logBuilder = new StringBuilder();

        if (gamesDir.exists()) gamesDir.delete();

        try {
            if (mainFolder.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(mainFolder.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(mainFolder.getName()).append("\n");
            if (gamesDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(gamesDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(gamesDir.getName()).append("\n");
            if (srcDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(srcDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(srcDir.getName()).append("\n");
            if (mainDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(mainDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(mainDir.getName()).append("\n");
            if (testDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(testDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(testDir.getName()).append("\n");
            if (tempDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(tempDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(tempDir.getName()).append("\n");
            if (resDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(resDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(resDir.getName()).append("\n");
            if (drawablesDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(drawablesDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(drawablesDir.getName()).append("\n");
            if (vectorsDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(vectorsDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(vectorsDir.getName()).append("\n");
            if (iconsDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(iconsDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(iconsDir.getName()).append("\n");
            if (savegamesDir.mkdir())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(savegamesDir.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(savegamesDir.getName()).append("\n");
            if (mainFile.createNewFile())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(mainFile.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(mainFile.getName()).append("\n");
            if (utilsFile.createNewFile())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(utilsFile.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(utilsFile.getName()).append("\n");
            if (tempFile.createNewFile())
                logBuilder.append(LocalDateTime.now()).append(": Created - ").append(tempFile.getName()).append("\n");
            else
                logBuilder.append(LocalDateTime.now()).append(": Already exists - ").append(tempFile.getName()).append("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        var log = logBuilder.toString();

        try {
            FileWriter writer = new FileWriter(tempFile, true);
            writer.write(log);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
