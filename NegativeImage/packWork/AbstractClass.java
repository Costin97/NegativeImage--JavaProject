package packWork;

public abstract class AbstractClass implements Interface {
    public abstract int getHeights();

    public abstract int getWidths();

    static {
        System.out.println("Apelarea constructorului clasei AbstractClass!");
    }

}
