package com.bnitest.springboot.controller;

import com.bnitest.springboot.model.Account;
import com.bnitest.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    // display list of Accounts
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listAccounts", accountService.getAllAccount());
        return "index";
    }

    @GetMapping("/showNewAccountForm")
    public String showNewAccountForm(Model model){
        // create model attribute to bind form data
        Account account = new Account();
        model.addAttribute("account", account);
        return "new_account";
    }

    @PostMapping("/saveAccount")
    public String saveAccount(@ModelAttribute("account") Account account){
        // save account to database
        accountService.saveAccount(account);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value="id") int accNum, Model model){
        // get account from the service
        Account account = accountService.getAccountById(accNum);

        // set account as a model attribute to pre-populate the form
        model.addAttribute("accountUpdate", account);
        return "update_account";
    }

    @GetMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable(value="id") int accNum){
        // call delete account method
        this.accountService.deleteAccountById(accNum);
        return "redirect:/";
    }

    @GetMapping("/showSearchAccountForm")
    public String showSearchAccountForm(Model model){
        // create model attribute to bind form data
        Account account = new Account();
        model.addAttribute("account", account);
        return "search_account";
    }

    @GetMapping("/showData/{id}")
    public Account showData(@PathVariable(value="id") int accNum){
        // get account from the service
        Account account = accountService.getAccountById(accNum);
        return account;
    }

    @PostMapping("/searchAccount")
    public String searchAccount(@ModelAttribute("account") Account account, Model model){
        account = showData(account.getAccNum());
        model.addAttribute("account", account);
        return "detail_account";
    }
}
