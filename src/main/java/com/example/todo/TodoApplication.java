package com.example.todo;

import com.couchbase.client.core.deps.io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import com.couchbase.client.core.env.IoConfig;
import com.couchbase.client.core.env.SecurityConfig;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.manager.query.CreatePrimaryQueryIndexOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;

@SpringBootApplication
public class TodoApplication {

    public static Bucket bucket;
    public static Cluster cluster;

    public static void main(String[] args) {
    	// Update this to your cluster
        String endpoint = "cb.tljqdrpbkdm-bdw.cloud.couchbase.com";
        String bucketName = "todo";
        String username = "TodoApp1#";
        String password = "TodoApp1#";
        // User Input ends here.

        ClusterEnvironment env = ClusterEnvironment.builder()
                .securityConfig(SecurityConfig.enableTls(true)
                        .trustManagerFactory(InsecureTrustManagerFactory.INSTANCE))
                .ioConfig(IoConfig.enableDnsSrv(true))
                .build();

        // Initialize the Connection
        cluster = Cluster.connect(endpoint,ClusterOptions.clusterOptions(username, password).environment(env));
        bucket = cluster.bucket(bucketName);
        bucket.waitUntilReady(Duration.parse("PT10S"));
        Collection collection = bucket.defaultCollection();

        cluster.queryIndexes().createPrimaryIndex(bucketName, CreatePrimaryQueryIndexOptions.createPrimaryQueryIndexOptions().ignoreIfExists(true));

        SpringApplication.run(TodoApplication.class, args);
    }

}
