package dev.eon.accountmanager.service;

import dev.eon.accountmanager.entity.BalanceTransaction;
import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.model.repository.BalanceTransactionSpecificSummary;
import dev.eon.accountmanager.model.repository.BalanceTransactionSummary;
import dev.eon.accountmanager.model.repository.BalanceTransactionUserSummary;
import dev.eon.accountmanager.model.request.CreateBalanceTransactionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BalanceTransactionService {
    BalanceTransaction save(CreateBalanceTransactionRequest createBalanceTransactionRequest);

    List<BalanceTransaction> getByUser(User user, String type);

    List<BalanceTransactionSummary> getSummaryByUser(User user);

    List<BalanceTransactionSpecificSummary> getSpecifiedSummaryByUser(User user, String type);

    List<BalanceTransactionUserSummary> getSpecifiedSummary(String type, String period);
}
