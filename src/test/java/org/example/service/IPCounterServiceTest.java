package org.example.service;

import org.example.exception.FileException;
import org.example.storage.BitSetIPStorage;
import org.example.storage.FileStorage;
import org.example.storage.IPStorage;
import org.example.utils.IPParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class IPCounterServiceTest {

    private final IPStorage ipStorage = BitSetIPStorage.getInstance();

    @AfterEach
    public void clearBitSetStorage() {
        ipStorage.clear();
    }

    @Test
    public void getUniqueIPCountTest() {
        IPCounterService ipCounterService = new IPCounterService(
                new FileStorage("src\\test\\resources\\files\\10_ip_addresses"),
                BitSetIPStorage.getInstance(),
                new IPParser()
        );
        long uniqueIPCount = ipCounterService.getUniqueIPCount();
        Assertions.assertEquals(10, uniqueIPCount);
    }

    @Test
    public void getUniqueIPCountIfEmptyFileTest() {
        IPCounterService ipCounterService = new IPCounterService(
                new FileStorage("src\\test\\resources\\files\\empty_ip_address"),
                BitSetIPStorage.getInstance(),
                new IPParser()
        );
        long uniqueIPCount = ipCounterService.getUniqueIPCount();
        Assertions.assertEquals(0, uniqueIPCount);
    }

    @Test
    public void getUniqueIPCountWrongPathToFileTest() {
        String wrongFilePath = "src\\test\\wrong_path";
        IPCounterService ipCounterService = new IPCounterService(
                new FileStorage(wrongFilePath),
                BitSetIPStorage.getInstance(),
                new IPParser()
        );
        Assertions.assertThrows(FileException.class,
                ipCounterService::getUniqueIPCount,
                "Fails to read a file:" + wrongFilePath);
    }
}