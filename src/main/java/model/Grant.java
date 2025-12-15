package model;

import java.math.BigDecimal;
import java.time.Year;

public class Grant {
    private String company;
    private String street;
    private BigDecimal quantity;
    private Year fiscalYear;
    private String businessType;
    private int workPlaces;

    public Grant(String company, String street, BigDecimal quantity,
                 Year fiscalYear, String businessType, int workPlaces) {
        this.company = company;
        this.street = street;
        this.quantity = quantity;
        this.fiscalYear = fiscalYear;
        this.businessType = businessType;
        this.workPlaces = workPlaces;
    }

    public String getCompany() {
        return company;
    }

    public String getStreet() {
        return street;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Year getFiscalYear() {
        return fiscalYear;
    }

    public String getBusinessType() {
        return businessType;
    }

    public int getWorkPlaces() {
        return workPlaces;
    }

    @Override
    public String toString() {
        return "Grant{" +
                "company='" + company + '\'' +
                ", street='" + street + '\'' +
                ", quantity=" + quantity +
                ", fiscalYear=" + fiscalYear +
                ", businessType='" + businessType + '\'' +
                ", workPlaces=" + workPlaces +
                '}';
    }
}
