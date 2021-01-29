package com.test.gianlucas.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name =  "profiles_movies")
public class ProfileMovies implements Serializable {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		@Column(name = "watchedList")
		private Boolean watchedList;
		
		@Column(name = "watchList")
		private Boolean watchList;
		
		@Column(name = "idUser")
		private int idUser;
		
		@Column(name = "idMovie")
		private int idMovie;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Boolean getWatchedList() {
			return watchedList;
		}

		public void setWatchedList(Boolean watchedList) {
			this.watchedList = watchedList;
		}

		public Boolean getWatchList() {
			return watchList;
		}

		public void setWatchList(Boolean watchList) {
			this.watchList = watchList;
		}

		public int getIdUser() {
			return idUser;
		}

		public void setIdUser(int idUser) {
			this.idUser = idUser;
		}

		public int getIdMovie() {
			return idMovie;
		}

		public void setIdMovie(int idMovie) {
			this.idMovie = idMovie;
		}
		
}
