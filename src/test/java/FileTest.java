import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;

import static files.Files.createFiles;
import static serialize.GameProgress.zipFiles;
import static serialize.GameProgress.zipFilesWithExceptionThroing;
import static org.junit.jupiter.api.Assertions.*;

public class FileTest {
    private static final String mainFolderName = "mainFolder";
    private static final File mainFolder = new File(mainFolderName);
    private static final Path maintFolderPath = Path.of(mainFolder.getPath());
    private static final Boolean isMainFolderExists = mainFolder.exists();

    /**
     * Метод для удаления созданных во время тестов файлов и папок
     * Все действия производятся в папке mainFolder
     */
    private static void deleteMainFolder() throws IOException {
        java.nio.file.Files.walk(maintFolderPath)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @BeforeEach
    @DisplayName("Удаление папки mainFolder и вложенных файлов и папок")
    public void cleanUpBefore() throws IOException {
        if (isMainFolderExists) {
            deleteMainFolder();
        }
    }

    @AfterEach
    @DisplayName("Удаление папки mainFolder и вложенных файлов и папок")
    public void cleanUpAfter() throws IOException {
        if (isMainFolderExists) {
            deleteMainFolder();
        }
    }

    @Test
    @DisplayName("Проверка создания  каталогов и файлов")
    public void creatingFile() throws IOException {
        // given
        File createdFolder;

        // when
        createFiles();
        createdFolder = new File(mainFolderName);

        // then
        assertTrue(createdFolder.exists());
    }

    @Test
    @DisplayName("Проверка создания архива")
    public void creationZipFiles() throws IOException {
        // given
        File createdZip;
        mainFolder.mkdir();
        File file4Zip = new File(mainFolder, "file4Zip.txt");
        String zip = "mainFolder/zip.zip";
        file4Zip.createNewFile();

        // when
        zipFiles(zip, file4Zip.getPath());
        createdZip = new File(zip);

        // then
        assertTrue(createdZip.exists());
    }

    @Test
    public void createZipWithException() {
        // given
        String filename = "file";
        String zip = "mainFolder/zip.zip";

        // when and then
        assertThrows(IOException.class, () -> zipFilesWithExceptionThroing(zip, filename));
    }
}
