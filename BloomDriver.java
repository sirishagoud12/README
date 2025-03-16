import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BloomDriver {
     public static void main(String[] args) throws Exception {
          if (args.length != 2) {
               System.err.println("Usage: BloomDriver <input path> <output path>");
               System.exit(-1);
          }

          Configuration conf = new Configuration();
          Job job = Job.getInstance(conf, "Bloom Filter Job");

          job.setJarByClass(BloomDriver.class);
          job.setMapperClass(BloomMapper.class);
          job.setReducerClass(BloomReducer.class);

          job.setMapOutputKeyClass(Text.class);
          job.setMapOutputValueClass(BloomWritable.class);

          job.setOutputKeyClass(Text.class);
          job.setOutputValueClass(BloomWritable.class);

          FileInputFormat.addInputPath(job, new Path(args[0]));
          FileOutputFormat.setOutputPath(job, new Path(args[1]));

          System.exit(job.waitForCompletion(true) ? 0 : 1);
     }
}
