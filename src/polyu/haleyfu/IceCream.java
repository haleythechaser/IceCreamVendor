package polyu.haleyfu;

import java.util.HashSet;
import java.util.Set;


class IceCream {
    Flavor mFlavor;
    Set<Decorator> mDecorator = new HashSet<Decorator>();
    int mPrice;
    void setFlavor(Flavor flavor){
        mFlavor = flavor;
    }
    void setDecorator(Decorator decorator){
        if (mDecorator.contains(decorator)){
            mDecorator.remove(decorator);
        }else{
            mDecorator.add(decorator);
        }
    }
    void clear(){
        mFlavor = null;
        mDecorator.clear();
        mPrice = 0;
    }
    static class Flavor {
        final String mName;
        int mPrice;

        Flavor(String name, int price) {
            mName = name;
            mPrice = price;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Flavor)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (((Flavor) obj).mName.equals(mName)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return mName.hashCode();
        }
    }

    static class Decorator {
        final String mName;
        int mPrice;

        Decorator(String name, int price) {
            mName = name;
            mPrice = price;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Decorator)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (((Decorator) obj).mName.equals(mName)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return mName.hashCode();
        }
    }
}
