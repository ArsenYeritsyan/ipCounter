package org.example.service;

import org.example.storage.FileStorage;
import org.example.storage.IPStorage;
import org.example.utils.IPParser;

public class IPCounterService {
  private final FileStorage fileStorage;
  private final IPStorage ipStorage;
  private final IPParser ipParser;

  public IPCounterService(FileStorage fileStorage, IPStorage ipStorage, IPParser ipParser) {
    this.fileStorage = fileStorage;
    this.ipStorage = ipStorage;
    this.ipParser = ipParser;
  }

  public long getUniqueIPCount() {
    fileStorage
        .getLines()
        .forEach(
            line -> {
              int ipHash = ipParser.parseToInt(line);
              this.ipStorage.addIPIfNotExists(ipHash);
            });
    return ipStorage.getIPCount();
  }
}
