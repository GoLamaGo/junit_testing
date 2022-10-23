import files.Files;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;

import static files.Files.createFiles;

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
        java.nio.file.Files.walk(maintFolderPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }

    @BeforeAll
    public static void cleanUpBefore() throws IOException {
        if (isMainFolderExists) {
            deleteMainFolder();
            log.info(() -> "MainFolder deleted");
        }
    }

    @AfterAll
    public static void cleanUpAfter() throws IOException {
        deleteMainFolder();
        log.info(() -> "MainFolder deleted");
    }

    @Test
    public void creating_file() throws IOException {
        // given
        File createdFolder;

        // when
        createFiles();
        createdFolder = new File(mainFolderName);

        // then
        Assertions.assertTrue(createdFolder.exists());
    }
}
