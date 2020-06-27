package cn.pbj.demo2020.book.springinaction4.chapter2;

import org.springframework.stereotype.Component;

/**
 * @pClassName: SgtPeppers
 * @author: pengbingjiang
 * @create: 2020/6/25 22:58
 * @description: TODO 带有 @Component 注解的 CompactDisc 实现类 SgtPeppers
 */
@Component//类会作为组件类，并告知 Spring 要为这个类创建 bean
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}
