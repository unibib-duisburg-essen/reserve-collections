package unidue.rc.dao;


import miless.model.LegalEntity;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unidue.rc.DbTestUtils;
import unidue.rc.system.SystemConfigurationService;
import unidue.rc.system.SystemConfigurationServiceImpl;

import java.io.File;
import java.io.IOException;

/**
 * @author Nils Verheyen
 * @since 08.07.13 11:24
 */
public class TestLegalEntityXMLFileDAO extends Assert {

    private static final Logger LOG = LoggerFactory.getLogger(TestLegalEntityXMLFileDAO.class);

    private String legalEntityBaseDir;

    private File testLegalEntityDir;

    @BeforeClass
    public void setup() throws ConfigurationException, IOException, DatabaseException {

        LOG.info("running " + this.getClass().getName() + " tests");
        DbTestUtils dbTestUtils = new DbTestUtils();
        dbTestUtils.setupdb();
        SystemConfigurationService config = new SystemConfigurationServiceImpl(dbTestUtils.getDatabaseConfiguration(), new SettingDAOImpl());
        legalEntityBaseDir = config.getString("legal.entity.basedir");

        LOG.debug("legal entity base dir: " + legalEntityBaseDir);

        testLegalEntityDir = new File(legalEntityBaseDir, "7890");
        File testLegalEntitySubDir = new File(testLegalEntityDir, "56");

        LOG.debug("test legal entity base dir: " + testLegalEntityDir.getAbsolutePath());
        testLegalEntitySubDir.mkdirs();

        File output = new File(testLegalEntitySubDir, "legalentity_78905678.xml");
        File input = new File(TestLegalEntityXMLFileDAO.class.getResource("legalEntity.xml").getFile());
        FileUtils.copyFile(input, output);
    }

    @AfterClass(alwaysRun = true)
    public void shutdown() {
        LOG.info("Test of " + this.getClass().getName() + " done...");
//        try {
//            FileUtils.forceDelete(testLegalEntityDir);
//        } catch (IOException e) {
//            LOG.error(e.getMessage());
//            if (LOG.isDebugEnabled())
//                e.printStackTrace();
//        }
    }

    @Test
    public void readValid() {
        LegalEntityXMLFileDAO dao = new LegalEntityXMLFileDAO(new File(legalEntityBaseDir), 4, 2, 2);
        LegalEntity legalEntity = dao.getLegalEntityById(78905678);
        assertTrue(legalEntity != null);
    }
}
