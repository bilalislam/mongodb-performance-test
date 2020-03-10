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


    // partial_one
    @Override
    long executeQuery(int threadId, long threadRunCount, long globalRunCount, long selectorId, long randomId) {
        BasicDBObject filter = new BasicDBObject("Customer", new BasicDBObject()
                .append("_id", UUID.fromString("58d5df9f-d335-4c8f-911c-b0fbbf5e9e40")));
//        filter.append("Order", new BasicDBObject()
//                .append("OrderNumber", 84772));

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set", new BasicDBObject("Customer", new BasicDBObject()
                .append("EmailAddress", "bilal")));

        return mongoCollection.updateOne(filter, updateQuery).getModifiedCount();
    }

    //replace_one
//    @Override
//    long executeQuery(int threadId, long threadRunCount, long globalRunCount, long selectorId, long randomId) {
//        BasicDBObject filter = new BasicDBObject("Customer._id", UUID.fromString("58d5df9f-d335-4c8f-911c-b0fbbf5e9e40"));
//        filter.append("Order", new BasicDBObject()
//                .append("OrderNumber", 84772));
//
//        int min = 10000;
//        int max = 1000000;
//
//        List<BasicDBObject> basketItems = new ArrayList<>();
//        List<BasicDBObject> pendingApprovalActions = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            BasicDBObject basketItem = new BasicDBObject("_id", UUID.randomUUID());
//            basketItem.append("Created", LocalDateTime.now())
//                    .append("Updated", LocalDateTime.now())
//                    .append("InterestRate", 0.0)
//                    .append("Price", new BasicDBObject()
//                            .append("Amount", 10)
//                            .append("Currency", "949"))
//                    .append("Tax", new BasicDBObject()
//                            .append("Amount", 10)
//                            .append("Currency", "949"));
//
//            basketItems.add(basketItem);
//        }
//
//        BasicDBObject pendingApprovalAction_1 = new BasicDBObject();
//        pendingApprovalAction_1.append("Key", "ThreeDRequired")
//                .append("Value", true)
//                .append("Reasons", "ChoiceOfCustomer");
//
//        BasicDBObject pendingApprovalAction_2 = new BasicDBObject();
//        pendingApprovalAction_2.append("Key", "OtpRequired")
//                .append("Value", "OrderSummary")
//                .append("Reasons", "OtpRequired");
//
//        pendingApprovalActions.add(pendingApprovalAction_1);
//        pendingApprovalActions.add(pendingApprovalAction_2);
//
//        DBObject payment = new BasicDBObject("_id", UUID.randomUUID())
//                .append("Created", LocalDateTime.now())
//                .append("Updated", LocalDateTime.now())
//                .append("Version", 0)
//                .append("Customer", new BasicDBObject("_id", UUID.randomUUID())
//                        .append("Created", LocalDateTime.now())
//                        .append("Updated", LocalDateTime.now())
//                        .append("EmailAddress", "bilal.islam@hepsiburada.com"))
//                .append("Basket", new BasicDBObject()
//                        .append("BasketItems", basketItems)
//                        .append("TotalPrice", new BasicDBObject()
//                                .append("Amount", 20)
//                                .append("Currency", "949"))
//                        .append("TotalTax", new BasicDBObject()
//                                .append("Amount", 10)
//                                .append("Currency", "949")))
//                .append("Delivery", new BasicDBObject("_id", UUID.randomUUID())
//                        .append("Created", LocalDateTime.now())
//                        .append("Updated", LocalDateTime.now())
//                        .append("ShippingPrice", new BasicDBObject()
//                                .append("Amount", 20)
//                                .append("Currency", "949"))
//                        .append("ShippingPriceTax", new BasicDBObject()
//                                .append("Amount", 10)
//                                .append("Currency", "949")))
//                .append("Options", new BasicDBObject()
//                        .append("CreditCard", new BasicDBObject()
//                                .append("PaidAmount", new BasicDBObject()
//                                        .append("Amount", 10)
//                                        .append("Currency", 949))
//                                .append("TemporaryId", UUID.randomUUID())
//                                .append("MaskedNumber", "424242******4242")
//                                .append("HolderName", "Bilal İslam")
//                                .append("Installment", new BasicDBObject().
//                                        append("Count", 1))
//                                .append("Information", new BasicDBObject()
//                                        .append("BankName", "Garanti")
//                                        .append("Family", "Bonus")
//                                        .append("IsCorporate", false)
//                                        .append("IsDebit", false)
//                                        .append("Is3DMust", false)
//                                        .append("IsVft", false)
//                                        .append("VPosName", "GarantiVpos")
//                                        .append("IssuerBankId", null)
//                                        .append("StoredCreditCard", null)
//                                        .append("Vft", null))
//                                .append("InterestRate", new BasicDBObject()
//                                        .append("Price", new BasicDBObject()
//                                                .append("Amount", 20)
//                                                .append("Currency", "949"))
//                                        .append("Tax", new BasicDBObject()
//                                                .append("Amount", 10)
//                                                .append("Currency", "949")))
//                                .append("KeepForFutureUse", false)
//                        )
//                        .append("Transaction", null)
//                        .append("PendingApprovalActions", pendingApprovalActions)
//                )
//                .append("Order", new BasicDBObject()
//                        .append("OrderNumber", (int) (Math.random() * (max - min + 1) + min))
//                        .append("Application", "umut.cakil@hepsiburada.com")
//                        .append("SnapshotId", UUID.randomUUID()))
//                .append("TotalPrice", new BasicDBObject()
//                        .append("Amount", 20)
//                        .append("Currency", "949"))
//                .append("TotalTax", new BasicDBObject()
//                        .append("Amount", 10)
//                        .append("Currency", "949"))
//                .append("State", new BasicDBObject()
//                        .append("Value", "PaymentUpdated"))
//                .append("Status", new BasicDBObject()
//                        .append("Value", "Active"));
//
//        return mongoCollection.replaceOne(filter, new Document(payment.toMap())).getModifiedCount();
//    }

    @Override
    public OperationModes getOperationMode() {
        if (IOperation.THREAD_RUN_COUNT.equals(queriedField)) return OperationModes.UPDATE_MANY;
        else return OperationModes.UPDATE_ONE;
    }
}
