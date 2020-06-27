package a.m.carrental.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import a.m.carrental.R;
import a.m.carrental.model.Constants;

public class SetupData {

    public SetupData()
    {
        loadCars();
        loadUsers();
    }

    public static void loadCars(){
        DbModel db = DbModel.getInstance();
        Car car1=new Car();
        car1.brand="Tesla";
        car1.modelName="Model S";
        car1.carType="Sedan";
        car1.image=R.drawable.tesla_model_s;
        car1.date="04/11/2019";
        car1.price=95;
        car1.id = 0;
        car1.description="The Tesla Model S is an all-electric five-door liftback sedan \n" +
                "the Model S Long Range has an EPA range of 370 miles (600 km), higher than any other battery electric car.";

        db.add(car1);


        Car car2=new Car();
        car2.brand="Tesla";
        car2.modelName="Roadster";
        car2.carType="Sedan";
        car2.image=R.drawable.tesla_roadster_20;
        car2.date="06/11/2019";
        car2.price=150;
        car2.id = 1;
        car2.description="The Tesla Roadster is all-electric battery-powered four-seater sports car made for luxury.\n" +
                " capable of 0 to 97 km/h (0 to 60 mph) in 1.9 seconds. \n" +
                "quicker than any street legal production car to date.including a model with ~10 cold gas thrusters.";
        db.add(car2);


        Car car3=new Car();
        car3.brand="Hyundai";
        car3.modelName="Elantra";
        car3.carType=Constants.CAR_TYPE_SEDAN;
        car3.image=R.drawable.elantra;
        car3.date="08/11/2019";
        car3.price=50;
        car3.id = 2;
        car3.description="Hyndai elentra is comfortable sedan for family trips upto 4 adults \n" +
                " with amazing milage and drive experience";
        db.add(car3);

        Car car4=new Car();
        car4.brand="Audi";
        car4.modelName="A4";
        car4.carType=Constants.CAR_TYPE_SEDAN;
        car4.image=R.drawable.audi_a4;
        car4.date="09/11/2019";
        car4.price=100;
        car4.id = 3;
        car4.description="Audi A4 for best performence and ride quality \n " +
                "Audi pre sense® city and Audi pre sense® basic\n" +
                "7.0” color driver information system\n" +
                "Power sunroof\n" +
                "Leather seating surfaces with heated front seats\n" +
                "Audi smartphone interface including Apple CarPlay® and Google™ Android Auto™\n" +
                "quattro® all-wheel drive";
        db.add(car4);

        Car car5=new Car();
        car5.brand="BMW";
        car5.modelName="M5";
        car5.carType=Constants.CAR_TYPE_SEDAN;
        car5.image=R.drawable.bmw_m5;
        car5.date="10/11/2019";
        car5.price=105;
        car5.id = 4;
        car5.description="BMW M5 best in comfort and performance for seemless journey \n " +
                "It sports a twin-turbo 4.4-liter V-8 with 600 horsepower and 553 lb-ft of torque \n " +
                 "making the M5 mighty quick.it launched to 60 mph in 2.8 seconds and snapped off a 10.9-second quarter-mile time at 129 mph. \n" +
                "top speed maxed out at 163 mph.";
        db.add(car5);

        Car car6=new Car();
        car6.brand="Chevrolet";
        car6.modelName="Camaro";
        car6.carType=Constants.CAR_TYPE_SEDAN;
        car6.image=R.drawable.chevrolet_camaro;
        car6.date="12/11/2019";
        car6.price=160;
        car6.id = 5;
        car6.description="Chevy camaro best sports car for luxurious tripping \n " +
                "Heated and ventilated front seats\n" +
                "RECARO® performance seats\n" +
                "Heated steering wheel\n" +
                "Power seats with Memory Package\n" +
                "Red seat belts\n" +
                "Sueded steering wheel and shifter\n" +
                "Aluminum driving pedals";
        db.add(car6);

        Car car7=new Car();
        car7.brand="Lamborghini";
        car7.modelName="Urus";
        car7.carType=Constants.CAR_TYPE_SUV;
        car7.image=R.drawable.lambo_urus;
        car7.date="14/11/2019";
        car7.price=200;
        car7.id = 6;
        car7.description="Urus is best in class SUV providing premium luxury and endless comfort \n " +
                "The engine is rated at a maximum power output of 478 kW (641 hp; 650 PS) at 6,000 rpm and maximum torque of 850 N⋅m (627 lb⋅ft) at 2,250–4,500 rpm.\n " +
                "The Urus has a front-engine, all-wheel-drive layout, and a top speed of 305 km/h (190 mph) \n " +
                "making it one of the world's fastest production SUVs.";
        db.add(car7);

        Car car8=new Car();
        car8.brand="Mazda";
        car8.modelName="Mazda6";
        car8.carType=Constants.CAR_TYPE_SEDAN;
        car8.image=R.drawable.mazda_6;
        car8.date="05/11/2019";
        car8.price=85;
        car8.id = 7;
        car8.description="The Mazda6 is a five-seat mid-size sedan \n" +
                " that's powered by a choice of four-cylinder engines: a standard 187-horsepower, 2.5-liter four-cylinder or an available 227-hp, turbocharged 2.5-liter four-cylinder.\n" +
                " Both engines work with a six-speed automatic transmission";
        db.add(car8);

        Car car9=new Car();
        car9.brand="Nissan";
        car9.modelName="GTR";
        car9.carType=Constants.CAR_TYPE_SEDAN;
        car9.image=R.drawable.nissan_gtr;
        car9.date="15/11/2019";
        car9.price=180;
        car9.id = 8;
        car9.description="This is the fastest, most powerful, and most focused Nissan GT-R. \n" +
                "Built by Nissan Motorsports International in Japan, it has aerodynamic, suspension and engine upgrades\n" +
                " with the optional ‘Track Pack’ fitted it’s the car that’s set the fastest ever time for a series production car around the Nurburgring \n" +
                " at 7.08.679. The Porsche 918 Spyder has gone quicker\n" +
                " bringing into question what ‘series production’ actually means, but there’s no doubt that this is one hell of a focused car.";
        db.add(car9);

        Car car10=new Car();
        car10.brand="Hyndai";
        car10.modelName="Santafe";
        car10.carType=Constants.CAR_TYPE_HATCHBACK;
        car10.image=R.drawable.hyundai_santafe;
        car10.date="18/11/2019";
        car10.price=70;
        car10.id = 9;
        car10.description="Santafe is known for best family SUV\n" +
                "Smooth ride\n" +
                "Compelling value trims with plenty of features\n" +
                "Standard active safety features\n" +
                "Spacious interior\n" +
                "Sharp style" + "Santafe is known for best family SUV\n" +
                "Smooth ride\n" +
                "Compelling value trims with plenty of features\n" +
                "Standard active safety features\n" +
                "Spacious interior\n" +
                "Sharp style";
        db.add(car10);
    }

    public static void loadUsers(){
        DbModel dbModel = DbModel.getInstance();
        User user1=new User();
        user1.firstName="Harsh";
        user1.lastName="Mehta";
        user1.email="harsh@carrental.com";
        user1.password="harsh";
        user1.userType= Constants.USER_TYPE_EMPLOYEE;
        dbModel.add(user1);


        User user2=new User();
        user2.firstName="Himanshu";
        user2.lastName="Shah";
        user2.email="himanshu@carrental.com";
        user2.userType= Constants.USER_TYPE_EMPLOYEE;
        user2.password="himanshu";
        dbModel.add(user2);


        User user3=new User();
        user3.firstName="Urvil";
        user3.lastName="Shah";
        user3.email="urvil@carrental.com";
        user3.password="urvil";
        user3.userType= Constants.USER_TYPE_EMPLOYEE;
        dbModel.add(user3);


        User user4=new User();
        user4.firstName="Harshil";
        user4.lastName="Ramswaroop";
        user4.email="harshil@carrental.com";
        user4.password="harshil";
        user4.userType= Constants.USER_TYPE_EMPLOYEE;
        dbModel.add(user4);


        User user5=new User();
        user5.firstName="Tommy";
        user5.lastName="Shelby";
        user5.email="tommy@peakyblinders.com";
        user5.password="tommy";
        user5.userType= Constants.USER_TYPE_CUSTOMER;
        dbModel.add(user5);


        User user6=new User();
        user6.firstName="Jon";
        user6.lastName="Snow";
        user6.email="aegon@targeryan.com";
        user6.userType= Constants.USER_TYPE_CUSTOMER;
        user6.password="jon";
        dbModel.add(user6);

        User user7=new User();
        user7.firstName="Jessy";
        user7.lastName="Pinkman";
        user7.email="jessy@thebreakingbad.com";
        user7.password="jessy";
        user7.userType= Constants.USER_TYPE_CUSTOMER;
        dbModel.add(user7);

        User customer = new User();
        customer.firstName = "App";
        customer.lastName = "Customer";
        customer.email = "customer@app.com";
        customer.password = "admin";
        customer.userType = Constants.USER_TYPE_CUSTOMER;

        User employee = new User();
        employee.firstName = "App";
        employee.lastName = "Admin";
        employee.email = "admin@app.com";
        employee.password = "admin";
        employee.userType = Constants.USER_TYPE_EMPLOYEE;

        dbModel.add(customer);
        dbModel.add(employee);

        }
    }

