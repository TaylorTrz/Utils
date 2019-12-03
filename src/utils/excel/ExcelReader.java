package utils.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.*;

import static javax.swing.UIManager.getString;

/**
 * @author taoruizhe
 * @details Excel工具包
 * @date 20191203 14:00
 * @since v1.0.0
 */

public class ExcelReader {

    public static List<Map<String, String>> excelReader(InputStream inputStream, String sheetName) {
        // 定义工作薄
        XSSFWorkbook xssfWorkbook = null;
        try {
            xssfWorkbook = new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            System.out.println("Excel data file cannot be found!");
        }

        // 定义工作表
        XSSFSheet xssfSheet;
        if (sheetName.equals("")) {
            // 默认取第一个子表
            xssfSheet = xssfWorkbook.getSheetAt(0);
        } else {
            xssfSheet = xssfWorkbook.getSheet(sheetName);
        }

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        // 定义行，默认第一行为标题行
        XSSFRow titleRow = xssfSheet.getRow(0);
        // 循环取每行的数据
        for (int rowIndex = 1; rowIndex < xssfSheet.getPhysicalNumberOfRows(); rowIndex++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowIndex);
            if (xssfRow == null) {
                continue;
            }
            Map<String, String> map = new LinkedHashMap<>();
            // 循环取每个单元格的数据
            for (int cellIndex = 0; cellIndex < xssfRow.getPhysicalNumberOfCells(); cellIndex++) {
                XSSFCell titleCell = titleRow.getCell(cellIndex);
                XSSFCell xssfCell = xssfRow.getCell(cellIndex);
                map.put(getString(titleCell), getString(xssfCell));
            }
            list.add(map);
        }
        return list;
    }

    @Test
    public void ExcelReaderTest() {
        try {
            File file = new File("tmp", "C、C++组校聘员工名单(1).xlsx");
            InputStream fis = new FileInputStream(file);
            List<Map<String, String>> list = excelReader(fis, "");
            Iterator<Map<String, String>> iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            System.out.println("cannot find file");
        }

    }
}
