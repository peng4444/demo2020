package cn.pbj.demo2020.gateway.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @pClassName: GpDefineGatewayFilterFactory
 * @author: pengbingjiang
 * @create: 2020/11/18 15:45
 * @description: TODO 自定义过滤器GpDefineGatewayFilterFactory
 *  - 类名必须要统一以GatewayFilterFactory结尾，因为默认情况下过滤器的name会采用该自定义类的前缀，这里的name=GpDefine
 *  - 在apply方法中，同时包含Pre和POST过滤。在then方法中是请求执行结束之后的后置处理。
 *  - GpConfig是一个配置类，该类中只有一个属性name，这个属性可以在yml文件中使用。
 *  - 该类需要装载到Spring IOC容器，此处使用@Service注解实现
 *  在application.yml文件中配置
 *          - id: define_filter    # id :自定义路由ID，保持唯一
 *           predicates:          # 路由条件，根据匹配的结果决定是否执行该请求路由
 *             - Path=/gateway/**
 *           filters:            # 过滤规则，包含pre和post过滤，其中StripPrefix=1，表示Gateway根据该配置的值去掉URL路径中的部分前缀SpringCloudGateway网关
 *             - name: GpDefine  #name属性就是GpDefineGatewayFilterFactory的前缀。
 *               args:
 *                 name: Gp_Mic
 *             - StripPrefix=1
 *           uri: http://localhost:9981/say
 */
@Service
@Slf4j
public class GpDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<GpDefineGatewayFilterFactory.GpConfig> {

    public GpDefineGatewayFilterFactory(){
        super(GpConfig.class);
    }

    @Override
    public GatewayFilter apply(GpConfig config) {
        return ((exchange, chain) -> {
            log.info("[Pre] Filter Request,name:"+config.getName());
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("[Post] Response Filter");
            }));
        });
    }

    public static class GpConfig{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
