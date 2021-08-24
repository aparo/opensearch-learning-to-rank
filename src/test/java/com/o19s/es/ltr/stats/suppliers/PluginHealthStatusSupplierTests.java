package com.o19s.es.ltr.stats.suppliers;

import com.o19s.es.ltr.feature.store.index.IndexFeatureStore;
import org.opensearch.cluster.metadata.IndexNameExpressionResolver;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.util.concurrent.ThreadContext;
import org.opensearch.test.OpenSearchIntegTestCase;
import org.junit.Before;

public class PluginHealthStatusSupplierTests extends OpenSearchIntegTestCase {
    private PluginHealthStatusSupplier pluginHealthStatusSupplier;

    @Before
    public void setup() {
        pluginHealthStatusSupplier =
                new PluginHealthStatusSupplier(clusterService(), new IndexNameExpressionResolver(new ThreadContext(Settings.EMPTY)));
    }

    public void testPluginHealthStatusNoLtrStore() {
        assertEquals("green", pluginHealthStatusSupplier.get());
    }

    public void testPluginHealthStatus() {
        createIndex(IndexFeatureStore.DEFAULT_STORE,
                IndexFeatureStore.DEFAULT_STORE + "_custom1",
                IndexFeatureStore.DEFAULT_STORE + "_custom2");
        flush();
        String status = pluginHealthStatusSupplier.get();
        assertTrue(status.equals("green") || status.equals("yellow"));
    }
}
