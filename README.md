# It's Learning API for Java
A complete Java API for the It's Learning platform

##### Quickstart

For full API documentation, please visit the [documentation site](https://alexgustafsson.github.io/itslearning/).

```Java
public class Main {
    public static void main(String[] args) {
        try {
            ItslearningConnector connector = new ItslearningConnector("organisationName", "username", "password");

            HashMap<Integer, Course> courses = connector.getCourses();
            for (Course course : courses.values()) {
                System.out.println(course.getTitle());
            }

            for (Task task : connector.getTasks().values()) {
                if (courses.containsKey(task.getLocationID())) {
                    System.out.println(task.getTitle());
                }
            }

            for (Notification notification : connector.getNotifications().values()) {
                if (notification.getType() != Notification.NotificationType.ASSESSMENT) {
                    System.out.println(notification.getText());
                }
            }

            for (NewsUpdate newsUpdate : connector.getNews().values()) {
                if (newsUpdate.getPublishedDate().isAfter(LocalDateTime.now().minusDays(7))) {
                    System.out.println(newsUpdate.getText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Contributing

Any contribution is welcome. If you're not able to code it yourself, perhaps someone else is - so post an issue if there's anything on your mind.

### Disclaimer

_This project is not in any way affiliated with It's Learning._