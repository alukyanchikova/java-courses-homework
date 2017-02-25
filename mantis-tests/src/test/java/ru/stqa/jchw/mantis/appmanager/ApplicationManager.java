package ru.stqa.jchw.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.BrowserType.*;

public class ApplicationManager {
    private WebDriver wd;

    private String browser;
    private final Properties properties;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
//        Uncomment if running on Windows
//        System.setProperty("webdriver.gecko.driver", "C:\\Users\\ALukyanchikova\\Desktop\\geckodriver.exe");
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ALukyanchikova\\Desktop\\chromedriver.exe");
//        System.setProperty("webdriver.ie.driver", "C:\\Users\\ALukyanchikova\\Desktop\\IEDriverServer.exe");

        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

        if (browser.equals(FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(IE)) {
            wd = new InternetExplorerDriver();
        }
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
    }

    public void stop() {
        wd.quit();
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
