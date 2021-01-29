package com.test.gianlucas.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.test.gianlucas.model.Movie;
import com.test.gianlucas.model.Pages;
import com.test.gianlucas.model.Profile;
import com.test.gianlucas.model.ProfileMovies;
import com.test.gianlucas.model.Result;
import com.test.gianlucas.model.User;
import com.test.gianlucas.service.ProfileService;
import com.test.gianlucas.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		String email = null;
		ModelAndView mv = new ModelAndView("index");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean hasUserRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("FACEBOOK_USER"));
		if(hasUserRole == true) {
			List<Profile> profiles = profileService.findProfile(name);
			mv.addObject("profiles", profiles);
			mv.addObject(new Profile());
			if(profiles.size() == 4 ) {
				mv.addObject("a", "invisible");
			}
			else {
				mv.addObject("a", "visible");
			}
		}
		else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			email = ((UserDetails) principal).getUsername();
			List<Profile> profiles = profileService.findProfile(email);
			mv.addObject("profiles", profiles);
			mv.addObject(new Profile());
			if(profiles.size() == 4 ) {
				mv.addObject("a", "invisible");
			}
			else {
				mv.addObject("a", "visible");
			}
		}
		
		return mv;
	}
	
	@GetMapping("/criar-perfil")
	public ModelAndView criarPerfil() {
		ModelAndView mv = new ModelAndView("criar-perfil");
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		mv.addObject("users", userService.findByEmail(name));
		mv.addObject(new User());
		return mv;
	}
	
	@PostMapping("/criar-perfil")
	public String salvar(Profile profile) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean hasUserRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("FACEBOOK_USER"));
		if(hasUserRole == true) {
			//verifica a quantidade de perfis facebook user e redireciona
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			List<Profile> profilesFacebook = profileService.findProfile(name);
			if(profilesFacebook.size() == 4 ) {
				
			}
			else {
				profileService.salvar(profile);
			}
		}
		else {
			//verifica a quantidade de perfis user comum e redireciona
			String email = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			email = ((UserDetails) principal).getUsername();
			List<Profile> profiles = profileService.findProfile(email);
			if(profiles.size() == 4 ) {
				
			}
			else {
				profileService.salvar(profile);
			}
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/perfil/{id}")
	//Os valores e nomes mudam de acordo com a forma, se retorna uma lista os nomes dos valores são diferentes do que se retornar só um filme, então criei a result e movie
	public ModelAndView perfil(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("perfil");
		List<Result> moviesListPerfil = new ArrayList<Result>();
		ResponseEntity<Pages> response = restTemplate.getForEntity(
			  "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=3b9539f015061e6646cde93720412a2b&page=1&language=pt-BR", Pages.class);
		Pages movies = response.getBody();
		List<ProfileMovies> watchList = profileService.watchList(id);
		List<ProfileMovies> watchedList = profileService.watchedList(id);
		mv.addObject("movies",movies.getResults());
		mv.addObject("idUser",id);
		
		if(watchList != null) {
			for (int i = 0; i < watchList.size(); i++) {
				Result movieWatchList = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+watchList.get(i).getIdMovie()+"?api_key=3b9539f015061e6646cde93720412a2b&language=pt-BR", Result.class);
				moviesListPerfil.add(movieWatchList);
			}
			mv.addObject("watchList",moviesListPerfil);
		}
		
		if(watchedList != null) {
			Pages a = new Pages();
			for (int i = 0; i < watchedList.size(); i++) {
				Movie Generes = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+watchedList.get(i).getIdMovie()+"?api_key=3b9539f015061e6646cde93720412a2b&language=pt-BR", Movie.class);
				ResponseEntity<Pages> suggested = restTemplate.getForEntity(//coloquei o get(1) para limitar ao primeiro genero do filme
						  "https://api.themoviedb.org/3/discover/movie?api_key=3b9539f015061e6646cde93720412a2b&with_genres="+Generes.getGenres().get(1).getId()+"&language=pt-BR", Pages.class);
				a = suggested.getBody();
				
			}
			mv.addObject("suggestedList",a.getResults());
		}

		return mv;
	}
	
	@GetMapping("/filme/{id}/{idUser}")
	public ModelAndView filme(@PathVariable("id") Long id, @PathVariable("idUser") Long idUser) {
		ModelAndView mv = new ModelAndView("filme");
        Result movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+id+"?api_key=3b9539f015061e6646cde93720412a2b&language=pt-BR", Result.class);
        ProfileMovies conditionMovie = profileService.conditionMovie(id, idUser);
        if(conditionMovie != null) {
        	if( conditionMovie.getWatchList() == true) {
				mv.addObject("botaoAdd", "invisible");
				mv.addObject("botaoOk", "visible");
				mv.addObject("botaoWatched", "invisible");
        	}	
			else if(conditionMovie.getWatchedList() == true) {
					mv.addObject("botaoWatched", "visible");
					mv.addObject("botaoAdd", "invisible");
					mv.addObject("botaoOk", "invisible");
			}
       
        }
        else {
        	mv.addObject("botaoAdd", "visible");
			mv.addObject("botaoWatched", "invisible");
			mv.addObject("botaoOk", "invisible");
        }
        
        mv.addObject("movies", movie);
        mv.addObject("idUser", idUser);
		return mv;
	}
	
	@RequestMapping("/busca")
	public ModelAndView buscar(@RequestParam(value = "search") String search, @RequestParam(value = "id") String idUser) {
		ModelAndView mv = new ModelAndView("busca");
		if(search != null) {
			ResponseEntity<Pages> response = restTemplate.getForEntity(
					  "https://api.themoviedb.org/3/search/movie?api_key=3b9539f015061e6646cde93720412a2b&language=pt-BR&query="+ search +"&page=1&include_adult=false", Pages.class);
			Pages movies = response.getBody();
			mv.addObject("movies", movies.getResults());
			mv.addObject("idUser", idUser);
		}
		
		return mv;
	}
	
	@GetMapping("/add-to-watchlist/{id}/{idUser}")
	public String AddInWatchList(@PathVariable("id") int id, @PathVariable("idUser") int idUser) {
		ProfileMovies profileMovies = new ProfileMovies();
		
		profileMovies.setIdMovie(id);
		profileMovies.setIdUser(idUser);
		profileMovies.setWatchedList(false);
		profileMovies.setWatchList(true);
		profileService.addToWatchList(profileMovies);
	
		return "redirect:/perfil/"+idUser;
	}
	
	@GetMapping("/add-to-watched/{id}/{idUser}")
	public String AddInWatched(@PathVariable("id") int id, @PathVariable("idUser") int idUser) {
		profileService.AddInWatched(id, idUser);
	
		return "redirect:/filme/"+id+"/"+idUser;
	}
}


