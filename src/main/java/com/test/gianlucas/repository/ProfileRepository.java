package com.test.gianlucas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.test.gianlucas.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
    @Query(value = "SELECT id FROM user WHERE email=:Email", nativeQuery = true)
    String findIdByEmail(@Param("Email") String Email);
    
    @Query(value = "SELECT * FROM profile WHERE user_email=:Id", nativeQuery = true)
    List<Profile> findProfileById(@Param("Id") String Id);
    
}
