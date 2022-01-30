package com.example.todo.repositories;

import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.QueryScanConsistency;
import com.example.todo.configurations.GetScope;
import com.example.todo.dto.EventDto;
import com.example.todo.entities.Event;
import com.example.todo.entities.User;

import java.util.ArrayList;
import java.util.List;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;
import static com.example.todo.TodoApplication.cluster;

public class EventRepository {

    static final String EVENT_COLLECTION_NAME = "events";
    GetScope getScope;
    Collection collection = getScope.getScope("eventScope",EVENT_COLLECTION_NAME);

    public Event save(Event event) {

        JsonObject doc = JsonObject.create()
                .put("type", "event")
                .put("date", event.getDate())
                .put("title", event.getTitle())
                .put("message", event.getMessage())
                .put("userPhone", event.getUserPhone())
                .put("isComplete", event.getIsComplete());

        collection.upsert(
                "e:"+ event.getTitle() + "userPhone:" + event.getUserPhone() + "title:" + event.getTitle(),
                doc
        );

        return event;
    }


    public Boolean deleteEvent(EventDto eventDto) {
        try {
            collection.remove("e:"+ eventDto.getTitle() + "userPhone:" + eventDto.getUserPhone() + "title:" + eventDto.getTitle());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Event find(EventDto eventDto) {

        GetResult getResult = collection.get("e:"+ eventDto.getTitle() + "userPhone:" + eventDto.getUserPhone() + "title:" + eventDto.getTitle());

        return new Event(
                getResult.contentAsObject().getString("date"),
                getResult.contentAsObject().getString("title"),
                getResult.contentAsObject().getString("message"),
                getResult.contentAsObject().getString("userPhone"),
                getResult.contentAsObject().getString("isComplete")
        );
    }

    public List<Event> findAll(EventDto eventDto) {

        QueryResult result = cluster.query(
                "SELECT * FROM todo.eventScope e WHERE e.userPhone = $phone",
                queryOptions().parameters(JsonObject.create().put("phone", eventDto.getUserPhone())));

        List<Event> eventList = new ArrayList<>();

        for (JsonObject row : result.rowsAsObject()) {
            eventList.add(new Event(
                    row.getString("date"),
                    row.getString("title"),
                    row.getString("message"),
                    row.getString("userPhone"),
                    row.getString("isComplete")
            ));
        }
        return eventList;
    }
}
