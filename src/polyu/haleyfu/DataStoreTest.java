package polyu.haleyfu;

import junit.framework.Assert;
import org.junit.Test;

public class DataStoreTest {

    @Test
    public void testGetInstance() throws Exception {
        DataStore dataStore = DataStore.getInstance();
        Assert.assertNotNull(dataStore);
    }

    @Test
    public void testAddFlavor() throws Exception {
        DataStore dataStore = DataStore.getInstance();
        dataStore.addFlavor("test",0);
        IceCream.Flavor flavor = dataStore.mFlavors.get(dataStore.mFlavors.size()-1);
        Assert.assertEquals("test",flavor.mName);
        Assert.assertEquals(0,flavor.mPrice);
    }

    @Test
    public void testAddDecorator() throws Exception {
        DataStore dataStore = DataStore.getInstance();
        dataStore.addDecorator("test", 0);
        IceCream.Decorator decorator = dataStore.mDecorators.get(dataStore.mDecorators.size()-1);
        Assert.assertEquals("test",decorator.mName);
        Assert.assertEquals(0,decorator.mPrice);
    }
}