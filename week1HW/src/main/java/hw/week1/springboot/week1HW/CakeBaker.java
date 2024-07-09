package hw.week1.springboot.week1HW;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CakeBaker {

    @Autowired
    Frosting frosting;
    //here we are using interface But how ?
//    How does spring know which polymorphic type to use.
//
//    As long as there is only a single implementation of the interface and
//    that implementation is annotated with @Component with Spring's component scan enabled,
//    Spring framework can find out the (interface, implementation) pair. If component scan is not enabled,
//    then you have to define the bean explicitly in your application-config.xml (or equivalent spring configuration file).
//
//    Do I need @Qualifier or @Resource?
//
//    Once you have more than one implementation, then you need to qualify each of them and during auto-wiring,
//    you would need to use the @Qualifier annotation to inject the right implementation, along with @Autowired annotation.
//    If you are using @Resource (J2EE semantics), then you should specify the bean name using the name attribute of this annotation.
//
//    Why do we autowire the interface and not the implemented class?
//
//    Firstly, it is always a good practice to code to interfaces in general. Secondly, in case of spring,
//    you can inject any implementation at runtime. A typical use case is to inject mock implementation during testing stage.

    @Autowired
    Syrup syrup;

    public void bakeCake(){
        System.out.println("Cake is being baked with "+ frosting.getFrostingtype() + " and " + syrup.getSyrupType() + ".");
    }
}
