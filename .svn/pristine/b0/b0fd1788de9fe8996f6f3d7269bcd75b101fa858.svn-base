package de.uniwue.jpp.testing.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestGuestType extends AbstractEnumTestClass {

    @Test
    public void testEnumValues(){
        var value = getEnumClass();
        var test = Arrays.asList(value.getEnumConstants());

        if (test.size() != 3){
            fail("The values in the GuestType enum are not as expected");
        }

        for (int i = 0; i < test.size(); i++) {
            if (!test.get(i).toString().equals("GUEST") && !test.get(i).toString().equals("STAFF")
                    && !test.get(i).toString().equals("STUDENT")){
                fail("The values in the GuestType enum are not as expected");
            }
        }
    }

    @Test
    public void testDiscountFactor(){
        if (getGuestTypeConstant("GUEST").getDiscountFactor() != 1.0){
            fail("Incorrect value returned by getDiscountFactor() on " + "GUEST");
        }
        if (getGuestTypeConstant("STUDENT").getDiscountFactor() != 0.6){
            fail("Incorrect value returned by getDiscountFactor() on " + "STUDENT");
        }

        if (getGuestTypeConstant("STAFF").getDiscountFactor() != 0.8){
            fail("Incorrect value returned by getDiscountFactor() on " + "STAFF");
        }
    }
}
