/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.DAL;

import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Leonardo
 */
/*Interface za popis metoda*/
public interface Repository {

    void deleteAllDataInDataBase() throws Exception;

    boolean checkIfUsernameExists(String userName) throws Exception;

    int createUser(User user) throws Exception;

    String selectUserPasswordForLogIn(String username) throws Exception;

    String selectUserTypeOfRoleForLogIn(String username) throws Exception;

    Optional<Movie> selectMovieByID(int id) throws Exception;

    int createNewMovie(Movie movie) throws Exception;

    int createNewActor(Actor actor) throws Exception;

    int createNewDirector(Director director) throws Exception;

    int createNewGenre(Genre genre) throws Exception;

    void deleteMovie(int id) throws Exception;

    void deleteActor(int id) throws Exception;

    void deleteDirector(int id) throws Exception;

    void deleteGenre(int id) throws Exception;

    List<Movie> selectAllMovies() throws Exception;

    Optional<Actor> selectActorByID(int id) throws Exception;

    List<Actor> selectAllActors() throws Exception;

    Optional<Director> selectDirectorByID(int id) throws Exception;

    List<Director> selectAllDirectors() throws Exception;

    Optional<Genre> selectGenreByID(int id) throws Exception;

    List<Genre> selectAllGenres() throws Exception;

    void updateMovie(int id, Movie movie) throws Exception;

    void updateActor(int id, Actor actor) throws Exception;

    void UpdateDirector(int id, Director director) throws Exception;

    void updateGenre(int id, Genre genre) throws Exception;

    boolean checkIfMovieExists(String title) throws Exception;

    boolean checkIfActorExists(String actorfullname) throws Exception;

    boolean checkIfDirectorExists(String directorfullname) throws Exception;

    boolean checkIfGenreExists(String genrefullname) throws Exception;
    int getMovieIDByTitle(String movieTitle) throws Exception;
    //----------
    int getActorIDByActorFullName(String actorFullName) throws Exception;
    List<Actor> getAllActorsForMovieTitle (String movieTitle) throws Exception;
    void connectActorAndMovie(int idmovie, int idactor) throws Exception;
    boolean chechIfActorAreConnectWithMovie(int idActor) throws Exception;
    void deleteActorAndHimFromAllMovie(int idActor) throws Exception;
    boolean chechIfActorAlreadyOnMovie(int movieID, int actorID) throws Exception;
    void disconnectActorAndMovie(int movieID, int actorID) throws Exception;
    Optional<Actor> selectActorByActorFullName (String actorFullName) throws Exception;
    int createActorAndConnectHimWithMovie(int movieID, Actor actor) throws Exception;
    int checkIfActorExistsAndReturnActorID (Actor actor) throws Exception;
    //----------
    int getDirectorIDByDirectorFullName(String directorFullName) throws Exception;
    List<Director> getAllDirectorsForMovieTitle(String movieTitle) throws Exception;
    void connectDirectorAndMovie(int idmovie, int iddirector) throws Exception;
    boolean chechIfDirectorAreConnectWithMovie(int idDirector) throws Exception;
    void deleteDirectorAndHimFromAllMovie(int idDirector) throws Exception;
    boolean chechIfDirectorAlreadyOnMovie(int movieID, int directorID) throws Exception;
    void disconnectDirectorAndMovie (int movieID, int directorID) throws Exception;
    Optional<Director> selectDirectorByDirectorFullName (String directorFullName) throws Exception;
    int createDirectorAndConnectHimWithMovie(int movieID, Director director) throws Exception;
    int checkIfDirectorExistsAndReturnDirectorID (Director director) throws Exception;
    //----------
    int getGenreIDByGenreFullName(String genreFullName) throws Exception;
    List<Genre> getAllGenresForMovieTitle(String movieTitle) throws Exception;
    void connectGenreAndMovie(int idmovie, int idgenre) throws Exception;
    boolean chechIfGenreAreConnectWithMovie(int idGenre) throws Exception;
    void deleteGenreAndHisFromAllMovie(int idGenre) throws Exception;
    boolean chechIfGenreAlreadyOnMovie(int movieID, int genreID) throws Exception;
    void disconnectGenreAndMovie (int movieID, int genreID) throws Exception;
    Optional<Genre> selectGenreByGenreFullName (String genreFullName) throws Exception;
    int createGenreAndConnectHimWithMovie(int movieID, Genre genre) throws Exception;
    int checkIfGenreExistsAndReturnGenreID (Genre genre) throws Exception;
    
    int selectIDCurrentLogInUserByUserName(String username) throws Exception;
    void ConnectUserIDAndHisFavoriteActorID(int iDUser, int iDActor) throws Exception;
    boolean alredyHaveFavoriteActor(int iDUser) throws Exception;
    void deleteUserFavoriteActor (int iDUser) throws Exception;
    int getActorIDByActorFullNameForConnectInDaD(String actorFullName) throws Exception;
    
    void deleteActorConnectionWithUserBeforeDelete(int actorID) throws Exception;
}
