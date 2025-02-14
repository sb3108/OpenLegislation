package gov.nysenate.openleg.api;

import com.google.common.collect.Range;
import gov.nysenate.openleg.common.util.DateUtils;

import java.time.LocalDateTime;

public class DateTimeRangeView implements ViewObject {

    String startDateTime;
    String endDateTime;

    public DateTimeRangeView(Range<LocalDateTime> dateTimeRange) {
        if (dateTimeRange != null) {
            this.startDateTime = DateUtils.startOfDateTimeRange(dateTimeRange).toString();
            this.endDateTime = DateUtils.endOfDateTimeRange(dateTimeRange).toString();
        }
    }

    @Override
    public String getViewType() {
        return "date-time-range";
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }
}
