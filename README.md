**Object-Oriented Project 3 Roll Restaurant Simulation  
By: Elmer Baca Holguin and Timothy Euken**

======================= 

How to use?
------------------
Main function class can be found under food-business-simulator\src\shop_simulation

In order to run this program you must have the JUnit5.4 dependencies installed.

**Important Locations:** 


**Rolls:** -> food-business-simulation/src/rolls  
contains all different rolls, factories, and decorators

**Shops:** -> food-business-simulation/src/shops  
contains ColoradoRollShop class and RollShop abstract class

**customers:** -> food-business-simulation/src/customers  
Includes Casual, Catering, and Business customer objects

**Tests:** -> food-business-simulation/src/customers  
 Contains MyUnitTest class for testing the main program.

**Initialize:** -> food-business-simulation/src/initialization  
Initializes data structure required for main. Cuts down on code complexity and allows reusage.

**outputFiles** -> food-business-simulation/outputFiles 
Contains sampele output files for a noraml run with 30 roll capacity for the store, a run with 45 roll capacity, and a run with 60 roll capacity for the store

Assumptions
------------------
(1)Assumed that each customer had a random change of adding a topping, on top of the random amount of toppings the person would pick  
(2)Assumed the store started its day with creating certain amount of rolls
