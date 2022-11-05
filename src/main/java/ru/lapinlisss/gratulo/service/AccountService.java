package ru.lapinlisss.gratulo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.lapinlisss.gratulo.model.Account;
import ru.lapinlisss.gratulo.repository.AccountRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Optional<Account> findAccount(Account account) {
        return accountRepository.findAccountByLoginAndPassword(account.getLogin(), account.getPassword());
    }

    public Account save(Account account) {
        return accountRepository.saveAndFlush(account);
    }
}
