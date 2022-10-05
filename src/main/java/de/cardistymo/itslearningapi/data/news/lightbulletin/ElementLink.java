package de.cardistymo.itslearningapi.data.news.lightbulletin;

import java.net.URL;

public class ElementLink {

    private final URL contentURL;
    private final URL iconURL;
    private final ElementLinkType elementLinkType;
    private final String title;
    private final int learningToolID;
    private final int elementID;
    private final URL url;
    private final boolean homework;

    public ElementLink(URL contentURL, URL iconURL, ElementLinkType elementLinkType, String title, int learningToolID, int elementID, URL url, boolean homework) {
        this.contentURL = contentURL;
        this.iconURL = iconURL;
        this.elementLinkType = elementLinkType;
        this.title = title;
        this.learningToolID = learningToolID;
        this.elementID = elementID;
        this.url = url;
        this.homework = homework;
    }

    public URL getContentURL() {
        return contentURL;
    }

    public URL getIconURL() {
        return iconURL;
    }

    public ElementLinkType getElementLinkType() {
        return elementLinkType;
    }

    public String getTitle() {
        return title;
    }

    public int getLearningToolID() {
        return learningToolID;
    }

    public int getElementID() {
        return elementID;
    }

    public URL getUrl() {
        return url;
    }

    public boolean isHomework() {
        return homework;
    }

    public enum ElementLinkType {
        LEARNING_TOOL_ELEMENT;

        public static ElementLinkType fromString(String string) {
            return LEARNING_TOOL_ELEMENT;
        }
    }

}
