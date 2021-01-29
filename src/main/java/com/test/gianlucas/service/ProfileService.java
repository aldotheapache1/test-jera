package com.test.gianlucas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.gianlucas.model.Profile;
import com.test.gianlucas.model.ProfileMovies;
import com.test.gianlucas.repository.ProfileMoviesRepository;
import com.test.gianlucas.repository.ProfileRepository;
import java.util.List;

@Service @Transactional
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private ProfileMoviesRepository profileMoviesRepository;
	
	public List<Profile> findProfile(String email) {
		return profileRepository.findProfileById(profileRepository.findIdByEmail(email));
	}
	
	public void salvar(Profile profile) {
		profileRepository.save(profile);
	}
	
	public void addToWatchList(ProfileMovies profileMovies) {
		profileMoviesRepository.save(profileMovies);
	}
	
	public void AddInWatched( int id, int idUser) {
		profileMoviesRepository.updateToWatched( id, idUser);
	}
	
	public ProfileMovies conditionMovie(Long idMovie, Long idUser) {
		return profileMoviesRepository.findMovieConditionl(idMovie, idUser);
	}
	
	public List<ProfileMovies> watchList(Long id) {
		return profileMoviesRepository.findWatchList(id);
	}
	
	public List<ProfileMovies> watchedList(Long id) {
		return profileMoviesRepository.findWatchedList(id);
	}
}

