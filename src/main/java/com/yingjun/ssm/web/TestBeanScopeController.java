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
        System.out.println("BeanFactory :" + beanFactory);
        System.out.println("ApplicationContext :" + applicationContext);

        System.out.println("first  time singleton is :" + getSingletonObj());
        System.out.println("second time singleton is :" + getSingletonObj());

        System.out.println("first  time prototype is :" + getPrototypeObj());
        System.out.println("second time prototype is :" + getPrototypeObj());

        System.out.println("first  time request is :" + getRequestObj());
        System.out.println("second time request is :" + getRequestObj());

        System.out.println("first  time session is :" + getSessionObj());
        System.out.println("second time session is :" + getSessionObj());
    }

    public RequestObj getRequestObj() {
        return applicationContext.getBean(RequestObj.class);
    }

    public SessionObj getSessionObj() {
        return applicationContext.getBean(SessionObj.class);
    }


    public PrototypeObj getPrototypeObj() {
        return applicationContext.getBean(PrototypeObj.class);
    }

    public SingletonObj getSingletonObj() {
        return applicationContext.getBean(SingletonObj.class);
    }

}
