package ru.ajana.claim;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Абстрактный интеграционный тест для общих настроект.
 *
 * @author Andrey Kharintsev
 * @date 27.06.2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("/beans-test.xml")
public abstract class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {

}
