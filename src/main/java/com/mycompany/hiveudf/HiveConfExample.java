package com.mycompany.hiveudf;

/* Print the value of hive.auto.convert.join.noconditionaltask.size */

import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.conf.HiveConf; 
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.hive.ql.session.SessionState;
 
public final class HiveConfExample extends UDF {
  public Text evaluate() {
    SessionState ss = SessionState.get();
    if (ss != null) {
      long size = ss.getConf().getLongVar(ConfVars.HIVECONVERTJOINNOCONDITIONALTASKTHRESHOLD);
      return new Text(Long.toString(size));
    } else {
      return null;
    }
  }
}
