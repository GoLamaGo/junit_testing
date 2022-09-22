package serialize;

import files.Files;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        Files.createFiles();

        GameProgress game1 = new GameProgress(1, 2, 3, 4.5D);
        GameProgress game2 = new GameProgress(2, 3, 4, 5.6D);
        GameProgress game3 = new GameProgress(3, 4, 5, 6.7D);

        File save1 = new File("mainFolder/games/savegames/save1.dat");
        File save2 = new File("mainFolder/games/savegames/save2.dat");
        File save3 = new File("mainFolder/games/savegames/save3.dat");

        GameProgress.saveGame(save1.toString(), game1);
        GameProgress.saveGame(save2.toString(), game2);
        GameProgress.saveGame(save3.toString(), game3);

        GameProgress.zipFiles("mainFolder/games/savegames/saves.zip",
                save1.toString(),
                save2.toString(),
                save3.toString()
        );

        GameProgress.openZip("mainFolder/games/savegames/saves.zip",
                "mainFolder/games/savegames");

        var savedGame = GameProgress.openProgress(save1.toString());
        System.out.println(savedGame.toString());


        if (save1.delete()) System.out.printf("File %s was deleted%n", save1.getName());
        if (save2.delete()) System.out.printf("File %s was deleted%n", save2.getName());
        if (save3.delete()) System.out.printf("File %s was deleted%n", save3.getName());
    }
}
