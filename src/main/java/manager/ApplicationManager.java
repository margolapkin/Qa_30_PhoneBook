package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    WebDriver wd;
    HelperUser helperUser;
    HelperContact helperContact;

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
        if(browser.equals(Browser.CHROME.browserName())){
            wd = new ChromeDriver();
            logger.info("All tests runs in Chrome Browser");
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            wd = new FirefoxDriver();
            logger.info("All tests runs in firefox Browser");
        } else if (browser.equals(Browser.EDGE.browserName())) {
            wd = new EdgeDriver();
            logger.info("All tasts runs in Edge Browser");

        }


        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverListener webDriverListener = new ListenerWD();
        wd = new EventFiringDecorator(webDriverListener).decorate(wd);
        wd.navigate().to("https://telranedu.web.app/");
        logger.info("The link--->"+wd.getCurrentUrl());
        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);



    }
    public void stop(){

        wd.quit();
    }


    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }
}
