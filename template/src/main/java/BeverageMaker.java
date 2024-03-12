public abstract class BeverageMaker {

    protected abstract void boilWater();

    protected abstract void pour();

    protected abstract void addCondiments();

    public final void prepare(){
        boilWater();
        pour();
        addCondiments();
    }
}




