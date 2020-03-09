package de.idealo.mongodb.perf.operations;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import de.idealo.mongodb.perf.MongoDbAccessor;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by kay.agahd on 23.11.16.
 */
public class InsertOperation extends AbstractOperation {

    private final ThreadLocalRandom random;
    private int randomFieldLength = 0;


    public InsertOperation(MongoDbAccessor mongoDbAccessor, String db, String collection, String field) {
        super(mongoDbAccessor, db, collection, field);
        random = ThreadLocalRandom.current();
    }

    @Override
    long executeQuery(int threadId, long threadRunCount, long globalRunCount, long selectorId, long randomId) {
        List<BasicDBObject> basketItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            BasicDBObject basketItem = new BasicDBObject("_id", UUID.randomUUID());
            basketItem.append("Created", LocalDateTime.now())
                    .append("Updated", LocalDateTime.now())
                    .append("Quantity", 1)
                    .append("Price", new BasicDBObject()
                            .append("Amount", 10)
                            .append("Tax", 5))
                    .append("Product", new BasicDBObject("_id", UUID.randomUUID())
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
        DBObject basket = new BasicDBObject("_id", UUID.randomUUID())
                .append("Created", LocalDateTime.now())
                .append("Updated", LocalDateTime.now())
                .append("Version", 0)
                .append("Merchant", new BasicDBObject("_id", UUID.randomUUID())
                        .append("Created", LocalDateTime.now())
                        .append("Updated", LocalDateTime.now()))
                .append("Customer", new BasicDBObject("_id", UUID.randomUUID())
                        .append("Created", LocalDateTime.now())
                        .append("Updated", LocalDateTime.now())
                        .append("EmailAddress", "bilal.islam@hepsiburada.com"))
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


        mongoCollection.insertOne(new Document(basket.toMap()));
        return 1l;
    }

    @Override
    public OperationModes getOperationMode() {
        return OperationModes.INSERT;
    }

    ;

    public void setRandomFieldLength(int randomFieldLength) {
        this.randomFieldLength = randomFieldLength;
    }

    private String generateRandomString(int length) {
        return random.ints(48, 123)
                .filter(i -> (i < 58) || (i > 64 && i < 91) || (i > 96))
                .limit(length)
                .collect(StringBuilder::new, (sb, i) -> sb.append((char) i), StringBuilder::append)
                .toString();
    }

}
