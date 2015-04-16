package polyu.haleyfu;

import junit.framework.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void testActionPerformed() throws Exception {
        Main main = new Main();
        try {
            main.actionPerformed(null);
        }catch (Exception e){
            Assert.fail("exception happened");
        }
    }

    @Test
    public void testDecoratorChanged() throws Exception {
        Main main = new Main();
        try {
            main.decoratorChanged();
        }catch (Exception e){
            Assert.fail("exception happened");
        }
    }

    @Test
    public void testFlavorChanged() throws Exception {
        Main main = new Main();
        try {
            main.flavorChanged();
        }catch (Exception e){
            Assert.fail("exception happened");
        }
    }

    @Test
    public void testMain() throws Exception {
        Main main = new Main();
        try {
            main.main(null);
            main.main(new String[]{"test"});
        }catch (Exception e){
            Assert.fail("exception happened");
        }
    }
}