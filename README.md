# Temporal-Database
This project aims to create a interface between the application developer and the Mongo Database for temporal operations.

Temporal Database is essential because it can be queried for the past details too. Current database can be queried over the present values but in temporal database we have a collection called as history_Collection  which stores the previous entires for any event.
It has two important attributes - start_time, end_time.
Start_time is the system time whenever an entry is made to the database.
End_time is initially null but on every new entry for the same _id it gets updated.

Temporal Operations include : 
  1. { First / Last }Column : Indicates the first or last value of the column and its timestamp.
  2. { Previous[Val] / Next[Val] }Column : Indicates the valid value which preceeds or follows the current value or the value 'Val' and the corresponding timestamp.
  3. Previous_Scale Column : Indicates the value which preceeds the current value and its timestamp according to the specified granule(seconds,minutes,hours,days,month ..)
  4. Next_Scale Column : Indicates the value which follows the current value and its timestamp according to the specified granule.
  5. Evolution History : Indicates all the evolution dates of the column.
  6. First/Last Evolution Column : Indicates first/last evolution date of the column.
  7. Overlap : It finds the overlap between two events taking place at the same time. 

Apart from this the user will have an option to temporalise the database i.e. it will add the time attributes - start_time, end_time to an already existing non-temporal database with each entry's end_time as null.
  
