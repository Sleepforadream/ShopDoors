package com.shopdoors.util;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
public class TransactionRunner {

    @Transactional
    public <T> T doInTransaction(Supplier<T> action) {
        return action.get();
    }

    @Transactional
    public void doInTransaction(Runnable action) {
        action.run();
    }
}
