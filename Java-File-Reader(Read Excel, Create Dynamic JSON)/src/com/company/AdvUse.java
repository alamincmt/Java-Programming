package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
//*************************************************************
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//*************************************************************
public class AdvUse {

    private static Workbook wb;
    private static Sheet sh;
    private static FileInputStream fis;
    private static FileOutputStream fos;
    private static Row row;
    private static Cell cell;
    private static String ExcelPath;

    //*************************************************************
    public static void setEcxelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File f = new File(ExcelPath);
            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File not Found so created");
            }

            fis = new FileInputStream("/home/al-amin/Desktop/Quran Bangla Meaning/001/al_fatiha.xlsx");
            wb = WorkbookFactory.create(fis);
            sh = wb.getSheet("SheetName");
            if (sh == null) {
                sh = wb.getSheet(SheetName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //*************************************************************
    public static void setCellData(String text, int rowno, int colno) {
        try {
            row = sh.getRow(rowno);
            if (row == null) {
                row = sh.createRow(rowno);
            }
            cell = row.getCell(colno);
            if (cell != null) {
                cell.setCellValue(text);

            } else {
                cell = row.createCell(colno);
                cell.setCellValue(text);

            }
            fos = new FileOutputStream(ExcelPath);
            wb.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //*************************************************************
    public static String getCellData(int rowno, int colno) {
        try {

            cell = sh.getRow(rowno).getCell(colno);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    CellData = Double.toString(cell.getNumericCellValue());
                    if (CellData.contains(".o")) {
                        CellData = CellData.substring(0, CellData.length() - 2);

                    }
                    break;
                case BLANK:
                    CellData = "";
                    break;

            }
            return CellData;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //*************************************************************
    public static int getLastRow() {
        return sh.getLastRowNum();
    }
}
