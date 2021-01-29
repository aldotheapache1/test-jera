package com.test.gianlucas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.test.gianlucas.model.ProfileMovies;

@Repository
public interface ProfileMoviesRepository extends JpaRepository<ProfileMovies, Long> {
	
    @Query(value = "SELECT * FROM profiles_movies WHERE id_user=:idUser  AND id_movie=:idMovie", nativeQuery = true)
    ProfileMovies findMovieConditionl(@Param("idMovie") Long idMovie, @Param("idUser") Long idUser);

    @Query(value = "SELECT * FROM profiles_movies WHERE id_user=:id AND watch_list=true", nativeQuery = true)
    List<ProfileMovies> findWatchList(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM profiles_movies WHERE id_user=:id AND watched_list=true", nativeQuery = true)
    List<ProfileMovies> findWatchedList(@Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE profiles_movies SET watch_list=false, watched_list=true WHERE id_user=:idUser  AND id_movie=:idMovie", nativeQuery = true)
	void updateToWatched(@Param("idMovie") int id, @Param("idUser") int idUser);

    
    
}
