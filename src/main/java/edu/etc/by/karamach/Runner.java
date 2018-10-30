package edu.etc.by.karamach;

import edu.etc.by.karamach.composite.Text;
import edu.etc.by.karamach.handler.LexemeConstructor;
import edu.etc.by.karamach.handler.PartConstructor;
import edu.etc.by.karamach.handler.SentenceConstructor;
import edu.etc.by.karamach.handler.TextConstructor;
import edu.etc.by.karamach.receiver.StringFileReader;

import java.util.List;

public class Runner {
    //TODO: DOCUMENTATION
    //TODO: TEST SUITES
    public static void main(String[] args) throws Exception {
        StringFileReader reader = new StringFileReader();

        List<String> strings = reader.readAll("");

        StringBuilder totalString = new StringBuilder();
        for (String string : strings) {
            totalString.append(string + "\n");
        }

        TextConstructor textConstructor = new TextConstructor();
        PartConstructor partConstructor = new PartConstructor();
        SentenceConstructor sentenceConstructor = new SentenceConstructor();
        LexemeConstructor lexemeConstructor = new LexemeConstructor();

        textConstructor.setNext(partConstructor);
        partConstructor.setNext(sentenceConstructor);
        sentenceConstructor.setNext(lexemeConstructor);

//        String finalString = totalString.toString();
//
//        System.out.println(finalString.replaceAll("\n", "\n\n\n"));

        Text text = textConstructor.construct(totalString.toString());
        text.getValue();
    }
}
