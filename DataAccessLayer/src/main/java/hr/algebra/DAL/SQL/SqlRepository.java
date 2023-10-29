/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.DAL.SQL;

import hr.algebra.DAL.Repository;
import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Leonardo
 */
/*Klasa SQL koja nasljeduje Repository i implementira metode*/
public class SqlRepository implements Repository {

    private static final String DELETE_ALL_DATA_IN_DATA_BASE = "{ CALL DeleteAllFromDatBase }";

    private static final String RESULT = "result";
    private static final String ID_USER = "IDUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ROLE = "TypeOfRole";
    private static final String CHECK_IF_USERNAME_EXISTS = "{ CALL CheckIfUsernameExists (?,?) }";
    private static final String CREATE_USER = "{ CALL CreateUser (?,?,?,?) }";
    private static final String SELECT_USER_PASSWORD_FOR_LOG_IN = "{ CALL SelectUserPasswordForLogIn (?,?) }";
    private static final String SELECT_USER_TYPE_OF_ROLE_FOR_LOG_IN = "{ CALL SelectUserTypeOfRoleForLogIn (?,?) }";
    private static final String SELECT_USER_ID_BY_USERNAME = "{ CALL SelectIDCurrentLogInUserByUserName (?,?) }";
    private static final String CONNECT_USERID_AND_ACTORID_FAVORITE = "{ CALL ConnectUserIDAndHisFavoriteActorID (?,?) }";
    private static final String CHECK_IF_USER_HAVE_FAVORITE_ACTOR = "{ CALL AlredyHaveFavoriteActor (?,?) }";
    private static final String DELETE_USER_FAVORITE_ACTOR = "{ CALL DeleteUserFavoriteActor (?) }";
    private static final String GET_ID_ACTOR_FOR_FAVORITE_CONNECT = "{ CALL GetActorIDByActorFullNameForConnectInDaD (?,?) }";

    private static final int EXISTS_MOVIE = 0;
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUB_DATE = "PubDate";
    private static final String DESCRIPTION = "Description";
    private static final String ORIGINAL_TITLE = "OriginalTitle";
    private static final String DURATION = "Duration";
    private static final String POSTER_PATH = "PosterPath";
    private static final String LINK = "Link";
    private static final String DATE_OF_DISPLAY = "DateOfDisplay";
    private static final String CREATE_NEW_MOVIE = "{ CALL CreateMovie (?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie (?) }";
    private static final String SELECT_MOVIE_BY_ID = "{ CALL SelectMovieByID (?) }";
    private static final String SELECT_ALL_MOVIES = "{ CALL SelectAllMovies }";
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie (?,?,?,?,?,?,?,?,?) }";
    private static final String CHECK_IF_MOVIE_EXISTS = "{ CALL CheckIfMovieExists (?,?) }";
    private static final String GET_MOVIE_ID_BY_TITLE = "{ CALL GetMovieIDByTitle (?,?) }";

    private static final int EXISTS_ACTOR = 0;
    private static final String ID_ACTOR = "IDActor";
    private static final String ACTOR_FULL_NAME = "ActorFullName";
    private static final String CREATE_NEW_ACTOR = "{ CALL CreateNewActor (?,?) }";
    private static final String DELETE_ACTOR = "{ CALL DeleteActor (?) }";
    private static final String SELECT_ACTOR_BY_ID = "{ CALL SelectActorByID (?) }";
    private static final String SELECT_ALL_ACTORS = "{ CALL SelectAllActors }";
    private static final String UPDATE_ACTOR = "{ CALL UpdateActor (?,?) }";
    private static final String CHECK_IF_ACTOR_EXISTS = "{ CALL CheckIfActorExists (?,?) }";
    private static final String GET_ACTOR_ID_BY_ACTOR_FULL_NAME = "{ CALL GetActorIDByActorFullName (?,?) }";
    private static final String GET_ACTOR_FOR_MOVIE_TITLE = "{ CALL GetAllActorsForMovieTitle (?) }";
    private static final String CONNECT_ACTOR_AND_MOVIE = "{ CALL ConnectActorAndMovie (?,?) }";
    private static final String CHECK_IF_ACTOR_CONNECT_WITH_MOVIE = "{ CALL ChechIfActorAreConnectWithMovie (?,?) }";
    private static final String DELETE_ACTOR_AND_ALL_CONNECTION_WITH_HIM = "{ CALL DeleteActorAndHimFromAllMovie (?) }";
    private static final String CHECK_IF_ACTOR_ALREADY_ON_MOVIE = "{ CALL ChechIfActorAlreadyOnMovie (?,?,?) }";
    private static final String DISCONNECT_ACCTOR_AND_MOVIE = "{ CALL DisconnectActorAndMovie (?,?) }";
    private static final String SELECT_ACTOR_BY_ACTOR_FULLNAME = "{ CALL SelectActorByActorFullName (?) }";
    private static final String CREATE_ACTOR_AND_CONNECT_HIM_WITH_MOVIE = "{ CALL CreateActorAndConnectHimWithMovie (?,?,?) }";
    private static final String CHECK_IF_ACTOR_EXISTS_AND_RETURN_ACTOR_ID = "{ CALL CheckIfActorExistsAndReturnActorID (?,?) }";
    private static final String DELETE_ACTOR_CON_BEFORE_DELETE = "{ CALL DeleteActorConnectionWithUserBeforeDelete (?) }";

    private static final int EXISTS_DIRECTOR = 0;
    private static final String ID_DIRECTOR = "IDDirector";
    private static final String DIRECTOR_FULL_NAME = "DirectorFullName";
    private static final String CREATE_NEW_DIRECTOR = "{ CALL CreateNewDirector (?,?) }";
    private static final String DELETE_DIRECTOR = "{ CALL DeleteDirector (?) }";
    private static final String SELECT_DIRECTOR_BY_ID = "{ CALL SelectDirectorByID (?) }";
    private static final String SELECT_ALL_DIRECTORS = "{ CALL SelectAllDirectors }";
    private static final String UPDATE_DIRECTOR = "{ CALL UpdateDirector (?,?) }";
    private static final String CHECK_IF_DIRECTOR_EXISTS = "{ CALL CheckIfDirectorExists (?,?) }";
    private static final String GET_DIRECTOR_ID_BY_DIRECTOR_FULL_NAME = "{ CALL GetDirectorIDByDirectorFullName (?,?) }";
    private static final String GET_DIRECTOR_FOR_MOVIE_TITLE = "{ CALL GetAllDirectorsForMovieTitle (?) }";
    private static final String CONNECT_DIRECTOR_AND_MOVIE = "{ CALL ConnectDirectorAndMovie (?,?) }";
    private static final String CHECK_IF_DIRECTOR_CONNECT_WITH_MOVIE = "{ CALL ChechIfDirectorAreConnectWithMovie (?,?) }"; //For delete
    private static final String DELETE_DIRECTOR_AND_ALL_CONNECTION_WITH_HIM = "{ CALL DeleteDirectorAndHimFromAllMovie (?) }";
    private static final String CHECK_IF_DIRECTOR_ALREADY_ON_MOVIE = "{ CALL ChechIfDirectorAlreadyOnMovie (?,?,?) }"; //For chech connect beetween specific autor and movie
    private static final String DISCONNECT_DIRECTOR_AND_MOVIE = "{ CALL DisconnectDirectorAndMovie (?,?) }";
    private static final String SELECT_DIRECTOR_BY_DIRECTOR_FULLNAME = "{ CALL SelectDirectorByDirectorFullName (?) }";
    private static final String CREATE_DIRECTOR_AND_CONNECT_HIM_WITH_MOVIE = "{ CALL CreateDirectorAndConnectHimWithMovie (?,?,?) }";
    private static final String CHECK_IF_DIRECTOR_EXISTS_AND_RETURN_DIRECTOR_ID = "{ CALL CheckIfDirectorExistsAndReturnDirectorID (?,?) }";

    private static final int EXISTS_GENRE = 0;
    private static final String ID_GENRE = "IDGenre";
    private static final String GENRE_FULL_NAME = "GenreFullName";
    private static final String CREATE_NEW_GENRE = "{ CALL CreateNewGenre (?,?) }";
    private static final String DELETE_GENRE = "{ CALL DeleteGenre (?) }";
    private static final String SELECT_GENRE_BY_ID = "{ CALL SelectGenreByID (?) }";
    private static final String SELECT_ALL_GENRES = "{ CALL SelectAllGenres }";
    private static final String UPDATE_GENRE = "{ CALL UpdateGenre (?,?) }";
    private static final String CHECK_IF_GENRE_EXISTS = "{ CALL CheckIfGenreExists (?,?) }";
    private static final String GET_GENRE_ID_BY_ACTOR_FULL_NAME = "{ CALL GetGenreIDByGenreFullName (?,?) }";
    private static final String GET_GENRE_FOR_MOVIE_TITLE = "{ CALL GetAllGenresForMovieTitle (?) }";
    private static final String CONNECT_GENRE_AND_MOVIE = "{ CALL ConnectGenreAndMovie (?,?) }";
    private static final String CHECK_IF_GENRE_CONNECT_WITH_MOVIE = "{ CALL ChechIfGenreAreConnectWithMovie (?,?) }"; //For delete
    private static final String DELETE_GENRE_AND_ALL_CONNECTION_WITH_HIM = "{ CALL DeleteGenreAndHisFromAllMovie (?) }";
    private static final String CHECK_IF_GENRE_ALREADY_ON_MOVIE = "{ CALL ChechIfGenreAlreadyOnMovie (?,?,?) }"; //For chech connect beetween specific autor and movie
    private static final String DISCONNECT_GENRE_AND_MOVIE = "{ CALL DisconnectGenreAndMovie (?,?) }";
    private static final String SELECT_GENRE_BY_GENRE_FULLNAME = "{ CALL SelectGenreByGenreFullName (?) }";
    private static final String CREATE_GENRE_AND_CONNECT_HIM_WITH_MOVIE = "{ CALL CreateGenreAndConnectHimWithMovie (?,?,?) }";
    private static final String CHECK_IF_GENRE_EXISTS_AND_RETURN_GENRE_ID = "{ CALL CheckIfGenreExistsAndReturnGenreID (?,?) }";

    @Override
    public void deleteAllDataInDataBase() throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ALL_DATA_IN_DATA_BASE)) {
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean checkIfUsernameExists(String userName) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_USERNAME_EXISTS)) {
            stmt.setString(USERNAME, userName);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            stmt.setString(USERNAME, user.getUserName());
            stmt.setString(PASSWORD, user.getPassword());
            stmt.setString(ROLE, user.getRoleType());
            stmt.registerOutParameter(ID_USER, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_USER);
        }
    }

    @Override
    public String selectUserPasswordForLogIn(String username) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER_PASSWORD_FOR_LOG_IN)) {
            stmt.setString(USERNAME, username);
            stmt.registerOutParameter(RESULT, Types.NVARCHAR);
            stmt.executeUpdate();
            return stmt.getString(RESULT);
        }
    }

    @Override
    public String selectUserTypeOfRoleForLogIn(String username) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER_TYPE_OF_ROLE_FOR_LOG_IN)) {
            stmt.setString(USERNAME, username);
            stmt.registerOutParameter(RESULT, Types.NVARCHAR);
            stmt.executeUpdate();
            return stmt.getString(RESULT);
        }
    }

    @Override
    public List<Movie> selectAllMovies() throws Exception {
        List<Movie> movies = new ArrayList<Movie>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ALL_MOVIES); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                movies.add(
                        new Movie(
                                rs.getInt(ID_MOVIE),
                                rs.getString(TITLE),
                                LocalDateTime.parse(rs.getString(PUB_DATE), Movie.FORMAT_FOR_PUB_DATE),
                                rs.getString(DESCRIPTION),
                                rs.getString(ORIGINAL_TITLE),
                                rs.getString(DURATION),
                                rs.getString(POSTER_PATH),
                                rs.getString(LINK),
                                LocalDate.parse(rs.getString(DATE_OF_DISPLAY), Movie.FORMAT_FOR_DATE_OF_DISPLAY)
                        ));
            }
        }
        return movies;
    }

    @Override
    public Optional<Movie> selectMovieByID(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(SELECT_MOVIE_BY_ID)) {

            stmt.setInt(ID_MOVIE, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(rs.getString(PUB_DATE), Movie.FORMAT_FOR_PUB_DATE),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_TITLE),
                            rs.getString(DURATION),
                            rs.getString(POSTER_PATH),
                            rs.getString(LINK),
                            LocalDate.parse(rs.getString(DATE_OF_DISPLAY), Movie.FORMAT_FOR_DATE_OF_DISPLAY)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public int createNewMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_NEW_MOVIE)) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(PUB_DATE, movie.getPubDate().format(Movie.FORMAT_FOR_PUB_DATE));
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
            stmt.setString(DURATION, movie.getDuration());
            stmt.setString(POSTER_PATH, movie.getPosterPath());
            stmt.setString(LINK, movie.getLink());
            stmt.setString(DATE_OF_DISPLAY, movie.getDateOfDisplay().format(Movie.FORMAT_FOR_DATE_OF_DISPLAY));
            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE);
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE);) {
            stmt.setInt(ID_MOVIE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateMovie(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(PUB_DATE, movie.getPubDate().format(Movie.FORMAT_FOR_PUB_DATE));
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
            stmt.setString(DURATION, movie.getDuration());
            stmt.setString(POSTER_PATH, movie.getPosterPath());
            stmt.setString(LINK, movie.getLink());
            stmt.setString(DATE_OF_DISPLAY, movie.getDateOfDisplay().format(Movie.FORMAT_FOR_DATE_OF_DISPLAY));
            stmt.setInt(ID_MOVIE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Actor> selectAllActors() throws Exception {
        List<Actor> actors = new ArrayList<Actor>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ALL_ACTORS); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                actors.add(
                        new Actor(
                                rs.getInt(ID_ACTOR),
                                rs.getString(ACTOR_FULL_NAME)
                        ));
            }
        }
        return actors;
    }

    @Override
    public int createNewActor(Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_NEW_ACTOR)) {
            stmt.setString(ACTOR_FULL_NAME, actor.getActorFullName());
            stmt.registerOutParameter(ID_ACTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_ACTOR);
        }
    }

    @Override
    public void deleteActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ACTOR);) {
            stmt.setInt(ID_ACTOR, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateActor(int id, Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_ACTOR);) {
            stmt.setString(ACTOR_FULL_NAME, actor.getActorFullName());
            stmt.setInt(ID_ACTOR, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public int createNewDirector(Director director) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_NEW_DIRECTOR)) {
            stmt.setString(DIRECTOR_FULL_NAME, director.getDirectorFullName());
            stmt.registerOutParameter(ID_DIRECTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_DIRECTOR);
        }
    }

    @Override
    public void UpdateDirector(int id, Director director) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR);) {
            stmt.setString(DIRECTOR_FULL_NAME, director.getDirectorFullName());
            stmt.setInt(ID_DIRECTOR, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public int createNewGenre(Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_NEW_GENRE)) {
            stmt.setString(GENRE_FULL_NAME, genre.getGenreFullName());
            stmt.registerOutParameter(ID_GENRE, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_GENRE);
        }
    }

    @Override
    public void deleteDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR);) {
            stmt.setInt(ID_DIRECTOR, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Genre> selectGenreByID(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(SELECT_GENRE_BY_ID)) {
            stmt.setInt(ID_GENRE, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Genre(
                            rs.getInt(ID_GENRE),
                            rs.getString(GENRE_FULL_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void deleteGenre(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_GENRE);) {
            stmt.setInt(ID_GENRE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Actor> selectActorByID(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(SELECT_ACTOR_BY_ID)) {

            stmt.setInt(ID_ACTOR, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Actor(
                            rs.getInt(ID_ACTOR),
                            rs.getString(ACTOR_FULL_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Director> selectDirectorByID(int id) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(SELECT_DIRECTOR_BY_ID)) {
            stmt.setInt(ID_DIRECTOR, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Director(
                            rs.getInt(ID_DIRECTOR),
                            rs.getString(DIRECTOR_FULL_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Director> selectAllDirectors() throws Exception {
        List<Director> directors = new ArrayList<Director>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ALL_DIRECTORS); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                directors.add(
                        new Director(
                                rs.getInt(ID_DIRECTOR),
                                rs.getString(DIRECTOR_FULL_NAME)
                        ));
            }
        }
        return directors;
    }

    @Override
    public List<Genre> selectAllGenres() throws Exception {
        List<Genre> genres = new ArrayList<Genre>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ALL_GENRES); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                genres.add(
                        new Genre(
                                rs.getInt(ID_GENRE),
                                rs.getString(GENRE_FULL_NAME)
                        ));
            }
        }
        return genres;
    }

    @Override
    public void updateGenre(int id, Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_GENRE);) {
            stmt.setString(GENRE_FULL_NAME, genre.getGenreFullName());
            stmt.setInt(ID_GENRE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean checkIfMovieExists(String title) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_MOVIE_EXISTS)) {
            stmt.setString(TITLE, title);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public boolean checkIfActorExists(String actorfullname) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_ACTOR_EXISTS)) {
            stmt.setString(ACTOR_FULL_NAME, actorfullname);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public boolean checkIfDirectorExists(String directorfullname) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_DIRECTOR_EXISTS)) {
            stmt.setString(DIRECTOR_FULL_NAME, directorfullname);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }

    }

    @Override
    public boolean checkIfGenreExists(String genrefullname) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_GENRE_EXISTS)) {
            stmt.setString(GENRE_FULL_NAME, genrefullname);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public int getMovieIDByTitle(String movieTitle) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_MOVIE_ID_BY_TITLE)) {
            stmt.setString(TITLE, movieTitle);
            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE);
        }
    }

    @Override
    public int getActorIDByActorFullName(String actorFullName) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_ACTOR_ID_BY_ACTOR_FULL_NAME)) {
            stmt.setString(ACTOR_FULL_NAME, actorFullName);
            stmt.registerOutParameter(ID_ACTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_ACTOR);
        }
    }

    @Override
    public List<Actor> getAllActorsForMovieTitle(String movieTitle) throws Exception {
        List<Actor> actors = new ArrayList<Actor>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_ACTOR_FOR_MOVIE_TITLE)) {
            stmt.setString(TITLE, movieTitle);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                actors.add(
                        new Actor(
                                rs.getInt(ID_ACTOR),
                                rs.getString(ACTOR_FULL_NAME)
                        )
                );
            }
        }
        return actors;
    }

    @Override
    public void connectActorAndMovie(int idmovie, int idactor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CONNECT_ACTOR_AND_MOVIE)) {
            stmt.setInt(ID_MOVIE, idmovie);
            stmt.setInt(ID_ACTOR, idactor);
            stmt.execute();
        }
    }

    @Override
    public boolean chechIfActorAreConnectWithMovie(int idActor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_ACTOR_CONNECT_WITH_MOVIE)) {
            stmt.setInt(ID_ACTOR, idActor);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public void deleteActorAndHimFromAllMovie(int idActor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ACTOR_AND_ALL_CONNECTION_WITH_HIM)) {
            stmt.setInt(ID_ACTOR, idActor);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean chechIfActorAlreadyOnMovie(int idMovie, int idActor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_ACTOR_ALREADY_ON_MOVIE)) {
            stmt.setInt(ID_MOVIE, idMovie);
            stmt.setInt(ID_ACTOR, idActor);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public void disconnectActorAndMovie(int movieID, int actorID) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DISCONNECT_ACCTOR_AND_MOVIE)) {
            stmt.setInt(ID_MOVIE, movieID);
            stmt.setInt(ID_ACTOR, actorID);
            stmt.executeUpdate();
        }
    }

    @Override
    public int getDirectorIDByDirectorFullName(String directorFullName) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_DIRECTOR_ID_BY_DIRECTOR_FULL_NAME)) {
            stmt.setString(DIRECTOR_FULL_NAME, directorFullName);
            stmt.registerOutParameter(ID_DIRECTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_DIRECTOR);
        }
    }

    @Override
    public List<Director> getAllDirectorsForMovieTitle(String movieTitle) throws Exception {
        List<Director> directors = new ArrayList<Director>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_DIRECTOR_FOR_MOVIE_TITLE)) {
            stmt.setString(TITLE, movieTitle);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                directors.add(
                        new Director(
                                rs.getInt(ID_DIRECTOR),
                                rs.getString(DIRECTOR_FULL_NAME)
                        )
                );
            }
        }
        return directors;
    }

    @Override
    public void connectDirectorAndMovie(int idmovie, int iddirector) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CONNECT_DIRECTOR_AND_MOVIE)) {
            stmt.setInt(ID_MOVIE, idmovie);
            stmt.setInt(ID_DIRECTOR, iddirector);
            stmt.execute();
        }
    }

    @Override
    public boolean chechIfDirectorAreConnectWithMovie(int idDirector) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_DIRECTOR_CONNECT_WITH_MOVIE)) {
            stmt.setInt(ID_DIRECTOR, idDirector);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public void deleteDirectorAndHimFromAllMovie(int idDirector) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR_AND_ALL_CONNECTION_WITH_HIM)) {
            stmt.setInt(ID_DIRECTOR, idDirector);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean chechIfDirectorAlreadyOnMovie(int movieID, int directorID) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_DIRECTOR_ALREADY_ON_MOVIE)) {
            stmt.setInt(ID_MOVIE, movieID);
            stmt.setInt(ID_DIRECTOR, directorID);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public void disconnectDirectorAndMovie(int movieID, int directorID) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DISCONNECT_DIRECTOR_AND_MOVIE)) {
            stmt.setInt(ID_MOVIE, movieID);
            stmt.setInt(ID_DIRECTOR, directorID);
            stmt.executeUpdate();
        }
    }

    @Override
    public int getGenreIDByGenreFullName(String genreFullName) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_GENRE_ID_BY_ACTOR_FULL_NAME)) {
            stmt.setString(GENRE_FULL_NAME, genreFullName);
            stmt.registerOutParameter(ID_GENRE, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_GENRE);
        }
    }

    @Override
    public List<Genre> getAllGenresForMovieTitle(String movieTitle) throws Exception {
        List<Genre> genres = new ArrayList<Genre>();
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(GET_GENRE_FOR_MOVIE_TITLE)) {
            stmt.setString(TITLE, movieTitle);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                genres.add(
                        new Genre(
                                rs.getInt(ID_GENRE),
                                rs.getString(GENRE_FULL_NAME)
                        )
                );
            }
        }
        return genres;
    }

    @Override
    public void connectGenreAndMovie(int idmovie, int idgenre) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CONNECT_GENRE_AND_MOVIE)) {
            stmt.setInt(ID_MOVIE, idmovie);
            stmt.setInt(ID_GENRE, idgenre);
            stmt.execute();
        }
    }

    @Override
    public boolean chechIfGenreAreConnectWithMovie(int idGenre) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_GENRE_CONNECT_WITH_MOVIE)) {
            stmt.setInt(ID_GENRE, idGenre);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public void deleteGenreAndHisFromAllMovie(int idGenre) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_GENRE_AND_ALL_CONNECTION_WITH_HIM)) {
            stmt.setInt(ID_GENRE, idGenre);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean chechIfGenreAlreadyOnMovie(int movieID, int genreID) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CHECK_IF_GENRE_ALREADY_ON_MOVIE)) {
            stmt.setInt(ID_MOVIE, movieID);
            stmt.setInt(ID_GENRE, genreID);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public void disconnectGenreAndMovie(int movieID, int genreID) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DISCONNECT_GENRE_AND_MOVIE)) {
            stmt.setInt(ID_MOVIE, movieID);
            stmt.setInt(ID_GENRE, genreID);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Actor> selectActorByActorFullName(String actorFullName) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(SELECT_ACTOR_BY_ACTOR_FULLNAME)) {
            stmt.setString(ACTOR_FULL_NAME, actorFullName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Actor(
                            rs.getInt(ID_ACTOR),
                            rs.getString(ACTOR_FULL_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Director> selectDirectorByDirectorFullName(String directorFullName) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(SELECT_DIRECTOR_BY_DIRECTOR_FULLNAME)) {
            stmt.setString(DIRECTOR_FULL_NAME, directorFullName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Director(
                            rs.getInt(ID_DIRECTOR),
                            rs.getString(DIRECTOR_FULL_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Genre> selectGenreByGenreFullName(String genreFullName) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(SELECT_GENRE_BY_GENRE_FULLNAME)) {
            stmt.setString(GENRE_FULL_NAME, genreFullName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Genre(
                            rs.getInt(ID_GENRE),
                            rs.getString(GENRE_FULL_NAME)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public int createActorAndConnectHimWithMovie(int movieID, Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(CREATE_ACTOR_AND_CONNECT_HIM_WITH_MOVIE)) {
            stmt.setInt(ID_MOVIE, movieID);
            stmt.setString(ACTOR_FULL_NAME,actor.getActorFullName());
            stmt.registerOutParameter(ID_ACTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_ACTOR);
        }
    }

    @Override
    public int createDirectorAndConnectHimWithMovie(int movieID, Director director) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(CREATE_DIRECTOR_AND_CONNECT_HIM_WITH_MOVIE)) {
            stmt.setInt(ID_MOVIE, movieID);
            stmt.setString(DIRECTOR_FULL_NAME,director.getDirectorFullName());
            stmt.registerOutParameter(ID_DIRECTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_DIRECTOR);
        }
    }

    @Override
    public int createGenreAndConnectHimWithMovie(int movieID, Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(CREATE_GENRE_AND_CONNECT_HIM_WITH_MOVIE)) {
            stmt.setInt(ID_MOVIE, movieID);
            stmt.setString(GENRE_FULL_NAME,genre.getGenreFullName());
            stmt.registerOutParameter(ID_GENRE, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_GENRE);
        }
    }

    @Override
    public int checkIfActorExistsAndReturnActorID(Actor actor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(CHECK_IF_ACTOR_EXISTS_AND_RETURN_ACTOR_ID)) {
            stmt.setString(ACTOR_FULL_NAME, actor.getActorFullName());
            stmt.registerOutParameter(ID_ACTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_ACTOR);
        }
    }
    

    @Override
    public int checkIfDirectorExistsAndReturnDirectorID(Director director) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(CHECK_IF_DIRECTOR_EXISTS_AND_RETURN_DIRECTOR_ID)) {
            stmt.setString(DIRECTOR_FULL_NAME, director.getDirectorFullName());
            stmt.registerOutParameter(ID_DIRECTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_DIRECTOR);
        }
    }

    @Override
    public int checkIfGenreExistsAndReturnGenreID(Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(CHECK_IF_GENRE_EXISTS_AND_RETURN_GENRE_ID)) {
            stmt.setString(GENRE_FULL_NAME, genre.getGenreFullName());
            stmt.registerOutParameter(ID_GENRE, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_GENRE);
        }
    }

    @Override
    public int selectIDCurrentLogInUserByUserName(String username) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(SELECT_USER_ID_BY_USERNAME)) {
            stmt.setString(USERNAME, username);
            stmt.registerOutParameter(RESULT, Types.NVARCHAR);
            stmt.executeUpdate();
            return stmt.getInt(RESULT);
        }
    }

    @Override
    public void ConnectUserIDAndHisFavoriteActorID(int iDUser, int iDActor) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(CONNECT_USERID_AND_ACTORID_FAVORITE)) {
            stmt.setInt(ID_USER, iDUser);
            stmt.setInt(ID_ACTOR, iDActor);
            stmt.execute();
        }
    }

    @Override
    public boolean alredyHaveFavoriteActor(int iDUser) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(CHECK_IF_USER_HAVE_FAVORITE_ACTOR)) {
            stmt.setInt(ID_USER, iDUser);
            stmt.registerOutParameter(RESULT, Types.BIT);
            stmt.executeUpdate();
            return stmt.getBoolean(RESULT);
        }
    }

    @Override
    public void deleteUserFavoriteActor(int iDUser) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(DELETE_USER_FAVORITE_ACTOR)) {
            stmt.setInt(ID_USER, iDUser);
            stmt.executeUpdate();
        }
    }

    @Override
    public int getActorIDByActorFullNameForConnectInDaD(String actorFullName) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection conn = dataSource.getConnection(); CallableStatement stmt = conn.prepareCall(GET_ID_ACTOR_FOR_FAVORITE_CONNECT)) {
            stmt.setString(ACTOR_FULL_NAME, actorFullName);
            stmt.registerOutParameter(ID_ACTOR, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(ID_ACTOR);
        }
    }

    @Override
    public void deleteActorConnectionWithUserBeforeDelete(int actorID) throws Exception {
        DataSource dataSource = DataSourceSingelton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ACTOR_CON_BEFORE_DELETE);) {
            stmt.setInt(ID_ACTOR, actorID);
            stmt.executeUpdate();
        }
    }
}
