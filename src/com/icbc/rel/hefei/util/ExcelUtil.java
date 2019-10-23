package com.icbc.rel.hefei.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {

    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

	/**
     * ����excel
     * @param title  ������ı���
     * @param rowsName �����������
     * @param dataList  ��Ҫ����������
     * @param fileName  ����excel�ļ����ļ���
     * @param response
     */
    public void exportExcel(String title, String[] rowsName, List<Object[]> dataList, String fileName, HttpServletResponse response) throws Exception{
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition",
                "attachment; filename="+fileName);
        //response.setContentType("application/msexcel");
       response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setContentType("application/octet-stream; charset=UTF-8");// ���ݸ�����Ҫ,����������ļ�������

        this.export(title,rowsName,dataList,fileName,output);
        this.close(output);
    }

    /*
     * ��������
     */
    private void export(String title,String[] rowName,List<Object[]> dataList,String fileName,OutputStream out) throws Exception {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(); // ��������������
            HSSFSheet sheet = workbook.createSheet(title); // ����������
            HSSFRow rowm = sheet.createRow(0);  // ������������
            HSSFCell cellTiltle = rowm.createCell(0);   //������������
            // sheet��ʽ����;    getColumnTopStyle();    getStyle()��Ϊ�Զ��巽�� --������,����չ
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);// ��ȡ��ͷ��ʽ����
            HSSFCellStyle style = this.getStyle(workbook); // ��ȡ��Ԫ����ʽ����
            //�ϲ��������У��ϲ�����Ϊ�����ĳ���,��һ��0Ϊ��ʼ�кţ��ڶ���1Ϊ��ֹ�кţ�������0Ϊ��ʼ�кã����ĸ�����Ϊ��ֹ�к�
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            cellTiltle.setCellStyle(columnTopStyle);    //���ñ�������ʽ
            cellTiltle.setCellValue(title);     //���ñ�����ֵ
            int columnNum = rowName.length;     // ������������
            HSSFRow rowRowName = sheet.createRow(2); // ������2��λ�ô�����(��˵��п�ʼ�ĵڶ���)
            // ����ͷ���õ�sheet�ĵ�Ԫ����
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n); // ������ͷ��Ӧ�����ĵ�Ԫ��
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // ������ͷ��Ԫ�����������
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text); // ������ͷ��Ԫ���ֵ
                cellRowName.setCellStyle(columnTopStyle); // ������ͷ��Ԫ����ʽ
            }

            // ����ѯ�����������õ�sheet��Ӧ�ĵ�Ԫ����
            for (int i = 0; i < dataList.size(); i++) {
                Object[] obj = dataList.get(i);   // ����ÿ������
                HSSFRow row = sheet.createRow(i + 3);   // �������������
                for (int j = 0; j < obj.length; j++) {
                    HSSFCell cell = null;   // ���õ�Ԫ�����������
//                    if (j == 0) {
//                        cell = row.createCell(j, HSSFCell.CELL_TYPE_NUMERIC);
//                        cell.setCellValue(i + 1);
//                    } else {
                        cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                        if (!"".equals(obj[j]) && obj[j] != null) {
                            cell.setCellValue(obj[j].toString()); // ���õ�Ԫ���ֵ
                        }
                        else{
                        	 cell.setCellValue(""); // ���õ�Ԫ���ֵ
                        }
                    //}
                    cell.setCellStyle(style); // ���õ�Ԫ����ʽ
                }
            }

            // ���п����ŵ������г��Զ���Ӧ
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    // ��ǰ��δ��ʹ�ù�
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        currentCell.setCellType(Cell.CELL_TYPE_STRING);
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        	//String cellValue = numCell.getStringCellValue();
                            int length = currentCell.getStringCellValue()
                                    .getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * ��ͷ��Ԫ����ʽ
     */
    private HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // ��������
        HSSFFont font = workbook.createFont();
        // ���������С
        font.setFontHeightInPoints((short) 11);
        // ����Ӵ�
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // ������������
        font.setFontName("����");
        // ������ʽ;
        HSSFCellStyle style = workbook.createCellStyle();
        // ���õױ߿�;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // ���õױ߿���ɫ;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // ������߿�;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // ������߿���ɫ;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // �����ұ߿�;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // �����ұ߿���ɫ;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // ���ö��߿�;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // ���ö��߿���ɫ;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // ����ʽ��Ӧ�����õ�����;
        style.setFont(font);
        // �����Զ�����;
        style.setWrapText(false);
        // ����ˮƽ�������ʽΪ���ж���;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // ���ô�ֱ�������ʽΪ���ж���;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /*
     * ��������Ϣ��Ԫ����ʽ
     */
    private HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // ��������
        HSSFFont font = workbook.createFont();
        // ���������С
        // font.setFontHeightInPoints((short)10);
        // ����Ӵ�
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // ������������
        font.setFontName("����");
        // ������ʽ;
        HSSFCellStyle style = workbook.createCellStyle();
        // ���õױ߿�;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // ���õױ߿���ɫ;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // ������߿�;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // ������߿���ɫ;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // �����ұ߿�;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // �����ұ߿���ɫ;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // ���ö��߿�;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // ���ö��߿���ɫ;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // ����ʽ��Ӧ�����õ�����;
        style.setFont(font);
        // �����Զ�����;
        style.setWrapText(false);
        // ����ˮƽ�������ʽΪ���ж���;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // ���ô�ֱ�������ʽΪ���ж���;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * �ر������
     * @param os
     */
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
	 * ����excel�ļ��������󷵻�
	 *
	 * @param file
	 * @throws IOException
	 */
	public static List<String[]> ReadExcel(Workbook workbook, int sheetNum) {
		// ����ļ�
		// checkFile(file);
		// ���Workbook����������

		// �������ض��󣬰�ÿ���е�ֵ��Ϊһ�����飬��������Ϊһ�����Ϸ���
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {

			// ��õ�ǰsheet������
			Sheet sheet = workbook.getSheetAt(sheetNum);
			if (sheet == null) {
				return list;
			}
			// ��õ�ǰsheet�Ŀ�ʼ��
			int firstRowNum = sheet.getFirstRowNum();
			// ��õ�ǰsheet�Ľ�����
			int lastRowNum = sheet.getPhysicalNumberOfRows();
			int lastCellNum=0;
			lastCellNum = sheet.getRow(1).getPhysicalNumberOfCells();
			// ѭ�����˵�һ�е�������
			for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
				// ��õ�ǰ��
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				// ��õ�ǰ�еĿ�ʼ��
				// int firstCellNum = row.getFirstCellNum();
				// ��õ�ǰ�е�����
				//if(rowNum==firstRowNum + 1) {

				//}
				String[] cells = new String[lastCellNum];
				// ѭ����ǰ��
				String total="";

				for (int cellNum = 0; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					String value=getCellValue(cell);
					cells[cellNum] = value;
					total+=value.trim();
				}
				//����
				if(total.equals("")) {
					continue;
				}
				list.add(cells);
				// }
			}
			// workbook. close();
		}
		return list;
	}

	public static Workbook getWorkBook(String filePath) throws IOException {
		// ����Workbook���������󣬱�ʾ����excel
		Workbook workbook = null;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));

			// �����ļ���׺����ͬ(xls��xlsx)��ò�ͬ��Workbookʵ�������
			if (filePath.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(in);
			} else if (filePath.endsWith("xlsx")) {
				// 2007
				workbook = new XSSFWorkbook(in);
			}
		} catch (IOException e) {
			throw e;
		}
		return workbook;
	}

	public static Workbook getWorkBookByUrl(String filePath) throws IOException {
		// ����Workbook���������󣬱�ʾ����excel
		Workbook workbook = null;
		URL url=new URL(filePath);

		try {
			BufferedInputStream in = new BufferedInputStream(url.openStream());

			// �����ļ���׺����ͬ(xls��xlsx)��ò�ͬ��Workbookʵ�������
			if (filePath.endsWith("xls")) {
				// 2003
				workbook = new HSSFWorkbook(in);
			} else if (filePath.endsWith("xlsx")) {
				// 2007
				workbook = new XSSFWorkbook(in);
			}
		} catch (IOException e) {
			throw e;
		}
		return workbook;
	}



	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// �����ֵ���String�������������1����1.0�����
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				Date d = cell.getDateCellValue();
				DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
				cellValue = formater.format(d);
				cell.setCellValue(cellValue);
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);

		}
		// �ж����ݵ�����
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // ����
			cellValue = String.valueOf(cell.getNumericCellValue());

			break;
		case Cell.CELL_TYPE_STRING: // �ַ���
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // ��ʽ
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // ��ֵ
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // ����
			cellValue = "�Ƿ��ַ�";
			break;

		default:
			cellValue = "δ֪����";
			break;
		}
		return cellValue;
	}


	//ILNIQ
	@SuppressWarnings("resource")
	public static void writer(String path, String fileName, String fileType, List<String[]> list, String titleRow[])throws IOException {
		Workbook wb = null;
		String excelPath = path + File.separator + fileName + "." + fileType;
		File file = new File(excelPath);
		Sheet sheet = null;
		// ���������ĵ�����
		if (!file.exists()) {
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook();

			} else if (fileType.equals("xlsx")) {

				wb = new XSSFWorkbook();
			} else {
				try {
					throw new Exception("�ļ���ʽ����ȷ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// ����sheet����
			sheet = (Sheet) wb.createSheet("sheet1");
			OutputStream outputStream = new FileOutputStream(excelPath);
			wb.write(outputStream);
			outputStream.flush();
			outputStream.close();
			System.out.println("������");
		} else {
			System.out.println("����");
			if (fileType.equals("xls")) {
				wb = new HSSFWorkbook();

			} else if (fileType.equals("xlsx")) {
				wb = new XSSFWorkbook();

			} else {
				try {
					throw new Exception("�ļ���ʽ����ȷ");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// ����sheet����
		if (sheet == null) {
			sheet = (Sheet) wb.createSheet("sheet1");
		}

	/*	// ��ӱ�ͷ
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		row.setHeight((short) 540);
		cell.setCellValue("ʧ����־"); // ������һ��
*/
		CellStyle style = wb.createCellStyle(); // ��ʽ����
		// ���õ�Ԫ��ı�����ɫΪ����ɫ
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// ��ֱ
		style.setAlignment(CellStyle.ALIGN_CENTER);// ˮƽ
		style.setWrapText(true);// ָ������Ԫ��������ʾ����ʱ�Զ�����

		//cell.setCellStyle(style); // ��ʽ������

		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontName("����");
		font.setFontHeight((short) 280);
		style.setFont(font);

		// ��Ԫ��ϲ�
		// �ĸ������ֱ��ǣ���ʼ�У���ʼ�У������У�������
		//sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
		//sheet.autoSizeColumn(5200);

		Row row = sheet.createRow(0); // �����ڶ���
		Cell cell=row.createCell(0);
		for (int i = 0; i < titleRow.length; i++) {
			System.out.println("i:"+i);
			cell = row.createCell(i);
			cell.setCellValue(titleRow[i]);
			//cell.setCellStyle(style); // ��ʽ������
			//sheet.setColumnWidth(i, 20 * 256);
			//sheet.autoSizeColumn(i);
		}
		row.setHeight((short) 540);

		// ѭ��д��������
		for (int i = 0; i < list.size(); i++) {
			row = (Row) sheet.createRow(i + 1);
			row.setHeight((short) 500);
			for (int j = 0; j < list.get(i).length; j++) {
				row.createCell(j).setCellValue(list.get(i)[j]);
			}
		}

		// �����ļ���
		OutputStream stream = new FileOutputStream(excelPath);
		// д������
		wb.write(stream);
		// �ر��ļ���
		stream.close();
	}

	//ɾ��ָ��·�����ļ�




}
