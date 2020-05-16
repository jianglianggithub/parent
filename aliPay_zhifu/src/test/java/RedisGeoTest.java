import com.App;
import com.controller.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class RedisGeoTest {


    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TestController testController;

    @Test
    public void ssssss() throws Exception {
        testController.prePay();
    }
    @Test
    public void aaa() {
        GeoOperations geo = redisTemplate.opsForGeo();
        geo.add("userPotion",new Point(116.397128,39.916527),"张三");
        geo.add("userPotion",new Point(116.50321473095701,39.8390666654405),"李四");
        geo.add("userPotion",new Point(116.41163338635252,39.805974473304985),"王五");

    }
    @Test
    public void bbb() {
        GeoOperations geo = redisTemplate.opsForGeo();
        Distance distance = geo.distance("userPotion", "李四", "王五", Metrics.KILOMETERS);
        System.out.println(distance);
        System.out.println(distance.getValue());

    }
    @Test
    public void ccc() {
        GeoOperations geo = redisTemplate.opsForGeo();
        Point point = new Point(116.50321473095701,39.8390666654405);
        Distance distance = new Distance(10000, RedisGeoCommands.DistanceUnit.KILOMETERS);

        RedisGeoCommands.GeoRadiusCommandArgs param =
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                .includeDistance()
                .sortAscending()
                .includeCoordinates();
        GeoResults<RedisGeoCommands.GeoLocation<String>> userPotion = geo.radius(
                "userPotion",
                new Circle(point, distance),
                param
        );

        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> content = userPotion.getContent();

        double i=-1;
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : content) {
//            NumberFormat formatter = new DecimalFormat("000000");
//            NumberFormat formatter = new DecimalFormat("##");
//            String format = formatter.format(result.getDistance().getValue());
            BigDecimal db = new BigDecimal(result.getDistance().getValue());
            System.out.println(db);
            if (i==-1) {
                i=result.getDistance().getValue();
            }else if (result.getDistance().getValue() < i ){
                i=result.getDistance().getValue();
            }
            System.out.println(i+"  "+ result.getDistance().getNormalizedValue());
        }

        System.out.println(i);

    }
    @Test
    public void dddd() {
        GeoOperations geo = redisTemplate.opsForGeo();
        Distance distance = new Distance(10000, RedisGeoCommands.DistanceUnit.KILOMETERS);

        RedisGeoCommands.GeoRadiusCommandArgs param =
                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
                        .includeDistance()
                        .sortAscending()
                        .includeCoordinates();
        GeoResults<RedisGeoCommands.GeoLocation<String>> userPotion = geo.radius(
                "userPotion",
                "李四",
                distance,
                param
        );

        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> content = userPotion.getContent();

        double i=-1;
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : content) {
//            NumberFormat formatter = new DecimalFormat("000000");
//            NumberFormat formatter = new DecimalFormat("##");
//            String format = formatter.format(result.getDistance().getValue());
            BigDecimal db = new BigDecimal(result.getDistance().getValue());
            System.out.println(db);
            if (i==-1) {
                i=result.getDistance().getValue();
            }else if (result.getDistance().getValue() < i ){
                i=result.getDistance().getValue();
            }
            System.out.println(i+"  "+ result.getDistance().getNormalizedValue());
        }

        System.out.println(i);

    }

    @Test
    public void ffff() {
        GeoOperations geo = redisTemplate.opsForGeo();
        List hash = geo.hash("userPotion", "李四","王五");
        System.out.println(hash);
    }
}
