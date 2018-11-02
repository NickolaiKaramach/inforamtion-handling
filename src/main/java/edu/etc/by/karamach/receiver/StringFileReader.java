package edu.etc.by.karamach.receiver;

import edu.etc.by.karamach.exception.ReceiverException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * File reader
 *
 * @author Nickolai Karamach
 */
public class StringFileReader implements FileReader {
    private static final String DEFAULT_URI = "repository/input.txt";
    private static final Logger logger = LogManager.getLogger("default");

    /**
     * Read strings from file
     * <p>
     * Method try to read all strings from file
     * by uri, but if uri is empty - trying to
     * read from default input path.
     * </p>
     *
     * @param uri used to read from it.
     * @return All strings of file
     * @throws ReceiverException if something goes wrong
     */
    public List<String> readAll(String uri) throws ReceiverException {
        List<String> outputList;

        if (uri.isEmpty()) {
            logger.debug("Couldn't read from empty path, trying to use default uri: " + DEFAULT_URI);
            uri = DEFAULT_URI;
        }

        ClassLoader classLoader = getClass().getClassLoader();

        try (Stream<String> stream = Files.lines(Paths.get(
                classLoader.getResource(uri).getFile()))) {

            outputList = stream.collect(Collectors.toList());
            logger.debug("Successfully read lines from File(" + uri + "):");
            logger.trace(outputList);
        } catch (FileNotFoundException e) {
            throw new ReceiverException("Couldn't find input file", e);
        } catch (IOException e) {
            throw new ReceiverException("Something go wrong during reading the file", e);
        }
        return outputList;
    }
}

