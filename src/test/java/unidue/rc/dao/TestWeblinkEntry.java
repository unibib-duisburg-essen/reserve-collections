package unidue.rc.dao;


import org.apache.cayenne.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unidue.rc.DbTestUtils;
import unidue.rc.model.ReserveCollection;
import unidue.rc.model.WebLink;

/**
 * Created with IntelliJ IDEA. User: mkoesters Date: 20.02.14 Time: 11:08 To change this template use File | Settings |
 * File Templates.
 */
public class TestWeblinkEntry extends Assert {
    private static final Logger LOG = LoggerFactory.getLogger(TestWeblinkEntry.class);


    private EntryDAO entryDAO;

    private DbTestUtils dbTestUtils;
    private ReserveCollection rc;

    @BeforeClass
    public void setup() throws Exception {

        try {

            dbTestUtils = new DbTestUtils();
            dbTestUtils.setupdb();


            entryDAO = new EntryDAOImpl();


            rc = dbTestUtils.createMockReserveCollection("TestA");
        } catch (DatabaseException | ValidationException e) {
            LOG.error("could not setup " + this.getClass().getSimpleName() + " tests: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void shutdown() {

        dbTestUtils.shutdown();
    }


    @Test
    public void testCreateInvalidWeblink() {
        WebLink weblink = new WebLink();
        weblink.setUrl(null);
        try {
            entryDAO.createEntry(weblink, rc);
        } catch (CommitException e) {
            assertTrue(e instanceof CommitException);
        }
    }


    @Test
    public void testCreateValidWeblink() {
        WebLink weblink = null;

        try {
            weblink = dbTestUtils.createMockWeblink(rc);
        } catch (CommitException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        weblink = entryDAO.get(WebLink.class, weblink.getId());
        assertTrue(weblink != null);

    }

}
