--Leonardo Vuèenoviæ JAVA1
create database CinestarJava1
go
use CinestarJava1
SELECT * FROM sys.procedures
-----------------------------------------TABLE--------------------------------------------------
create table Users
(
	IDUser int primary key identity,
	Username nvarchar(50) not null,
	[Password] nvarchar(50) not null,
	TypeOfRole nvarchar(50) not null
)
go
create table Movie
(
	IDMovie int primary key identity,
	Title nvarchar(100) not null,
	PubDate nvarchar(100) not null,
	[Description] nvarchar(max) not null,
	OriginalTitle nvarchar(100) not null,
	Duration nvarchar(10) not null,
	PosterPath nvarchar(max) not null,
	Link nvarchar(max) not null,
	DateOfDisplay nvarchar(25) not null,
)
go
create table Actor
(
	IDActor int primary key identity,
	ActorFullName nvarchar(50) not null
)
create table Director
(
	IDDirector int primary key identity,
	DirectorFullName nvarchar(50) not null
)
go
create table Genre
(
	IDGenre int primary key identity,
	GenreFullName nvarchar(50)
)
go
--MovieActor table M-N
create table MovieActor
(
	IDMovieActor int not null primary key identity,
	MovieID int not null foreign key references Movie(IDMovie),
	ActorID int null foreign key references Actor (IDActor)
)
go
--MovieDirector table M-N
create table MovieDirector
(
	IDMovieDirector int not null primary key identity,
	MovieID int not null foreign key references Movie(IDMovie),
	DirectorID int not null foreign key references Director(IDDirector)
)
go
--MovieGenre table M-N
create table MovieGenre
(
	IDMovieGenre int not null primary key identity,
	MovieID int not null foreign key references Movie(IDMovie),
	GenreID int null foreign key references Genre(IDGenre)
)
go
create table FavoriteActorOfUser
(
	IDFavoriteMovieForUser int not null primary key identity,
	UserID int not null foreign key references [Users](IDUser),
	ActorID int null foreign key references Actor(IDActor)
)
go
----------------------------------PROCEDURES---------------------------------------------------
delete from Users
insert into Users values ('Admin','12345','Admin')
insert into Users values ('User','12345','User')
SELECT TOP (1000) [IDUser]
      ,[Username]
      ,[Password]
      ,[TypeOfRole]
  FROM [CinestarJava1].[dbo].[Users]
  go
create or alter proc CheckIfUsernameExists
	@Username nvarchar(50),
	@result bit output
as
begin
	if exists(select * from Users where Username = @Username)
	begin
	set @result = 1
	end
	else
	begin
	set @result = 0
	end
end
go
declare @result bit;
exec CheckIfUsernameExists
	@Username = 'Admin',
	@result = @result output;
select @result as Result;
go
create or alter proc CreateUser
	@Username nvarchar(50),
	@Password nvarchar(50),
	@TypeOfRole nvarchar(50),
	@IDUser int output
as
begin
	insert into Users values(@Username, @Password, @TypeOfRole)
	set @IDUser = SCOPE_IDENTITY()
end
go
create or alter proc SelectUserPasswordForLogIn
	@Username nvarchar(50),
	@result nvarchar(50) output
as
begin
	if exists(select * from Users where Username = @Username)
	begin
		select @result = Password from Users where Username = @Username
	end
	else
	begin
		set @result = ''
	end
end
go
declare @Username nvarchar(50)
declare @Password nvarchar(50)
set @Username = 'Admin'
exec SelectUserPasswordForLogIn @Username, @Password output
select @Password as Password
go
create or alter proc SelectUserTypeOfRoleForLogIn
	@Username nvarchar(50),
	@result nvarchar(50) output
as
begin
	if exists(select * from Users where Username = @Username)
	begin
		select @result = TypeOfRole from Users where Username = @Username
	end
	else
	begin
		set @result = ''
	end
end
go
declare @username nvarchar(50)
declare @result nvarchar(50)
set @username = 'User'
exec SelectUserTypeOfRoleForLogIn @Username = @username, @result = @result output
select @result as result
go
create or alter proc SelectAllMovies
as
begin
	select * from Movie
end
go
--
insert into Movie values ('Title','2023-06-11T14:30:45','Description','OriginalTitle','120','PosterPath','Link','2023-06-11')
delete from Movie
select * from Movie
go
create or alter proc SelectMovieByID
	@IDMovie int
as
begin
	select * from Movie where IDMovie = @IDMovie
end
go
create or alter proc CreateMovie
	@Title nvarchar(100),
	@PubDate nvarchar(100),
	@Description nvarchar(max),
	@OriginalTitle nvarchar(100),
	@Duration nvarchar(10),
	@PosterPath nvarchar(max),
	@Link nvarchar(max),
	@DateOfDisplay nvarchar(25),
	@IDMovie int output
as
begin
	insert into Movie values(@Title, @PubDate, @Description, @OriginalTitle, @Duration, @PosterPath, @Link, @DateOfDisplay)
	set @IDMovie = SCOPE_IDENTITY()
end
go
create or alter proc DeleteMovie
	@IDMovie int
as
begin
	delete from MovieActor where MovieID = @IDMovie
	delete from MovieDirector where MovieID = @IDMovie
	delete from MovieGenre where MovieID = @IDMovie
	delete from Movie where IDMovie = @IDMovie
end
go
create or alter proc UpdateMovie
	@Title nvarchar(100),
	@PubDate nvarchar(100),
	@Description nvarchar(max),
	@OriginalTitle nvarchar(100),
	@Duration nvarchar(10),
	@PosterPath nvarchar(max),
	@Link nvarchar(max),
	@DateOfDisplay nvarchar(25),
	@IDMovie int
as
begin
	UPDATE Movie 
	set
	Title = @Title, PubDate = @PubDate, [Description] = @Description, OriginalTitle = @OriginalTitle,
	Duration = @Duration, PosterPath = @PosterPath, Link = @Link, DateOfDisplay = @DateOfDisplay
	where IDMovie = @IDMovie
end
go
create or alter proc SelectAllActors
as
begin
	select * from Actor
end
go
create or alter proc SelectAllDirectors
as
begin
	select * from Director
end
go
create or alter proc SelectAllGenres
as
begin
	select * from Genre
end
go
create or alter proc DeleteActor
	@IDActor int
as
begin
	DELETE from Actor
	where IDActor = @IDActor
end
go
create or alter proc UpdateActor
	@IDActor int,
	@ActorFullName nvarchar(50)
as
begin
	UPDATE Actor
	set
	ActorFullName = @ActorFullName
	where IDActor = @IDActor
end
go
create or alter proc CreateNewActor
	@ActorFullName nvarchar(50),
	@IDActor int output
as
begin
	insert into Actor values (@ActorFullName)
	set @IDActor = SCOPE_IDENTITY()
end
go
create or alter proc CreateNewDirector
	@DirectorFullName nvarchar(50),
	@IDDirector int output
as
begin
	insert into Director values (@DirectorFullName)
	set @IDDirector = SCOPE_IDENTITY()
end
go
create or alter proc UpdateDirector
	@IDDirector int,
	@DirectorFullName nvarchar(50)
as
begin
	UPDATE Director
	set
	DirectorFullName = @DirectorFullName
	where IDDirector = @IDDirector
end
go
create or alter proc CreateNewGenre
	@GenreFullName nvarchar(50),
	@IDGenre int output
as
begin
	insert into Genre values (@GenreFullName)
	set @IDGenre = SCOPE_IDENTITY()
end
go
create or alter proc DeleteDirector
	@IDDirector int
as
begin
	DELETE from Director
	where IDDirector = @IDDirector
end
go
create or alter proc SelectGenreByID
	@IDGenre int
as
begin
	select * from Genre where IDGenre = @IDGenre
end
go
create or alter proc DeleteGenre
	@IDGenre int
as
begin
	DELETE from Genre
	where IDGenre = @IDGenre
end
go
create or alter proc SelectActorByID
	@IDActor int
as
begin
	select * from Actor where IDActor = @IDActor
end
go
create or alter proc SelectDirectorByID
	@IDDirector int
as
begin
	select * from Director where IDDirector = @IDDirector
end
go
create or alter proc UpdateGenre
	@IDGenre int,
	@GenreFullName nvarchar(50)
as
begin
	UPDATE Genre
	set
	GenreFullName = @GenreFullName
	where IDGenre = @IDGenre
end
go
create or alter proc CheckIfMovieExists
	@Title nvarchar(100),
	@result bit output
as
begin
	declare @count int
	select @count = COUNT(IDMovie) from Movie where Title = @Title
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc CheckIfActorExists
	@ActorFullName nvarchar(50),
	@result int output
as
begin
	declare @count int
	select @count = COUNT(IDActor) from Actor where ActorFullName = @ActorFullName
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter  proc CheckIfDirectorExists
	@DirectorFullName nvarchar(50),
	@result int output
as
begin
	declare @count int
	select @count = COUNT(IDDirector) from Director where DirectorFullName = @DirectorFullName
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc CheckIfGenreExists
	@GenreFullName nvarchar(50),
	@result int output
as
begin
	declare @count int
	select @count = COUNT(IDGenre) from Genre where GenreFullName = @GenreFullName
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc GetMovieIDByTitle
    @Title nvarchar(100),
	@IDMovie int output
as
begin
	select @IDMovie = m.IDMovie from Movie as m
	where m.Title = @Title
end
go
create or alter proc GetActorIDByActorFullName
	@ActorFullName nvarchar(50),
	@IDActor int output
as
begin
	select @IDActor = a.IDActor from Actor as a
	where a.ActorFullName = @ActorFullName
end
go
create or alter proc GetAllActorsForMovieTitle
	@Title nvarchar(100)
as
begin
	select a.IDActor,a.ActorFullName from Movie as m
	join MovieActor as ma on m.IDMovie = ma.MovieID
	join Actor as a on a.IDActor = ma.ActorID
	where m.Title = @Title
end
go
create or alter proc ConnectActorAndMovie
	@IDMovie int,
	@IDActor int
as
begin
	insert into MovieActor values (@IDMovie, @IDActor)
end
go
create or alter proc ChechIfActorAreConnectWithMovie
	@IDActor int,
	@result bit output
as
begin
	declare @count int
	select @count = COUNT(ActorID) from MovieActor where ActorID = @IDActor
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc DeleteActorAndHimFromAllMovie
	@IDActor int
as
begin
	delete from MovieActor where ActorID = @IDActor
	delete from Actor where IDActor = @IDActor
end
go
create or alter proc ChechIfActorAlreadyOnMovie
	@IDMovie int,
	@IDActor int,
	@result bit output
as
begin
	declare @count int
	select @count = COUNT(IDMovieActor) from MovieActor
	where MovieID = @IDMovie and ActorID = @IDActor
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc DisconnectActorAndMovie
	@IDMovie int,
	@IDActor int
as
begin
	delete from MovieActor
	where MovieID = @IDMovie and ActorID = @IDActor
end
go
create or alter proc GetDirectorIDByDirectorFullName
	@DirectorFullName nvarchar(50),
	@IDDirector int output
as
begin
	select @IDDirector = d.IDDirector from Director as d
	where d.DirectorFullName = @DirectorFullName
end
go
create or alter proc GetAllDirectorsForMovieTitle
	@Title nvarchar(100)
as
begin
	select * From Movie as m
	join MovieDirector as md on m.IDMovie = md.MovieID
	join Director as d on d.IDDirector = md.DirectorID
	where m.Title = @Title
end
go
create or alter proc ConnectDirectorAndMovie
	@IDMovie int,
	@IDDirector int
as
begin
	insert into MovieDirector values (@IDMovie,@IDDirector)
end
go
create or alter proc ChechIfDirectorAreConnectWithMovie
	@IDDirector int,
	@result bit output
as
begin
	declare @count int
	select @count = COUNT(DirectorID) from MovieDirector where DirectorID = @IDDirector
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc DeleteDirectorAndHimFromAllMovie
	@IDDirector int
as
begin
	delete from MovieDirector where DirectorID = @IDDirector
	delete from Director where IDDirector = @IDDirector
end
go
create or alter proc ChechIfDirectorAlreadyOnMovie
	@IDMovie int,
	@IDDirector int,
	@result bit output
as
begin
	declare @count int
	select @count = COUNT(IDMovieDirector) from MovieDirector
	where MovieID = @IDMovie and DirectorID = @IDDirector
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc DisconnectDirectorAndMovie
	@IDMovie int,
	@IDDirector int
as
begin
	delete from MovieDirector
	where MovieID = @IDMovie and DirectorID = @IDDirector
end
go
create or alter proc GetGenreIDByGenreFullName
	@GenreFullName nvarchar(50),
	@IDGenre int output
as
begin
	select @IDGenre = g.IDGenre from Genre g
	where g.GenreFullName = @GenreFullName
end
go
create or alter proc GetAllGenresForMovieTitle
	@Title nvarchar(100)
as
begin
	select * From Movie as m
	join MovieGenre as mg on m.IDMovie = mg.MovieID
	join Genre as g on g.IDGenre = mg.GenreID
	where m.Title = @Title
end
go
create or alter proc ConnectGenreAndMovie
	@IDMovie int,
	@IDGenre int
as
begin
	insert into MovieGenre values (@IDMovie,@IDGenre)
end
go
create or alter proc ChechIfGenreAreConnectWithMovie
	@IDGenre int,
	@result bit output
as
begin
	declare @count int
	select @count = COUNT(GenreID) from MovieGenre where GenreID = @IDGenre
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc DeleteGenreAndHisFromAllMovie
	@IDGenre int
as
begin
	delete from MovieGenre where GenreID = @IDGenre
	delete from Genre where IDGenre = @IDGenre
end
go
create or alter proc ChechIfGenreAlreadyOnMovie
	@IDMovie int,
	@IDGenre int,
	@result bit output
as
begin
	declare @count int
	select @count = COUNT(IDMovieGenre) from MovieGenre
	where MovieID = @IDMovie and GenreID = @IDGenre
	if @count = 0
	begin
		set @result = 0
	end
	else
	begin
		set @result = 1
	end
end
go
create or alter proc DisconnectGenreAndMovie
	@IDMovie int,
	@IDGenre int
as
begin
	delete from MovieGenre
	where MovieID = @IDMovie and GenreID = @IDGenre
end
go
create or alter proc DeleteAllFromDatBase
as
begin
	delete from FavoriteActorOfUser
	delete from MovieActor
	delete from MovieDirector
	delete from MovieGenre
	delete from Actor
	delete from Director
	delete from Genre
	delete from Movie
end
go
create or alter proc SelectGenreByGenreFullName
	@GenreFullName nvarchar(50)
as
begin
	select * from Genre
	where GenreFullName = @GenreFullName
end
go
create or alter proc SelectActorByActorFullName
	@ActorFullName nvarchar(50)
as
begin
	select * from Actor
	where ActorFullName = @ActorFullName
end
go
create or alter proc SelectDirectorByDirectorFullName
	@DirectorFullName nvarchar(50)
as
begin
	select * from Director
	where DirectorFullName = @DirectorFullName
end
go
create or alter proc CreateActorAndConnectHimWithMovie
	@IDMovie int,
	@ActorFullName nvarchar(50),
	@IDActor int output
as
begin
	insert into Actor values (@ActorFullName)
	set @IDActor = SCOPE_IDENTITY()
	insert into MovieActor values (@IDMovie, @IDActor)
end
go
--
create or alter proc CreateDirectorAndConnectHimWithMovie
	@IDMovie int,
	@DirectorFullName nvarchar(50),
	@IDDirector int output
as
begin
	insert into Director values (@DirectorFullName)
	set @IDDirector = SCOPE_IDENTITY()
	insert into MovieDirector values (@IDMovie,@IDDirector)
end
go
create or alter proc CreateGenreAndConnectHimWithMovie
	@IDMovie int,
	@GenreFullName nvarchar(50),
	@IDGenre int output
as
begin
	insert into Genre values (@GenreFullName)
	set @IDGenre = SCOPE_IDENTITY()
	insert into MovieGenre values (@IDMovie,@IDGenre)
end
go
create or alter proc CheckIfGenreExistsAndReturnGenreID
	@GenreFullName nvarchar(50),
	@IDGenre int output
as
begin
	declare @count int
	select @count = COUNT(@GenreFullName) from Genre
	where GenreFullName = @GenreFullName
	if @count > 0
	begin
		select @IDGenre = IDGenre from Genre where GenreFullName = @GenreFullName
	end
	else
	begin
		set @IDGenre = 0
	end
end
go
create or alter proc CheckIfActorExistsAndReturnActorID
	@ActorFullName nvarchar(50),
	@IDActor int output
as
begin
	declare @count int
	select @count = COUNT(@ActorFullName) from Actor
	where ActorFullName = @ActorFullName
	if @count > 0
	begin
		select @IDActor = IDActor from Actor where ActorFullName = @ActorFullName
	end
	else
	begin
		set @IDActor = 0
	end
end
go
create or alter proc CheckIfDirectorExistsAndReturnDirectorID
	@DirectorFullName nvarchar(50),
	@IDDirector int output
as
begin
	declare @count int
	select @count = COUNT(@DirectorFullName) from Director
	where DirectorFullName = @DirectorFullName
	if @count > 0
	begin
		select @IDDirector = IDDirector from Director where DirectorFullName = @DirectorFullName
	end
	else
	begin
		set @IDDirector = 0
	end
end
go
create or alter proc SelectIDCurrentLogInUserByUserName
	@Username nvarchar(50),
	@result nvarchar(max) output
as
begin
	select @result = Users.IDUser from Users
	where Users.Username = @Username
end
go


create or alter proc ConnectUserIDAndHisFavoriteActorID
	@IDUser int,
	@IDActor int
as
begin
	insert into FavoriteActorOfUser values (@IDUser,@IDActor)
end
go
create or alter proc AlredyHaveFavoriteActor
	@IDUser int,
	@result bit output
as
begin
	declare @count int
	select @count = COUNT(@IDUser) from FavoriteActorOfUser 
	where UserID = @IDUser
	if @count > 0
	begin
		set @result = 1
	end
	else 
	begin
		set @result = 0
	end
end
go
create or alter proc DeleteUserFavoriteActor
	@IDUser int
as
begin
	delete from FavoriteActorOfUser
	where UserID = @IDUser
end
go
create or alter proc GetActorIDByActorFullNameForConnectInDaD
	@ActorFullName nvarchar(50),
	@IDActor int output
as
begin
	select @IDActor = Actor.IDActor from Actor
	where ActorFullName = @ActorFullName
end
go
create or alter proc DeleteActorConnectionWithUserBeforeDelete
	@IDActor int
as
begin
	delete from FavoriteActorOfUser
	where @IDActor = @IDActor
end
--PROC ZA LOG IN, user i admin


select * from FavoriteActorOfUser
select * from MovieActor
select * from MovieDirector
select * from MovieGenre
select * from Actor
select * from Director
select * from Genre
select * from Movie
select * from [Users]












































