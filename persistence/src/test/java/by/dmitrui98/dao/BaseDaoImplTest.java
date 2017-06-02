package by.dmitrui98.dao;

import by.dmitrui98.config.DatabaseConfigTest;
import by.dmitrui98.util.SessionUtil;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Администратор on 30.05.2017.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DatabaseConfigTest.class})
@EnableTransactionManagement
@Transactional
@Rollback(true)
public abstract class BaseDaoImplTest {
    @Autowired
    protected SessionUtil sessionUtil;
}
