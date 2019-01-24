package com.yingjun.ssm.web;

import com.yingjun.ssm.entity.scope.PrototypeObj;
import com.yingjun.ssm.entity.scope.RequestObj;
import com.yingjun.ssm.entity.scope.SessionObj;
import com.yingjun.ssm.entity.scope.SingletonObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scope")
public class TestBeanScopeController implements ApplicationContextAware, BeanFactoryAware {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/")
    public String index() {
        print();
        return "Welcome";
    }

    public void print() {

        System.out.println("测试bean的生命周期，获取bean singleton :" + applicationContext.getBean(SingletonObj.class));
        System.out.println("再次获取bean singleton :" + applicationContext.getBean(SingletonObj.class));

        System.out.println("测试bean的生命周期，获取bean prototype :" +applicationContext.getBean(PrototypeObj.class));
        System.out.println("再次获取bean prototype :" + applicationContext.getBean(PrototypeObj.class));

        System.out.println("测试bean的生命周期，获取bean request :" + applicationContext.getBean(RequestObj.class));
        System.out.println("再次获取bean request :" + applicationContext.getBean(RequestObj.class));

        System.out.println("测试bean的生命周期，获取bean session :" + applicationContext.getBean(SessionObj.class));
        System.out.println("再次获取bean session :" + applicationContext.getBean(SessionObj.class));
    }

}
