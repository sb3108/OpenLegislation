package gov.nysenate.openleg.api.auth;

import gov.nysenate.openleg.api.ViewObject;
import gov.nysenate.openleg.auth.admin.AdminUser;

public class AdminUserView implements ViewObject {

    private String username;
    private boolean active;
    private boolean master;
    private boolean currentSubject;

    public AdminUserView(AdminUser user) {
        this.username = user.getUsername();
        this.active = user.isActive();
        this.master = user.isMaster();
    }

    @Override
    public String getViewType() {
        return "admin-user";
    }

    public String getUsername() {
        return username;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isMaster() {
        return master;
    }
}
