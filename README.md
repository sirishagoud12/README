# Hadoop Bloom Filter using MapReduce

This project implements a Bloom filter using Hadoop's MapReduce framework.  
The Bloom filter is created in the mapper phase and merged in the reducer phase.

## Prerequisites

- Java 8 or higher
- Apache Hadoop 3.x or higher
- Maven or Hadoop setup with `hadoop-common` and `hadoop-mapreduce-client-core` dependencies

## Compilation & Execution

### **Step 1: Compile the Code**

```sh
javac -cp `hadoop classpath` -d . BloomWritable.java BloomMapper.java BloomReducer.java BloomDriver.java
jar cf bloomfilter.jar *.class
```

hadoop jar bloomfilter.jar BloomDriver <input_path> <output_path>
Run Hadoop Job
sh
Copy
Edit
hadoop jar bloomfilter.jar BloomDriver <input_path> <output_path>
Step 3: View Output
sh
Copy
Edit
hdfs dfs -cat <output_path>/part-r-00000
Example Input
nginx
Copy
Edit
apple
banana
grape
apple
mango
Expected Output
css
Copy
Edit
bloom <serialized Bloom filter data>
