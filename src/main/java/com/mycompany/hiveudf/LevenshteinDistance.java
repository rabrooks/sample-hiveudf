package com.mycompany.hiveudf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
 
public final class LevenshteinDistance extends UDF {
  public IntWritable evaluate(final Text at, final Text bt) {
    if (at == null || bt == null) { return null; }

    String a = at.toString().toLowerCase();
    String b = bt.toString().toLowerCase();
    int [] costs = new int [b.length() + 1];
    for (int j = 0; j < costs.length; j++)
        costs[j] = j;
    for (int i = 1; i <= a.length(); i++) {
        costs[0] = i;
        int nw = i - 1;
        for (int j = 1; j <= b.length(); j++) {
            int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
            nw = costs[j];
            costs[j] = cj;
        }
    }
    return new IntWritable(costs[b.length()]);
  }
}
