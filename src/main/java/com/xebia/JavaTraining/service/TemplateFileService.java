package com.xebia.JavaTraining.service;

import com.xebia.JavaTraining.initialization.DataFileLoader;
import com.xebia.JavaTraining.initialization.JsonFileLoader;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TemplateFileService {
    private static final Logger log = LoggerFactory.getLogger(DataFileLoader.class);
    @Autowired
    public DataFileLoader dataFileLoader;
    @Autowired
    public JsonFileLoader jsonFileLoader;
    Map<Integer, List> templateFileHashMap = new HashMap<>();

    @PreDestroy
    public void generateOutputFileFromTemplateFile() throws IOException, ParseException {

        // Loading Data from Initilization package
        List<String> jsonValues = jsonFileLoader.readJsonFile();
        templateFileHashMap = dataFileLoader.dataFileHashMap;

        // Reading Template File
        File templateinputFile = new File("C:/Java Projects/CleanCode/JavaTraining/src/main/resources/Sample-Template-File.xlsx");
        FileInputStream templateInputStream = new FileInputStream(templateinputFile);
        log.info("Template File Loaded Successfully");
        XSSFWorkbook templateWorkBook = new XSSFWorkbook(templateInputStream);
        XSSFSheet sheet = templateWorkBook.getSheetAt(0);

        // Iterating of Each Key Value Pair to STORE & LOAD  Individual value
        // VARIABLE : mapValueIterator iterates the JSON Values stored in list

        for (Integer hash_key : templateFileHashMap.keySet()) {
            // If stored outside key_Value Loop, Out of Bound Error because only 5 cell Address corresponding to 5 hash_Value. Otherwise it keep on increasing
            int mapValueIterator = 0;
            for (Object hash_value : templateFileHashMap.get(hash_key)) {

                if (hash_key == 0) break;

                // Read Cell Address corrosponding to Json Value
                CellReference cellReference = new CellReference(jsonValues.get(mapValueIterator));
                Row row = sheet.getRow(cellReference.getRow());
                Cell cell = row.getCell(cellReference.getCol());

                //*IMPORTANT* You First Need to Create an Empty Cell of Type String Before Writing Values Otherwise shows error

                if (cell == null) {
                    cell = row.createCell(cellReference.getCol());
                }
                cell.setCellValue(Cell.CELL_TYPE_STRING);

                // Converting object Hash Value to string and storing it in a VARIABLE
                //For each Hash_key Loop, toPutValueInCell stores the particular value to be loaded in the cell.
                String toPutValueInCell = hash_value.toString();

                //CONSOLE OUTPUT
                log.info("Value: " + toPutValueInCell + ": Row Number :" + cell.getRowIndex() + ": Column Number : " + cell.getColumnIndex());

                // Writing to a Emptly Cell created at Cell Address determined by *mapValueIterator*
                cell.setCellValue(toPutValueInCell);

                // after Writing a Hash_value to one Cell Iterating over another cell Address in JSON VALUE LIST
                mapValueIterator++;


                FileOutputStream fileOut = new FileOutputStream("output" + hash_key + ".xlsx");
                templateWorkBook.write(fileOut);
                fileOut.close();

            }


        }


    }


}
