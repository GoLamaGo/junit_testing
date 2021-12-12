package serialize;

import files.Files;

import java.io.File;

public class Main {
    public static void main(String[] args) {

//        File file = new File("mainFolder/games/savegames/save1.dat");
//        System.out.println(file.isFile());
//        System.out.println(file.getName());
//        System.out.println(file.isDirectory());

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

    }
}
