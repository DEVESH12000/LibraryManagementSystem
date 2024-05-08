package com.lms.Repository;


import com.lms.Entity.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



@Transactional
public interface CardRepository extends JpaRepository<Card,Long> {

    @Modifying
    @Query(value = "update card c set c.card_status=:new_card_status where c.id in(select card_id from user u  where u.id=:user_id)",nativeQuery = true)
    void deactivateCard(@Param("user_id") long user_id,@Param("new_card_status") String new_card_status);

//    @Modifying
//    @Query("update Card c set c.books=:#{#new_card.books} where c.id=:#{#new_card.id}")
//    int updateCard(@Param("new_card") Card card);
}