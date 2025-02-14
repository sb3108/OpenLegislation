package gov.nysenate.openleg.search.member;

import com.google.common.collect.Lists;
import gov.nysenate.openleg.api.legislation.member.view.FullMemberView;
import gov.nysenate.openleg.search.ElasticBaseDao;
import gov.nysenate.openleg.common.dao.LimitOffset;
import gov.nysenate.openleg.search.SearchIndex;
import gov.nysenate.openleg.legislation.member.FullMember;
import gov.nysenate.openleg.search.SearchResults;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class ElasticMemberSearchDao extends ElasticBaseDao implements MemberSearchDao
{
    private static final Logger logger = LoggerFactory.getLogger(ElasticMemberSearchDao.class);

    private static final String memberIndexName = SearchIndex.MEMBER.getIndexName();

    /** {@inheritDoc} */
    @Override
    public SearchResults<Integer> searchMembers(QueryBuilder query, QueryBuilder filter, List<SortBuilder<?>> sort, LimitOffset limOff) {
        return search(memberIndexName, query, filter, sort, limOff, this::getMemberIdFromHit);
    }

    /** {@inheritDoc}
     * @param member*/
    @Override
    public void updateMemberIndex(FullMember member) {
        updateMemberIndex(Collections.singletonList(member));
    }

    /** {@inheritDoc}
     * @param members*/
    @Override
    public void updateMemberIndex(Collection<FullMember> members) {
        BulkRequest bulkRequest = new BulkRequest();
        members.stream()
                .map(FullMemberView::new)
                .map(fmv -> getJsonIndexRequest(memberIndexName, String.valueOf(fmv.getMemberId()), fmv))
                .forEach(bulkRequest::add);
        safeBulkRequestExecute(bulkRequest);
    }

    /** {@inheritDoc} */
    @Override
    protected List<String> getIndices() {
        return Lists.newArrayList(memberIndexName);
    }

    private Integer getMemberIdFromHit(SearchHit hit) {
        return Integer.valueOf(hit.getId());
    }
}
