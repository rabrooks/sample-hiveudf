package com.mycompany.hiveudf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
 
public final class ThrowError extends UDF {
  public Text evaluate(final Text s) throws Exception {
    throw new Error("FAKE_ERROR: " + s.toString());
  }
}
