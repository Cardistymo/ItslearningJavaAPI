package de.cardistymo.itslearningapi.data;

import java.net.URL;

public class Course {

    private final String courseColor;
    private final boolean hasAdminPermissions;
    private final int newBulletinsCount;
    private final int courseID;
    private final String friendlyName;
    private final String courseFillColor;
    private final String title;
    private final String courseCode;
    private final int newNotificationsCount;
    private final boolean hasStudentPermissions;
    private final URL url;
    private final String lastUpdatedUtc;

    public Course(String courseColor, boolean hasAdminPermissions, int newBulletinsCount, int courseID, String friendlyName, String courseFillColor, String title, String courseCode, int newNotificationsCount, boolean hasStudentPermissions, URL url, String lastUpdatedUtc) {
        this.courseColor = courseColor;
        this.hasAdminPermissions = hasAdminPermissions;
        this.newBulletinsCount = newBulletinsCount;
        this.courseID = courseID;
        this.friendlyName = friendlyName;
        this.courseFillColor = courseFillColor;
        this.title = title;
        this.courseCode = courseCode;
        this.newNotificationsCount = newNotificationsCount;
        this.hasStudentPermissions = hasStudentPermissions;
        this.url = url;
        this.lastUpdatedUtc = lastUpdatedUtc;
    }

    public String getCourseColor() {
        return courseColor;
    }

    public boolean isHasAdminPermissions() {
        return hasAdminPermissions;
    }

    public int getNewBulletinsCount() {
        return newBulletinsCount;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getCourseFillColor() {
        return courseFillColor;
    }

    public String getTitle() {
        return title;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getNewNotificationsCount() {
        return newNotificationsCount;
    }

    public boolean isHasStudentPermissions() {
        return hasStudentPermissions;
    }

    public URL getUrl() {
        return url;
    }

    public String getLastUpdatedUtc() {
        return lastUpdatedUtc;
    }
}
