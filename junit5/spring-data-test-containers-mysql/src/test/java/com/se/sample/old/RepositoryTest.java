package com.se.sample.old;

import com.se.sample.model.DocumentHistory;
import com.se.sample.repository.DocumentHistoryRepository;
import com.se.sample.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ContextConfiguration(classes = HistoryService.class)
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql("/db/jpa/history/schema.sql")
class RepositoryTest extends RepositoryTestBase {
    @Autowired
    private DocumentHistoryRepository historyRepository;


    /**
     * The table will be created as an empty table automatically
     * BEFORE the test case is run.
     * */
    @Test
    void should_insert_and_then_fetch_work() {
        // insert a new record, and its PK will be returned

        DocumentHistory documentHistory = new DocumentHistory();
        String docId = "doc_id";
        String mail = "email@mail.com";
        documentHistory.setDocumentId(docId);
        documentHistory.setDocumentName("doc_name");

        documentHistory.setUserEmail(mail);

        historyRepository.save(documentHistory);

        // do the query by PK
        DocumentHistory record = historyRepository.findByDocumentIdAndUserEmail(docId, mail).get();

        // we expect to see the inserted record
        assertEquals(docId, record.getDocumentId());
        assertEquals(mail, record.getUserEmail());
    }
}