import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;

import java.io.IOException;

public class BloomReducer extends Reducer<Text, BloomWritable, Text, BloomWritable> {
     private BloomFilter finalBloomFilter = new BloomFilter(10000, 5, 1);

     @Override
     protected void reduce(Text key, Iterable<BloomWritable> values, Context context)
               throws IOException, InterruptedException {
          for (BloomWritable bloomWritable : values) {
               finalBloomFilter.or(bloomWritable.getBloomFilter());
          }
          context.write(key, new BloomWritable(finalBloomFilter));
     }
}
