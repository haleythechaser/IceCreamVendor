package polyu.haleyfu;

import junit.framework.Assert;
import org.junit.Test;

public class AdminPanelTest {

    @Test
    public void testActionPerformed() throws Exception {
        AdminPanel adminPanel = new AdminPanel();
        try {
            adminPanel.actionPerformed(null);
        }catch (Exception e){
            Assert.fail("exception happened");
        }
    }
}