package app.service;

import app.dto.NotesDTO;

import java.util.HashMap;
import java.util.LinkedList;

public interface IMerchantService {
    NotesDTO enterNotes(NotesDTO notes);
    void answerQuestions(LinkedList<String> questions, HashMap<String,String> data, LinkedList<String> answers);
}
