package com.codedifferently.tankofamerica.domain.account;


import com.codedifferently.tankofamerica.domain.account.models.Account;
import com.codedifferently.tankofamerica.domain.account.repos.AccountRepo;
import com.codedifferently.tankofamerica.domain.account.services.AccountService;
import com.codedifferently.tankofamerica.domain.exceptions.AccountNotFoundException;
import com.codedifferently.tankofamerica.domain.exceptions.InvalidDepositException;
import com.codedifferently.tankofamerica.domain.exceptions.UserNotFoundException;
import com.codedifferently.tankofamerica.domain.user.models.User;
import com.codedifferently.tankofamerica.domain.user.repos.UserRepo;
import com.codedifferently.tankofamerica.domain.user.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT + ".enabled=false"
})
@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepo accountRepo;



    @Test
    @DisplayName("Create Account Test")
    public void createAccountTest() throws UserNotFoundException {
        User owner = new User("Dan","Boyce","danielboyce@gamil.com","password");
        Account account = new Account("DanAcc",100.0,owner);
        account.setId(UUID.fromString("ff0ec580-1359-4496-9a32-b3a05b8269fa"));
        BDDMockito.doReturn(account).when(accountRepo).save(account);
        accountService.createAccount(owner.getId(), account);

    }

    @Test
    @DisplayName("Deposit Test")
    public void depositTest() throws InvalidDepositException, AccountNotFoundException {
        User owner = new User("Dan","Boyce","danielboyce@gamil.com","password");
        Account account = new Account("DanAcc",100.0,owner);
        account.setId(UUID.fromString("ff0ec580-1359-4496-9a32-b3a05b8269fa"));
        BDDMockito.doReturn(account).when(accountRepo).save(account);
        accountService.deposit(100.0, UUID.fromString("ff0ec580-1359-4496-9a32-b3a05b8269fa"));
        Double expected = 200.0;
        Double actual = account.getBalance();
        Assertions.assertEquals(expected,actual);
    }


}
