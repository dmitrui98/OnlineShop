package by.dmitrui98.dao;

import by.dmitrui98.config.DatabaseConfigTest;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
public abstract class BaseDaoImplTest {
    @Autowired
    protected SessionFactory sessionFactory;
}
