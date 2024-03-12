import org.junit.jupiter.api.Test;

public class TemplateTest {

    @Test
    public void testTemplate(){
        BeverageMaker teaMaker = new TeaBeverageMaker();
        BeverageMaker coffeeMaker = new CoffeeBeverageMaker();

        teaMaker.prepare();
        coffeeMaker.prepare();
    }
}
