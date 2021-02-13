

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.chrome.ChromeDriver

waiting {
    timeout = 30
    retryInterval = 0.5
}

environments {

    chrome {
        WebDriverManager.chromedriver().setup()

        driver = {
            def options = new ChromeOptions()
            options.addArguments("--disable-features=VizDisplayCompositor");

            def isHeadless = System.getProperty('headless')?.toBoolean()
            if (isHeadless) {
                options.addArguments('--headless')
            }

            def driverInstance = new ChromeDriver(options)
            driverInstance.manage().window().maximize()
            driverInstance
        }
    }

}
