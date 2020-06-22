package com.xebia.JavaTraining.initialization;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataFileLoaderTest {

    @Test
    void readDataFile() throws IOException {
        Map<Integer, List> actual = new HashMap<>();
        Map<Integer, List> expected = new HashMap<>();
        DataFileLoader dataFileLoader = new DataFileLoader();
        expected.put(0, Arrays.asList("Name", "Age", "Class", "Section", "Rank"));

        actual = dataFileLoader.readDataFile();

        // Matching Random Values , rather the whole List
        assertEquals(actual.get(0).get(1).toString(), expected.get(0).get(1).toString());
    }
}