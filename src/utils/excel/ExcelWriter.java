package utils.excel;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.net.URLEncoder.encode;

/**
 * @author taoruizhe@inspur.com
 * @details Excel工具包
 * @date 20191203 10:30
 */

public class ExcelWriter {

    /**
     * 导出数据
     *
     * @param response
     * @param request
     * @param fileName
     * @param nameArr
     * @param titleArr
     * @param alignArr
     * @param widthArr
     * @param dataList
     */
//    public static void doExport(HttpServletResponse response, HttpServletRequest request, String fileName, String[] nameArr,
//                                String[] titleArr, String[] alignArr, int[] widthArr, List dataList) {
//        try {
//            OutputStream os = response.getOutputStream();
//            response.reset();
//            String agent = request.getHeader("USER-AGENT");
//            if (agent != null && agent.indexOf("MSIE") != -1 || agent != null && agent.indexOf("Trident") != -1) {
//                // ie
//                String name = encode(fileName, "UTF8");
//                fileName = name;
//            } else if (agent != null && agent.indexOf("Mozilla") != -1) {
//                // fireFox, chrome
//                fileName = new String(fileName.getBytes("UTF8"), "iso-8859-1");
//            }
//            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
//            response.setContentType("application/msexcel");
////            doExport(os, dataMap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 导出数据到Excel
     *
     * @param nameArr  导出的列英文名称数组，必填，示例：String[] nameArr = new String[]{"name","sex","age"};
     * @param titleArr 列中文名称数组，必填，示例：String[] titleArr = new String[]{"姓名","性别","年龄"};
     * @param alignArr 列对齐方式数组，非必填，默认居中对齐，示例：String[] alignArr = new String[]{"center","left","right"}
     * @param widthArr 列宽度数组 ，非必填，默认10个汉字宽度，示例：int[] widthArr = new int[]{5,20,10},数字为汉字的个数
     * @param dataList 要导出的数据，List中为javabean或map对象
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream doExport2(String[] nameArr, String[] titleArr, String[] alignArr, int[] widthArr, List<Object> dataList) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String title = "Sheet1";
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet(title);
            // 设置表格默认列宽度为10个中文字符
            sheet.setDefaultColumnWidth(10 * 2);
            // 添加标题
            XSSFRow titlleRow = sheet.createRow(0);
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            XSSFFont titleFontStyle = workbook.createFont();
            // 设置标题字体样式
            titleFontStyle.setFontName("宋体");
            titleFontStyle.setFontHeightInPoints((short) 10);
            titleFontStyle.setBold(true);
            titleStyle.setFont(titleFontStyle);
            // 添加标题到excel
            for (int i = 0; i < titleArr.length; i++) {
                XSSFCell cell = titlleRow.createCell(i);
                cell.setCellValue(titleArr[i]);
                cell.setCellStyle(titleStyle);
                //
                if (widthArr != null && widthArr.length > 0) {
                    sheet.setColumnWidth(i, widthArr[i] * 2 * 256);
                }
            }

            // 添加数据
            Boolean isMap = false;
            for (int i = 0; i < dataList.size(); i++) {
                Map<String, Object> data = new HashMap<>();
                Object obj = dataList.get(i);
                if (obj instanceof Map) {
                    isMap = true;
                    data = (Map<String, Object>) obj;
                }
                // 设置样式
                XSSFRow row = sheet.createRow(i + 1);
                XSSFCellStyle contentStyle = workbook.createCellStyle();
                contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                XSSFFont contentFontStyle = workbook.createFont();
                contentFontStyle.setFontName("宋体");
                contentFontStyle.setFontHeightInPoints((short) 10);
                contentStyle.setWrapText(true);
                contentStyle.setFont(contentFontStyle);
                // 添加列数据到excel
                for (int j = 0; j < nameArr.length; j++) {
                    if (alignArr == null || alignArr.length == 0) {
                        contentStyle.setAlignment(HorizontalAlignment.CENTER);
                    } else {
                        contentStyle.setAlignment(changeAlign(alignArr[j]));
                    }
                    XSSFCell cell = row.createCell(j);
                    Object value = null;
                    if (isMap) {
                        value = data.get(nameArr[j]);
                    } else {
                        Class cls = obj.getClass();
                        String getMethodName = "get" + nameArr[j].substring(0, 1).toUpperCase() + nameArr[j].substring(1);
                        Method method = cls.getMethod(getMethodName, new Class[]{});
                        value = method.invoke(obj, new Object[]{});
                    }
                    // 时间标签
                    String textValue = "";
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        textValue = format.format(date);
                        cell.setCellValue(textValue);
                    } else if (value instanceof Integer || value instanceof Float
                            || value instanceof Double || value instanceof Long
                            || value instanceof BigDecimal) {
                        textValue = changeToStr(value);
                        if (textValue.length() <= 11) {
                            cell.setCellValue((Double.parseDouble(textValue)));
                        } else {
                            cell.setCellValue(new BigDecimal(textValue).toString());
                        }
                    } else {
                        textValue = changeToStr(value);
                        cell.setCellValue(textValue);
                    }
                    cell.setCellStyle(contentStyle);
                }
            }
            workbook.write(out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }
        return out;
    }

    /**
     * 转换对齐方式
     *
     * @param align
     * @return
     */
    private static HorizontalAlignment changeAlign(String align) {
        HorizontalAlignment alignment;
        switch (align) {
            case "LEFT": {
                alignment = HorizontalAlignment.LEFT;
                ;
                break;
            }
            case "CENTER": {
                alignment = HorizontalAlignment.CENTER;
                ;
                break;
            }
            default:
                alignment = HorizontalAlignment.RIGHT;
        }
        return alignment;
    }

    /**
     * 处理NOP
     *
     * @param value
     * @return
     */
    private static String changeToStr(Object value) {
        if (value == null || "null".equals(value)) {
            return "";
        }
        return String.valueOf(value);
    }
}
