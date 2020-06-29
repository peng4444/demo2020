package cn.pbj.demo2020.book.springinaction4.chapter12;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @pClassName: MongoConfig
 * @author: pengbingjiang
 * @create: 2020/6/29 11:17
 * @description: TODO
 */
@Configuration
@EnableMongoRepositories(basePackages = "cn.pbj.demo2020.book.springinaction4.chapter12")//借助@EnableMongoRepositories启用Spring Data MongoDB
public class MongoConfig extends AbstractMongoConfiguration {

//    @Bean
//    public MongoFactoryBean mongo() {
//        MongoFactoryBean mongo = new MongoFactoryBean();
//        mongo.setHost("localhost");
//        return mongo;
//    }

//    @Bean
//    public MongoOperations mongoTemplate(Mongo mongo) {
//        return new MongoTemplate(mongo, "OrderDB");
//    }

    @Override
    public MongoClient mongoClient() {
        return null;
    }

    @Override
    protected String getDatabaseName() {
        return "OrdersDB";
    }
}
