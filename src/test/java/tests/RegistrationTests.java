package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        //if SignOut present--->logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();

        }
    }


    @Test
    public void registrationSuccess(){
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        User user = new User().setEmail("margo" + i +"@gmail.com").setPassword("Mmar123456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());

    }
    @Test(enabled = false,description = "Bug report#4567")
    public void registrationWrongEmail(){
        User user = new User().setEmail("margogmail.com").setPassword("Mmar123456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));


    }
    @Test(groups = {"smoke"})
    public void registrationWrongPassword(){
        User user = new User().setEmail("margo@gmail.com").setPassword("Mmar123");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));


    }
    @Test(enabled = false)
    public void registrationExistsUser(){
        User user = new User().setEmail("margo@gmail.com").setPassword("Mmar123456$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exists"));


    }
}




