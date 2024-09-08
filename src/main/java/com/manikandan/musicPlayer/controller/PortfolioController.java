package com.manikandan.musicPlayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.manikandan.musicPlayer.entity.TransactionCategory;
import com.manikandan.musicPlayer.entity.TransactionHistory;
import com.manikandan.musicPlayer.entity.Users;
import com.manikandan.musicPlayer.entity.UsersProfile;
import com.manikandan.musicPlayer.model.*;
import com.manikandan.musicPlayer.service.PortfolioService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("manikandan")
    public List<Users> manikandan(){
        return portfolioService.getPortfolio();
    }

    @PostMapping("login")
    public Users login(@RequestBody LoginRequest loginRequest) {
        return portfolioService.isUserExists(loginRequest.getUserId(),loginRequest.getPassword());
    }

    @PostMapping("createUser")
    public Users createUser(@RequestBody CreateUserRequest createUserRequest) {
        try{
            return portfolioService.createUser(createUserRequest.getUserId(),
                    createUserRequest.getPassword(), createUserRequest.getPhoneNumber());
        }catch(Exception e){
            return new Users();
        }
    }

    @PostMapping("createTransactionCategory")
    public TransactionCategory createTransactionCategory(@RequestBody CategoryRequest categoryRequest) {
        try{
            return portfolioService.createTransactionCategory(categoryRequest.getCategory());
        }catch(Exception e){
            return new TransactionCategory();
        }
    }

    @GetMapping("fetchTransactionCategory")
    public List<TransactionCategory> fetchTransactionCategory() {
         List<TransactionCategory> categories= portfolioService.getTransactionCategories();
        System.out.println(categories);
        return categories;
    }

    @PostMapping("isProfileExists")
    public UsersProfile isProfileExists(@RequestBody IsProfileExistsRequest isProfileExistsRequest) {
        return portfolioService.ifProfileExists(isProfileExistsRequest.getUserId());
    }

    @PostMapping("createProfile")
    public UsersProfile createProfile(@RequestBody CreateProfileRequest createProfileRequest) {
        try{
            System.out.println("Request: " +createProfileRequest);
            return portfolioService.createProfile(createProfileRequest.getUserId(), createProfileRequest.getCurrentBalance());
        }catch(Exception e){
            return new UsersProfile();
        }
    }

    @PostMapping("createTransactionRecord")
    public TransactionHistory createTransactionRecord(@RequestBody CreateTransactionRecordRequest createTransactionRecordRequest) {
        try{
            System.out.println("Request: " + createTransactionRecordRequest);
            return portfolioService.createTransactionRecord(createTransactionRecordRequest.getUserId(),
                    createTransactionRecordRequest.getTransactionType(), createTransactionRecordRequest.getTransactionAmount(),
                    createTransactionRecordRequest.getTransactionCategory(), createTransactionRecordRequest.getTransactionDetail());
        }catch(Exception e){
            System.out.println("The Exception : "+ e);
            return new TransactionHistory();
        }
    }

    @PostMapping("fetchTransactionHistory")
    public List<TransactionHistory> fetchTransactionHistory(@RequestBody FetchTransactionHistoryRequest fetchTransactionHistoryRequest) {
        try{
            return portfolioService.fetchTransactionHistory(fetchTransactionHistoryRequest.getUserId());
        }catch(Exception e){
            System.out.println("The Exception : "+ e);
            return null;
        }
    }

    @PostMapping("fetchTransactionChartDetails")
    public List<TransactionHistory> fetchTransactionChartDetails(@RequestBody FetchTransactionChartRequest fetchTransactionChartRequest) {
        try{
            System.out.println(fetchTransactionChartRequest);
            return portfolioService.fetchTransactionChart(fetchTransactionChartRequest.getUserId(),
                    fetchTransactionChartRequest.getTypeOfChart(),
                    fetchTransactionChartRequest.getFromDate(), fetchTransactionChartRequest.getToDate());
        }catch(Exception e){
            System.out.println("The Exception : "+ e);
            return null;
        }
    }

}