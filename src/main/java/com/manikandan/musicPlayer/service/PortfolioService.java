package com.manikandan.musicPlayer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manikandan.musicPlayer.dao.TransactionCategoryDao;
import com.manikandan.musicPlayer.dao.TransactionHistoryDao;
import com.manikandan.musicPlayer.dao.UsersDao;
import com.manikandan.musicPlayer.dao.UsersProfileDao;
import com.manikandan.musicPlayer.entity.TransactionCategory;
import com.manikandan.musicPlayer.entity.TransactionHistory;
import com.manikandan.musicPlayer.entity.Users;
import com.manikandan.musicPlayer.entity.UsersProfile;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    UsersDao usersDao;

    @Autowired
    TransactionCategoryDao transactionCategoryDao;

    @Autowired
    TransactionHistoryDao transactionHistoryDao;

    @Autowired
    UsersProfileDao usersProfileDao;

    public List<Users> getPortfolio() {
        return usersDao.findAll();
    }


    public Users isUserExists(String userId, String password) {
        List<Users> users = usersDao.findByUserIdAndPassword(userId,password);

        if(users.isEmpty()){
            System.out.println("NOT FOUND");
            return new Users();
        }
        System.out.println(users.get(0));
        return users.get(0);
    }

    public TransactionCategory createTransactionCategory(String category) throws Exception {
        TransactionCategory entity = new TransactionCategory();
        entity.setCategory(category);
        try{
            return transactionCategoryDao.save(entity);
        }catch (Exception e){
            throw new Exception(e);
        }
    }


    public List<TransactionCategory> getTransactionCategories() {
        return transactionCategoryDao.findAll();
    }

    public Users createUser(String userId, String password, Long  phoneNumber) throws Exception {
        List<Users> existingEntity = usersDao.findByUserId(userId);
        try{
            if(existingEntity.isEmpty()){
                Users entity = new Users();
                entity.setUserId(userId);
                entity.setPassword(password);
                entity.setPhoneNumber(phoneNumber);
                System.out.println(entity);
                return usersDao.save(entity);
            }else{
                throw new Exception("Existing user Name");
            }
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public UsersProfile ifProfileExists(String userId) {
        List<UsersProfile> entityList = usersProfileDao.findByUserId(userId);
        if(entityList.isEmpty()){
            System.out.println("NOT FOUND");
            return new UsersProfile();
        }
        System.out.println(entityList.get(0));
        return entityList.get(0);
    }

    public UsersProfile createProfile(String userId, Double currentBalance) throws Exception {
        UsersProfile entity = new UsersProfile();
        entity.setUserId(userId);
        entity.setCurrentBalance(currentBalance);
        try{
            System.out.println(entity);
            return usersProfileDao.save(entity);
        }catch (Exception e){
            System.out.println("The exception occurred: "+ e);
            throw new Exception(e);
        }
    }

    public TransactionHistory createTransactionRecord(String userId, Character transactionType, Double transactionAmount,
                                                      String transactionCategory, String transactionDetail) throws Exception {
        TransactionHistory entity = new TransactionHistory();
        List<TransactionHistory>  entityList= transactionHistoryDao.findByUserId(userId);
        entity.setUserId(userId);
        entity.setTransactionSequence(entityList.size()+1L);
        entity.setTransactionType(transactionType);
        entity.setTransactionAmount(transactionAmount);
        entity.setTransactionCategory(transactionCategory);
        entity.setTransactionDetail(transactionDetail);
        entity.setTransactionTime(new Timestamp(System.currentTimeMillis()));

        if(transactionType == 'C'){
            List<UsersProfile> userProfileEntityList = usersProfileDao.findByUserId(userId);
            UsersProfile userProfileEntity = userProfileEntityList.get(0);
            userProfileEntity.setUserId(userId);
            userProfileEntity.setCurrentBalance(userProfileEntity.getCurrentBalance() + transactionAmount);
            usersProfileDao.save(userProfileEntity);
        }else if (transactionType == 'D'){
            List<UsersProfile> userProfileEntityList = usersProfileDao.findByUserId(userId);
            if(!userProfileEntityList.isEmpty() && userProfileEntityList.get(0).getCurrentBalance() >= transactionAmount){
                UsersProfile userProfileEntity = userProfileEntityList.get(0);
                userProfileEntity.setUserId(userId);
                userProfileEntity.setCurrentBalance(userProfileEntity.getCurrentBalance() - transactionAmount);
                usersProfileDao.save(userProfileEntity);
            }else{
                throw new Exception("Not Available balance");
            }
        }
        try{
            System.out.println(entity);
            return transactionHistoryDao.save(entity);
        }catch (Exception e){
            System.out.println("The exception occurred: "+ e);
            throw new Exception(e);
        }
    }

    public List<TransactionHistory> fetchTransactionHistory(String userId) {
        List<TransactionHistory> entityList = transactionHistoryDao.findByUserIdOrderByTransactionSequenceDesc(userId);
        System.out.println(entityList);
        return entityList;
    }

    public List<TransactionHistory> fetchTransactionChart(String userId, String typeOfChart, Timestamp fromDate, Timestamp toDate) {

//        LocalDateTime fromDateTime = fromDate.toLocalDateTime();
//
//        // Set time part to midnight
//        LocalDateTime fromMidnightDateTime = fromDateTime.toLocalDate().atTime(LocalTime.MIN);
//
//        // Convert LocalDateTime to Timestamp
//        fromDate = Timestamp.from(fromMidnightDateTime.toInstant(ZoneOffset.UTC));
//
//        LocalDateTime toDateTime = toDate.toLocalDateTime();
//
//        // Set time part to midnight
//        LocalDateTime toMidnightDateTime = toDateTime.toLocalDate().atTime(LocalTime.MIN);
//
//        // Convert LocalDateTime to Timestamp
//        toDate = Timestamp.from(fromMidnightDateTime.toInstant(ZoneOffset.UTC));

        List<TransactionHistory> entityList;
        if(typeOfChart.equals("SE") ) {
            entityList = transactionHistoryDao.findByUserIdWithinTheRange(userId, fromDate, toDate);
        }else{
            entityList = transactionHistoryDao.findByUserIdWithinTheRangeAndType(userId,typeOfChart.charAt(0), fromDate, toDate);
        }
        System.out.println(entityList);
        return entityList;
    }

}
