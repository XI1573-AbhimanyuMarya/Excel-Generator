package com.xebia.JavaTraining.initialization;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileLoaderTest {

    @Test
    void readJsonFile() throws IOException, ParseException {
        JsonFileLoader jsonFileLoader=new JsonFileLoader();
        List<String> actual =jsonFileLoader.readJsonFile();
        List<String> expected= Arrays.asList("b1","b2","b3","b4","b5");
        assertEquals(actual,expected);
    }
}