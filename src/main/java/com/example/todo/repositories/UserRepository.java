package com.example.todo.repositories;

import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.example.todo.configurations.GetScope;
import com.example.todo.entities.User;
import org.springframework.stereotype.Service;

import static com.example.todo.TodoApplication.bucket;

@Service
public class UserRepository implements IUserRepository{

    static final String USERS_COLLECTION_NAME = "users";

    @Override
    public User save(User user) {

        JsonObject doc = JsonObject.create()
                .put("type", "user")
                .put("firstName", user.getFirstName())
                .put("lastName", user.getLastName())
                .put("password", user.getPassword())
                .put("email", user.getEmail())
                .put("phone", user.getPhone());

        Scope scp = bucket.scope("userScope");
        Collection collection = scp.collection(USERS_COLLECTION_NAME);

        collection.upsert(
                "u:"+user.getPhone(),
                doc
        );

        return user;
    }

    @Override
    public User findByPhone(String phone) {

        Scope scp = bucket.scope("userScope");
        Collection collection = scp.collection(USERS_COLLECTION_NAME);

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