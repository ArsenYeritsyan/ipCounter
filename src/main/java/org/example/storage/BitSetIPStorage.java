package org.example.storage;

import java.util.BitSet;

public class BitSetIPStorage implements IPStorage {
  private final BitSet negativeNumbers;
  private final BitSet positiveNumbers;

  private BitSetIPStorage(BitSet negativeNumbers, BitSet positiveNumbers) {
    this.negativeNumbers = negativeNumbers;
    this.positiveNumbers = positiveNumbers;
  }

  public static BitSetIPStorage getInstance() {
    return SingletonHolder.HOLDER_INSTANCE;
  }

  @Override
  public void addIPIfNotExists(int ipHash) {
    if (ipHash >= 0) {
      positiveNumbers.set(ipHash);
    } else {
      negativeNumbers.set(-ipHash);
    }
  }

  @Override
  public long getIPCount() {
    return negativeNumbers.cardinality() + positiveNumbers.cardinality();
  }

  @Override
  public void clear() {
    negativeNumbers.clear();
    positiveNumbers.clear();
  }

  private static class SingletonHolder {
    public static final BitSetIPStorage HOLDER_INSTANCE =
        new BitSetIPStorage(new BitSet(Integer.MAX_VALUE), new BitSet(Integer.MAX_VALUE));
  }
}
