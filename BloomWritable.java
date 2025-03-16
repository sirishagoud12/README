import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.bloom.BloomFilter;
import org.apache.hadoop.util.bloom.Key;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class BloomWritable implements Writable {
     private BloomFilter bloomFilter;

     public BloomWritable() {
          this.bloomFilter = new BloomFilter(10000, 5, 1);
     }

     public BloomWritable(BloomFilter bloomFilter) {
          this.bloomFilter = bloomFilter;
     }

     public BloomFilter getBloomFilter() {
          return bloomFilter;
     }

     @Override
     public void write(DataOutput out) throws IOException {
          bloomFilter.write(out);
     }

     @Override
     public void readFields(DataInput in) throws IOException {
          bloomFilter.readFields(in);
     }
}
