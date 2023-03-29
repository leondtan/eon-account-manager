package dev.eon.accountmanager.service;

import dev.eon.accountmanager.entity.BalanceTransaction;
import dev.eon.accountmanager.entity.User;
import dev.eon.accountmanager.exception.GeneralException;
import dev.eon.accountmanager.feedback.ErrorCode;
import dev.eon.accountmanager.model.repository.BalanceTransactionSpecificSummary;
import dev.eon.accountmanager.model.repository.BalanceTransactionSummary;
import dev.eon.accountmanager.model.repository.BalanceTransactionUserSummary;
import dev.eon.accountmanager.model.request.CreateBalanceTransactionRequest;
import dev.eon.accountmanager.model.request.CreateUserRequest;
import dev.eon.accountmanager.repository.BalanceTransactionRepository;
import dev.eon.accountmanager.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class BalanceTransactionServiceImpl implements BalanceTransactionService{

    private final BalanceTransactionRepository _repo;
    private final UserRepository _userRepo;

    public BalanceTransactionServiceImpl(
            BalanceTransactionRepository balanceTransactionRepository,
            UserRepository userRepository
    ) {
        this._repo = balanceTransactionRepository;
        this._userRepo = userRepository;
    }

    @Override
    @SneakyThrows
    public BalanceTransaction save(CreateBalanceTransactionRequest request) {
        User currentUser = null;
        if(request.getUserId() == null) {
            currentUser = _userRepo.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                    .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
        } else {
            currentUser = _userRepo.findById(request.getUserId())
                    .orElseThrow(() -> new GeneralException(ErrorCode.USER_NOT_FOUND));
        }

        BalanceTransaction newBalanceTransaction = new BalanceTransaction(
                currentUser,
                request
        );

        newBalanceTransaction = _repo.save(newBalanceTransaction);

        return newBalanceTransaction;
    }

    @Override
    public List<BalanceTransaction> getByUser(User currentUser, String type) {
        if(type != null && !type.isBlank()) {
            if (type.toLowerCase(Locale.ROOT).equals("loss")) {
                return _repo.getLossWithCurrency(currentUser);
            } else if (type.toLowerCase(Locale.ROOT).equals("profit")) {
                return _repo.getProfitWithCurrency(currentUser);
            }
        }
        return _repo.findByUser(currentUser);
    }

    @Override
    public List<BalanceTransactionSummary> getSummaryByUser(User user) {
        return _repo.getTotalSummary(user);
    }

    @Override
    public List<BalanceTransactionSpecificSummary> getSpecifiedSummaryByUser(User user, String type) {
        return _repo.getSpecificTotalSummary(user, type);
    }

    @Override
    public List<BalanceTransactionUserSummary> getSpecifiedSummary(String type, String period) {
        if(type.equals("loss")) {
            return _repo.getSpecificTotalLossSummary(period);
        } else {
            return _repo.getSpecificTotalProfitSummary(period);
        }
    }
}
