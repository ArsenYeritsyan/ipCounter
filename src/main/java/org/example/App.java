package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exception.FileException;
import org.example.service.IPCounterService;
import org.example.storage.BitSetIPStorage;
import org.example.storage.FileStorage;
import org.example.utils.IPParser;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Objects;

public class App {
  private static final Logger log = LogManager.getLogger(App.class);

  private static final String FILE_COMMAND = "-file";

  public static void main(String[] args) {
    String filePath = getFilePath(args);

    IPCounterService ipCounterService =
        new IPCounterService(
            new FileStorage(filePath), BitSetIPStorage.getInstance(), new IPParser());

    System.out.println("Started at " + OffsetDateTime.now());
    Instant startTime = Instant.now();

    System.out.println("Unique IP count: " + ipCounterService.getUniqueIPCount());

    Instant endTime = Instant.now();
    System.out.println("Finished at " + OffsetDateTime.now());

    System.out.println("All duration time in Millis: " + Duration.between(startTime, endTime).toMillis());
  }

  private static String getFilePath(String[] args) {
    String filePath;
    if (FILE_COMMAND.equals(args[0]) && Objects.nonNull(args[1])) {
      filePath = args[1];
    } else {
      throw new FileException(
          "The path to the file was not passed. Write, for example, like this: java -jar ipcounter-1.0-SNAPSHOT-jar-with-dependencies.jar -file test.txt");
    }
    return filePath;
  }
}
