package gov.nysenate.openleg.updates.transcripts.hearing;

import gov.nysenate.openleg.legislation.transcripts.hearing.PublicHearing;
import gov.nysenate.openleg.updates.ContentUpdateEvent;

import java.time.LocalDateTime;
import java.util.Collection;

public class BulkPublicHearingUpdateEvent extends ContentUpdateEvent
{
    protected Collection<PublicHearing> hearings;

    public BulkPublicHearingUpdateEvent(Collection<PublicHearing> hearings, LocalDateTime updateDateTime) {
        super(updateDateTime);
        this.hearings = hearings;
    }

    public Collection<PublicHearing> getPublicHearings() {
        return hearings;
    }
}
