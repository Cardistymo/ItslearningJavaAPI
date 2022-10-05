package de.cardistymo.itslearningapi.data.news;

import de.cardistymo.itslearningapi.data.news.lightbulletin.LightBulletin;
import de.cardistymo.itslearningapi.data.news.lightbulletin.Person;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.TimeZone;

public class NewsUpdate {

    private final URL contentURL;
    private final URL iconUrl;
    private final NewsUpdateType updateType;
    private final int notificationID;
    private final String text;
    private final String locationTitle;
    private final String publishedDate;
    private final Person publisher;
    private final URL url;
    private final int elementID;
    private final LightBulletin lightBulletin;

    public NewsUpdate(URL contentURL, URL iconUrl, NewsUpdateType updateType, int notificationID, String text, String locationTitle, String publishedDate, Person publisher, URL url, int elementID, LightBulletin lightBulletin) {
        this.contentURL = contentURL;
        this.iconUrl = iconUrl;
        this.updateType = updateType;
        this.notificationID = notificationID;
        this.text = text;
        this.locationTitle = locationTitle;
        this.publishedDate = publishedDate;
        this.publisher = publisher;
        this.url = url;
        this.elementID = elementID;
        this.lightBulletin = lightBulletin;
    }

    public URL getContentURL() {
        return contentURL;
    }

    public URL getIconUrl() {
        return iconUrl;
    }

    public NewsUpdateType getUpdateType() {
        return updateType;
    }

    public int getNotificationID() {
        return notificationID;
    }

    public String getText() {
        return text;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public LocalDateTime getPublishedDate() {
        return LocalDateTime.parse(publishedDate.substring(0, publishedDate.length() - 1), DateTimeFormatter.ISO_LOCAL_DATE_TIME).plusHours(2);
    }

    public Person getPublisher() {
        return publisher;
    }

    public URL getUrl() {
        return url;
    }

    public int getElementID() {
        return elementID;
    }

    public LightBulletin getLightBulletin() {
        return lightBulletin;
    }

    public enum NewsUpdateType {
        UNKNOWN, LEARNING_TOOL_ELEMENT, CUSTOM_ACTIVITY;

        public static NewsUpdateType fromString(String string) {
            if (string.toLowerCase().contains("learning")) {
                return LEARNING_TOOL_ELEMENT;
            } else if (string.toLowerCase().contains("custom")) {
                return CUSTOM_ACTIVITY;
            }

            return UNKNOWN;
        }
    }

}
