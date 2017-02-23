package cn.nn.io.excel;

import org.apache.poi.POIDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/11/8.
 * 测试使用POI
 */
public class PrimaryPoi {

    public static void readExcel(String path) throws IOException {
        boolean isE2007 = false;    //判断是否是excel2007格式
        InputStream input = null;
        if (path.endsWith("xlsx"))
            isE2007 = true;
        try {
            input = new FileInputStream(path);    //建立输入流
            Workbook wb = null;
            //根据文件格式(2003或者2007)来初始化
            if (isE2007)
                wb = new XSSFWorkbook(input);
            else
                wb = new HSSFWorkbook(input);
            Sheet sheet = wb.getSheetAt(0);        //获得第一个表单
            Iterator<Row> rows = sheet.rowIterator();    //获得第一个表单的迭代器
            while (rows.hasNext()) {
                Row row = rows.next();    //获得行数据
                System.out.println("Row #" + row.getRowNum());    //获得行号从0开始
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    System.out.print("Cell #" + cell.getColumnIndex() + "\t");
                    switch (cell.getCellType()) {    //根据cell中的类型来输出数据
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() +"\t");
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue()+"\t");
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue()+"\t");
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA:
                            System.out.print(cell.getCellFormula()+"\t");
                            break;
                        default:
                            System.out.print("unsuported sell type" +"\t");
                            break;
                    }
                }
                System.out.println();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
