package dev.eon.accountmanager.repository;

import dev.eon.accountmanager.entity.BalanceTransaction;
import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.model.repository.BalanceTransactionSpecificSummary;
import dev.eon.accountmanager.model.repository.BalanceTransactionSummary;
import dev.eon.accountmanager.model.repository.BalanceTransactionUserSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BalanceTransactionRepository extends JpaRepository<BalanceTransaction, UUID> {
    List<BalanceTransaction> findByUser(User user);

    @Query("select bt from BalanceTransaction bt where user = ?1 and (credit * creditRate > debit * debitRate)")
    List<BalanceTransaction> getLossWithCurrency(User user);

    @Query("select bt from BalanceTransaction bt where user = ?1 and (credit * creditRate < debit * debitRate)")
    List<BalanceTransaction> getProfitWithCurrency(User user);

    @Query("select new dev.eon.accountmanager.model.repository.BalanceTransactionSummary(SUM(credit * creditRate), SUM(debit * debitRate)) from BalanceTransaction bt where user = ?1")
    List<BalanceTransactionSummary> getTotalSummary(User user);

    @Query("select new dev.eon.accountmanager.model.repository.BalanceTransactionSpecificSummary(" +
            "(CASE WHEN ?2 = 'month' THEN 'monthly' WHEN ?2 = 'year' THEN 'yearly' ELSE 'daily' END) as g_type, " +
            "(CASE WHEN ?2 = 'month' THEN EXTRACT(MONTH from createdAt) WHEN ?2 = 'year' THEN EXTRACT(YEAR from createdAt) ELSE EXTRACT(DAY from createdAt) END) as g_value, " +
            "SUM(credit * creditRate), SUM(debit * debitRate)) " +
            "from BalanceTransaction bt " +
            "where user = ?1 group by g_value"
    )
    List<BalanceTransactionSpecificSummary> getSpecificTotalSummary(User user, String type);

    @Query("select new dev.eon.accountmanager.model.repository.BalanceTransactionUserSummary(" +
            "(CASE WHEN ?1 = 'month' THEN 'monthly' WHEN ?1 = 'year' THEN 'yearly' ELSE 'daily' END) as g_type, " +
            "(CASE WHEN ?1 = 'month' THEN EXTRACT(MONTH from createdAt) WHEN ?1 = 'year' THEN EXTRACT(YEAR from createdAt) ELSE EXTRACT(DAY from createdAt) END) as g_value, " +
            "SUM(credit * creditRate), SUM(debit * debitRate), user) " +
            "from BalanceTransaction bt " +
            "group by g_value, user_id " +
            "having SUM(credit * credit_rate) < SUM(debit * debit_rate)"
    )
    List<BalanceTransactionUserSummary> getSpecificTotalProfitSummary(String period);

    @Query("select new dev.eon.accountmanager.model.repository.BalanceTransactionUserSummary(" +
            "(CASE WHEN ?1 = 'month' THEN 'monthly' WHEN ?1 = 'year' THEN 'yearly' ELSE 'daily' END) as g_type, " +
            "(CASE WHEN ?1 = 'month' THEN EXTRACT(MONTH from createdAt) WHEN ?1 = 'year' THEN EXTRACT(YEAR from createdAt) ELSE EXTRACT(DAY from createdAt) END) as g_value, " +
            "SUM(credit * creditRate), SUM(debit * debitRate), user) " +
            "from BalanceTransaction bt " +
            "group by g_value, user_id " +
            "having SUM(credit * credit_rate) > SUM(debit * debit_rate)"
    )
    List<BalanceTransactionUserSummary> getSpecificTotalLossSummary(String period);
}
