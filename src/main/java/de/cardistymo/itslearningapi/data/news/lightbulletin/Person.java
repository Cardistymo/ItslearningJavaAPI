package de.cardistymo.itslearningapi.data.news.lightbulletin;

import java.net.URL;

public class Person {

    private final String additionalInfo;
    private final int personID;
    private final String firstName;
    private final String fullName;
    private final String lastName;
    private final URL profileImageURLSmall;
    private final URL profileURL;
    private final URL profileImageURL;

    public Person(String additionalInfo, int personID, String firstName, String fullName, String lastName, URL profileImageURLSmall, URL profileURL, URL profileImageURL) {
        this.additionalInfo = additionalInfo;
        this.personID = personID;
        this.firstName = firstName;
        this.fullName = fullName;
        this.lastName = lastName;
        this.profileImageURLSmall = profileImageURLSmall;
        this.profileURL = profileURL;
        this.profileImageURL = profileImageURL;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public int getPersonID() {
        return personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public URL getProfileImageURLSmall() {
        return profileImageURLSmall;
    }

    public URL getProfileURL() {
        return profileURL;
    }

    public URL getProfileImageURL() {
        return profileImageURL;
    }
}
