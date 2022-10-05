package de.cardistymo.itslearningapi.data;

import java.net.URL;

public class Task {

    private final TaskStatus status;
    private final URL contentUrl;
    private final String elementType;
    private final String description;
    private final int taskID;
    private final String deadline;
    private final int locationID;
    private final String title;
    private final String locationTitle;
    private final int learningToolID;
    private final URL url;
    private final URL iconURL;
    private final String locationType;
    private final String locationFriendlyName;
    private final int elementID;
    private final boolean homework;

    public Task(TaskStatus status, URL contentUrl, String elementType, String description, int taskID, String deadline, int locationID, String title, String locationTitle, int learningToolID, URL url, URL iconURL, String locationType, String locationFriendlyName, int elementID, boolean homework) {
        this.status = status;
        this.contentUrl = contentUrl;
        this.elementType = elementType;
        this.description = description;
        this.taskID = taskID;
        this.deadline = deadline;
        this.locationID = locationID;
        this.title = title;
        this.locationTitle = locationTitle;
        this.learningToolID = learningToolID;
        this.url = url;
        this.iconURL = iconURL;
        this.locationType = locationType;
        this.locationFriendlyName = locationFriendlyName;
        this.elementID = elementID;
        this.homework = homework;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public URL getContentUrl() {
        return contentUrl;
    }

    public String getElementType() {
        return elementType;
    }

    public String getDescription() {
        return description;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getDeadline() {
        return deadline;
    }

    public int getLocationID() {
        return locationID;
    }

    public String getTitle() {
        return title;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public int getLearningToolID() {
        return learningToolID;
    }

    public URL getUrl() {
        return url;
    }

    public URL getIconURL() {
        return iconURL;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getLocationFriendlyName() {
        return locationFriendlyName;
    }

    public int getElementID() {
        return elementID;
    }

    public boolean isHomework() {
        return homework;
    }

    public enum TaskStatus {
        NOT_STARTED, IN_PROGRESS;

        public static TaskStatus fromString(String string) {
            if (string.toLowerCase().contains("progress")) {
                return IN_PROGRESS;
            }
            return NOT_STARTED;
        }
    }

}
