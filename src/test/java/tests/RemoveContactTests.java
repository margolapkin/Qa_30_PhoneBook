package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$"));
        }
        app.getHelperContact().provideContacts();//if list size contact <3--->add 3 contacts
    }
    @Test(groups = {"smoke"})
    public void removeFirstContact(){
        //Assert size contact list less by one
        Assert.assertEquals(app.getHelperContact().removeOneContact(),1);


    }




    @Test
    public void removeAllContact(){
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperContact().isNoContactsHereDisplayed());
        // "No contacts here"

    }
}
