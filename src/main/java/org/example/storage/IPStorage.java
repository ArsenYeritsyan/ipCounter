package org.example.storage;

public interface IPStorage {
  void addIPIfNotExists(int ipHash);

  long getIPCount();

  void clear();
}
