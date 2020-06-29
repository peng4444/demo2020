package cn.pbj.demo2020.book.springinaction4.chapter13;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @pClassName: CachingConfig
 * @author: pengbingjiang
 * @create: 2020/6/29 16:41
 * @description: TODO
 */
@Configuration
@EnableCaching
public class CachingConfig {

//    @Bean
//    public EhCacheCacheManager cacheManager(CacheManager cm) {
//        EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager(cm);
//        return ehCacheCacheManager;
//    }
//
//    //缓存管理器
//    @Bean
//    public EhCacheManagerFactoryBean ehcache() {
//        EhCacheManagerFactoryBean ehCacheFactoryBean =
//                new EhCacheManagerFactoryBean();
//        ehCacheFactoryBean.setConfigLocation(
//                new ClassPathResource("spittr/cache/ehcache.xml"));
//        return ehCacheFactoryBean;
//
//        //EhCache 配置声明一个名为 spittleCache 的缓存，它最大的堆存储为 50MB，存活时间为 100 秒。
//        //<ehcache>
//        //  <cache name="spittleCache" maxBytesLocalHeap="50m" timeToLiveSeconds="100">
//        //  </cache>
//        //</ehcache>
//    }
//
//    //使用Redis缓存
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisCF) {
//        RedisTemplate<String, String> redisTemplate =
//                new RedisTemplate<String, String>();
//        redisTemplate.setConnectionFactory(redisCf);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    //使用多个缓存管理器  CompositeCacheManager 会迭代一个缓存管理器的列表
//    @Bean
//    public CacheManager ehcacheManager(
//            net.sf.ehcache.CacheManager cm,
//            javax.cache.CacheManager jcm) {
//        CompositeCacheManager cacheManager = new CompositeCacheManager();
//        List<CacheManager> managers = new ArrayList<CacheManager>();
//        managers.add(new JCacheCacheManager(jcm));
//        managers.add(new EhcacheCacheManager(cm));
//        managers.add(new RedisCacheManager(redisTemplate()));
//        cacheManager,setCacheManagers(managers);
//        return cacheManager;
//    }

}
