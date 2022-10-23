import org.junit.jupiter.api.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import serialize.GameProgress;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;

import static files.Files.createFiles;
import static serialize.GameProgress.zipFiles;
import static serialize.GameProgress.zipFilesWithExceptionThroing;

public class FileTest {
    private static final String mainFolderName = "mainFolder";
    private static final Logger log = LoggerFactory.getLogger(FileTest.class);
    private static final File mainFolder;
    private static final Path maintFolderPath;
    private static final Boolean isMainFolderExists;

    static {
        mainFolder = new File(mainFolderName);
        maintFolderPath = Path.of(mainFolder.getPath());
        isMainFolderExists = mainFolder.exists();
    }

    private static void deleteMainFolder() throws IOException {
        java.nio.file.Files.walk(maintFolderPath)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @BeforeEach
    public void cleanUpBefore() throws IOException {
        if (isMainFolderExists) {
            deleteMainFolder();
            log.info(() -> "MainFolder deleted");
        }
    }

    @AfterEach
    public void cleanUpAfter() throws IOException {
        if (isMainFolderExists) {
            deleteMainFolder();
            log.info(() -> "MainFolder deleted");
        }
    }

    @Test
    @DisplayName("Проверка создания  каталогов и файлов")
    public void creating_file() throws IOException {
        // given
        File createdFolder;

        // when
        createFiles();
        createdFolder = new File(mainFolderName);

        // then
        Assertions.assertTrue(createdFolder.exists());
    }

    @Test
    @DisplayName("Проверка создания архива")
    public void zip_files() throws IOException {
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
        Assertions.assertTrue(createdZip.exists());
    }

    @Test
    public void create_zip_with_exception() throws IOException {
        // given
        String filename = "file";
        String zip = "mainFolder/zip.zip";

        // when and then
        Assertions.assertThrows(IOException.class, () -> zipFilesWithExceptionThroing(zip, filename));
    }
}
