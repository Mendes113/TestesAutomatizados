package com.moodle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
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

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Keys;

/**
 * Unit test for simple App.
 */

/**
 * Rigorous Test :-)
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class QuestionarioTest {
    int id;
    WebDriver driver;
    String caminho = "C:\\Users\\andre\\OneDrive\\Documentos\\GitHub\\marco02-grupo05\\Marco 2\\Questionario1\\src\\test\\java\\com\\moodle\\Questionario.csv";
    String caminho2 = "C:\\Users\\andre\\OneDrive\\Documentos\\GitHub\\marco02-grupo05\\Marco 2\\Questionario1\\src\\test\\java\\com\\moodle\\QuestionarioNomes.csv";
    String caminho3 = "C:\\Users\\andre\\OneDrive\\Documentos\\GitHub\\marco02-grupo05\\Marco 2\\Questionario1\\src\\test\\java\\com\\moodle\\Login.csv";
    List<String> dadosQuestoes = new ArrayList<String>();
    List<String> dadosNomes = new ArrayList<String>();
    List<String> dadosLogin = new ArrayList<String>();
    // reader le o arquivo e separa as strings por ',' e parser quando criado o
    // reader, utilizando o parser ele vai realizar a leitura com ';'
    // readNext le a linha e separa pelo ; e coloca as colunas em 1 indice do vetor
    // no for > passa tudo que esta nas colunas para a lista de dados
    @BeforeEach
    public void setup() throws CsvValidationException, IOException, InterruptedException {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader reader = new CSVReaderBuilder(new FileReader(caminho)).withCSVParser(parser).build();
        String[] colunas = null;

        while ((colunas = reader.readNext()) != null) {
            for (int i = 0; i < colunas.length; i++) {
                dadosQuestoes.add(colunas[i]);
            }
        }

        CSVParser parser2 = new CSVParserBuilder().withSeparator(',').build();
        CSVReader reader2 = new CSVReaderBuilder(new FileReader(caminho2)).withCSVParser(parser2).build();
        String[] colunas2 = null;

        while ((colunas2 = reader2.readNext()) != null) {
            for (int i = 0; i < colunas2.length; i++) {
                dadosNomes.add(colunas2[i]);
            }
        }
        

        
        CSVParser parser3 = new CSVParserBuilder().withSeparator(',').build();
        CSVReader reader3  = new CSVReaderBuilder(new FileReader(caminho3)).withCSVParser(parser3).build();
        String[] colunas3 = null;

        while ((colunas3 = reader3.readNext()) != null) {
            for (int i = 0; i < colunas3.length; i++) {
                dadosLogin.add(colunas3[i]);
            }
        }
            // chrome
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
            driver.manage().window().maximize();
    
            // abrir site moodle
            driver.get(dadosLogin.get(0));
    
       
            driver.get(dadosLogin.get(0));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         
            WebElement username = driver.findElement(By.id("username"));
            username.sendKeys(dadosLogin.get(1));
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(dadosLogin.get(2));
            WebElement loginButton = driver.findElement(By.id("loginbtn"));
            loginButton.click();
            WebElement meuCursos =    driver.findElement(By.linkText("Meus cursos"));
            meuCursos.click();
            WebElement algoritimos = driver.findElement(By.className("coursename"));
            algoritimos.click();
         
           
          
        
            WebElement editionMode = driver.findElement(By.className("custom-control"));
            editionMode.click();
            WebElement addActivity = driver.findElement(By.cssSelector("#coursecontentcollapse1 > button > span.pluscontainer.icon-no-margin.icon-size-3.d-flex.p-2.mr-3"));
            addActivity.click();
            WebElement pesquisa = driver.findElement(By.linkText("Questionário"));
            pesquisa.click();



        
        }
        @AfterEach
       public void contador(){
        
        id++;
       }


    @Test
    public void criarQuestionario(){

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(0));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();
            WebElement checkTitle = driver.findElement(By.className("page-header-headings"));
            
            boolean check = checkTitle.getText().contains(dadosNomes.get(0));
            assertTrue(check);

    }

    
    @Test
    public void criarQuestionarioNomeVazio(){

            // WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            // nomeQuestionario.sendKeys(dados.get(3));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();
            WebElement checkError = driver.findElement(By.id("id_error_name"));
            
            boolean check = checkError.isDisplayed();
            assertTrue(check);

    }


    @Test
    public void AddQuestMultipla() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(1));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

        WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
        addQuest.click();
        WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
        adicionar.click();
        Thread.sleep(500);
        adicionar.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
        newQuest.click();
        WebElement multiplaEscolha = driver.findElement(By.id("item_qtype_multichoice"));
        multiplaEscolha.click();
        WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
       AddOK.click();

       WebElement questName = driver.findElement(By.id("id_name"));
         questName.sendKeys(dadosNomes.get(1));
        WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
        textDesc.sendKeys(dadosQuestoes.get(0));
        WebElement feedback = driver.findElement(By.id("id_generalfeedbackeditable"));
        feedback.sendKeys(dadosQuestoes.get(7));
        WebElement idNumber = driver.findElement(By.id("id_idnumber"));
        idNumber.sendKeys("321312452312312");
        WebElement answer0 = driver.findElement(By.id("id_answer_0editable"));
        answer0.sendKeys(dadosQuestoes.get(3));

        

        WebElement nota = driver.findElement(By.id("id_fraction_0"));
        nota.sendKeys(Keys.ARROW_DOWN);
        nota.sendKeys(Keys.ENTER);
        
        WebElement answer1 = driver.findElement(By.id("id_answer_1editable"));
        answer1.sendKeys(dadosQuestoes.get(4));
        WebElement answer2 = driver.findElement(By.id("id_answer_2editable"));
        answer2.sendKeys(dadosQuestoes.get(5));
        WebElement answer3 = driver.findElement(By.id("id_answer_3editable"));
        answer3.sendKeys(dadosQuestoes.get(6));
        WebElement answer4 = driver.findElement(By.id("id_answer_4editable"));
        answer4.sendKeys(dadosQuestoes.get(8));

        WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
        salvarMudanca.click();
        WebElement checkElement = driver.findElement(By.className("questionname"));
        boolean check = checkElement.getText().contains(dadosQuestoes.get(9));
            assertTrue(check);
    }

    @Test
    public void AddQuestMultiplaVazio() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(4));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

        WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
        addQuest.click();
        WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
        adicionar.click();
        Thread.sleep(500);
        adicionar.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
        newQuest.click();
        WebElement multiplaEscolha = driver.findElement(By.id("item_qtype_multichoice"));
        multiplaEscolha.click();
        WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
       AddOK.click();


        WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
        salvarMudanca.click();
     

        WebElement error = driver.findElement(By.id("id_error_name"));
        assertTrue(error.isDisplayed());
    }

    @Test
    public void AddQuestMultiplaLetrasNoID() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(1));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

        WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
        addQuest.click();
        WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
        adicionar.click();
        Thread.sleep(500);
        adicionar.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
        newQuest.click();
        WebElement multiplaEscolha = driver.findElement(By.id("item_qtype_multichoice"));
        multiplaEscolha.click();
        WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
       AddOK.click();

       WebElement questName = driver.findElement(By.id("id_name"));
         questName.sendKeys(dadosNomes.get(1));
        WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
        textDesc.sendKeys(dadosQuestoes.get(0));
        WebElement feedback = driver.findElement(By.id("id_generalfeedbackeditable"));
        feedback.sendKeys(dadosQuestoes.get(7));
        WebElement idNumber = driver.findElement(By.id("id_idnumber"));
        idNumber.sendKeys("1231233");
        WebElement answer0 = driver.findElement(By.id("id_answer_0editable"));
        answer0.sendKeys(dadosQuestoes.get(3));


        WebElement nota = driver.findElement(By.id("id_fraction_0"));
        nota.sendKeys(Keys.ARROW_DOWN);
        nota.sendKeys(Keys.ENTER);
        
        WebElement answer1 = driver.findElement(By.id("id_answer_1editable"));
        answer1.sendKeys(dadosQuestoes.get(4));
        WebElement answer2 = driver.findElement(By.id("id_answer_2editable"));
        answer2.sendKeys(dadosQuestoes.get(5));
        WebElement answer3 = driver.findElement(By.id("id_answer_3editable"));
        answer3.sendKeys(dadosQuestoes.get(6));
        WebElement answer4 = driver.findElement(By.id("id_answer_4editable"));
        answer4.sendKeys(dadosQuestoes.get(8));

        WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
        salvarMudanca.click();
        WebElement checkElement = driver.findElement(By.className("questionname"));
        boolean check = checkElement.getText().contains(dadosQuestoes.get(9));
            assertTrue(check);
    }
    
    @Test
    public void AddQuestMultiplaSemNota() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(1));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

        WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
        addQuest.click();
        WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
        adicionar.click();
        Thread.sleep(500);
        adicionar.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
        newQuest.click();
        WebElement multiplaEscolha = driver.findElement(By.id("item_qtype_multichoice"));
        multiplaEscolha.click();
        WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
       AddOK.click();

       WebElement questName = driver.findElement(By.id("id_name"));
         questName.sendKeys(dadosNomes.get(1));
        WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
        textDesc.sendKeys(dadosQuestoes.get(0));
        WebElement feedback = driver.findElement(By.id("id_generalfeedbackeditable"));
        feedback.sendKeys(dadosQuestoes.get(7));
        WebElement idNumber = driver.findElement(By.id("id_idnumber"));
        idNumber.sendKeys("4");
        WebElement answer0 = driver.findElement(By.id("id_answer_0editable"));
        answer0.sendKeys(dadosQuestoes.get(3));


        // WebElement nota = driver.findElement(By.id("id_fraction_0"));
        // nota.sendKeys(Keys.ARROW_DOWN);
        // nota.sendKeys(Keys.ENTER);
        
        WebElement answer1 = driver.findElement(By.id("id_answer_1editable"));
        answer1.sendKeys(dadosQuestoes.get(4));
        WebElement answer2 = driver.findElement(By.id("id_answer_2editable"));
        answer2.sendKeys(dadosQuestoes.get(5));
        WebElement answer3 = driver.findElement(By.id("id_answer_3editable"));
        answer3.sendKeys(dadosQuestoes.get(6));
        WebElement answer4 = driver.findElement(By.id("id_answer_4editable"));
        answer4.sendKeys(dadosQuestoes.get(8));

        WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
        salvarMudanca.click();
        WebElement checkElement = driver.findElement(By.id("id_error_fraction_0"));
      
            assertTrue(checkElement.isDisplayed());
    }
    @Test
    public void AddQuestVF() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(2));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();


 

            WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
            addQuest.click();
            WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
            adicionar.click();
            Thread.sleep(500);
            adicionar.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
            newQuest.click();
       
        WebElement verdadeiroFalso = driver.findElement(By.id("item_qtype_truefalse"));
      verdadeiroFalso.click();

      WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
      AddOK.click();


      
      WebElement questName = driver.findElement(By.id("id_name"));
      questName.sendKeys(dadosNomes.get(1));
     WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
     textDesc.sendKeys(dadosQuestoes.get(0));
     WebElement marcacao = driver.findElement(By.id("id_defaultmark"));
     marcacao.sendKeys("7");
     WebElement idNumber = driver.findElement(By.id("id_idnumber"));
     idNumber.sendKeys("45152213");

     
        WebElement feedback = driver.findElement(By.id("id_generalfeedbackeditable"));
        feedback.sendKeys(dadosQuestoes.get(7));
        WebElement feedTrue = driver.findElement(By.id("id_feedbacktrueeditable"));
        feedTrue.sendKeys(dadosQuestoes.get(3));
        WebElement feedFalse = driver.findElement(By.id("id_feedbackfalseeditable"));
        feedFalse.sendKeys(dadosQuestoes.get(4));
        WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
        salvarMudanca.click();
        boolean checkElement = driver.findElement(By.className("mod-indent-outer")).isDisplayed();
        assertTrue(checkElement);
    }

    @Test
    public void AddQuestVFVazio() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(3));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

            WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
            addQuest.click();
            WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
            adicionar.click();
            Thread.sleep(500);
            adicionar.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
            newQuest.click();
       
        WebElement verdadeiroFalso = driver.findElement(By.id("item_qtype_truefalse"));
      verdadeiroFalso.click();

      WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
      AddOK.click();

      WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
      salvarMudanca.click();
    
     WebElement error = driver.findElement(By.id("id_error_name"));
        assertTrue(error.isDisplayed());
       
    }
    @Test
    public void AddQuestAssociacao() throws InterruptedException{

          
        WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
        nomeQuestionario.sendKeys(dadosNomes.get(5));
        WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
        descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
        WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
        salvarQuestionario.click();

        WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
        addQuest.click();
        WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
        adicionar.click();
        Thread.sleep(500);
        adicionar.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
        newQuest.click();
        
    WebElement associacao = driver.findElement(By.id("item_qtype_match"));
  associacao.click();

  WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
  AddOK.click();


  
  WebElement questName = driver.findElement(By.id("id_name"));
  questName.sendKeys(dadosNomes.get(1));
 WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
 textDesc.sendKeys(dadosQuestoes.get(0));
 WebElement marcacao = driver.findElement(By.id("id_defaultmark"));
 marcacao.sendKeys("7");
 WebElement idNumber = driver.findElement(By.id("id_idnumber"));
 idNumber.sendKeys("7213212541");

 


 WebElement quest1 = driver.findElement(By.id("id_subquestions_0editable"));
 quest1.sendKeys(dadosQuestoes.get(10));
 WebElement answ1 = driver.findElement(By.id("id_subanswers_0"));
    answ1.sendKeys(dadosQuestoes.get(3));
    
 quest1.sendKeys(dadosQuestoes.get(10));
 WebElement quest2 = driver.findElement(By.id("id_subquestions_1editable"));
 WebElement answ2 = driver.findElement(By.id("id_subanswers_1"));
 answ2.sendKeys(dadosQuestoes.get(4));
 quest2.sendKeys(dadosQuestoes.get(10));
 WebElement quest3 = driver.findElement(By.id("id_subquestions_2editable"));
 quest3.sendKeys(dadosQuestoes.get(10));
 WebElement answ3 = driver.findElement(By.id("id_subanswers_2"));
 answ3.sendKeys(dadosQuestoes.get(5));



    WebElement feedback = driver.findElement(By.id("id_generalfeedbackeditable"));
    feedback.sendKeys(dadosQuestoes.get(7));
   
    WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
    salvarMudanca.click();
    boolean checkElement = driver.findElement(By.className("mod-indent-outer")).isDisplayed();
    assertTrue(checkElement);
    }

    @Test
    public void AddQuestAssociacaoVazio() throws InterruptedException{

          
        WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
        nomeQuestionario.sendKeys(dadosNomes.get(6));
        WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
        descricaoQuestionario.sendKeys(dadosQuestoes.get(0));
        WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
        salvarQuestionario.click();

        WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
        addQuest.click();
        WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
        adicionar.click();
        Thread.sleep(500);
        adicionar.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
        newQuest.click();
   
    WebElement associacao = driver.findElement(By.id("item_qtype_match"));
  associacao.click();

  WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
  AddOK.click();


  
//   WebElement questName = driver.findElement(By.id("id_name"));
//   questName.sendKeys(dadosNomes.get(1));
//  WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
//  textDesc.sendKeys(dadosQuestoes.get(0));
//  WebElement marcacao = driver.findElement(By.id("id_defaultmark"));
//  marcacao.sendKeys("7");
//  WebElement idNumber = driver.findElement(By.id("id_idnumber"));
//  idNumber.sendKeys("7");


//  WebElement quest1 = driver.findElement(By.id("id_subquestions_0editable"));
//  quest1.sendKeys(dadosQuestoes.get(10));
//  WebElement answ1 = driver.findElement(By.id("id_subanswers_0"));
//     answ1.sendKeys(dadosQuestoes.get(3));
    
//  quest1.sendKeys(dadosQuestoes.get(10));
//  WebElement quest2 = driver.findElement(By.id("id_subquestions_1editable"));
//  WebElement answ2 = driver.findElement(By.id("id_subanswers_1"));
//  answ2.sendKeys(dadosQuestoes.get(4));
//  quest2.sendKeys(dadosQuestoes.get(10));
//  WebElement quest3 = driver.findElement(By.id("id_subquestions_2editable"));
//  quest3.sendKeys(dadosQuestoes.get(10));
//  WebElement answ3 = driver.findElement(By.id("id_subanswers_2"));
//  answ3.sendKeys(dadosQuestoes.get(5));



    WebElement feedback = driver.findElement(By.id("id_generalfeedbackeditable"));
    feedback.sendKeys(dadosQuestoes.get(7));
   
    WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
    salvarMudanca.click();
    WebElement error = driver.findElement(By.id("id_error_name"));
    assertTrue(error.isDisplayed());
    }
    @Test
    public void AddQuestTextoCurto() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(7));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(1));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

      
            WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
            addQuest.click();
            WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
            adicionar.click();
            Thread.sleep(500);
            adicionar.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
            newQuest.click();
        

        WebElement textcurto = driver.findElement(By.id("item_qtype_shortanswer"));
        textcurto.click();

        WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
        AddOK.click();
  
  
        
        WebElement questName = driver.findElement(By.id("id_name"));
        questName.sendKeys(dadosNomes.get(7));
       WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
       textDesc.sendKeys(dadosQuestoes.get(0));
       WebElement marcacao = driver.findElement(By.id("id_defaultmark"));
       marcacao.sendKeys("7");
       WebElement idNumber = driver.findElement(By.id("id_idnumber"));
       idNumber.sendKeys("14312312254");
        
        
     
       WebElement answ1 = driver.findElement(By.id("id_answer_0"));
          answ1.sendKeys(dadosQuestoes.get(1));
          WebElement feedback1 = driver.findElement(By.id("id_feedback_0editable"));
            feedback1.sendKeys(dadosQuestoes.get(11));
    
       WebElement answ2 = driver.findElement(By.id("id_answer_1"));
       answ2.sendKeys(dadosQuestoes.get(10));
       WebElement feedback2 = driver.findElement(By.id("id_feedback_0editable"));
       feedback2.sendKeys(dadosQuestoes.get(11));
        WebElement nota = driver.findElement(By.id("id_fraction_1"));
        nota.sendKeys(Keys.ARROW_DOWN);
        nota.click();
       
          WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
          salvarMudanca.click();

              boolean checkElement = driver.findElement(By.className("mod-indent-outer")).isDisplayed();
         assertTrue(checkElement);
       
    }

    @Test
    public void AddQuestTextoCurtoVazio() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(8));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(1));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

      
            WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
            addQuest.click();
            WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
            adicionar.click();
            Thread.sleep(500);
            adicionar.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
            newQuest.click();
      

        WebElement textcurto = driver.findElement(By.id("item_qtype_shortanswer"));
        textcurto.click();

        WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
        AddOK.click();
  
  
        
    //     WebElement questName = driver.findElement(By.id("id_name"));
    //     questName.sendKeys(dadosNomes.get(7));
    //    WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
    //    textDesc.sendKeys(dadosQuestoes.get(0));
    //    WebElement marcacao = driver.findElement(By.id("id_defaultmark"));
    //    marcacao.sendKeys("7");
    //    WebElement idNumber = driver.findElement(By.id("id_idnumber"));
    //    idNumber.sendKeys("14");
        

    
    //    WebElement answ1 = driver.findElement(By.id("id_answer_0"));
    //       answ1.sendKeys(dadosQuestoes.get(1));
    //       WebElement feedback1 = driver.findElement(By.id("id_feedback_0editable"));
    //         feedback1.sendKeys(dadosQuestoes.get(11));
    
    //    WebElement answ2 = driver.findElement(By.id("id_answer_1"));
    //    answ2.sendKeys(dadosQuestoes.get(10));
    //    WebElement feedback2 = driver.findElement(By.id("id_feedback_0editable"));
    //    feedback2.sendKeys(dadosQuestoes.get(11));
    //     WebElement nota = driver.findElement(By.id("id_fraction_1"));
    //     nota.sendKeys(Keys.ARROW_DOWN);
    //     nota.click();
       
          WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
          salvarMudanca.click();
          WebElement error = driver.findElement(By.id("id_error_name"));
          assertTrue(error.isDisplayed());
       
    }

    @Test
    public void AddQuestNumerico() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(9));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(1));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

      
            WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
            addQuest.click();
            WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
            adicionar.click();
            Thread.sleep(500);
            adicionar.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
            newQuest.click();
      
        WebElement numerico = driver.findElement(By.id("item_qtype_numerical"));
       numerico.click();

       WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
       AddOK.click();
 
 
       
       WebElement questName = driver.findElement(By.id("id_name"));
       questName.sendKeys(dadosNomes.get(9));
      WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
      textDesc.sendKeys(dadosQuestoes.get(10));
      WebElement marcacao = driver.findElement(By.id("id_defaultmark"));
      marcacao.sendKeys("10");
      WebElement idNumber = driver.findElement(By.id("id_idnumber"));
      idNumber.sendKeys("1202221232");
       
        
      
        
      WebElement answ1 = driver.findElement(By.id("id_answer_0"));
         answ1.sendKeys(dadosQuestoes.get(12));
         WebElement feedback1 = driver.findElement(By.id("id_feedback_0editable"));
           feedback1.sendKeys(dadosQuestoes.get(11));
   
      WebElement answ2 = driver.findElement(By.id("id_answer_1"));
      answ2.sendKeys(dadosQuestoes.get(13));
      WebElement feedback2 = driver.findElement(By.id("id_feedback_0editable"));
      feedback2.sendKeys(dadosQuestoes.get(11));
       WebElement nota = driver.findElement(By.id("id_fraction_0"));
       nota.sendKeys(Keys.ARROW_DOWN);
       nota.click();
        
       WebElement nota2 = driver.findElement(By.id("id_fraction_1"));
       nota2.sendKeys(Keys.ARROW_DOWN);
       nota2.sendKeys(Keys.ARROW_DOWN);
       nota2.click();

         WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
         salvarMudanca.click();
         boolean checkElement = driver.findElement(By.className("mod-indent-outer")).isDisplayed();
         assertTrue(checkElement);
       
    }
    
    @Test
    public void AddQuestNumericoVazio() throws InterruptedException{

            WebElement nomeQuestionario = driver.findElement(By.id("id_name"));
            nomeQuestionario.sendKeys(dadosNomes.get(11));
            WebElement descricaoQuestionario = driver.findElement(By.id("id_introeditoreditable"));
            descricaoQuestionario.sendKeys(dadosQuestoes.get(1));
            WebElement salvarQuestionario = driver.findElement(By.id("id_submitbutton"));
            salvarQuestionario.click();

      
            WebElement addQuest = driver.findElement(By.linkText("Adicionar questão"));
            addQuest.click();
            WebElement adicionar = driver.findElement(By.linkText("Adicionar"));
            adicionar.click();
            Thread.sleep(500);
            adicionar.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(500);
            WebElement newQuest = driver.findElement(By.linkText("uma nova questão"));
            newQuest.click();
      
        WebElement numerico = driver.findElement(By.id("item_qtype_numerical"));
       numerico.click();

       WebElement AddOK = driver.findElement(By.xpath("/html/body/div[11]/div[3]/div/div[2]/div/div/form/div[3]/input[1]"));
       AddOK.click();
 
 
       
    //    WebElement questName = driver.findElement(By.id("id_name"));
    //    questName.sendKeys(dadosNomes.get(11));
    //   WebElement textDesc = driver.findElement(By.id("id_questiontexteditable"));
    //   textDesc.sendKeys(dadosQuestoes.get(0));
    //   WebElement marcacao = driver.findElement(By.id("id_defaultmark"));
    //   marcacao.sendKeys("7");
    //   WebElement idNumber = driver.findElement(By.id("id_idnumber"));
    //   idNumber.sendKeys("1202");
       

   
    //   WebElement answ1 = driver.findElement(By.id("id_answer_0"));
    //      answ1.sendKeys(dadosQuestoes.get(12));
    //      WebElement feedback1 = driver.findElement(By.id("id_feedback_0editable"));
    //        feedback1.sendKeys(dadosQuestoes.get(11));
   
    //   WebElement answ2 = driver.findElement(By.id("id_answer_1"));
    //   answ2.sendKeys(dadosQuestoes.get(13));
    //   WebElement feedback2 = driver.findElement(By.id("id_feedback_0editable"));
    //   feedback2.sendKeys(dadosQuestoes.get(11));
    //    WebElement nota = driver.findElement(By.id("id_fraction_0"));
    //    nota.sendKeys(Keys.ARROW_DOWN);
    //    nota.click();
        
    //    WebElement nota2 = driver.findElement(By.id("id_fraction_1"));
    //    nota2.sendKeys(Keys.ARROW_DOWN);
    //    nota2.sendKeys(Keys.ARROW_DOWN);
    //    nota2.click();

         WebElement salvarMudanca = driver.findElement(By.id("id_submitbutton"));
         salvarMudanca.click();
         WebElement error = driver.findElement(By.id("id_error_name"));
         assertTrue(error.isDisplayed());
       
    }


  
}
