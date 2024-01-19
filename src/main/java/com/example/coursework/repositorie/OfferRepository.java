package com.example.coursework.repositorie;

import com.example.coursework.model.Offer;
import com.example.coursework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    @Query("SELECT o.description FROM Offer o " +
            "JOIN o.model m " +
            "JOIN m.brand b " +
            "WHERE b.name = :brandName AND m.name = :modelName")
    List<String> findDescriptionsByBrandAndModel(@Param("brandName") String brandName, @Param("modelName") String modelName);


    List<Offer> findAllByOrderByViewCountDesc();
//
//    @Query("SELECT o.description, o.viewCount,u.username AS sellerUsername, b.name AS brandName FROM Offer o JOIN o.seller u JOIN o.model m JOIN m.brand b WHERE LOWER(o.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    List<String> findOffersWithKeyword(@Param("keyword") String keyword);

    List<Offer> findAll();

    List<Offer> findBySeller(User seller);
}
