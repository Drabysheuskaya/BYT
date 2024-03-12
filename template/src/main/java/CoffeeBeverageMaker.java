public class CoffeeBeverageMaker extends BeverageMaker {
    @Override
    protected void boilWater() {
        System.out.println("Boil water for coffee");

    }

    @Override
    protected void pour() {
        System.out.println("Add tea and water in the cup");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Add sugar and ginger");
    }
}
