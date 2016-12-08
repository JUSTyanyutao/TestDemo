package com.mxep.web.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by douya on 2016-08-11.
 */
public class ExcelReader {
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private XSSFRow row;
    /** 总行数 */

    private int totalRows = 0;

    /** 总列数 */

    private int totalCells = 0;

    /** 构造方法 */

    public ExcelReader()
    {

    }
    public int getTotalRows()
    {

        return totalRows;

    }
    public int getTotalCells()
    {

        return totalCells;

    }
    /**
     * 读取Excel表格表头的内容
     * @param
     * @return String 表头内容的数组
     *
     */
    public  String[] readExcelTitle(InputStream is) {
        try {
            //fs = new POIFSFileSystem(is);
            wb = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        //标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = new String[colNum];
        for (int i=0; i<colNum; i++) {
            title[i] = getTitleValue(row.getCell((short) i));
        }
        return title;
    }




    public String getTitleValue(XSSFCell cell) {
        String strCell =  cell.getStringCellValue();
        return strCell;
    }
    public List<List<String>> read(InputStream is) throws IOException {

        wb = new XSSFWorkbook(is);

        List<List<String>> dataLst = new ArrayList<List<String>>();

        /** 得到第一个shell */

        Sheet sheet = wb.getSheetAt(0);

        /** 得到Excel的行数 */

        this.totalRows = sheet.getPhysicalNumberOfRows();

        /** 得到Excel的列数 */

        if (this.totalRows >= 1 && sheet.getRow(0) != null)
        {

            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();

        }

        /** 循环Excel的行 */

        for (int r = 0; r < this.totalRows; r++)
        {

            Row row = sheet.getRow(r);

            if (row == null)
            {

                continue;

            }

            List<String> rowLst = new ArrayList<String>();

            /** 循环Excel的列 */

            for (int c = 0; c < this.getTotalCells(); c++)
            {

                Cell cell = row.getCell(c);

                String cellValue = "";

                if (null != cell)
                {
                    cell.setCellType(1);
                    cellValue = cell.getStringCellValue();
                    // 以下是判断数据的类型
                    /*switch (cell.getCellType())
                    {
                        case XSSFCell.CELL_TYPE_NUMERIC: // 数字
                            cellValue = cell.getNumericCellValue() + "";
                            break;

                        case XSSFCell.CELL_TYPE_STRING: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;

                        case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;

                        case XSSFCell.CELL_TYPE_FORMULA: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;

                        case XSSFCell.CELL_TYPE_BLANK: // 空值
                            cellValue = "";
                            break;

                        case XSSFCell.CELL_TYPE_ERROR: // 故障
                            cellValue = "非法字符";
                            break;

                        default:
                            cellValue = "未知类型";
                            break;
                    }*/
                }

                rowLst.add(cellValue);

            }

            /** 保存第r行的第c列 */

            dataLst.add(rowLst);

        }

        return dataLst;

    }
}
