package com.lms.Repository;


import com.lms.Dto.UserDTO;
import com.lms.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User,Long> {

    //JPQL-->Java persistence Query language-->(Objects and Attributes)
    //Native sql query-->(columns and tables)
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User u set u.email=:newEmail where u.email=:oldEmail")
    int updateUserEmail(@Param("oldEmail") String oldEmail,@Param("newEmail") String newEmail);


    @Modifying
    @Query("delete from User u where u.id=:id ")
    int deleteCustom(@Param("id") long id);


    @Modifying
    @Query("update User u set u.email= :#{#user.email}," +
            "u.name=:#{#user.name}," +
            "u.age=:#{#user.age}," +
            "u.country=:#{#user.country} where u.id=:#{#user.id}")
    int updateUserDetails(@Param("user") UserDTO userDTO);

    //find student by given name
    //Terminal---> Select * from student where email =email
    //1. JPQL--Dealing with java objects
    //Student(exact class name) class has the variable name as emailId so b.emailId
    // :mail has to passed in the argument of the function exact variable name as in the args

    @Query("select b from User b where b.email=: mail")
    List<User> find_by_mail(String mail);

    //Native sql query -- dealing with sql tables
    //SQL table formed with name student not Student
    //if the variable name is emailId then parameter used in query is email_id
    //Hibernate converts camel case to _ separated names
    //Think of it as a sql table


    @Query(value = "select * from user u where u.email=:mail",nativeQuery = true)
    List<User> findbymail(String mail);


}