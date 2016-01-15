package me.poberherr.transactionservice.handlers;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import me.poberherr.transactionservice.Answer;
import me.poberherr.transactionservice.model.Model;

import org.easymock.EasyMock;
import org.junit.Test;


public class PostTransactionHandlerTest {

    @Test
    public void aTransactionIsCorrectlyCreated() {
        EmptyPayload newTransaction = new EmptyPayload();
        assertTrue(newTransaction.isValid());

        Model model = EasyMock.createMock(Model.class);
        expect(model.createTransaction()).andReturn(Long.valueOf(1));
        replay(model);

        TransactionCreateHandler handler = new TransactionCreateHandler(model);
        assertEquals(new Answer(201, "1"), handler.process(newTransaction, Collections.emptyMap(), false));

        verify(model);
    }
}
