package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DraftReceiptTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static DraftReceipt getDraftReceiptSample1() {
        return new DraftReceipt()
            .id(1L)
            .comment("comment1")
            .customerDeliverDate("customerDeliverDate1")
            .date("date1")
            .deleteDate("deleteDate1")
            .deliverDate("deliverDate1")
            .deliverDuration(1)
            .documentTransactionEffectiveDate("documentTransactionEffectiveDate1")
            .freightLetterDate("freightLetterDate1")
            .freightLetterNumber("freightLetterNumber1")
            .issueDate("issueDate1")
            .receiptDate("receiptDate1")
            .row(1)
            .serial("serial1")
            .transportRow("transportRow1")
            .paymentCurrencyRateTypeDesc("paymentCurrencyRateTypeDesc1")
            .paymentItemTypeDesc("paymentItemTypeDesc1")
            .letterNumberTazirat("letterNumberTazirat1")
            .letterDateTazirat("letterDateTazirat1")
            .deadlineSubmitDocumentDate("deadlineSubmitDocumentDate1");
    }

    public static DraftReceipt getDraftReceiptSample2() {
        return new DraftReceipt()
            .id(2L)
            .comment("comment2")
            .customerDeliverDate("customerDeliverDate2")
            .date("date2")
            .deleteDate("deleteDate2")
            .deliverDate("deliverDate2")
            .deliverDuration(2)
            .documentTransactionEffectiveDate("documentTransactionEffectiveDate2")
            .freightLetterDate("freightLetterDate2")
            .freightLetterNumber("freightLetterNumber2")
            .issueDate("issueDate2")
            .receiptDate("receiptDate2")
            .row(2)
            .serial("serial2")
            .transportRow("transportRow2")
            .paymentCurrencyRateTypeDesc("paymentCurrencyRateTypeDesc2")
            .paymentItemTypeDesc("paymentItemTypeDesc2")
            .letterNumberTazirat("letterNumberTazirat2")
            .letterDateTazirat("letterDateTazirat2")
            .deadlineSubmitDocumentDate("deadlineSubmitDocumentDate2");
    }

    public static DraftReceipt getDraftReceiptRandomSampleGenerator() {
        return new DraftReceipt()
            .id(longCount.incrementAndGet())
            .comment(UUID.randomUUID().toString())
            .customerDeliverDate(UUID.randomUUID().toString())
            .date(UUID.randomUUID().toString())
            .deleteDate(UUID.randomUUID().toString())
            .deliverDate(UUID.randomUUID().toString())
            .deliverDuration(intCount.incrementAndGet())
            .documentTransactionEffectiveDate(UUID.randomUUID().toString())
            .freightLetterDate(UUID.randomUUID().toString())
            .freightLetterNumber(UUID.randomUUID().toString())
            .issueDate(UUID.randomUUID().toString())
            .receiptDate(UUID.randomUUID().toString())
            .row(intCount.incrementAndGet())
            .serial(UUID.randomUUID().toString())
            .transportRow(UUID.randomUUID().toString())
            .paymentCurrencyRateTypeDesc(UUID.randomUUID().toString())
            .paymentItemTypeDesc(UUID.randomUUID().toString())
            .letterNumberTazirat(UUID.randomUUID().toString())
            .letterDateTazirat(UUID.randomUUID().toString())
            .deadlineSubmitDocumentDate(UUID.randomUUID().toString());
    }
}
