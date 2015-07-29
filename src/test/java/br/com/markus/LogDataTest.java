package br.com.markus;

import br.com.markus.dao.impl.LogDataDAO;
import br.com.markus.enuns.LogTypeEnum;
import br.com.markus.model.LogData;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Markus on 28/07/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:testContext.xml")
public class LogDataTest {

    @Before
    public void setUp() {
        //cleaning database
        MongoClientURI uri = new MongoClientURI(LogDataDAO.MONGODB
                + LogDataDAO.CROSSOVER_USR + ":" + LogDataDAO.CROSSOVER_PWD + LogDataDAO.MONGODB_URL);
        MongoClient client = new MongoClient(uri);
        DB db = client.getDB(uri.getDatabase());
        db.getCollection(LogDataDAO.LOGDATA_COLLECTION_NAME).drop();
        client.close();
    }

    protected LogData createValidLogData() {
        LogData logData = new LogData();
        logData.setAppCode("gu4a");
        logData.setLogType(LogTypeEnum.CSTM_PRDT_VIEW);
        logData.setTimestamp(new Date());
        logData.setDataLogged("Iphone 6");
        return logData;
    }

    @Test
    public void emptyTest(){
        assert true;
    }
}
