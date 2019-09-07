import model.SensorData;
import model.SensorDataGenerator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CQLImplementation {
    public static void main(String args[]) {
        try {
            final long streamSize = 20000;
            SensorDataGenerator sensorDataGenerator = new SensorDataGenerator(3, 12, 28);
            Stream<SensorData> sensorDataStream = Stream.generate(sensorDataGenerator::generate).limit(streamSize);

         /*   Stream<SensorData> streamToGroup = sensorDataStream.limit(150).filter(g -> g.getName().equals("sensor1") || g.getName().equals("sensor2"));

            Map<String, Double> averageTemperatureBySensorName = streamToGroup
                    .collect(
                            Collectors.groupingBy(
                                    SensorData::getName,
                                    TreeMap::new,
                                    Collectors.averagingDouble(SensorData::getTemperature)));
            Set<Map.Entry<String,Double>> entrySet = averageTemperatureBySensorName.entrySet();
            for(Map.Entry<String, Double> entry: entrySet) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }*/

            String query = "select [RANGE 1000] AVG(sensor1) from sensorDataStream where temperature < 15";
            CQLParser cqlParser = new CQLParser(query, sensorDataStream);

            Stream<SensorData> result = cqlParser.parse();
            result.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
