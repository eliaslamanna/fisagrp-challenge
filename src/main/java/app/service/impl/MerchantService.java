package app.service.impl;

import app.converter.RomanConverter;
import app.dto.NotesDTO;
import app.service.IMerchantService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Stream;

@Service
public class MerchantService implements IMerchantService {

    private HashMap<String,String> data = new HashMap<>();
    private LinkedList<String> questions = new LinkedList<>();
    private LinkedList<String> answers = new LinkedList<>();

    @Override
    public NotesDTO enterNotes(NotesDTO notes) {
        notes.getNotes().stream()
                .forEach( note -> {
                    String[] words = note.split(" ");
                    if(!note.contains("?")) {
                        String[] roman = {""};
                        Arrays.stream(words).forEach(word -> {
                            if(words.length == 3) {
                                data.put(words[0],words[2]);
                            } else if(!note.contains("?")) {
                                if(!word.equals("Silver") && !word.equals("Gold") && !word.equals("Iron")) {
                                    roman[0] += data.get(word);
                                } else if(word.equals("Silver") || word.equals("Gold") || word.equals("Iron")){
                                    data.put(word, String.valueOf(Integer.valueOf(words[words.length - 2]) / RomanConverter.convertoToNumber(roman[0])));
                                }
                            }
                        });
                    } else {
                        questions.add(note);
                    }
                });

        questions.stream().forEach(question -> {
            String[] words = question.split(" ");
            String[] roman = {""};
            String[] answer = {""};
            if(question.contains("how much")) {
                Stream.iterate(3, n -> n + 1)
                        .limit(words.length - 3)
                        .forEach(i -> {
                            if(!words[i].equals("Silver") && !words[i].equals("Gold") && !words[i].equals("Iron") && data.containsKey(words[i])) {
                                roman[0] += data.get(words[i]);
                                answer[0] += words[i] + " ";
                            } else if(words[i].equals("?")){
                                answer[0] += "is " + (RomanConverter.convertoToNumber(roman[0]));
                            }
                        });
            } else {
                Stream.iterate(3, n -> n + 1)
                        .limit(words.length - 3)
                        .forEach(i -> {
                            if(!words[i].equals("Silver") && !words[i].equals("Gold") && !words[i].equals("Iron") && data.containsKey(words[i])) {
                                roman[0] += data.get(words[i]);
                                answer[0] += words[i] + " ";
                            } else if(words[i].equals("Silver") || words[i].equals("Gold") || words[i].equals("Iron")){
                                answer[0] += words[i] + " is " + (RomanConverter.convertoToNumber(roman[0]) * Integer.valueOf(data.get(words[i]))) + " Credits";
                            }
                        });
            }
            answers.add(answer[0]);
            System.out.println(answer[0]);
        });
        return new NotesDTO(answers);
    }

}
