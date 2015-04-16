package polyu.haleyfu;

import junit.framework.Assert;
import org.junit.Test;

public class IceCreamTest {

    @Test
    public void testSetFlavor() throws Exception {
        IceCream iceCream = new IceCream();
        iceCream.setFlavor(new IceCream.Flavor("test", 0));
        Assert.assertNotNull(iceCream.mFlavor);
    }

    @Test
    public void testSetDecorator() throws Exception {
        IceCream iceCream = new IceCream();
        iceCream.setDecorator(new IceCream.Decorator("test", 0));
        Assert.assertNotNull(iceCream.mDecorator);
        Assert.assertEquals(1, iceCream.mDecorator.size());
    }

    @Test
    public void testClear() throws Exception {
        IceCream iceCream = new IceCream();
        iceCream.setFlavor(new IceCream.Flavor("test", 0));
        iceCream.setDecorator(new IceCream.Decorator("test", 0));
        iceCream.clear();
        Assert.assertNull(iceCream.mFlavor);
        Assert.assertEquals(0,iceCream.mDecorator.size());
        Assert.assertEquals(0,iceCream.mPrice);
    }
}