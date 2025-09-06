package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LoginTests extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        //if SignOut present--->logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Before method finished logout");

        }
    }

    @Test(dataProvider ="loginData" ,dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email,String password) {
        logger.info("Start test with name'loginSuccess'");
        logger.info("Test data--->email:"+email+" & password:"+password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email,password );
        app.getHelperUser().submitLogin();


        //Assert
//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue();
//        Assert.assertFalse();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign out' present");
    }


    @Test(dataProvider = "loginModels",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {
        logger.info("Test data--->"+user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is Element button 'Sign out' present");

    }
    @Test(groups = {"smoke"})
    public void loginWrongEmail(){
        logger.info("Test data--->email:'margogmail.com'& password:'Mmar123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("margogmail.com", "Mmar123456$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert present with error text 'Wrong email or password'");


    }
    @Test
    public void loginWrongPassword(){
        logger.info("Test data--->email:'margo@gmail.com'& password:'Mmar123'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("margo@gmail.com", "Mmar123");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert present with error text 'Wrong email or password'");

    }


    @Test
    public void loginUnregisteredUser(){
        logger.info("Test data--->email:'margo13@gmail.com'& password:'Mmar123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("margo13@gmail.com", "Mmar123456$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is Alert present with error text 'Wrong email or password'");


    }
}