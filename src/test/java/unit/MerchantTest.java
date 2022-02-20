package unit;

import app.configuration.ConfiguradorSpring;
import app.dto.NotesDTO;
import app.service.impl.MerchantService;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfiguradorSpring.class })
public class MerchantTest {

    @Autowired
    MerchantService merchantService;

    @Test
    public void dataIsAnalisedCorrectlyTest() {
        //Arrange
        LinkedList<String> entries = new LinkedList<>();
        entries.add("glob is I");
        entries.add("prok is V");
        entries.add("pish is X");
        entries.add("tegj is L");
        entries.add("glob glob Silver is 34 Credits");
        entries.add("glob prok Gold is 57800 Credits");
        entries.add("pish pish Iron is 3910 Credits");
        entries.add("how much is pish tegj glob glob ?");
        entries.add("how many Credits is glob prok Silver ?");
        entries.add("how many Credits is glob prok Gold ?");
        entries.add("how many Credits is glob prok Iron ?");

        NotesDTO body = new NotesDTO(entries);

        LinkedList<String> expectedAnswers = new LinkedList<>();
        expectedAnswers.add("pish tegj glob glob is 42");
        expectedAnswers.add("glob prok Silver is 68 Credits");
        expectedAnswers.add("glob prok Gold is 57800 Credits");
        expectedAnswers.add("glob prok Iron is 782 Credits");

        NotesDTO expected = new NotesDTO(expectedAnswers);

        //Act
        NotesDTO result = merchantService.enterNotes(body);

        //Assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void differentCurrencyAnalisedCorrectlyTest() {
        //Arrange
        LinkedList<String> entries = new LinkedList<>();
        entries.add("pum is I");
        entries.add("pam is V");
        entries.add("tic is X");
        entries.add("toc is L");
        entries.add("pum pum Silver is 34 Credits");
        entries.add("pum pam Gold is 57800 Credits");
        entries.add("tic tic Iron is 3910 Credits");
        entries.add("how much is tic toc pum pum ?");
        entries.add("how many Credits is pum pam Silver ?");
        entries.add("how many Credits is pum pam Gold ?");
        entries.add("how many Credits is pum pam Iron ?");

        NotesDTO body = new NotesDTO(entries);

        LinkedList<String> expectedAnswers = new LinkedList<>();
        expectedAnswers.add("tic toc pum pum is 42");
        expectedAnswers.add("pum pam Silver is 68 Credits");
        expectedAnswers.add("pum pam Gold is 57800 Credits");
        expectedAnswers.add("pum pam Iron is 782 Credits");

        NotesDTO expected = new NotesDTO(expectedAnswers);

        //Act
        NotesDTO result = merchantService.enterNotes(body);

        //Assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void invalidHowMuchQuestionTest() {
        //Arrange
        LinkedList<String> entries = new LinkedList<>();
        entries.add("glob is I");
        entries.add("prok is V");
        entries.add("pish is X");
        entries.add("tegj is L");
        entries.add("glob glob Silver is 34 Credits");
        entries.add("glob prok Gold is 57800 Credits");
        entries.add("pish pish Iron is 3910 Credits");
        entries.add("how much is pish tegj glob glob ?");
        entries.add("how many Credits is glob prok Silver ?");
        entries.add("how many Credits is glob prok Gold ?");
        entries.add("how many Credits is glob prok Iron ?");
        entries.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

        NotesDTO body = new NotesDTO(entries);

        LinkedList<String> expectedAnswers = new LinkedList<>();
        expectedAnswers.add("pish tegj glob glob is 42");
        expectedAnswers.add("glob prok Silver is 68 Credits");
        expectedAnswers.add("glob prok Gold is 57800 Credits");
        expectedAnswers.add("glob prok Iron is 782 Credits");
        expectedAnswers.add("I have no idea what you are talking about");

        NotesDTO expected = new NotesDTO(expectedAnswers);

        //Act
        NotesDTO result = merchantService.enterNotes(body);

        //Assert
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void invalidHowManyQuestionTest() {
        //Arrange
        LinkedList<String> entries = new LinkedList<>();
        entries.add("glob is I");
        entries.add("prok is V");
        entries.add("pish is X");
        entries.add("tegj is L");
        entries.add("glob glob Silver is 34 Credits");
        entries.add("glob prok Gold is 57800 Credits");
        entries.add("pish pish Iron is 3910 Credits");
        entries.add("how much is pish tegj glob glob ?");
        entries.add("how many Credits is glob prok Silver ?");
        entries.add("how many Credits is glob prok Gold ?");
        entries.add("how many Credits is glob prok Iron ?");
        entries.add("how many Credits is pum pam Iron ?");

        NotesDTO body = new NotesDTO(entries);

        LinkedList<String> expectedAnswers = new LinkedList<>();
        expectedAnswers.add("pish tegj glob glob is 42");
        expectedAnswers.add("glob prok Silver is 68 Credits");
        expectedAnswers.add("glob prok Gold is 57800 Credits");
        expectedAnswers.add("glob prok Iron is 782 Credits");
        expectedAnswers.add("I have no idea what you are talking about");

        NotesDTO expected = new NotesDTO(expectedAnswers);

        //Act
        NotesDTO result = merchantService.enterNotes(body);

        //Assert
        Assertions.assertEquals(expected,result);
    }
}
