var mysql = require('mysql');
var express = require('express');
var jsonParser = require('body-parser');
var crypto = require('crypto');
const { v4: uuidv4 } = require('uuid');

var con = mysql.createConnection({
    host:"localhost",
    user: "root",
    password: "himanshu123",
    database: "movieRental"
});

var app =  express();
app.use(jsonParser.json());
app.use(jsonParser.urlencoded({ extended:true }));

/* Test API */
app.get('/', (req, res, next) => {
    res.json({'data':'For Movie : /api/movies, For Rented Movies : /api/rentedMovieList', 'error': null});
});

/* Movie api get*/


app.get('/api/movies', (request, response)=>{

    sql = "select * from movies inner join category on category.categoryid = movies.categoryId;";

    con.query(sql, function(err, result){

        con.on('error', (err)=>{
            console.log('[MySQL Error]', err);
        });

        var list = [];
        if(result && result.length){
            for(var i =0; i< result.length; i++){
                var data = {};
                data['movieId'] = result[i].movieId;
                data['movieName'] = result[i].movieName;
                data['duration']=result[i].duration;
                data['description']=result[i].description;
                data['image']=result[i].image;
                data['categoryName']=result[i].categoryName;
                data['rentPrice']=result[i].rentPrice;
                
                data['movie']
                list.push(data);
            }
            response.json({'data': list, 'error': null});
        }else{
            res.json({'data': null, 'error': 'No Data Found!'});
        }
    });

});

app.get('/api/rentedMovieList/', (request, response)=>{
    console.log(request.body);

    var rentedBy=request.query.userId;
    

    con.query('select movierenting.rentId,movies.movieName,movies.image,movies.rentPrice,movierenting.rentedDate,movierenting.dueDate from movies inner join movierenting on movies.movieId = movierenting.movieId where movierenting.rentedBy=?',[rentedBy], function(err, result,fields){

        con.on('error', (err)=>{
            console.log('[MySQL Error]', err);
        });

        var list = [];
        if(result && result.length){
            for(var i =0; i< result.length; i++){
                var data = {};
                data['movieId'] = result[i].movieId;
                data['rentedBy']=result[i].rentedBy;
                data['movieName'] = result[i].movieName;
                data['rentId']=result[i].rentId;
                data['image']=result[i].image;
                data['rentPrice']=result[i].rentPrice;
                data['rentedDate']=result[i].rentedDate;
                data['dueDate']=result[i].dueDate;
                data['movielist']
                list.push(data);
            }
            response.json({'data': list, 'error': null});
        }else{
           response.json({'data': null, 'error': 'No Data Found!'});
        }
    });

});




/* Register API */
app.post('/api/register/', (req,res) => {

    console.log(req.body);
    var data = req.body;
    var password = crypto.createHash('sha256').update(data.password).digest('hex');
    var email = data.email;
    var name = data.name;

        con.query('SELECT 1 FROM users WHERE email = ?', [email], function(err, result, fields){

            con.on('error', (err)=> {
                console.log('[MySQL Error]', err);
            });
            
            if(result && result.length){
                res.json({'data': null, 'error': 'User already exists!'});
            }else{
                var sql = "INSERT INTO users (name, email, password) values (?,?,?)";
                var values = [name, email, password];
                console.log(values);

                con.query(sql, values, function(err, result, fields){
                    con.on('error', (err)=>{
                        console.log('[MySQL Error]', err);
                    });
                    if (result.affectedRows==1){
                        res.json( {'data': 'Registered Successfully!', 'error' : null });
                    }else{
                        res.json({ 'data': null,  'error': 'Something went wrong, Please try after sometime!'});
                    }
                });
            }
        });
});


// Rent api 

app.post('/api/rentMovie',(req,res) => {

    console.log(req.body);
    var data = req.body;
    var movieId = data.movieId;
    var rentedBy=data.rentedBy;
    var rentedBy= parseInt(data.rentedBy);
    var rentDate=new Date();
    rentDate.setDate(rentDate.getDate());
    var dueDate=new Date();
    dueDate.setDate(dueDate.getDate()+30);
    

    con.query('SELECT 1 FROM movieRenting  WHERE movieId = ? AND rentedBy=?', [movieId,rentedBy], function(err, result, fields){

        con.on('error', (err)=> {
            console.log('[MySQL Error]', err);
        });
        
        if(result && result.length){
            res.json({'data': null, 'error': 'Movie already exists!'});
        }else{
            var sql = "INSERT INTO movierenting (movieId, rentedDate, rentedBy,dueDate) values (?,?,?,?)";
            var values = [movieId, rentDate,rentedBy,dueDate];
            console.log(values);

            con.query(sql, values, function(err, result, fields){
                con.on('error', (err)=>{
                    console.log('[MySQL Error]', err);
                });
                if (result.affectedRows==1){
                    res.json( {'data': 'Rented Successfully!', 'error' : null });
                }else{
                    res.json({ 'data': null,  'error': 'Something went wrong, Please try after sometime!'});
                }
            });
        }
    });
});
/* Login API */

app.post('/api/login/', (req,res) => {

    var data = req.body;
    var password = crypto.createHash('sha256').update(data.password).digest('hex');
    var email = data.email;

            con.query('SELECT userid,name FROM users WHERE email = ? AND password  = ?', [email, password], function(err, result, fields){

                con.on('error', (err)=> {
                    console.log('[MySQL Error]', err);
                    throw(err);
                });
                var userid = result[0].userid;
                var name=result[0].name;

                if(result && result.length){

                    var userid = result[0].userid;
                    var name=result[0].name;

                    /* If Token Already Exists */
                    con.query('SELECT 1 FROM token WHERE userid = ?', [userid], function(err, result, fields){

                        con.on('error', (err)=> {
                            console.log('[MySQL Error]', err);
                        });
                        var token = uuidv4();
                        var created_date = new Date();
                        created_date.setDate(created_date.getDate());
                        var expire_date = new Date();
                        expire_date.setDate(expire_date.getDate() + 90);
                        
                        var sql = "";
                        var values;

                        /*Token Creation*/
                        if(result && result.length){
                            sql = "UPDATE token SET token = ?, created_date = ?, expire_date = ? WHERE userid = ? ";
                            values = [token, created_date, expire_date, userid];
                        }else{
                            sql = "INSERT INTO token (userid, token, created_date, expire_date) values (?,?,?,?)";
                            values = [userid, token, created_date, expire_date];
                        }

                        con.query(sql, values, function(err, result, fields){
                            con.on('error', (err)=>{
                                console.log('[MySQL Error]', err);
                            });
                            if (result.affectedRows==1){
                                res.json( {'data': {'token': token, 'userid' : userid,'name' : name}, 'error' : null });
                            }else{
                                res.json({ 'data': null,  'error': 'Something went wrong, Please try after sometime!'});
                            }
                        });
                    });

                }else{
                    res.json({'data': null,'error': 'Email or password is worng!'});
                }
        
            });
});

app.use(express.static('assets'));
app.listen(process.env.PORT);