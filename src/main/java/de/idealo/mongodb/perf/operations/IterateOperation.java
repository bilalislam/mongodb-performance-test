package de.idealo.mongodb.perf.operations;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import de.idealo.mongodb.perf.MongoDbAccessor;
import org.bson.Document;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
//import static com.mongodb.client.model.Filters.in;

/**
 * Created by kay.agahd on 23.11.16.
 */
public class IterateOperation extends AbstractOperation {

    public IterateOperation(MongoDbAccessor mongoDbAccessor, String db, String collection, String field) {
        super(mongoDbAccessor, db, collection, field);
    }

    @Override
    long executeQuery(int threadId, long threadRunCount, long globalRunCount, long selectorId, long randomId) {
        BasicDBObject searchQuery = new BasicDBObject("Customer._id", UUID.fromString("58d5df9f-d335-4c8f-911c-b0fbbf5e9e40"));
        searchQuery.append("Order", new BasicDBObject()
                .append("OrderNumber", 84772));

        //more effective
        //final MongoCursor<Document> cursor = mongoCollection.find(searchQuery).sort(new BasicDBObject("Created", -1)).iterator();
        final MongoCursor<Document> cursor = mongoCollection.find(searchQuery).iterator();
        long result = 0;
        try {
            while (cursor.hasNext()) {
                final Document doc = cursor.next();
                LOG.debug("Document {}", doc.toJson());
                result++;
            }
        } finally {
            cursor.close();
        }

        return result;
    }

    @Override
    public OperationModes getOperationMode() {
        if (IOperation.THREAD_RUN_COUNT.equals(queriedField)) return OperationModes.ITERATE_MANY;
        else return OperationModes.ITERATE_ONE;
    }

    ;

}
