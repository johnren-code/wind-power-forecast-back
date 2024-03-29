package com.example.mybatisplus.common.utls;


import com.example.mybatisplus.common.BaseConstant;
import com.example.mybatisplus.model.vo.FeedbackVO;
import com.example.mybatisplus.model.vo.UserVO;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ExcelUtil {

    public final static String XLS = "xls";
    public final static String XLSX = "xlsx";

    public static List<String[]> readExcel(MultipartFile formFile) throws IOException {
        //检查文件
        //checkFile(formFile);
        //获得工作簿对象
        Workbook workbook = getWorkBook(formFile);
        //创建返回对象，把每行中的值作为一个数组，所有的行作为一个集合返回
        List<String[]> list = new ArrayList<>();
        if (null != workbook) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                //获取当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (null == sheet) {
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行之外的所有行
                for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    //后的当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("deprecation")
    private static String getCellValue(Cell cell) {
        String cellValue = "";

        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == CellType.NUMERIC && !HSSFDateUtil.isCellDateFormatted(cell)) {
            cell.setCellType(CellType.STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case NUMERIC://数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellValue = DateFormatUtils.format(date, "yyyy-MM-dd");
                } else {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    DecimalFormat df = new DecimalFormat("0");
                    cellValue = df.format(cellValue);
                }
                break;
            case STRING://字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN://Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA://公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK://空值
                cellValue = "";
                break;
            case ERROR://故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


    public static Workbook getWorkBook(MultipartFile formFile) {
        //获得文件名
        String fileName = formFile.getOriginalFilename();
        //创建Workbook工作簿对象，表示整个excel
        Workbook workbook = null;
        try {
            //获得excel文件的io流
            InputStream is = formFile.getInputStream();
            //根据文件后缀名不同（xls和xlsx）获得不同的workbook实现类对象
            if (fileName.endsWith(XLS)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(XLSX)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
    public static void exportFeedback(HttpServletResponse response, List<FeedbackVO> feedbackVOS) throws IOException {
        //声明一个工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        //声明一个sheet并命名
        HSSFSheet sheet = hwb.createSheet("Sheet1");
        //给sheet名称一个长度
        sheet.setDefaultColumnWidth((short) 10);

        sheet.setColumnWidth(3, "2023-02-25T14:56:55  ".getBytes().length * 256);
        sheet.setColumnWidth(4, "测试数据:管理员用户   ".getBytes().length * 256);

        // 设置单元格格式为文本格式
        HSSFCellStyle textStyle = hwb.createCellStyle();
        HSSFDataFormat format = hwb.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //设置单元格格式为"文本"
        sheet.setDefaultColumnStyle(6, textStyle);

        //设置下拉框
        // DataValidationHelper helper = sheet.getDataValidationHelper();

        //生成表头样式
        HSSFCellStyle headStyle = hwb.createCellStyle();
        headStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderRight(BorderStyle.THIN);
        headStyle.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成一个字体
        HSSFFont headFont = hwb.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 16);
        headFont.setBold(true);
        // 把字体应用到当前的样式
        headStyle.setFont(headFont);

        //生成表头样式
        HSSFCellStyle style2 = hwb.createCellStyle();
        style2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderRight(BorderStyle.THIN);
        style2.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderTop(BorderStyle.THIN);
        style2.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        // 生成一个字体
        HSSFFont font2 = hwb.createFont();
        font2.setFontName("Book Antiqua");
        font2.setFontHeightInPoints((short) 12);
        font2.setBold(true);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        //公用样式
        HSSFCellStyle style = hwb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成一个字体
        HSSFFont font = hwb.createFont();
        font.setFontName("Book Antiqua");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);

        //表格内部样式
        HSSFCellStyle commStyle = hwb.createCellStyle();
        commStyle.setBorderBottom(BorderStyle.THIN);
        commStyle.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderLeft(BorderStyle.THIN);
        commStyle.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderRight(BorderStyle.THIN);
        commStyle.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderTop(BorderStyle.THIN);
        commStyle.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setAlignment(HorizontalAlignment.CENTER);
        commStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font1 = hwb.createFont();
        font1.setFontName("Book Antiqua");
        font1.setFontHeightInPoints((short) 10);
        commStyle.setFont(font1);
        //创建表标题
        HSSFRow headerRow = sheet.createRow(0);
        //样式字体居中
        headerRow.setHeightInPoints(20);
        headerRow.setHeight((short) (34.40 * 20));
        HSSFCell cell0 = headerRow.createCell(0);
        HSSFCell cell7 = headerRow.createCell(5);
        cell0.setCellValue("反馈信息表");
        cell0.setCellStyle(headStyle);
        cell7.setCellStyle(headStyle);
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(cellAddresses);

        //创建表头
        HSSFRow row = sheet.createRow(1);
        row.setHeight((short) (20.40 * 20));
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("风场ID");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("运行状态");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("描述");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("上报时间");
        cell.setCellStyle(style);


        for (short i = 0; i < feedbackVOS.size(); i++) {
            row = sheet.createRow(i + 2);
            cell = row.createCell(0);
            cell.setCellStyle(commStyle);
            cell.setCellValue(feedbackVOS.get(i).getId());

            cell = row.createCell(1);
            cell.setCellStyle(commStyle);
            cell.setCellValue(feedbackVOS.get(i).getFarmId());

            cell = row.createCell(2);
            cell.setCellStyle(commStyle);
            cell.setCellValue(feedbackVOS.get(i).getStateName());

            cell = row.createCell(3);
            cell.setCellStyle(commStyle);
            cell.setCellValue(feedbackVOS.get(i).getDescription());

            cell = row.createCell(4);
            cell.setCellStyle(commStyle);
            cell.setCellValue(Objects.isNull(feedbackVOS.get(i).getCreateTime())?"":feedbackVOS.get(i).getCreateTime().toString().replace("T"," "));

        }
        hwb.write(response.getOutputStream());
        hwb.close();
    }
    public static void exportUser(HttpServletResponse response, List<UserVO> userVOS) throws IOException {
        //声明一个工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        //声明一个sheet并命名
        HSSFSheet sheet = hwb.createSheet("Sheet1");
        //给sheet名称一个长度
        sheet.setDefaultColumnWidth((short) 10);

        sheet.setColumnWidth(3, "2023-02-25T14:56:55  ".getBytes().length * 256);
        sheet.setColumnWidth(4, "测试数据:管理员用户   ".getBytes().length * 256);

        // 设置单元格格式为文本格式
        HSSFCellStyle textStyle = hwb.createCellStyle();
        HSSFDataFormat format = hwb.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //设置单元格格式为"文本"
        sheet.setDefaultColumnStyle(6, textStyle);

        //设置下拉框
        // DataValidationHelper helper = sheet.getDataValidationHelper();

        //生成表头样式
        HSSFCellStyle headStyle = hwb.createCellStyle();
        headStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderRight(BorderStyle.THIN);
        headStyle.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成一个字体
        HSSFFont headFont = hwb.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 16);
        headFont.setBold(true);
        // 把字体应用到当前的样式
        headStyle.setFont(headFont);

        //生成表头样式
        HSSFCellStyle style2 = hwb.createCellStyle();
        style2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderRight(BorderStyle.THIN);
        style2.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderTop(BorderStyle.THIN);
        style2.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        // 生成一个字体
        HSSFFont font2 = hwb.createFont();
        font2.setFontName("Book Antiqua");
        font2.setFontHeightInPoints((short) 12);
        font2.setBold(true);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        //公用样式
        HSSFCellStyle style = hwb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成一个字体
        HSSFFont font = hwb.createFont();
        font.setFontName("Book Antiqua");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);

        //表格内部样式
        HSSFCellStyle commStyle = hwb.createCellStyle();
        commStyle.setBorderBottom(BorderStyle.THIN);
        commStyle.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderLeft(BorderStyle.THIN);
        commStyle.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderRight(BorderStyle.THIN);
        commStyle.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderTop(BorderStyle.THIN);
        commStyle.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setAlignment(HorizontalAlignment.CENTER);
        commStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font1 = hwb.createFont();
        font1.setFontName("Book Antiqua");
        font1.setFontHeightInPoints((short) 10);
        commStyle.setFont(font1);
        //创建表标题
        HSSFRow headerRow = sheet.createRow(0);
        //样式字体居中
        headerRow.setHeightInPoints(20);
        headerRow.setHeight((short) (34.40 * 20));
        HSSFCell cell0 = headerRow.createCell(0);
        HSSFCell cell7 = headerRow.createCell(5);
        cell0.setCellValue("用户信息表");
        cell0.setCellStyle(headStyle);
        cell7.setCellStyle(headStyle);
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(cellAddresses);

        //创建表头
        HSSFRow row = sheet.createRow(1);
        row.setHeight((short) (20.40 * 20));
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);

        cell = row.createCell((short) 1);
        cell.setCellValue("账号");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("密码");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("邮箱");
        cell.setCellStyle(style);

        cell = row.createCell((short) 4);
        cell.setCellValue("描述");
        cell.setCellStyle(style);

        cell = row.createCell((short) 5);
        cell.setCellValue("角色");
        cell.setCellStyle(style);



        for (short i = 0; i < userVOS.size(); i++) {
            row = sheet.createRow(i + 2);
            cell = row.createCell(0);
            cell.setCellStyle(commStyle);
            cell.setCellValue(userVOS.get(i).getId());

            cell = row.createCell(1);
            cell.setCellStyle(commStyle);
            cell.setCellValue(userVOS.get(i).getAccount());

            cell = row.createCell(2);
            cell.setCellStyle(commStyle);
            cell.setCellValue(userVOS.get(i).getPassword());

            cell = row.createCell(3);
            cell.setCellStyle(commStyle);
            cell.setCellValue(Objects.isNull(userVOS.get(i).getEmail())?"":userVOS.get(i).getEmail().toString().replace("T"," "));
            cell = row.createCell(4);
            cell.setCellStyle(commStyle);
            cell.setCellValue(userVOS.get(i).getDescription());
            cell = row.createCell(5);
            cell.setCellStyle(commStyle);
            cell.setCellValue(userVOS.get(i).getRoleName());


        }
        hwb.write(response.getOutputStream());
        hwb.close();
    }
    public static void downloadTemp(HttpServletResponse response) throws IOException {
        //声明一个工作簿
        HSSFWorkbook hwb = new HSSFWorkbook();
        //声明一个sheet并命名
        HSSFSheet sheet = hwb.createSheet("老师申请信息");
        //给sheet名称一个长度
        sheet.setDefaultColumnWidth((short) 10);

        sheet.setColumnWidth(3, "2023-02-25T14:56:55  ".getBytes().length * 256);
        sheet.setColumnWidth(4, "测试数据:管理员用户   ".getBytes().length * 256);

        // 设置单元格格式为文本格式
        HSSFCellStyle textStyle = hwb.createCellStyle();
        HSSFDataFormat format = hwb.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        //设置单元格格式为"文本"
        sheet.setDefaultColumnStyle(6, textStyle);

        //设置下拉框
        DataValidationHelper helper = sheet.getDataValidationHelper();

        //生成表头样式
        HSSFCellStyle headStyle = hwb.createCellStyle();
        headStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setBorderBottom(BorderStyle.THIN);
        headStyle.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderLeft(BorderStyle.THIN);
        headStyle.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderRight(BorderStyle.THIN);
        headStyle.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setBorderTop(BorderStyle.THIN);
        headStyle.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成一个字体
        HSSFFont headFont = hwb.createFont();
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 16);
        headFont.setBold(true);
        // 把字体应用到当前的样式
        headStyle.setFont(headFont);

        //生成表头样式
        HSSFCellStyle style2 = hwb.createCellStyle();
        style2.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderRight(BorderStyle.THIN);
        style2.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style2.setBorderTop(BorderStyle.THIN);
        style2.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        // 生成一个字体
        HSSFFont font2 = hwb.createFont();
        font2.setFontName("Book Antiqua");
        font2.setFontHeightInPoints((short) 12);
        font2.setBold(true);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        //公用样式
        HSSFCellStyle style = hwb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        // 生成一个字体
        HSSFFont font = hwb.createFont();
        font.setFontName("Book Antiqua");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);

        //表格内部样式
        HSSFCellStyle commStyle = hwb.createCellStyle();
        commStyle.setBorderBottom(BorderStyle.THIN);
        commStyle.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderLeft(BorderStyle.THIN);
        commStyle.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderRight(BorderStyle.THIN);
        commStyle.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setBorderTop(BorderStyle.THIN);
        commStyle.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
        commStyle.setAlignment(HorizontalAlignment.CENTER);
        commStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFFont font1 = hwb.createFont();
        font1.setFontName("Book Antiqua");
        font1.setFontHeightInPoints((short) 10);
        commStyle.setFont(font1);
        //创建表标题
        HSSFRow headerRow = sheet.createRow(0);
        //样式字体居中
        headerRow.setHeightInPoints(20);
        headerRow.setHeight((short) (34.40 * 20));
        HSSFCell cell0 = headerRow.createCell(0);
        HSSFCell cell7 = headerRow.createCell(3);
        cell0.setCellValue("用户信息表");
        cell0.setCellStyle(headStyle);
        cell7.setCellStyle(headStyle);
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(cellAddresses);

        //创建表头
        HSSFRow row = sheet.createRow(1);
        row.setHeight((short) (20.40 * 20));
        //给表头第一行一次创建单元格
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);

        cell = row.createCell((short)1);
        cell.setCellValue("密码");
        cell.setCellStyle(style);

        cell = row.createCell((short) 2);
        cell.setCellValue("备注");
        cell.setCellStyle(style);

        cell = row.createCell((short) 3);
        cell.setCellValue("角色");
        cell.setCellStyle(style);

        row = sheet.createRow( 2);
        cell = row.createCell(0);
        cell.setCellStyle(commStyle);
        cell.setCellValue("hello");

        cell = row.createCell(1);
        cell.setCellStyle(commStyle);
        cell.setCellValue("hello");

        cell = row.createCell(2);
        cell.setCellStyle(commStyle);
        cell.setCellValue("这是备注");

        cell = row.createCell(3);
        cell.setCellStyle(commStyle);

        creatDropDownList(sheet,helper, BaseConstant.ROLES,2,2,3,3);
        cell.setCellValue("超级管理员");


        hwb.write(response.getOutputStream());
        hwb.close();
    }
    public static void checkFile(MultipartFile formFile) throws IOException {
        //判断文件是否存在
        if (null == formFile) {
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = formFile.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            throw new IOException(fileName + "不是excel文件！");
        }
    }
    public static Boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


//    public static List<ExportError> checkHead(String[] head) {
//        List<ExportError> error = new ArrayList<>();
//        String[] oldHead = {"名称","省份","年份","招生数目"};
//        if (head.length != oldHead.length) {
//            error.add(new ExportError(2, "列数不匹配"));
//            return error;
//        }
//        for (int i = 0; i < oldHead.length; i++) {
//            if (!oldHead[i].equals(head[i])) {
//                error.add(new ExportError(2, "列名不对,正确列名为:" + oldHead[i] + ",当前列名为:" + head[i]));
//            }
//        }
//        return error;
//    }
    //创建下拉框
    private static void creatDropDownList(Sheet taskInfoSheet, DataValidationHelper helper, String[] list,
                                          Integer firstRow, Integer lastRow, Integer firstCol, Integer lastCol) {
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        //设置下拉框数据
        DataValidationConstraint constraint = helper.createExplicitListConstraint(list);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        //处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }
        taskInfoSheet.addValidationData(dataValidation);
    }


}
