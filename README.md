# Driver Trips Kata
  Written in Groovy using a Maven build, Unit tests with Spock

#### Overall goals
  * easy to follow logic through descriptive variables and method names
  * reduce repeated logic by utilizing shared methods

### Design Choices
	
  #### Input is temporarily held and passed using a 'ParsedDriverInput' object
  * allows for asking the input what type it is
  * any changes to input format would only affect this object
  * no need to access mystery indexes from input in DriverRegistry class
    
  #### DriverRegistry class
  * handles adding drivers, accepting input, returning output
  * validation is done in the "add trip" & "add driver" methods, this way 
    these methods could be exposed outside of the class at some point.

  #### Trips are held in the Driver object
  * ability to ask the driver their total distance and travel time

  #### Using a DriverDto for output
  * calculation of total distance and trip time only has to be done once for sorting

  #### SpeedCalculator Util
  * used for getAverageSpeed
  * externalized for shared usage between driver's total & individual trips

  #### Enums TripValidation & CommandType
  * values with potential to change are moved out of services & models
  * allows for changes in one place if these had been shared elsewhere
  * allows for more descriptive values
