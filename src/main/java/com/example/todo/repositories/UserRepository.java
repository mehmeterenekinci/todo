package com.example.todo.repositories;

import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.example.todo.configurations.GetScope;
import com.example.todo.entities.User;

public class UserRepository {

    static final String USERS_COLLECTION_NAME = "users";
    GetScope getScope;
    Collection collection = getScope.getScope("userScope",USERS_COLLECTION_NAME);

    public User save(User user) {

        JsonObject doc = JsonObject.create()
                .put("type", "user")
                .put("firstName", user.getFirstName())
                .put("lastName", user.getLastName())
                .put("password", user.getPassword())
                .put("email", user.getEmail())
                .put("phone", user.getPhone());

        collection.upsert(
                "u:"+user.getPhone(),
                doc
        );

        return user;
    }

    public User findByPhone(String phone) {

        GetResult getResult = collection.get("u:"+phone);
        User user = new User(
                getResult.contentAsObject().getString("firstName"),
                getResult.contentAsObject().getString("lastName"),
                getResult.contentAsObject().getString("password"),
                getResult.contentAsObject().getString("email"),
                getResult.contentAsObject().getString("phone")
        );

        return user;
    }

}