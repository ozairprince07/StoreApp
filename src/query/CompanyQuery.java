package query;

import database.Const;

public class CompanyQuery {

    public static String getCompanies() {
        return "SELECT * FROM " + Const.TABLE_COMPANY;
    }

    public static String insertCompany(
            String name,
            String contact,
            String address
    ) {
        return "INSERT INTO " + Const.TABLE_COMPANY +
                "(" + Const.COMPANY_NAME + ", " +
                Const.COMPANY_PHONE + ", " +
                Const.COMPANY_ADDRESS +
                ") VALUES('" + name + "', '" + contact + "', '" + address + "' ";
    }

    public static String deleteCompany(int companyId) {
        return "DELETE FROM " + Const.TABLE_COMPANY +
                " WHERE " + Const.COMPANY_ID + " = " + companyId + " ";
    }

    public static String getCompanyId(String contact) {
        return "SELECT " + Const.COMPANY_ID +
                " FROM " + Const.TABLE_COMPANY +
                " WHERE " + Const.COMPANY_PHONE + " = '" + contact + "' ";
    }

}
