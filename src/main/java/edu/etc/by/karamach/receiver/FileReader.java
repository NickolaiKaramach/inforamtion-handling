package edu.etc.by.karamach.receiver;

import edu.etc.by.karamach.exception.ReceiverException;

import java.util.List;

public interface FileReader {
    List<String> readAll(String uri) throws ReceiverException;
}
