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

        int min = 10000;
        int max = 1000000;

        List<BasicDBObject> basketItems = new ArrayList<>();
        List<BasicDBObject> pendingApprovalActions = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            BasicDBObject basketItem = new BasicDBObject("_id", UUID.randomUUID());
            basketItem.append("Created", LocalDateTime.now())
                    .append("Updated", LocalDateTime.now())
                    .append("InterestRate", 0.0)
                    .append("Price", new BasicDBObject()
                            .append("Amount", 10)
                            .append("Currency", "949"))
                    .append("Tax", new BasicDBObject()
                            .append("Amount", 10)
                            .append("Currency", "949"));

            basketItems.add(basketItem);
        }

        BasicDBObject pendingApprovalAction_1 = new BasicDBObject();
        pendingApprovalAction_1.append("Key", "ThreeDRequired")
                .append("Value", true)
                .append("Reasons", "ChoiceOfCustomer");

        BasicDBObject pendingApprovalAction_2 = new BasicDBObject();
        pendingApprovalAction_2.append("Key", "OtpRequired")
                .append("Value", "OrderSummary")
                .append("Reasons", "OtpRequired");

        pendingApprovalActions.add(pendingApprovalAction_1);
        pendingApprovalActions.add(pendingApprovalAction_2);

        DBObject payment = new BasicDBObject("_id", UUID.randomUUID())
                .append("Created", LocalDateTime.now())
                .append("Updated", LocalDateTime.now())
                .append("Version", 0)
                .append("Customer", new BasicDBObject("_id", UUID.randomUUID())
                        .append("Created", LocalDateTime.now())
                        .append("Updated", LocalDateTime.now())
                        .append("EmailAddress", "bilal.islam@hepsiburada.com"))
                .append("Basket", new BasicDBObject()
                        .append("BasketItems", basketItems)
                        .append("TotalPrice", new BasicDBObject()
                                .append("Amount", 20)
                                .append("Currency", "949"))
                        .append("TotalTax", new BasicDBObject()
                                .append("Amount", 10)
                                .append("Currency", "949")))
                .append("Delivery", new BasicDBObject("_id", UUID.randomUUID())
                        .append("Created", LocalDateTime.now())
                        .append("Updated", LocalDateTime.now())
                        .append("ShippingPrice", new BasicDBObject()
                                .append("Amount", 20)
                                .append("Currency", "949"))
                        .append("ShippingPriceTax", new BasicDBObject()
                                .append("Amount", 10)
                                .append("Currency", "949")))
                .append("Options", new BasicDBObject()
                        .append("CreditCard", new BasicDBObject()
                                .append("PaidAmount", new BasicDBObject()
                                        .append("Amount", 10)
                                        .append("Currency", 949))
                                .append("TemporaryId", UUID.randomUUID())
                                .append("MaskedNumber", "424242******4242")
                                .append("HolderName", "Bilal İslam")
                                .append("Installment", new BasicDBObject().
                                        append("Count", 1))
                                .append("Information", new BasicDBObject()
                                        .append("BankName", "Garanti")
                                        .append("Family", "Bonus")
                                        .append("IsCorporate", false)
                                        .append("IsDebit", false)
                                        .append("Is3DMust", false)
                                        .append("IsVft", false)
                                        .append("VPosName", "GarantiVpos")
                                        .append("IssuerBankId", null)
                                        .append("StoredCreditCard", null)
                                        .append("Vft", null))
                                .append("InterestRate", new BasicDBObject()
                                        .append("Price", new BasicDBObject()
                                                .append("Amount", 20)
                                                .append("Currency", "949"))
                                        .append("Tax", new BasicDBObject()
                                                .append("Amount", 10)
                                                .append("Currency", "949")))
                                .append("KeepForFutureUse", false)
                        )
                        .append("Transaction", null)
                        .append("PendingApprovalActions", pendingApprovalActions)
                )
                .append("Order", new BasicDBObject()
                        .append("OrderNumber", (int) (Math.random() * (max - min + 1) + min))
                        .append("Application", "umut.cakil@hepsiburada.com")
                        .append("SnapshotId", UUID.randomUUID()))
                .append("TotalPrice", new BasicDBObject()
                        .append("Amount", 20)
                        .append("Currency", "949"))
                .append("TotalTax", new BasicDBObject()
                        .append("Amount", 10)
                        .append("Currency", "949"))
                .append("State", new BasicDBObject()
                        .append("Value", "PaymentUpdated"))
                .append("Status", new BasicDBObject()
                        .append("Value", "Active"));


        mongoCollection.insertOne(new Document(payment.toMap()));
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
