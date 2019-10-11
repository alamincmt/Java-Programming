package com.company;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadExcel {
    private String inputFile;

    public void setInputFile(String inputFile){
        this.inputFile = inputFile;
    }

    public void read() {
        File inputWordBook = new File(inputFile);
        Workbook workbook;
        try{
            workbook = Workbook.getWorkbook(inputWordBook);
            // Get the first sheet
            int totalSura = 40;
            JSONArray jsonArray = new JSONArray();
            jsonArray.clear();
            JSONObject ayatObject = new JSONObject();
            int id = 0;
            List<JSONObject> ayatObjList = new ArrayList<>();

            for(int suraNumber = 0; suraNumber < totalSura; suraNumber ++){
                Sheet sheet = workbook.getSheet(suraNumber);
                // Loop over first 10 column and lines
                if(sheet != null){
                    for(int j=0; j<sheet.getColumns(); j++){
                        for(int i=0; i<sheet.getRows(); i++){
                            ayatObject = new JSONObject();
                            Cell cell = sheet.getCell(j, i);
                            CellType cellType = cell.getType();
                            if(cellType == CellType.LABEL){
                                id++;
                                ayatObject.put("id", id+"");
                                ayatObject.put("sura", (suraNumber+1) + "");
                                ayatObject.put("aya", (i+1) + "");
                                ayatObject.put("text", cell.getContents());

                                System.out.println("Sura Number: " + (suraNumber+1) + " Ayat Number: " + (i+1) + "\nAyat: " + cell.getContents());
                            }

                            ayatObjList.add(ayatObject);
                        }
                    }
                }

                System.out.println(" End Of Sura " + (suraNumber+1) + "\n\n\n");
            }

            if(ayatObjList != null && ayatObjList.size() > 0){
                jsonArray.addAll(ayatObjList);
            }

            System.out.println(" JSONArray " + jsonArray.toString());

        }catch (BiffException | IOException exception){
            System.out.println(exception.getMessage());
        }
    }

}
