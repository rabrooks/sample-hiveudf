# Sample Hive UDF Maven project with a few simple UDFs.

Use this if you want to write a quick Hive UDF with no hassle.
For a quick start just create some classes in src/main/java/com/mycompany/hiveudf.

For production JARs create your own Maven project and use this pom.xml as a starting point.

## Quickstart:

1. Build the JAR:
    ```c
    mvn package
    ```

2. Start Hive CLI with the JAR in your classpath:
    ```c
    export HADOOP_CLASSPATH=target/sample-hiveudf-1.0-SNAPSHOT.jar ; hive
    ```

3. Execute:
    ```c
    create temporary function my_lower as 'com.mycompany.hiveudf.CustomLower'; 
    ```

4. Execute:
    ```c
    select my_lower("UPPER CASE LETTERS");
    ```

## For production:

1. Copy your JAR to HDFS where HiveServer2 will be able to read it.
2. Create a permanent function:
    ```c
    sudo -u hdfs hdfs dfs -mkdir /apps/hive/functions
    sudo -u hdfs hdfs dfs -chown hive:hive /apps/hive/functions
    cp sample-hiveudf-1.0-SNAPSHOT.jar /tmp
    sudo -u hive hdfs dfs -copyFromLocal -f /tmp/sample-hiveudf-1.0-SNAPSHOT.jar /apps/hive/functions
    ```
3. In beeline:
    ```c
    CREATE FUNCTION my_lower AS 'com.mycompany.hiveudf.CustomLower' USING JAR 'hdfs:////apps/hive/functions/sample-hiveudf-1.0-SNAPSHOT.jar';
    ```

3. Call the functions from your ODBC/JDBC clients via HiveServer2.
