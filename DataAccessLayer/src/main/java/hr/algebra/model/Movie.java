/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Leonardo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    public static final DateTimeFormatter FORMAT_FOR_PUB_DATE = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static final DateTimeFormatter FORMAT_FOR_DATE_OF_DISPLAY = DateTimeFormatter.ISO_LOCAL_DATE;

    @XmlElement(name = "idmovie")
    private int idMovie;
    private String title;
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "pubdate")
    private LocalDateTime pubDate;
    private String description;
    @XmlElement(name = "originaltitle")
    private String originalTitle;
    private String duration;
    @XmlElement(name = "posterpath")
    private String posterPath;
    private String link;
    //@XmlJavaTypeAdapter(DateAdapter.class)
    //@XmlElement(name = "dateofdisplay")
    private LocalDate dateOfDisplay;
    private String actor;
    private String director;
    private String genre;
    @XmlElementWrapper
    @XmlElement (name = "actor")
    private List<Actor> actors;
    @XmlElementWrapper
    @XmlElement (name = "director")
    private List<Director> directors;
    @XmlElementWrapper
    @XmlElement (name = "genre")
    private List<Genre> genres;

    public Movie() {
    }

    public Movie(String title, LocalDateTime pubDate, String description, String originalTitle, String duration, String posterPath, String link, LocalDate dateOfDisplay) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.posterPath = posterPath;
        this.link = link;
        this.dateOfDisplay = dateOfDisplay;
    }

    public Movie(int idMovie, String title, LocalDateTime pubDate, String description, String originalTitle, String duration, String posterPath, String link, LocalDate dateOfDisplay) {
        this.idMovie = idMovie;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.posterPath = posterPath;
        this.link = link;
        this.dateOfDisplay = dateOfDisplay;
    }

    public Movie(int idMovie, String title, LocalDateTime pubDate, String description, String originalTitle, String duration, String posterPath, String link, LocalDate dateOfDisplay, String actor, String director, String genre, List<Actor> actors, List<Director> directors, List<Genre> genres) {
        this.idMovie = idMovie;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.originalTitle = originalTitle;
        this.duration = duration;
        this.posterPath = posterPath;
        this.link = link;
        this.dateOfDisplay = dateOfDisplay;
        this.actor = actor;
        this.director = director;
        this.genre = genre;
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getDateOfDisplay() {
        return dateOfDisplay;
    }

    public void setDateOfDisplay(LocalDate dateOfDisplay) {
        this.dateOfDisplay = dateOfDisplay;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return idMovie + "-" + title;
    }

}
