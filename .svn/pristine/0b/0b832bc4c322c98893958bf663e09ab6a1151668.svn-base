package de.uniwue.jpp.testing.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestDishType extends AbstractEnumTestClass {

    @Test
    public void testEnumValues(){
        var value = getEnumClass();
        var test = Arrays.asList(value.getEnumConstants());
        System.out.println("test " + test.toString());
        if (test.size() != 4) {
            fail("The values in the DishType enum are not as expected");
        }

        for (int i = 0; i < test.size(); i++) {
            if (!test.get(i).toString().equals("MAIN_DISH") && !test.get(i).toString().equals("DESSERT")
                    && !test.get(i).toString().equals("OTHER") && !test.get(i).toString().equals("STARTER")) {
                fail("The values in the DishType enum are not as expected");
            }
        }
    }
}
