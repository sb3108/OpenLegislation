package gov.nysenate.openleg.legislation.transcripts.hearing.dao;

import gov.nysenate.openleg.common.dao.LimitOffset;
import gov.nysenate.openleg.common.dao.SqlBaseDao;
import gov.nysenate.openleg.common.util.FileIOUtils;
import gov.nysenate.openleg.legislation.transcripts.hearing.PublicHearingFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static gov.nysenate.openleg.common.util.DateUtils.toDate;
import static gov.nysenate.openleg.legislation.transcripts.hearing.dao.SqlPublicHearingFileQuery.*;

@Repository
public class SqlFsPublicHearingFileDao extends SqlBaseDao implements PublicHearingFileDao
{
    private static final Logger logger = LoggerFactory.getLogger(SqlFsPublicHearingFileDao.class);

    /** Directory were new PublicHearingFiles come in from external sources. */
    private File incomingPublicHearingDir;

    /** Directory where we store PublicHearingFiles that have been processed. */
    private File archivePublicHearingDir;

    @PostConstruct
    private void init() {
        incomingPublicHearingDir = new File(environment.getStagingDir(), "hearing_transcripts");
        archivePublicHearingDir = new File(environment.getArchiveDir(), "hearing_transcripts");
    }

    /* --- Implemented Methods --- */

    /** {@inheritDoc} */
    @Override
    public List<PublicHearingFile> getIncomingPublicHearingFiles(LimitOffset limOff) throws IOException {
        List<File> files = new ArrayList<>(FileIOUtils.safeListFiles(incomingPublicHearingDir, false, null));
        List<PublicHearingFile> publicHearingFiles = new ArrayList<>();
        for (File file : files)
            publicHearingFiles.add(new PublicHearingFile(file));
        // Sort files for consistent processing.
        Collections.sort(publicHearingFiles);
        return LimitOffset.limitList(publicHearingFiles, limOff);
    }

    /** {@inheritDoc} */
    @Override
    public void updatePublicHearingFile(PublicHearingFile publicHearingFile) {
        MapSqlParameterSource params = getPublicHearingFileParams(publicHearingFile);
        if (jdbcNamed.update(UPDATE_PUBLIC_HEARING_FILE.getSql(schema()), params) == 0) {
            jdbcNamed.update(INSERT_PUBLIC_HEARING_FILE.getSql(schema()), params);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void archivePublicHearingFile(PublicHearingFile publicHearingFile) throws IOException {
        File stagedFile = publicHearingFile.getFile();
        if (stagedFile.getParentFile().equals(incomingPublicHearingDir)) {
            File archiveFile = new File(archivePublicHearingDir, publicHearingFile.getFileName());
            FileIOUtils.moveFile(stagedFile, archiveFile);
            publicHearingFile.setFile(archiveFile);
            publicHearingFile.setArchived(true);
        }
        else {
            throw new FileNotFoundException("PublicHearingFile " + stagedFile + " must be in the incoming" +
                                            "hearings directory in order to be archived.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<PublicHearingFile> getPendingPublicHearingFile(LimitOffset limOff) {
        var temp = jdbcNamed.query(SELECT_PENDING_PUBLIC_HEARING_FILES.getSql(schema()),
                new PublicHearingFileRowMapper());
        Collections.sort(temp);
        return LimitOffset.limitList(temp, limOff);
    }

    /** --- Internal Methods --- */

    private File getFileInArchiveDir(String fileName) {
        return new File(archivePublicHearingDir, fileName);
    }

    private File getFileInIncomingDir(String fileName) {
        return new File(incomingPublicHearingDir, fileName);
    }

    /** --- Helper Classes --- */

    protected class PublicHearingFileRowMapper implements RowMapper<PublicHearingFile> {

        @Override
        public PublicHearingFile mapRow(ResultSet rs, int i) throws SQLException {
            String fileName = rs.getString("filename");
            boolean archived = rs.getBoolean("archived");

            File file = archived ? getFileInArchiveDir(fileName) : getFileInIncomingDir(fileName);
            PublicHearingFile publicHearingFile = null;
            try {
                publicHearingFile = new PublicHearingFile(file);
                publicHearingFile.setProcessedDateTime(getLocalDateTimeFromRs(rs, "processed_date_time"));
                publicHearingFile.setProcessedCount(rs.getInt("processed_count"));
                publicHearingFile.setStagedDateTime(getLocalDateTimeFromRs(rs, "staged_date_time"));
                publicHearingFile.setPendingProcessing(rs.getBoolean("pending_processing"));
                publicHearingFile.setArchived(rs.getBoolean("archived"));
            }
            catch (FileNotFoundException ex) {
                logger.error("PublicHearing File " + fileName + " was not found in the expected location.", ex);
            }

            return publicHearingFile;
        }
    }

    /** --- Param Source Methods --- */

    private MapSqlParameterSource getPublicHearingFileParams(PublicHearingFile publicHearingFile) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fileName", publicHearingFile.getFileName());
        params.addValue("processedDateTime", toDate(publicHearingFile.getProcessedDateTime()));
        params.addValue("processedCount", publicHearingFile.getProcessedCount());
        params.addValue("pendingProcessing", publicHearingFile.isPendingProcessing());
        params.addValue("archived", publicHearingFile.isArchived());
        return params;
    }
}
