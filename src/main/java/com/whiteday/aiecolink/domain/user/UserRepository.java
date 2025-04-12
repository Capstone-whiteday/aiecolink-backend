//package com.whiteday.aiecolink.domain.user;
//
//import com.whiteday.aiecolink.domain.user.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface UserRepository extends JpaRepository<User,Long> {
//    @Query("SELECT u FROM User u WHERE u.userId = :userId")
//    User getByName(String userId);
//}
