package de.cardistymo.itslearningapi.data;

import java.net.URL;

public class Organisation {

    private final String StateCode;
    private final String title;
    private final String shortName;
    private final int customerID;
    private final URL orgApiBaseURL;
    private final int segment;
    private final String cultureName;
    private final boolean showCustomerInDropdownList;
    private final boolean isPersonalRestApiEnabled;
    private final boolean isFronterUpgradedSite;
    private final boolean isParentAppEnabled;
    private final URL baseURL;
    private final String countryCode;

    public Organisation(String stateCode, String title, String shortName, int customerID, URL orgApiBaseURL, int segment, String cultureName, boolean showCustomerInDropdownList, boolean isPersonalRestApiEnabled, boolean isFronterUpgradedSite, boolean isParentAppEnabled, URL baseURL, String countryCode) {
        StateCode = stateCode;
        this.title = title;
        this.shortName = shortName;
        this.customerID = customerID;
        this.orgApiBaseURL = orgApiBaseURL;
        this.segment = segment;
        this.cultureName = cultureName;
        this.showCustomerInDropdownList = showCustomerInDropdownList;
        this.isPersonalRestApiEnabled = isPersonalRestApiEnabled;
        this.isFronterUpgradedSite = isFronterUpgradedSite;
        this.isParentAppEnabled = isParentAppEnabled;
        this.baseURL = baseURL;
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return StateCode;
    }

    public String getTitle() {
        return title;
    }

    public String getShortName() {
        return shortName;
    }

    public int getCustomerID() {
        return customerID;
    }

    public URL getOrgApiBaseURL() {
        return orgApiBaseURL;
    }

    public int getSegment() {
        return segment;
    }

    public String getCultureName() {
        return cultureName;
    }

    public boolean isShowCustomerInDropdownList() {
        return showCustomerInDropdownList;
    }

    public boolean isPersonalRestApiEnabled() {
        return isPersonalRestApiEnabled;
    }

    public boolean isFronterUpgradedSite() {
        return isFronterUpgradedSite;
    }

    public boolean isParentAppEnabled() {
        return isParentAppEnabled;
    }

    public URL getBaseURL() {
        return baseURL;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
