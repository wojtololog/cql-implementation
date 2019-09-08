import model.SensorData;
import model.SensorDataGenerator;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CQLImplementation {
    public static void main(String args[]) {
        try {
            final long streamSize = 20000;
            SensorDataGenerator sensorDataGenerator = new SensorDataGenerator(3, 12, 28);
            Stream<SensorData> sensorDataStream = Stream.generate(sensorDataGenerator::generate).limit(streamSize);

            String query = "select [RANGE 200] * from sensorDataStream";
            CQLParser cqlParser = new CQLParser(query, sensorDataStream);

            Stream<SensorData> result = cqlParser.parse();
            result.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
