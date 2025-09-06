package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {
    @BeforeClass(alwaysRun = true)
    public void preCondition(){
        if (!app.getHelperUser().isLogged()){
           app.getHelperUser().login(new User().setEmail("margo@gmail.com").setPassword("Mmar123456$")) ;
        }

    }
    @Test(dataProvider = "ContactSuccess",dataProviderClass = DataProviderContact.class)
    public void addContactTestAllFields(Contact contact){

        int i = new Random().nextInt(1000)+1000;
      // Contact contact = Contact.builder()
        //.name("Tony"+i)
             //  .lastName("Molly")
              // .phone("343456783"+i)
              //.email("molly"+i+"@gmail.com")
               //.address("Haifa")
              // .description("All fields")
              // .build();

       app.getHelperContact().openContactForm();
       app.getHelperContact().fillContactForm(contact);
      // app.getHelperContact().pause(15000);
        app.getHelperContact().getScreen("src/test/screenschots/screen-"+i+".png");
       app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }
    @Test
    public void addContactTestReqFields(){
        int i = new Random().nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Tonyreq")
                .lastName("Molly")
                .phone("343456783"+i)
                .email("molly"+i+"@gmail.com")
                .address("Haifa")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));



    }
    @Test
    public void addContactTestWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Molly")
                .phone("343456783123")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("Wrong name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());


    }
    @Test
    public void addContactTestWrongLastName(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .phone("343456783")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("Wrong last name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());


    }
    @Test(groups = {"smoke"})
    public void addContactTestWrongPhone(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("")
                .email("molly@gmail.com")
                .address("Haifa")
                .description("Wrong phone")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));


    }
    @Test
    public void addContactTestWrongEmail(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("343456783123")
                .email("mollygmail.com")
                .address("Haifa")
                .description("Wrong email")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid:"));


    }
    @Test
    public void addContactTestWrongAddress(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .phone("343456783768")
                .email("molly@gmail.com")
                .address("")
                .description("Wrong address")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());


    }


}
