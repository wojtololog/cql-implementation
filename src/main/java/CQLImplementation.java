import model.SensorData;
import model.SensorDataGenerator;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CQLImplementation {
    public static void main(String args[]) {
        CQLParser cqlParser;
        try {
            SensorDataGenerator sensorDataGenerator = new SensorDataGenerator(3, 12, 28);
            //Stream<SensorData> sensorDataStream = Stream.generate(sensorDataGenerator::generate);
           /*sensorDataStream.filter(g -> g.getName().equals("sensor1") || g.getName().equals("sensor2"))
                    .limit(100)
                     .forEach(System.out::println);*/

            Supplier<Stream<SensorData>> sensorDataStreamSupplier = () -> Stream.generate(sensorDataGenerator::generate).limit(100);
            
            String query = "select sensor1, sensor2 from sensorDataStream";
            cqlParser = new CQLParser(query, sensorDataStreamSupplier);
            List<Stream<SensorData>> result = cqlParser.parse();
            for(Stream<SensorData> sensorDataStream : result) {
                sensorDataStream.limit(10).forEach(System.out::println);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
