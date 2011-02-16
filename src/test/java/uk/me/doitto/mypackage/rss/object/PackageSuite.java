/**
 * 
 */
package uk.me.doitto.mypackage.rss.object;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author ian
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CachedRssFeedTest.class,
        AggregatedFeedTest.class
        })
public class PackageSuite {
}
