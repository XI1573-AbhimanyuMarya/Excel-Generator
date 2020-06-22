package com.xebia.JavaTraining.initialization;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// JSON Tutorial Part-5 | How To Read Data from JSON File in Java | JSON Simple API

@Component
public class JsonFileLoader {
    public static final Logger log = LoggerFactory.getLogger(DataFileLoader.class);
    List<String> cellValue = new ArrayList<>();

    @PostConstruct
    public List readJsonFile() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        String fileLocation = "C:/Java Projects/CleanCode/JavaTraining/src/main/resources/mpping.json";
        FileReader jsonReader = new FileReader(fileLocation);
        log.info("JSON File Loaded Successfully");
        Object javaObject = jsonParser.parse(jsonReader);    // Simple Java Object
        JSONObject jsonObject = (JSONObject) javaObject;    //Java Object Type Cast To JSON Object

        cellValue.add((String) jsonObject.get("name"));
        cellValue.add((String) jsonObject.get("age"));
        cellValue.add((String) jsonObject.get("class"));
        cellValue.add((String) jsonObject.get("rank"));
        cellValue.add((String) jsonObject.get("section"));
        return cellValue;


    }
}
