package hw.week1.springboot.week1HW;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "cakeFlavour.env", havingValue = "strawberry")
public class StrawberryFrosting implements Frosting{
    @Override
    public String getFrostingtype() {
        return "Strawberry frosting";
    }
}
