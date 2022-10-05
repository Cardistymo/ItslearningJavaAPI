package de.cardistymo.itslearningapi.data;

import de.cardistymo.itslearningapi.data.news.lightbulletin.Person;

import java.net.URL;

public class Notification {

    private final URL contentURL;
    private final NotificationType type;
    private final int notificationID;
    private final boolean isAnonymous;
    private final String text;
    private final String publishedDate;
    private final Person publishedBy;
    private final boolean isRead;
    private final URL url;

    public Notification(URL contentURL, NotificationType type, int notificationID, boolean isAnonymous, String text, String publishedDate, Person publishedBy, boolean isRead, URL url) {
        this.contentURL = contentURL;
        this.type = type;
        this.notificationID = notificationID;
        this.isAnonymous = isAnonymous;
        this.text = text;
        this.publishedDate = publishedDate;
        this.publishedBy = publishedBy;
        this.isRead = isRead;
        this.url = url;
    }

    public URL getContentURL() {
        return contentURL;
    }

    public NotificationType getType() {
        return type;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public String getText() {
        return text;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public Person getPublishedBy() {
        return publishedBy;
    }

    public boolean isRead() {
        return isRead;
    }

    public URL getUrl() {
        return url;
    }

    public enum NotificationType {
        UNKNOWN, ASSESSMENT
    }

}
