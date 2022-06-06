package org.example.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exception.FileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileStorage {
  private static final Logger log = LogManager.getLogger(FileStorage.class);

  private final String filePath;

  public FileStorage(String filePath) {
    this.filePath = filePath;
  }

  public Stream<String> getLines() {
    try {
      return Files.lines(Paths.get(filePath));
    } catch (IOException e) {
      log.error("Fails to read a file", e);
      throw new FileException("Fails to read a file", e);
    }
  }
}
