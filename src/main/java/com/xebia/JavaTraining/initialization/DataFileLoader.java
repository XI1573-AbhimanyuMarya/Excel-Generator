package com.xebia.JavaTraining.initialization;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
 // https://www.javatpoint.com/how-to-read-excel-file-in-java
//http://javafries.blogspot.com/2015/10/reading-excel-file-and-storing-data-in.html
@Component
public class DataFileLoader {
    private static final Logger log = LoggerFactory.getLogger(DataFileLoader.class);
    public Map<Integer, List> dataFileHashMap = new HashMap<>();

    @PostConstruct
    public Map readDataFile() throws IOException {
        File datainputFile = new File("C:/Java Projects/CleanCode/JavaTraining/src/main/resources/Sample-Data-File.xlsx");
        FileInputStream dataInputStream = new FileInputStream(datainputFile);
        log.info("Data File Loaded Successfully");
        XSSFWorkbook dataWorkBook = new XSSFWorkbook(dataInputStream);
        XSSFSheet sheet = dataWorkBook.getSheetAt(0);
        Iterator rows = sheet.rowIterator();

        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();

            List data = new LinkedList<>();
            while (cells.hasNext()) {
                XSSFCell cell = (XSSFCell) cells.next();
                cell.setCellType(Cell.CELL_TYPE_STRING);
                data.add(cell);
            }
            dataFileHashMap.put(row.getRowNum(), data);

        }
        return dataFileHashMap;


    }


}
