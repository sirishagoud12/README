import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;
import org.apache.hadoop.util.hash.Hash;

import java.io.IOException;

public class BloomMapper extends Mapper<Object, Text, Text, BloomWritable> {
     private BloomFilter bloomFilter = new BloomFilter(10000, 5, Hash.MURMUR_HASH);
     private static final Text BLOOM_KEY = new Text("bloom");

     @Override
     protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
          bloomFilter.add(new Key(value.toString().getBytes()));
     }

     @Override
     protected void cleanup(Context context) throws IOException, InterruptedException {
          context.write(BLOOM_KEY, new BloomWritable(bloomFilter));
     }
}
