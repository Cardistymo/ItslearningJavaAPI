package de.cardistymo.itslearningapi.server;

import de.cardistymo.itslearningapi.data.Course;
import de.cardistymo.itslearningapi.data.Notification;
import de.cardistymo.itslearningapi.data.Organisation;
import de.cardistymo.itslearningapi.data.Task;
import de.cardistymo.itslearningapi.data.news.NewsUpdate;
import de.cardistymo.itslearningapi.data.news.lightbulletin.ElementLink;
import de.cardistymo.itslearningapi.data.news.lightbulletin.LightBulletin;
import de.cardistymo.itslearningapi.data.news.lightbulletin.Person;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ItslearningConnector {

    private static final String CLIENT_ID = "10ae9d30-1853-48ff-81cb-47b58a325685";
    private final String ACCESS_TOKEN;
    private final Organisation organisation;

    public ItslearningConnector(String organisationName, String username, String password) {
        try {
            organisation = getOrganisation(organisationName);
            ACCESS_TOKEN = ItslearningRequests.getAccessToken(organisation.getBaseURL() + "/restapi/oauth2/token", CLIENT_ID, username, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Organisation getOrganisation(String organisationName) throws IOException {
        JSONObject jsonObject = ItslearningRequests.execute("https://www.itslearning.com/restapi/sites/all/organisations/search/v1", Map.of("searchText", organisationName));
        JSONArray entityArray = jsonObject.getJSONArray("EntityArray");
        JSONObject schoolObject = entityArray.getJSONObject(0);

        int customerID = schoolObject.getInt("CustomerId");

        JSONObject organisationObject = ItslearningRequests.execute("https://www.itslearning.com/restapi/sites/" + customerID + "/v1", new HashMap<>());

        String stateCode = organisationObject.getString("StateCode");
        String title = organisationObject.getString("Title");
        String shortName = organisationObject.getString("ShortName");
        URL orgApiBaseURL = new URL(organisationObject.getString("OrgApiBaseUrl"));
        int segment = organisationObject.getInt("Segment");
        String cultureName = organisationObject.getString("CultureName");
        boolean showCustomerInDropdownList = organisationObject.getBoolean("ShowCustomerInDropdownList");
        boolean isPersonalRestApiEnabled = organisationObject.getBoolean("IsPersonalRestApiEnabled");
        boolean isFronterUpgradedSite = organisationObject.getBoolean("IsFronterUpgradedSite");
        boolean isParentAppEnabled = organisationObject.getBoolean("IsParentAppEnabled");
        URL baseURL = new URL(organisationObject.getString("BaseUrl"));
        String countryCode = organisationObject.getString("CountryCode");

        return new Organisation(stateCode, title, shortName, customerID, orgApiBaseURL, segment, cultureName, showCustomerInDropdownList, isPersonalRestApiEnabled, isFronterUpgradedSite, isParentAppEnabled, baseURL, countryCode);
    }

    public HashMap<Integer, Course> getCourses() throws IOException {
        JSONObject jsonObject = ItslearningRequests.execute(organisation.getBaseURL() + "/restapi/personal/courses/v1", Map.of("access_token", ACCESS_TOKEN));
        JSONArray jsonArray = jsonObject.getJSONArray("EntityArray");

        HashMap<Integer, Course> courses = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject courseObject = jsonArray.getJSONObject(i);

            String courseColor = courseObject.getString("CourseColor");
            boolean hasAdminPermissions = courseObject.getBoolean("HasAdminPermissions");
            int newBulletinsCount = courseObject.getInt("NewBulletinsCount");
            int courseID = courseObject.getInt("CourseId");
            String friendlyName = Objects.equals(courseObject.get("FriendlyName"), null) ? null : courseObject.getString("FriendlyName");
            String courseFillColor = courseObject.getString("CourseFillColor");
            String title = courseObject.getString("Title");
            String courseCode = courseObject.getString("CourseCode");
            int newNotificationsCount = courseObject.getInt("NewNotificationsCount");
            boolean hasStudentPermissions = courseObject.getBoolean("HasStudentPermissions");
            URL url = new URL(courseObject.getString("Url"));
            String lastUpdatedUtc = courseObject.getString("LastUpdatedUtc");

            courses.put(courseID, new Course(courseColor, hasAdminPermissions, newBulletinsCount, courseID, friendlyName, courseFillColor, title, courseCode, newNotificationsCount, hasStudentPermissions, url, lastUpdatedUtc));
        }

        return courses;
    }

    public HashMap<Integer, Task> getTasks() throws IOException {
        JSONObject jsonObject = ItslearningRequests.execute(organisation.getBaseURL() + "/restapi/personal/tasks/v1", Map.of("access_token", ACCESS_TOKEN));
        JSONArray jsonArray = jsonObject.getJSONArray("EntityArray");

        HashMap<Integer, Task> tasks = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject taskObject = jsonArray.getJSONObject(i);

            Task.TaskStatus status = Task.TaskStatus.fromString(taskObject.getString("Status"));
            URL contentURL = new URL(taskObject.getString("ContentUrl"));
            String elementType = taskObject.getString("ElementType");
            String description = taskObject.getString("Description");
            int taskID = taskObject.getInt("TaskId");
            String deadline = Objects.equals(taskObject.get("Deadline"), null) ? null : taskObject.getString("Deadline");
            int locationID = taskObject.getInt("LocationId");
            String title = taskObject.getString("Title");
            String locationTitle = taskObject.getString("LocationTitle");
            int learningToolID = taskObject.getInt("LearningToolId");
            URL url = new URL(taskObject.getString("Url"));
            URL iconURL = new URL(taskObject.getString("IconUrl"));
            String locationType = taskObject.getString("LocationType");
            String locationFriendlyName = taskObject.getString("LocationFriendlyName");
            int elementID = taskObject.getInt("ElementId");
            boolean homework = taskObject.getBoolean("Homework");

            tasks.put(taskID, new Task(status, contentURL, elementType, description, taskID, deadline, locationID, title, locationTitle, learningToolID, url, iconURL, locationType, locationFriendlyName, elementID, homework));
        }

        return tasks;
    }

    public HashMap<Integer, Notification> getNotifications() throws IOException {
        JSONObject jsonObject = ItslearningRequests.execute(organisation.getBaseURL() + "/restapi/personal/notifications/v1", Map.of("access_token", ACCESS_TOKEN));
        JSONArray jsonArray = jsonObject.getJSONArray("EntityArray");

        System.out.println(jsonArray);

        HashMap<Integer, Notification> notifications = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject notificationObject = jsonArray.getJSONObject(i);

            URL contentURL = new URL(notificationObject.getString("ContentUrl"));
            Notification.NotificationType type = Notification.NotificationType.valueOf(notificationObject.getString("Type").toUpperCase());
            int notificationID = notificationObject.getInt("NotificationId");
            boolean isAnonymous = notificationObject.getBoolean("IsAnonymous");
            String text = notificationObject.getString("Text");
            String publishedDate = notificationObject.getString("PublishedDate");
            Person publishedBy = getPerson(notificationObject.getJSONObject("PublishedBy"));
            boolean isRead = notificationObject.getBoolean("IsRead");
            URL url = new URL(notificationObject.getString("Url"));

            notifications.put(notificationID, new Notification(contentURL, type, notificationID, isAnonymous, text, publishedDate, publishedBy, isRead, url));
        }

        return notifications;
    }

    public HashMap<Integer, NewsUpdate> getNews() throws IOException {
        JSONObject jsonObject = ItslearningRequests.execute(organisation.getBaseURL() + "/restapi/personal/notifications/stream/v1", Map.of("access_token", ACCESS_TOKEN));
        JSONArray jsonArray = jsonObject.getJSONArray("EntityArray");

        HashMap<Integer, NewsUpdate> news = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject notificationObject = jsonArray.getJSONObject(i);

            URL contentURL = new URL(notificationObject.getString("Url"));
            URL iconURL = new URL(notificationObject.getString("IconUrl"));
            NewsUpdate.NewsUpdateType newsUpdateType = NewsUpdate.NewsUpdateType.fromString(notificationObject.getString("ElementType"));
            int notificationID = notificationObject.getInt("NotificationId");
            String text = notificationObject.getString("Text");
            String locationTitle = notificationObject.getString("LocationTitle");
            String publishedDate = notificationObject.getString("PublishedDate");
            Person publisher = getPerson(notificationObject.getJSONObject("PublishedBy"));

            URL url = new URL(notificationObject.getString("Url"));
            int elementID = notificationObject.optInt("ElementId", -i);

            LightBulletin lightBulletin = null;
            if (!Objects.equals(notificationObject.get("LightBulletin"), null)) {
                JSONObject lightBulletinObject = notificationObject.getJSONObject("LightBulletin");
                boolean allowComments = lightBulletinObject.getBoolean("AllowComments");
                URL embedURL = Objects.equals(lightBulletinObject.getString("EmbedUrl"), "") ? null : new URL(lightBulletinObject.getString("EmbedUrl"));
                int lightBulletinID = lightBulletinObject.getInt("LightBulletinId");
                String lightBulletinText = lightBulletinObject.getString("Text");

                ArrayList<ElementLink> elementLinks = new ArrayList<>();
                if (!Objects.equals(lightBulletinObject.get("ElementLinks"), null)) {
                    JSONArray elementLinksArray = lightBulletinObject.getJSONArray("ElementLinks");
                    for (int j = 0; j < elementLinksArray.length(); j++) {
                        JSONObject elementLinkObject = elementLinksArray.getJSONObject(j);

                        URL linkContentURL = new URL(elementLinkObject.getString("ContentUrl"));
                        URL linkIconURL = new URL(elementLinkObject.getString("IconUrl"));
                        ElementLink.ElementLinkType elementLinkType = ElementLink.ElementLinkType.LEARNING_TOOL_ELEMENT;
                        String title = elementLinkObject.getString("Title");
                        int learningToolID = elementLinkObject.getInt("LearningToolId");
                        int linkElementID = elementLinkObject.getInt("ElementId");
                        URL linkURL = new URL(elementLinkObject.getString("Url"));
                        boolean homework = elementLinkObject.getBoolean("Homework");

                        elementLinks.add(new ElementLink(linkContentURL, linkIconURL, elementLinkType, title, learningToolID, linkElementID, linkURL, homework));
                    }
                }

                lightBulletin = new LightBulletin(allowComments, embedURL, lightBulletinID, lightBulletinText, elementLinks);
            }

            news.put(elementID, new NewsUpdate(contentURL, iconURL, newsUpdateType, notificationID, text, locationTitle, publishedDate, publisher, url, elementID, lightBulletin));
        }

        return news;
    }

    private static Person getPerson(JSONObject publishedByObject) throws MalformedURLException {
        String additionalInfo = publishedByObject.getString("AdditionalInfo");
        int personID = publishedByObject.getInt("PersonId");
        String firstName = publishedByObject.getString("FirstName");
        String fullName = publishedByObject.getString("FullName");
        String lastName = publishedByObject.getString("LastName");
        URL profileImageURLSmall = Objects.equals(publishedByObject.get("ProfileImageUrlSmall"), null) ? null : new URL(publishedByObject.getString("ProfileImageUrlSmall"));
        URL profileURL = new URL(publishedByObject.getString("ProfileUrl"));
        URL profileImageURL = Objects.equals(publishedByObject.get("ProfileImageUrl"), null) ? null : new URL(publishedByObject.getString("ProfileImageUrl"));
        return new Person(additionalInfo, personID, firstName, fullName, lastName, profileImageURLSmall, profileURL, profileImageURL);
    }
}
