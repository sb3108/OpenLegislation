package gov.nysenate.openleg.api.notification.view;

import gov.nysenate.openleg.api.ViewObject;
import gov.nysenate.openleg.notifications.model.NotificationType;
import gov.nysenate.openleg.notifications.model.RegisteredNotification;

import java.time.LocalDateTime;

public class NotificationSummaryView implements ViewObject{

    protected long id;
    protected NotificationType notificationType;
    protected LocalDateTime occurred;
    protected String summary;

    public NotificationSummaryView(RegisteredNotification notification) {
        this.id = notification.getId();
        this.notificationType = notification.getNotificationType();
        this.occurred = notification.getOccurred();
        this.summary = notification.getSummary();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getOccurred() {
        return occurred;
    }

    public void setOccurred(LocalDateTime occurred) {
        this.occurred = occurred;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String getViewType() {
        return "notification";
    }
}
