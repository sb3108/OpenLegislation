package gov.nysenate.openleg.legislation.calendar;

import gov.nysenate.openleg.legislation.bill.Version;
import gov.nysenate.openleg.spotchecks.alert.calendar.CalendarEntryListId;

import java.io.Serializable;

public class CalendarActiveListId extends CalendarId implements Serializable {

    private static final long serialVersionUID = -7109530776473471462L;

    /** A sequence number that identifies this active list */
    private int sequenceNo;

    public CalendarEntryListId toCalendarEntryListId() {
        return new CalendarEntryListId(new CalendarId(this.calNo, this.year), CalendarType.ACTIVE_LIST, Version.ORIGINAL, this.sequenceNo);
    }

    /** --- Constructors --- */

    public CalendarActiveListId(int calNo, int year, int sequenceNo) {
        super(calNo, year);
        this.sequenceNo = sequenceNo;
    }

    public CalendarActiveListId(CalendarId calendarId, int sequenceNo) {
        this(calendarId.getCalNo(), calendarId.getYear(), sequenceNo);
    }

    /** --- Overrides --- */

    @Override
    public String toString() {
        return "#" + calNo + "-" + sequenceNo + " (" + year + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalendarActiveListId)) return false;
        if (!super.equals(o)) return false;

        CalendarActiveListId that = (CalendarActiveListId) o;

        if (sequenceNo != that.sequenceNo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + sequenceNo;
        return result;
    }

    /** --- Getters / Setters --- */

    public int getSequenceNo() {
        return sequenceNo;
    }
}
