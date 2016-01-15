package me.poberherr.transactionservice.handlers;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Map;

import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;
import me.poberherr.transactionservice.model.Transaction;

import org.easymock.EasyMock;
import org.easymock.internal.MocksControl;
import org.junit.Test;

import com.google.common.collect.ImmutableList;


public class TransactionIndexHandlerTest {

    @Test
    public void emptyTransactionsIsHandledCorrectly() {
        Model model = EasyMock.createMock(Model.class);
        expect(model.getAllTransactions()).andReturn(Collections.emptyMap());
        replay(model);

        TransactionIndexHandler handler = new TransactionIndexHandler(model);

        // Just found out that this is the proper response for empty resource: ""
        assertEquals(new Answer(204, ""), handler.process(new EmptyPayload(), Collections.emptyMap(), true));

        verify(model);
    }

//    @Test
//    public void aNonEmptyListIsHandledCorrectlyInJsonOutput() {
//        Model model = EasyMock.createMock(Model.class);
//
//        Transaction transaction1 = new Transaction(Long.valueOf(1));
//        transaction1.setAmount(1000);
//        transaction1.setType("cars");
//
//        Transaction transaction2 = new Transaction(Long.valueOf(2));
//        transaction2.setAmount(5000);
//        transaction2.setType("women");
//
//        expect(model.getAllTransactions()).andReturn();
//        replay(model);
//
//        TransactionIndexHandler handler = new TransactionIndexHandler(model);
//        String expectedJson = "[ {\n" +

//                "} ]";
//        assertEquals(new Answer(200, expectedJson), handler.process(new EmptyPayload(), Collections.emptyMap(), false));
//
//        verify(model);
//    }

}
