public class TeaBeverageMaker extends BeverageMaker {
    @Override
    protected void boilWater() {
        System.out.println("Boil water for tea");
    }

    @Override
    protected void pour() {
        System.out.println("Add coffee and water in the cup");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
