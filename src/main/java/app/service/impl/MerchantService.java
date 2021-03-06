package app.service.impl;

import app.converter.RomanConverter;
import app.dto.NotesDTO;
import app.enums.MaterialEnum;
import app.exception.types.InvalidQuestionException;
import app.service.IMerchantService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Stream;

@Service
public class MerchantService implements IMerchantService {

    private final int MAX_LENGTH_ROMAN_ASSIGNMENT = 3;
    private final int CURRENCY_FIRST_INDEX = 0;
    private final int ROMAN_ASSIGNMENT_VALUE_INDEX = 2;
    private final int MATERIAL_VALUE_INDEX = 2;
    private final int HOW_MUCH_QUESTION_START_INDEX = 3;
    private final int HOW_MANY_QUESTION_START_INDEX = 4;

    @Override
    public NotesDTO enterNotes(NotesDTO notes) {
        HashMap<String,String> data = new HashMap<>();
        LinkedList<String> questions = new LinkedList<>();
        LinkedList<String> answers = new LinkedList<>();

        notes.getNotes().stream()
                .forEach( note -> {
                    String[] words = note.split(" ");
                    if(!note.contains("?")) {
                        String[] roman = {""};
                        Arrays.stream(words).forEach(word -> {
                            //Roman values
                            if(words.length == MAX_LENGTH_ROMAN_ASSIGNMENT) {
                                data.put(words[CURRENCY_FIRST_INDEX],words[ROMAN_ASSIGNMENT_VALUE_INDEX]);
                            }
                            //Analise current Roman values
                            else if(data.containsKey(word)) {
                                roman[0] += data.get(word);
                            }
                            //Set Material value for analising questions later
                            else if(MaterialEnum.materialExists(word)){
                                Double valueMaterial = Double.valueOf(words[words.length - MATERIAL_VALUE_INDEX]) / RomanConverter.convertoToNumber(roman[0]);
                                data.put(word, String.valueOf(valueMaterial));
                            }
                        });
                    } else {
                        questions.add(note);
                    }
                });

        answerQuestions(questions,data,answers);
        return new NotesDTO(answers);
    }

    @Override
    public void answerQuestions(LinkedList<String> questions, HashMap<String, String> data, LinkedList<String> answers) {
        questions.stream().forEach(question -> {
            String[] words = question.split(" ");
            String[] roman = {""};
            String[] answer = {""};
            if(question.contains("how much")) {
                Stream.iterate(HOW_MUCH_QUESTION_START_INDEX, n -> n + 1)
                        .limit(words.length - HOW_MUCH_QUESTION_START_INDEX)
                        .forEach(i -> {
                            if(data.containsKey(words[i])) {
                                roman[0] += data.get(words[i]);
                                answer[0] += words[i] + " ";
                            }
                            //End of String
                            else if(words[i].equals("?") && !answer[0].equals("I have no idea what you are talking about")){
                                answer[0] += "is " + (RomanConverter.convertoToNumber(roman[0]));
                            }
                            //Check if message is not already set
                            else if(!answer[0].equals("I have no idea what you are talking about")){
                                try {
                                    throw new InvalidQuestionException();
                                } catch (InvalidQuestionException e) {
                                    answer[0] += "I have no idea what you are talking about";
                                }
                            }
                        });
            } else {
                Stream.iterate(HOW_MANY_QUESTION_START_INDEX, n -> n + 1)
                        .limit(words.length - HOW_MANY_QUESTION_START_INDEX)
                        .forEach(i -> {
                            //Analised input data
                            if(!MaterialEnum.materialExists(words[i]) && data.containsKey(words[i])) {
                                roman[0] += data.get(words[i]);
                                answer[0] += words[i] + " ";
                            }
                            //Material word case
                            else if(MaterialEnum.materialExists(words[i]) && !answer[0].equals("I have no idea what you are talking about")){
                                Integer credits = (int) (RomanConverter.convertoToNumber(roman[0]) * Double.valueOf(data.get(words[i])));
                                answer[0] += words[i] + " is " + credits + " Credits";
                            }
                            //Check if error message is not already set
                            else if(!words[i].equals("?") && !answer[0].equals("I have no idea what you are talking about")){
                                try {
                                    throw new InvalidQuestionException();
                                } catch (InvalidQuestionException e) {
                                    answer[0] += "I have no idea what you are talking about";
                                }
                            }
                        });
            }
            answers.add(answer[0]);
            System.out.println(answer[0]);
        });
    }


}
