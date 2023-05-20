package com.moodle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@DisplayName("Teste da funcionalidade de busca")
public class PesquisaTest {
    String caminho = "C:\\Users\\arthu\\Desktop\\TestesCriarEnviarTarefas\\src\\test\\java\\com\\moodle\\CriarEnviar.csv";
    List<String> dados = new ArrayList<String>();

    @BeforeEach
    public void setup() throws CsvValidationException, IOException {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(caminho)).withCSVParser(parser).build();
        String[] colunas = null;

        while ((colunas = reader.readNext()) != null) {
            for (int i = 0; i < colunas.length; i++) {
                dados.add(colunas[i]);
            }
        }

    }

    // Teste da pesquisa (EditarQuestao)
    @Test
    @Order(1)
    public void EditarQuestaoPadrao() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get(dados.get(0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys(dados.get(1));
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(dados.get(2));
        driver.findElement(By.id("loginbtn")).click();
        Thread.sleep(2000);
        WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
        meuCursos.click();
        WebElement algoritimos = driver.findElement(By.className("coursename"));
        algoritimos.click();
        driver.findElement(By.id("user-menu-toggle")).click();
        driver.findElement(By.linkText("Mudar papel para...")).click();
        driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[3]/div[1]/section[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[2]/ul[1]/li[6]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/a[1]")).click();
        driver.findElement(By.linkText("Responda as questões")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[3]/div[2]/fieldset[1]/div[1]/label[3]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[4]/div[2]/fieldset[1]/div[1]/div[1]/span[1]/input[1]")).click();
        Thread.sleep(2000);
        String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
        assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
        Thread.sleep(2000);
        driver.quit();

    }

    // Teste da pesquisa (PesquisaVariada)
    @Test
    @Order(2)
    public void PesquisaVariada() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get(dados.get(0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys(dados.get(1));
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(dados.get(2));
        driver.findElement(By.id("loginbtn")).click();
        Thread.sleep(2000);
        WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
        meuCursos.click();
        WebElement algoritimos = driver.findElement(By.className("coursename"));
        algoritimos.click();
        driver.findElement(By.id("user-menu-toggle")).click();
        driver.findElement(By.linkText("Mudar papel para...")).click();
        driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("PesquisaVariada")).click();
        driver.findElement(By.linkText("Responda as questões")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[3]/div[2]/fieldset[1]/div[1]/label[3]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[4]/div[2]/fieldset[1]/div[1]/label[5]")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#id_gonextpage")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[4]/div[2]/input[1]")).click();
        driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[4]/div[2]/input[1]")).sendKeys(dados.get(3));
        driver.findElement(By.id("id_savevalues")).click();
        String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
        assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
        Thread.sleep(2000);
        driver.quit();

    }


        // Teste da pesquisa (tarefaTextoDisponibiidadeMes)
    @Test
    @Order(3)
    public void tarefaTextoDisponibiidadeMes() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get(dados.get(0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys(dados.get(1));
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(dados.get(2));
        driver.findElement(By.id("loginbtn")).click();
        Thread.sleep(2000);
        WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
        meuCursos.click();
        WebElement algoritimos = driver.findElement(By.className("coursename"));
        algoritimos.click();
        driver.findElement(By.id("user-menu-toggle")).click();
        driver.findElement(By.linkText("Mudar papel para...")).click();
        driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
        Thread.sleep(6000);
        driver.findElement(By.xpath("//li[20]/div/div/div/div/div/div[2]/div[2]/a")).click();
        Thread.sleep(2000);
        String validartermino = driver.findElement(By.cssSelector("body.format-topics.limitedwidth.userswitchedrole.path-mod.path-mod-feedback.chrome.dir-ltr.lang-pt_br.yui-skin-sam.yui3-skin-sam.gmlunardi-pro-br--moodlerp2.pagelayout-incourse.course-7.context-2882.cmid-2701.cm-type-feedback.category-1.uses-drawers.jsenabled:nth-child(2) div.d-print-block:nth-child(3) div.drawers.drag-container:nth-child(9) div.main-inner div.pb-3.d-print-block div:nth-child(1) section:nth-child(1) div:nth-child(4) div.box.py-3.generalbox.boxaligncenter:nth-child(2) > div.alert.alert-danger.alert-block.fade.in.alert-dismissible")).getText();
        assertTrue(validartermino.contains("A pesquisa não está aberta"));
        Thread.sleep(2000);
        driver.quit();

}
        // Teste da pesquisa (tarefaTextoDisponibiidadeAno)
        @Test
        @Order(4)
        public void tarefaTextoDisponibiidadeAno() throws InterruptedException {
    
            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
            driver.findElement(By.id("username")).click();
            driver.findElement(By.id("username")).sendKeys(dados.get(1));
            driver.findElement(By.id("password")).click();
            driver.findElement(By.id("password")).sendKeys(dados.get(2));
            driver.findElement(By.id("loginbtn")).click();
            Thread.sleep(2000);
            WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
            meuCursos.click();
            WebElement algoritimos = driver.findElement(By.className("coursename"));
            algoritimos.click();
            driver.findElement(By.id("user-menu-toggle")).click();
            driver.findElement(By.linkText("Mudar papel para...")).click();
            driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
            Thread.sleep(6000);
            driver.findElement(By.xpath("//li[21]/div/div/div/div/div/div[2]/div[2]/a")).click();
            Thread.sleep(2000);
            String validartermino = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/div[2]/div[1]")).getText();
            assertTrue(validartermino.contains("A pesquisa não está aberta"));
            Thread.sleep(2000);
            driver.quit();
    
    }
            // Teste da pesquisa (EditarQuestaoMultiplaRateada)
            @Test
            @Order(5)
            public void EditarQuestaoMultiplaRateada() throws InterruptedException {
        
                WebDriver driver = new ChromeDriver();
                driver.get(dados.get(0));
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
                driver.findElement(By.id("username")).click();
                driver.findElement(By.id("username")).sendKeys(dados.get(1));
                driver.findElement(By.id("password")).click();
                driver.findElement(By.id("password")).sendKeys(dados.get(2));
                driver.findElement(By.id("loginbtn")).click();
                Thread.sleep(2000);
                WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
                meuCursos.click();
                WebElement algoritimos = driver.findElement(By.className("coursename"));
                algoritimos.click();
                driver.findElement(By.id("user-menu-toggle")).click();
                driver.findElement(By.linkText("Mudar papel para...")).click();
                driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
                Thread.sleep(2000);
                driver.findElement(By.xpath("//li[22]/div/div/div/div/div/div[2]/div[2]/a")).click();
                driver.findElement(By.linkText("Responda as questões")).click();
                Thread.sleep(3000);
                driver.findElement(By.xpath("//label[5]/input")).click();
                driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
                Thread.sleep(2000);
                String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
                assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
                Thread.sleep(2000);
                driver.quit();
        
        }
        // Teste da pesquisa (EditarQuestao)
        @Test
        @Order(6)
        public void EditarQuestaoNull() throws InterruptedException {
    
            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    
            driver.findElement(By.id("username")).click();
            driver.findElement(By.id("username")).sendKeys(dados.get(1));
            driver.findElement(By.id("password")).click();
            driver.findElement(By.id("password")).sendKeys(dados.get(2));
            driver.findElement(By.id("loginbtn")).click();
            Thread.sleep(2000);
            WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
            meuCursos.click();
            WebElement algoritimos = driver.findElement(By.className("coursename"));
            algoritimos.click();
            driver.findElement(By.id("user-menu-toggle")).click();
            driver.findElement(By.linkText("Mudar papel para...")).click();
            driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//li[24]/div/div/div/div/div/div[2]/div[2]/a")).click();
            driver.findElement(By.linkText("Responda as questões")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
            Thread.sleep(2000);
            String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[3]/div[2]/div[1]")).getText();
            assertEquals("Necessários", texto);
            Thread.sleep(2000);
            driver.quit();
    }
    // Teste da pesquisa (EditarQuestao)
    @Test
    @Order(7)
    public void EditarQuestao() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get(dados.get(0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).sendKeys(dados.get(1));
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys(dados.get(2));
        driver.findElement(By.id("loginbtn")).click();
        Thread.sleep(2000);
        WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
        meuCursos.click();
        WebElement algoritimos = driver.findElement(By.className("coursename"));
        algoritimos.click();
        driver.findElement(By.id("user-menu-toggle")).click();
        driver.findElement(By.linkText("Mudar papel para...")).click();
        driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[24]/div/div/div/div/div/div[2]/div[2]/a")).click();
        driver.findElement(By.linkText("Responda as questões")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//label[5]/input")).click();
        driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
        Thread.sleep(2000);
        String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
        assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
        Thread.sleep(2000);
        driver.quit();
}
// Teste da pesquisa (EditarQuestaoMultiplaRateadaExigidaNull)
@Test
@Order(8)
public void EditarQuestaoMultiplaRateadaExigidaNull() throws InterruptedException {

    WebDriver driver = new ChromeDriver();
    driver.get(dados.get(0));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(dados.get(1));
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(dados.get(2));
    driver.findElement(By.id("loginbtn")).click();
    Thread.sleep(2000);
    WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
    meuCursos.click();
    WebElement algoritimos = driver.findElement(By.className("coursename"));
    algoritimos.click();
    driver.findElement(By.id("user-menu-toggle")).click();
    driver.findElement(By.linkText("Mudar papel para...")).click();
    driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//li[26]/div/div/div/div/div/div[2]/div[2]/a")).click();
    driver.findElement(By.linkText("Responda as questões")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
    Thread.sleep(2000);
    String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[3]/div[2]/div[1]")).getText();
    assertEquals("Necessários", texto);
    Thread.sleep(2000);
    driver.quit();
    }
    // Teste da pesquisa (EditarQuestaoMultiplaRateadaExigida)
@Test
@Order(9)
public void EditarQuestaoMultiplaRateadaExigida() throws InterruptedException {

    WebDriver driver = new ChromeDriver();
    driver.get(dados.get(0));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(dados.get(1));
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(dados.get(2));
    driver.findElement(By.id("loginbtn")).click();
    Thread.sleep(2000);
    WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
    meuCursos.click();
    WebElement algoritimos = driver.findElement(By.className("coursename"));
    algoritimos.click();
    driver.findElement(By.id("user-menu-toggle")).click();
    driver.findElement(By.linkText("Mudar papel para...")).click();
    driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//li[26]/div/div/div/div/div/div[2]/div[2]/a")).click();
    driver.findElement(By.linkText("Responda as questões")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//label[5]/input")).click();
    driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
    Thread.sleep(2000);
    String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
    assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
    Thread.sleep(2000);
    driver.quit();
}
// Teste da pesquisa (EditarQuestaoRateadaVazio)
@Test
@Order(10)
public void EditarQuestaoRateadaVazio() throws InterruptedException {

    WebDriver driver = new ChromeDriver();
    driver.get(dados.get(0));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(dados.get(1));
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(dados.get(2));
    driver.findElement(By.id("loginbtn")).click();
    Thread.sleep(2000);
    WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
    meuCursos.click();
    WebElement algoritimos = driver.findElement(By.className("coursename"));
    algoritimos.click();
    driver.findElement(By.id("user-menu-toggle")).click();
    driver.findElement(By.linkText("Mudar papel para...")).click();
    driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//li[29]/div/div/div/div/div/div[2]/div[2]/a")).click();
    driver.findElement(By.linkText("Responda as questões")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//label[2]/input")).click();
    driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
    Thread.sleep(2000);
    String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
    assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
    Thread.sleep(2000);
    driver.quit();
    }
    @Test
    @Order(11)
    public void EditarQuestao2() throws InterruptedException {

    WebDriver driver = new ChromeDriver();
    driver.get(dados.get(0));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(dados.get(1));
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(dados.get(2));
    driver.findElement(By.id("loginbtn")).click();
    Thread.sleep(2000);
    WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
    meuCursos.click();
    WebElement algoritimos = driver.findElement(By.className("coursename"));
    algoritimos.click();
    driver.findElement(By.id("user-menu-toggle")).click();
    driver.findElement(By.linkText("Mudar papel para...")).click();
    driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//li[30]/div/div/div/div/div/div[2]/div[2]/a")).click();
    driver.findElement(By.linkText("Responda as questões")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//label[3]/input")).click();
    driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
    Thread.sleep(2000);
    String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
    assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
    Thread.sleep(2000);
    driver.quit();
    }
    @Test
    @Order(12)
    public void EditarQuestaoTextoLongoNull() throws InterruptedException {

    WebDriver driver = new ChromeDriver();
    driver.get(dados.get(0));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(dados.get(1));
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(dados.get(2));
    driver.findElement(By.id("loginbtn")).click();
    Thread.sleep(2000);
    WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
    meuCursos.click();
    WebElement algoritimos = driver.findElement(By.className("coursename"));
    algoritimos.click();
    driver.findElement(By.id("user-menu-toggle")).click();
    driver.findElement(By.linkText("Mudar papel para...")).click();
    driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//li[34]/div/div/div/div/div/div[2]/div[2]/a")).click();
    driver.findElement(By.linkText("Responda as questões")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[5]/div[2]/fieldset[1]/div[1]/div[1]/span[1]/input[1]")).click();
    Thread.sleep(2000);
    String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[3]/div[2]/div[1]")).getText();
    assertEquals("- Necessários", texto);
    Thread.sleep(2000);
    driver.quit();
    }
    @Test
    @Order(12)
    public void EditarQuestaoTextoLongo() throws InterruptedException {

    WebDriver driver = new ChromeDriver();
    driver.get(dados.get(0));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(dados.get(1));
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(dados.get(2));
    driver.findElement(By.id("loginbtn")).click();
    Thread.sleep(2000);
    WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
    meuCursos.click();
    WebElement algoritimos = driver.findElement(By.className("coursename"));
    algoritimos.click();
    driver.findElement(By.id("user-menu-toggle")).click();
    driver.findElement(By.linkText("Mudar papel para...")).click();
    driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//li[34]/div/div/div/div/div/div[2]/div[2]/a")).click();
    driver.findElement(By.linkText("Responda as questões")).click();
    Thread.sleep(3000);
    driver.findElement(By.id("id_textarea_171")).click();
    driver.findElement(By.id("id_textarea_171")).sendKeys(dados.get(4));
    Thread.sleep(3000);
    driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[5]/div[2]/fieldset[1]/div[1]/div[1]/span[1]/input[1]")).click();
    Thread.sleep(2000);
    String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[3]/div[2]/div[1]")).getText();
    assertEquals("- Necessários", texto);
    Thread.sleep(2000);
    driver.quit();
    }
    @Test
    @Order(13)
    public void EditarQuestaoTextoLongoVazioNull() throws InterruptedException {

    WebDriver driver = new ChromeDriver();
    driver.get(dados.get(0));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys(dados.get(1));
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys(dados.get(2));
    driver.findElement(By.id("loginbtn")).click();
    Thread.sleep(2000);
    WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
    meuCursos.click();
    WebElement algoritimos = driver.findElement(By.className("coursename"));
    algoritimos.click();
    driver.findElement(By.id("user-menu-toggle")).click();
    driver.findElement(By.linkText("Mudar papel para...")).click();
    driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//li[36]/div/div/div/div/div/div[2]/div[2]/a")).click();
    driver.findElement(By.linkText("Responda as questões")).click();
    Thread.sleep(3000);
    driver.findElement(By.id("id_savevalues")).click();
    Thread.sleep(2000);
    String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
    assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
    Thread.sleep(2000);
    driver.quit();
}
@Test
@Order(14)
public void QuestaoNumericaNull() throws InterruptedException {

WebDriver driver = new ChromeDriver();
driver.get(dados.get(0));
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

driver.findElement(By.id("username")).click();
driver.findElement(By.id("username")).sendKeys(dados.get(1));
driver.findElement(By.id("password")).click();
driver.findElement(By.id("password")).sendKeys(dados.get(2));
driver.findElement(By.id("loginbtn")).click();
Thread.sleep(2000);
WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
meuCursos.click();
WebElement algoritimos = driver.findElement(By.className("coursename"));
algoritimos.click();
driver.findElement(By.id("user-menu-toggle")).click();
driver.findElement(By.linkText("Mudar papel para...")).click();
driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//li[37]/div/div/div/div/div/div[2]/div[2]/a")).click();
driver.findElement(By.linkText("Responda as questões")).click();
Thread.sleep(3000);
driver.findElement(By.id("id_savevalues")).click();
Thread.sleep(2000);
String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
Thread.sleep(2000);
driver.quit();
}       
@Test
@Order(15)
public void QuestaoNumericaNumErro() throws InterruptedException {

WebDriver driver = new ChromeDriver();
driver.get(dados.get(0));
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

driver.findElement(By.id("username")).click();
driver.findElement(By.id("username")).sendKeys(dados.get(1));
driver.findElement(By.id("password")).click();
driver.findElement(By.id("password")).sendKeys(dados.get(2));
driver.findElement(By.id("loginbtn")).click();
Thread.sleep(2000);
WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
meuCursos.click();
WebElement algoritimos = driver.findElement(By.className("coursename"));
algoritimos.click();
driver.findElement(By.id("user-menu-toggle")).click();
driver.findElement(By.linkText("Mudar papel para...")).click();
driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//li[38]/div/div/div/div/div/div[2]/div[2]/a")).click();
driver.findElement(By.linkText("Responda as questões")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("//input[@id=\'id_numeric_644\']")).click();
driver.findElement(By.xpath("//input[@id=\'id_numeric_644\']")).sendKeys(dados.get(6));
driver.findElement(By.id("id_savevalues")).click();
Thread.sleep(2000);
String texto = driver.findElement(By.cssSelector("#id_error_numeric_644")).getText();
assertEquals("Valor fora do intervalo", texto);
Thread.sleep(2000);
driver.quit();
}       
@Test
@Order(16)
public void QuestaoNumericaNumCerto() throws InterruptedException {

WebDriver driver = new ChromeDriver();
driver.get(dados.get(0));
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

driver.findElement(By.id("username")).click();
driver.findElement(By.id("username")).sendKeys(dados.get(1));
driver.findElement(By.id("password")).click();
driver.findElement(By.id("password")).sendKeys(dados.get(2));
driver.findElement(By.id("loginbtn")).click();
Thread.sleep(2000);
WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
meuCursos.click();
WebElement algoritimos = driver.findElement(By.className("coursename"));
algoritimos.click();
driver.findElement(By.id("user-menu-toggle")).click();
driver.findElement(By.linkText("Mudar papel para...")).click();
driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//li[38]/div/div/div/div/div/div[2]/div[2]/a")).click();
driver.findElement(By.linkText("Responda as questões")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("//input[@id=\'id_numeric_644\']")).click();
driver.findElement(By.xpath("//input[@id=\'id_numeric_644\']")).sendKeys(dados.get(4));
driver.findElement(By.id("id_savevalues")).click();
Thread.sleep(2000);
String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
Thread.sleep(2000);
driver.quit();
}
@Test
@Order(17)
public void EditarQuestaoTextoCurtoNull() throws InterruptedException {

WebDriver driver = new ChromeDriver();
driver.get(dados.get(0));
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

driver.findElement(By.id("username")).click();
driver.findElement(By.id("username")).sendKeys(dados.get(1));
driver.findElement(By.id("password")).click();
driver.findElement(By.id("password")).sendKeys(dados.get(2));
driver.findElement(By.id("loginbtn")).click();
Thread.sleep(2000);
WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
meuCursos.click();
WebElement algoritimos = driver.findElement(By.className("coursename"));
algoritimos.click();
driver.findElement(By.id("user-menu-toggle")).click();
driver.findElement(By.linkText("Mudar papel para...")).click();
driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//li[45]/div/div/div/div/div/div[2]/div[2]/a")).click();
driver.findElement(By.linkText("Responda as questões")).click();
Thread.sleep(3000);
driver.findElement(By.id("id_savevalues")).click();
Thread.sleep(2000);
String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[3]/div[2]/div[1]")).getText();
assertEquals("- Necessários", texto);
Thread.sleep(2000);
driver.quit();
}

@Test
@Order(18)
public void EditarQuestaoTextoCurto() throws InterruptedException {

WebDriver driver = new ChromeDriver();
driver.get(dados.get(0));
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

driver.findElement(By.id("username")).click();
driver.findElement(By.id("username")).sendKeys(dados.get(1));
driver.findElement(By.id("password")).click();
driver.findElement(By.id("password")).sendKeys(dados.get(2));
driver.findElement(By.id("loginbtn")).click();
Thread.sleep(2000);
WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
meuCursos.click();
WebElement algoritimos = driver.findElement(By.className("coursename"));
algoritimos.click();
driver.findElement(By.id("user-menu-toggle")).click();
driver.findElement(By.linkText("Mudar papel para...")).click();
driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//li[45]/div/div/div/div/div/div[2]/div[2]/a")).click();
driver.findElement(By.linkText("Responda as questões")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("//div[2]/input")).click();
driver.findElement(By.xpath("//div[2]/input")).sendKeys(dados.get(6));
driver.findElement(By.id("id_savevalues")).click();
Thread.sleep(2000);
String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
Thread.sleep(2000);
driver.quit();
}

@Test
@Order(19)
public void EditarQuestaoTextoCurtoMaxChart() throws InterruptedException {

WebDriver driver = new ChromeDriver();
driver.get(dados.get(0));
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

driver.findElement(By.id("username")).click();
driver.findElement(By.id("username")).sendKeys(dados.get(1));
driver.findElement(By.id("password")).click();
driver.findElement(By.id("password")).sendKeys(dados.get(2));
driver.findElement(By.id("loginbtn")).click();
Thread.sleep(2000);
WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
meuCursos.click();
WebElement algoritimos = driver.findElement(By.className("coursename"));
algoritimos.click();
driver.findElement(By.id("user-menu-toggle")).click();
driver.findElement(By.linkText("Mudar papel para...")).click();
driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//li[49]/div/div/div/div/div/div[2]/div[2]/a")).click();
driver.findElement(By.linkText("Responda as questões")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("//div[2]/input")).click();
driver.findElement(By.xpath("//div[2]/input")).sendKeys(dados.get(6));
driver.findElement(By.id("id_savevalues")).click();
Thread.sleep(2000);
String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/div[2]/form[1]/div[3]/div[2]/div[1]")).getText();
assertEquals("- Máximo de -100 caracteres", texto);
Thread.sleep(2000);
driver.quit();
}
@Test
@Order(20)
public void EditarQuestoesMultiplaHideNoSelect() throws InterruptedException {

WebDriver driver = new ChromeDriver();
driver.get(dados.get(0));
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

driver.findElement(By.id("username")).click();
driver.findElement(By.id("username")).sendKeys(dados.get(1));
driver.findElement(By.id("password")).click();
driver.findElement(By.id("password")).sendKeys(dados.get(2));
driver.findElement(By.id("loginbtn")).click();
Thread.sleep(2000);
WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
meuCursos.click();
WebElement algoritimos = driver.findElement(By.className("coursename"));
algoritimos.click();
driver.findElement(By.id("user-menu-toggle")).click();
driver.findElement(By.linkText("Mudar papel para...")).click();
driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//li[50]/div/div/div/div/div/div[2]/div[2]/a")).click();
driver.findElement(By.linkText("Responda as questões")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("//label[3]/input")).click();
driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
Thread.sleep(2000);
String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
Thread.sleep(2000);
driver.quit();

}
@Test
@Order(11)
public void EditarQuestaoMultiplaExigida() throws InterruptedException {

WebDriver driver = new ChromeDriver();
driver.get(dados.get(0));
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

driver.findElement(By.id("username")).click();
driver.findElement(By.id("username")).sendKeys(dados.get(1));
driver.findElement(By.id("password")).click();
driver.findElement(By.id("password")).sendKeys(dados.get(2));
driver.findElement(By.id("loginbtn")).click();
Thread.sleep(2000);
WebElement meuCursos = driver.findElement(By.linkText("Meus cursos"));
meuCursos.click();
WebElement algoritimos = driver.findElement(By.className("coursename"));
algoritimos.click();
driver.findElement(By.id("user-menu-toggle")).click();
driver.findElement(By.linkText("Mudar papel para...")).click();
driver.findElement(By.xpath("//section[@id=\'region-main\']/div/div[3]/div/form/button")).click();
Thread.sleep(2000);
driver.findElement(By.xpath("//li[51]/div/div/div/div/div/div[2]/div[2]/a")).click();
driver.findElement(By.linkText("Responda as questões")).click();
Thread.sleep(3000);
driver.findElement(By.xpath("//label[3]/input")).click();
driver.findElement(By.xpath("//input[@id=\'id_savevalues\']")).click();
Thread.sleep(2000);
String texto = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[4]/div[1]/div[2]/div[1]/section[1]/span[1]/div[1]")).getText();
assertTrue(texto.contains("Suas respostas foram gravadas. Obrigado."));
Thread.sleep(2000);
driver.quit();
}
}