package com.dotin.tfbita.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AttributeValueGroupTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AttributeValueGroup getAttributeValueGroupSample1() {
        return new AttributeValueGroup().id(1L).name("name1");
    }

    public static AttributeValueGroup getAttributeValueGroupSample2() {
        return new AttributeValueGroup().id(2L).name("name2");
    }

    public static AttributeValueGroup getAttributeValueGroupRandomSampleGenerator() {
        return new AttributeValueGroup().id(longCount.incrementAndGet()).name(UUID.randomUUID().toString());
    }
}
