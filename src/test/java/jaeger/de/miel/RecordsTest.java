package jaeger.de.miel;

import jaeger.de.miel.record.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecordsTest {

    // Record classes are final, no inheritance is supported
    // They are intended just to hold data
    // They are immutable so NO setters are available

    @Test
    public void createProduct(){

        var product = new Product("Iphone",
                                  new BigDecimal("999.99"),
                                  "ELECTRONICS");

        System.out.println(product);

        assertEquals("Iphone", product.name());
        assertEquals(new BigDecimal("999.99"), product.cost());
        assertEquals("ELECTRONICS", product.type());
    }

    @Test
    public void createProductNameNotValid(){
        Executable product = () -> new Product("",
                                               new BigDecimal("999.99"),
                                               "ELECTRONICS");

        var exception = assertThrows(IllegalArgumentException.class, product);

        assertEquals("name is not valid.", exception.getMessage());

    }

    @Test
    public void createProductCostNotValid(){
        Executable product = () -> new Product("Iphone",
                                                new BigDecimal("-999.99"),
                                                "ELECTRONICS");

        var exception = assertThrows(IllegalArgumentException.class, product);

        assertEquals("cost is not valid.", exception.getMessage());

    }

    @Test
    public void createProductCustomConstructor(){

        var product = new Product("Iphone",
                                  new BigDecimal("999.99"));

        System.out.println(product);

        assertEquals("Iphone", product.name());
        assertEquals(new BigDecimal("999.99"), product.cost());
        assertEquals("GENERAL", product.type());
    }

    @Test
    public void createProductComparison(){

        var product1 = new Product("Iphone",
                new BigDecimal("999.99"),
                "ELECTRONICS");

        var product2 = new Product("Iphone",
                new BigDecimal("999.99"),
                "ELECTRONICS");

        System.out.println(product1);
        System.out.println(product2);

        assertEquals(product1, product2);  // Record class automatically generates equals(Object) method for all fields
                                           // But you can override equals(Object) and hashCode() to customize

        System.out.println(product1.equals(product2));
        System.out.println(product1 == product2);
    }
}
