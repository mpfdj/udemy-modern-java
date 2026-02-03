package jaeger.de.miel;


import org.junit.jupiter.api.Test;

public class TextBlocksTest {

    @Test
    public void test() {
        var hello = hello("miel");
        System.out.println(hello);
    }


    public String hello(String name) {
        return """
               Hello %s
               I can handle single quotes '
               and double quotes "
               without any problem!!
               """.formatted(name);
    }
}
