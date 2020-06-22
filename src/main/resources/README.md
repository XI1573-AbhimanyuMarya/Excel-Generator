# Excel Generator

Create an app which takes:
1. A XLSX template file
2. A XLSX data file which contains records in each row. (Firt row of this file will have col names)
3. A mapping.json file which stores mapping between each coloumn in the XLSX Data File to its corresponding cell    number in template file. 
   
Working:

Generate n XLSX files from the temmplate file for n rows in the data file. For example, if data file has 5 rows, then 5 excel
files needs to be generated.

Consider the coloumn names to case insensitive, ie. Name = name.

NOTE: This tool should work with any data file and any template file.
