import java.time.Duration;
import java.util.logging.Level;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class abrirgo {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Configuración para el navegador Chrome
        System.setProperty("webdriver.chrome.driver", "D:/Consultoria/primera_prueba/chromedriver.exe");

        // Configuración para mostrar logs de ChromeDriver
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);

        // Inicializa el navegador con las opciones configuradas
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        System.out.println("Finalizando y capturando logs...");
        // Captura y muestra los logs
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(entry.getLevel() + " " + entry.getMessage());
        }

        // Cierra el navegador al finalizar cada prueba
        driver.quit();
        System.out.println("Navegador cerrado.");
    }

    @Test
    public void testGoogleSearch() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Abre el sitio web de Google
        driver.get("https://www.google.com");

        // Encuentra el campo de búsqueda y espera su visibilidad
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        // Ingresa un término de búsqueda
        searchBox.sendKeys("Selenium WebDriver");

        // Envía el formulario de búsqueda
        searchBox.submit();

        // Verifica que el título de la página de resultados contenga la palabra
        // "Selenium"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertTrue(driver.getTitle().contains("Selenium"));
        System.out.println("Prueba de búsqueda en Google completada.");
    }

    @Test
    public void Nuevapagina() {
        // Abre el sitio web
        driver.get("https://colorlib.com/polygon/metis/form-general.html");

        // Encuentra el elemento select y selecciona una opción
        WebElement select = driver.findElement(By.xpath("//select[@multiple='multiple']"));
        Select dropdoSelect = new Select(select);
        dropdoSelect.selectByIndex(3);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        System.out.println("Seleccion completada");
    }
}