package utilities.pdfGenerator;

import java.io.File;

public class InvoiceGenerator {

    public static final String DEST = "/media/uzair/sal/Software/StoreApp/src/files/pdf/invoice/invoice.pdf";

    public static void main(String[] args) throws Exception {
        createInvoice();
    }

    public static void createInvoice() throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(DEST);
    }


    private static void manipulatePdf(String dest) throws Exception {
        createFile();
        createHeader();
        createCustomerTable();
        createProductTable();
        createFooter();
    }

    private static void createFile() {

    }

    private static void createHeader() {

    }

    private static void createCustomerTable() {

    }

    private static void createProductTable() {

    }

    private static void createFooter() {

    }

}
