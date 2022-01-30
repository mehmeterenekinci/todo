package com.example.todo.configurations;

import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;

import static com.example.todo.TodoApplication.bucket;

public class GetScope {

    public Collection getScope(String scope, String collection) {
        Scope scp = bucket.scope(scope);
        return scp.collection(collection);
    }

}
