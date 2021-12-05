package helper;

import javafx.scene.control.TableView;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExportHelper<T> {

    public ExportHelper() {

    }

    public void excelExport(TableView<T> tableView) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet 1");
        HSSFRow firstRow = sheet.createRow(0);

        // * Set Titles of Columns
        for (int i = 0; i < tableView.getColumns().size(); i++) {
            firstRow.createCell(i).setCellValue(tableView.getColumns().get(i).getText());
        }

        // * Set Table Data to Sheet
        // ? Loop for Rows
        for (int row = 0; row < tableView.getItems().size(); row++) {
            HSSFRow hssfRow = sheet.createRow(row + 1);
            // ? Loop for Columns
            for (int col = 0; col < tableView.getColumns().size(); col++) {
                Object cellValue = tableView.getColumns().get(col).getCellObservableValue(row).getValue();
                try {
                    if (cellValue != null && Double.parseDouble(cellValue.toString()) != 0.0)
                        hssfRow.createCell(col).setCellValue(Double.parseDouble(cellValue.toString()));
                } catch (NumberFormatException e) {
                    hssfRow.createCell(col).setCellValue(cellValue.toString());
                }
            }
        }

        // * Save Excel file and close the book
        try {
            workbook.write(new FileOutputStream("src/files/Excel/WorkBook.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}











