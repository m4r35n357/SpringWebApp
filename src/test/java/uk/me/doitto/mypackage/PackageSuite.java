/**
 * 
 */
package uk.me.doitto.mypackage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author ian
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	uk.me.doitto.mypackage.object.PackageSuite.class,
	uk.me.doitto.mypackage.admin.PackageSuite.class,
    uk.me.doitto.mypackage.mm.PackageSuite.class,
    uk.me.doitto.mypackage.rss.PackageSuite.class,
    uk.me.doitto.mypackage.test.DAOIntegrationTests.class,
    uk.me.doitto.mypackage.test.ServiceIntegrationTests.class
})
public class PackageSuite {
}
