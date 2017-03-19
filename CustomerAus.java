import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.util.Bytes;



public class CustomerAus 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Configuration conf = HBaseConfiguration.create();
		System.out.println("Creating HTable instance to 'customer'...");
		HTable table = new HTable(conf, "customer");
		System.out.println("Creating scan object...");
		Scan scan = new Scan();
		System.out.println("Narrowing down the result to details column family...");
		scan.addFamily(Bytes.toBytes("details"));
		System.out.println("Adding value filter on scan object...");
		scan.setFilter(new ValueFilter(CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes("AUS"))));
		System.out.println("scan:"+ scan.toString());
		System.out.println("Getting a result scanner object...");
		ResultScanner rs = table.getScanner(scan);
						
		for (Result r : rs) 
		{
			System.out.println("Result: " + r);
		}
		System.out.println("Closing Scanner instance...");
		
		rs.close();
		table.close();
	}

}
