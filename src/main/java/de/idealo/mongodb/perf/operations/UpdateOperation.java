package de.idealo.mongodb.perf.operations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;
import de.idealo.mongodb.perf.MongoDbAccessor;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kay.agahd on 23.11.16.
 */
public class UpdateOperation extends AbstractOperation {

    public UpdateOperation(MongoDbAccessor mongoDbAccessor, String db, String collection, String field) {
        super(mongoDbAccessor, db, collection, field);
    }

//    @Override
//    long executeQuery(int threadId, long threadRunCount, long globalRunCount, long selectorId, long randomId) {
//        BasicDBObject searchQuery = new BasicDBObject("Customer._id", UUID.fromString("27becf70-8ae7-4d04-8d02-f6a6ed5a76f3"));
//        searchQuery.append("Status", new BasicDBObject("Value", "Active"));
//
//        BasicDBObject updateQuery = new BasicDBObject();
//        updateQuery.append("$set", new BasicDBObject("Customer.EmailAddress", "test"));
//
//        return mongoCollection.updateOne(searchQuery, updateQuery).getModifiedCount();
//    }

    @Override
    long executeQuery(int threadId, long threadRunCount, long globalRunCount, long selectorId, long randomId) {
        BasicDBObject filter = new BasicDBObject("Customer._id", UUID.fromString("e4d91853-f747-4c73-b855-9088500921b1"));
        filter.append("Status", new BasicDBObject("Value", "Active"));

        List<Document> basketItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Document basketItem = new Document("_id", UUID.fromString("eabb2807-06ae-4cf5-9e9d-a9c3f9ede738"));
            basketItem.append("Created", LocalDateTime.now())
                    .append("Updated", LocalDateTime.now())
                    .append("Quantity", 1)
                    .append("Price", new BasicDBObject()
                            .append("Amount", 10)
                            .append("Tax", 5))
                    .append("Product", new BasicDBObject("_id", UUID.fromString("5b1a13d6-834c-4dd4-bca3-b62a0b276549"))
                            .append("Created", LocalDateTime.now())
                            .append("Updated", LocalDateTime.now())
                            .append("Limitation", new BasicDBObject()
                                    .append("InstallmentLimit", "12")
                                    .append("MaximumPurchasableQuantity", "3"))
                            .append("Merchant", new BasicDBObject()
                                    .append("ImageFileName", "9056467419186.jpg")
                                    .append("SecureLinkFormat", "https://productimages.hepsiburada.net/s/9/{size}/9056467419186.jpg"))
                            .append("Metadata", new BasicDBObject()
                                    .append("Sku", "OFISDOPLCAN6398"))
                            .append("Pricing", new BasicDBObject()
                                    .append("SellingPrice", new BasicDBObject()
                                            .append("Amount", 70.2)
                                            .append("Currency", "949")))
                            .append("SellingPriceWithDiscount", null)
                            .append("TaxVatRate", 8.0)
                            .append("UnitPrice", new BasicDBObject()
                                    .append("Amount", 65)
                                    .append("Currency", "949"))
                            .append("Stock", new BasicDBObject()
                                    .append("AvailableStockQuantity", 100)
                                    .append("Freight", 3.0)
                                    .append("WarehouseId", null))
                            .append("Marketplace", new BasicDBObject()
                                    .append("ListingId", UUID.randomUUID()))
                    );

            basketItems.add(basketItem);
        }
        Document basket = new Document("_id", UUID.fromString("95294cfd-aaa8-43c6-be40-8bba968a9c60"))
                .append("Created", LocalDateTime.now())
                .append("Updated", LocalDateTime.now())
                .append("Version", 0)
                .append("Merchant", new BasicDBObject("_id", UUID.fromString("ece6a350-a39c-436b-9e4e-e3fc34e3a1ef"))
                        .append("Created", LocalDateTime.now())
                        .append("Updated", LocalDateTime.now()))
                .append("Customer", new BasicDBObject("_id", UUID.fromString("e4d91853-f747-4c73-b855-9088500921b1"))
                        .append("Created", LocalDateTime.now())
                        .append("Updated", LocalDateTime.now())
                        .append("EmailAddress", "bilal.islam@gmail.com"))
                .append("BasketItems", basketItems)
                .append("TotalPrice", new BasicDBObject()
                        .append("Amount", 20)
                        .append("Currency", "949"))
                .append("TotalTax", new BasicDBObject()
                        .append("Amount", 10)
                        .append("Currency", "949"))
                .append("State", new BasicDBObject()
                        .append("Value", "Created"))
                .append("Status", new BasicDBObject()
                        .append("Value", "Active"));

        return mongoCollection.replaceOne(filter,  basket).getModifiedCount();
    }

    @Override
    public OperationModes getOperationMode() {
        if (IOperation.THREAD_RUN_COUNT.equals(queriedField)) return OperationModes.UPDATE_MANY;
        else return OperationModes.UPDATE_ONE;
    }
}
