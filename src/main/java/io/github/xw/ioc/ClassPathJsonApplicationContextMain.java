package io.github.xw.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.github.xw.model.MyBean;

/**
 * @author yangliu@tiduyun.com
 * @date 2023/10/9
 */
public class ClassPathJsonApplicationContextMain {
    public static void main(String[] args) {
        // 创建 ClassPathXmlApplicationContext 上下文
        ApplicationContext context = new JsonApplicationContext("applicationContext.json");

        // 获取 "myBean" 实例
        MyBean myBean = context.getBean("myBean", MyBean.class);

        // 使用 "myBean"
        myBean.printMessage();

        // 关闭上下文
        ((JsonApplicationContext) context).close();
    }
}
