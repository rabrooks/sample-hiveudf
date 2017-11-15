package com.mycompany.hiveudf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.LongWritable;
 
public final class Sleep extends UDF {
  public void evaluate(final LongWritable s) {
    if (s == null) { return; }
    try {
      Thread.sleep(s.get());
    } catch (java.lang.InterruptedException e) {
      // Continue
    }
  }
}
