package com.moodle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverAction;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.chrome.ChromeDriver;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.Keys;

/**
 * Unit test for simple App.
 */

    /**
     * Rigorous Test :-)
     */

    @DisplayName("Teste da funcionalidade de busca")
    public class Logintest {

        String caminho = "D:\\Documentos\\UNIPAMPA\\RPII\\Marco2\\moodletest\\src\\test\\java\\com\\moodle\\rp01.csv";
        List<String> dados = new ArrayList<String>();

        // reader le o arquivo e separa as strings por ',' e parser quando criado o
        // reader, utilizando o parser ele vai realizar a leitura com ';'
        // readNext le a linha e separa pelo ; e coloca as colunas em 1 indice do vetor
        // no for > passa tudo que esta nas colunas para a lista de dados
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

        @Test
        @DisplayName("Login com usuário e senha válidos")
        public void loginNormal() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(1));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(2));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("page-header-headings"));
            String text = textElement.getText();
            assertEquals(dados.get(7), text);
            driver.quit();
        }
    
        

        @Test
        @DisplayName("Login com usuário válido e senha inválida")
        public void senhaIncorreta() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
             username.sendKeys((dados.get(1)));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(5));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("alert"));
            String text = textElement.getText();
            assertEquals(dados.get(8), text);
            driver.quit();
        }

        


        @Test
        @DisplayName("Login com usuário incorreto e senha correta")
        public void usuarioIncorreto() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys((dados.get(6)));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(5));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("alert"));
            String text = textElement.getText();
            assertEquals(dados.get(8), text);
            driver.quit();
        }

     
        @Test
        @DisplayName("Login com usuário nulo e senha correta")
        public void usuarioNulo() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys((dados.get(3)));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(5));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("alert"));
            String text = textElement.getText();
            assertEquals(dados.get(8), text);
            driver.quit();
            
        }

     


        @Test
        @DisplayName("Login com usuário e senha nulos")
        public void usuarioSenhaNulos() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(3));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(3));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("alert"));
            String text = textElement.getText();
            assertEquals(dados.get(8), text);
            driver.quit();
            
        }

    

        @Test
        @DisplayName("Login com usuário válido e senha nula")
        public void senhaNula() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(1));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(3));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("alert"));
            String text = textElement.getText();
            assertEquals(dados.get(8), text);
            driver.quit();
            
        }

     
        


        @Test
        @DisplayName("Loginc com usuário  repetido na senha")
        public void usuarioNaSenha() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(1));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(1));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("alert"));
            String text = textElement.getText();
            assertEquals(dados.get(8), text);
            driver.quit();
            
        }

    

        @Test
        @DisplayName("Login com senha inserido no usuário ")
        public void senhanoUsuario()

        {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(2));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(2));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("alert"));
            String text = textElement.getText();
            assertEquals(dados.get(8), text);
            driver.quit();
            
        }

       


        @Test
        @DisplayName("Login com usuário com espaço em branco ao fim")
        public void usuarioComEspaçoFim() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(9));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(2));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("page-header-headings"));
            String text = textElement.getText();
            assertEquals(dados.get(7), text);
            driver.quit();
        }


       

        @Test
        @DisplayName("Login com usuário com espaço em branco no início")
        public void usuarioComEspaçoInicio() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(
                    By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(4));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(2));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement textElement = driver.findElement(By.className("page-header-headings"));
            String text = textElement.getText();
            assertEquals(dados.get(7), text);
            driver.quit();
        }



        @Test
        @DisplayName("Login com usuário e senha válidos, clicando Enter ao invés de clicar no botão")
        public void loginNormalComEnter() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(1));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(2));
            password.sendKeys(Keys.ENTER);
            WebElement textElement = driver.findElement(By.className("page-header-headings"));
            String text = textElement.getText();
            assertEquals(dados.get(7), text);
            driver.quit();
        }

        
     


        @Test
        @DisplayName("Digita usuário e senha, mas reinica a página. Os dados devem sumir")
        public void verificaSeReloadMantemDados() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dados.get(1));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dados.get(2));
            password.sendKeys(Keys.F5);
            WebElement textElement = driver.findElement(By.className("login-heading"));
            String text = textElement.getText();
            assertEquals(dados.get(10), text);
            driver.quit();
        }

      

        @Test
        @DisplayName("Veficar se existe o botão de esqueci minha senha")
        public void verificaEsqueceuSenha() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement textElement = driver.findElement(By.className("login-form-forgotpassword"));
            String text = textElement.getText();
            assertEquals(dados.get(11), text);
            driver.quit();
        }

      
        @Test
        @DisplayName("Verifica se o acesso como visitante funciona")
        public void verificaLoginConvidado() {

            WebDriver driver = new ChromeDriver();
            driver.get(dados.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement login = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/div/div/div[4]/div[1]/h3/a"));
            login.click();
            WebElement btn = driver.findElement(By.className("btn-secondary"));
            btn.click();
            WebElement textElement = driver.findElement(By.id("notice"));
            String text = textElement.getText();
            assertEquals(dados.get(12), text);
            driver.quit();
        }
      

    }
