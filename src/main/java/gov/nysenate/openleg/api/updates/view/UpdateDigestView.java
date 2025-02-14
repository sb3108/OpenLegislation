package gov.nysenate.openleg.api.updates.view;

import gov.nysenate.openleg.api.ViewObject;
import gov.nysenate.openleg.updates.UpdateDigest;
import org.apache.commons.text.WordUtils;

import java.util.HashMap;
import java.util.Map;

public class UpdateDigestView extends UpdateTokenView implements ViewObject
{
    protected String action;
    protected String scope;
    protected Map<String, String> fields;

    public UpdateDigestView(UpdateDigest<?> updateDigest, ViewObject idView) {
        super(updateDigest, idView);
        if (updateDigest != null) {
            this.action = (updateDigest.getAction() != null) ? WordUtils.capitalizeFully(updateDigest.getAction())
                                                             : updateDigest.getAction();
            this.scope = WordUtils.capitalizeFully(updateDigest.getTable().replaceAll("_", " "));
            this.fields = new HashMap<>();
            if (updateDigest.getFields() != null) {
                for (String key : updateDigest.getFields().keySet()) {
                    String val = updateDigest.getFields().get(key);
                    this.fields.put(WordUtils.capitalizeFully(key, new char[]{'_'}).replace("_", " "), val);
                }
            }
        }
    }

    @Override
    public String getViewType() {
        return "update-digest";
    }

    public String getAction() {
        return action;
    }

    public String getScope() {
        return scope;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public Integer getFieldCount() {
        return this.fields.size();
    }
}
