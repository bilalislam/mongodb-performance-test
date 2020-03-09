package de.idealo.mongodb.perf.operations;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import de.idealo.mongodb.perf.MongoDbAccessor;

import java.util.UUID;

/**
 * Created by kay.agahd on 23.11.16.
 */
public class UpdateOperation extends AbstractOperation {

    public UpdateOperation(MongoDbAccessor mongoDbAccessor, String db, String collection, String field) {
        super(mongoDbAccessor, db, collection, field);
    }

    @Override
    long executeQuery(int threadId, long threadRunCount, long globalRunCount, long selectorId, long randomId) {
        BasicDBObject searchQuery = new BasicDBObject("Customer._id", UUID.fromString("27becf70-8ae7-4d04-8d02-f6a6ed5a76f3"));
        searchQuery.append("Status", new BasicDBObject("Value", "Active"));

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set", new BasicDBObject("Customer.EmailAddress", "test"));

        return mongoCollection.updateOne(searchQuery, updateQuery).getModifiedCount();
    }

    @Override
    public OperationModes getOperationMode() {
        if (IOperation.THREAD_RUN_COUNT.equals(queriedField)) return OperationModes.UPDATE_MANY;
        else return OperationModes.UPDATE_ONE;
    }
}
