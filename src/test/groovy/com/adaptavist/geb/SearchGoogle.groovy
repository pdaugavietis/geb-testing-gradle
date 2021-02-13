package com.adaptavist.geb
/**
 * Works on Groovy 2.5.6.
 * Execute it as: groovy CloudMaintenance.groovy adaptavist-library-cloud@adaptavist.com <password>
 */


import geb.Browser
import geb.junit4.GebTest
import org.apache.commons.io.FileUtils
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.shooting.ShootingStrategies

import javax.imageio.ImageIO

class SearchGoogle extends GebTest {

    final Browser browser = new Browser()
    final static long timeBetweenActions = 15
    final BASE_URL = 'https://www.google.com'
    final SCREENSHOT_DIR = 'build/screenshots'

    @Before
    void setup() {
        navigate(BASE_URL)
    }

    private void saveScreenShot(String fileName, String screenshotLocation) {
        File screenshotFileLocation = new File(screenshotLocation + '/' + fileName + ".png")
        FileUtils.forceMkdirParent(screenshotFileLocation)
        def screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver)
        ImageIO.write(screenshot.image, "png", screenshotFileLocation)
    }

    private void fillTextField(String fieldName, String value) {
        sleep(timeBetweenActions)
        browser.waitFor { browser.$(By.name(fieldName)).displayed }
        browser.$(By.name(fieldName)) << value
        browser.$(By.name(fieldName)) << Keys.ENTER
    }

    private void navigate(String url) {
        sleep(timeBetweenActions)
        browser.go(url)
    }

    private void searchForString(String searchString) {
        fillTextField("q", searchString)
        sleep(3000)
        saveScreenShot(searchString, SCREENSHOT_DIR)
    }

    @Test
    void testCarrotSearch() {
        println 'Searching for carrots...'
        searchForString("carrots")
    }

    @Test
    void testPotatoSearch() {
        println 'Searching for potatoes...'
        searchForString("potatoes")
    }

}
