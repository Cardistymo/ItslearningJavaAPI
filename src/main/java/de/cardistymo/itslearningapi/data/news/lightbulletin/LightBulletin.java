package de.cardistymo.itslearningapi.data.news.lightbulletin;

import java.net.URL;
import java.util.ArrayList;

public class LightBulletin {

    private final boolean allowComments;
    private final URL embedURL;
    private final int lightBulletinID;
    private final String text;
    private final ArrayList<ElementLink> elementLinks;

    public LightBulletin(boolean allowComments, URL embedURL, int lightBulletinID, String text, ArrayList<ElementLink> elementLinks) {
        this.allowComments = allowComments;
        this.embedURL = embedURL;
        this.lightBulletinID = lightBulletinID;
        this.text = text;
        this.elementLinks = elementLinks;
    }

    public boolean isAllowComments() {
        return allowComments;
    }

    public URL getEmbedURL() {
        return embedURL;
    }

    public int getLightBulletinID() {
        return lightBulletinID;
    }

    public String getText() {
        return text;
    }

    public ArrayList<ElementLink> getElementLinks() {
        return elementLinks;
    }
}
