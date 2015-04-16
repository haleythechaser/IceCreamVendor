package polyu.haleyfu;

import java.util.ArrayList;
import java.util.List;


public class DataStore {

    private static DataStore mInstance;
    private IceCream.Flavor[] predefinedFlavors = {new IceCream.Flavor("Chocolate", 20),
            new IceCream.Flavor("Vanilla", 20)};
    private IceCream.Decorator[] predefinedDecorator = {new IceCream.Decorator("M&M", 5),
            new IceCream.Decorator("Strawberry", 4)};
    List<IceCream.Flavor> mFlavors = new ArrayList<IceCream.Flavor>();
    List<IceCream.Decorator> mDecorators = new ArrayList<IceCream.Decorator>();
    FlavorChangedListener mFlavorListener;
    DecoratorChangedListener mDecoratorListener;

    static interface FlavorChangedListener {
        void flavorChanged();
    }

    static interface DecoratorChangedListener {
        void decoratorChanged();
    }

    static DataStore getInstance() {
        if (mInstance == null) {
            mInstance = new DataStore();
        }
        return mInstance;
    }

    private DataStore() {
        for (IceCream.Flavor flavor : predefinedFlavors) {
            mFlavors.add(flavor);
        }
        for (IceCream.Decorator decorator : predefinedDecorator) {
            mDecorators.add(decorator);
        }
    }

    boolean addFlavor(String name, int price) {
        IceCream.Flavor flavor = new IceCream.Flavor(name, price);
        int index = mFlavors.indexOf(flavor);
        if (index != -1) {
            if (mFlavors.get(index).mPrice == price) {
                //the item with identical name & price has been added already
                return false;
            } else {
                //just reset the price of the this item
                mFlavors.get(index).mPrice = price;
                if (mFlavorListener != null) {
                    mFlavorListener.flavorChanged();
                }
                return true;
            }
        } else {
            //add item for the first time
            mFlavors.add(flavor);
            if (mFlavorListener != null) {
                mFlavorListener.flavorChanged();
            }
            return true;
        }
    }

    boolean addDecorator(String name, int price) {
        IceCream.Decorator decorator = new IceCream.Decorator(name, price);
        int index = mDecorators.indexOf(decorator);
        if (index != -1) {
            if (mDecorators.get(index).mPrice == price) {
                //the item with identical name & price has been added already
                return false;
            } else {
                //just reset the price of the this item
                mDecorators.get(index).mPrice = price;
                if (mDecoratorListener != null) {
                    mDecoratorListener.decoratorChanged();
                }
                return true;
            }
        } else {
            //add item for the first time
            mDecorators.add(decorator);
            if (mDecoratorListener != null) {
                mDecoratorListener.decoratorChanged();
            }
            return true;
        }
    }
}
