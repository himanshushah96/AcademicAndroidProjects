use movierental;

create Table users(

        userId integer auto_increment,

        name varchar(100) not null,

        email varchar(150) not null,

        password varchar(64) not null,

        constraint userID_pk PRIMARY KEY (userID),

        constraint email_uk UNIQUE KEY(email)

);

create table token
(

	userid int primary key,

    token varchar(64) not null unique key,

    created_date datetime not null,

    expire_date datetime not null,

    foreign key token(userid) references users(userid)

);

create table category(

        categoryID integer auto_increment,

        categoryName varchar(50) not null,

        constraint categoryID_pk PRIMARY KEY (categoryID)

);

create table movies(

        movieId integer auto_increment,

        movieName varchar(100) not null,

        duration varchar(5),

        description varchar(500) not null,

        image varchar(5000),

        categoryId integer not null,

        rentPrice decimal(5,2) not null,

        constraint movieId_pk PRIMARY KEY (movieId),

        constraint categoryId_fk FOREIGN KEY (categoryId) references category(categoryID)

);

create table movieRenting(

        rentId integer auto_increment,

        movieID integer not null,

        rentedDate datetime not null,

        rentedBy integer not null,

        dueDate datetime not null,

        constraint rentId_pk PRIMARY KEY (rentId),

        constraint movieId_fk FOREIGN KEY (movieID) references movies (movieId),

        constraint rentedBy_fk FOREIGN KEY (rentedBy) references users(userId)
);