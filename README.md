# EmployeeManagerManagement

### Environment setup

* Need to setup properties file location in Eclipse or any other IDE

  @PropertySource(value = { "file:${location}/management_${env}.properties" })

  As shown in above line before running the application there is need to set location and env variables
  
  -- Steps to set these location --

	1. Copy the location of management_dev.properties file
	2. Right click on project -> 'Run As/ Debug As' -> 'Debug Configuration../Run Configuration..'
	3. Then click on 'Arguments' of project ManagerEmployeeManagement
	4. Then set VM arguments as
		-Dlocation= {copiedLocation of 1st step}
		-Denv=dev

  Now rerun the application. Done.



### Testing of application

*  API's https://www.getpostman.com/collections/30549afa7ed976993832

	1. SIGNUP api for manager
		Request = POST - http://192.168.0.123:7050/api/auth/signup 
		Body = raw -> json - {
   					 "firstName": "Dhiraj",
    					 "lastName": "Patil",
   					 "userEmail": "harshu@yopmail.com",
  					 "address": "Amalner",
				         "password": "123",
   					 "dob": "2021-10-04T13:03:33"
				     }

	
	2. LOGIN api for manager
		Request = POST - http://192.168.0.123:7050/api/auth/login
		Body = raw -> json - {
    					"userName":"harshu@yopmail.com",
  				        "password":"123"
				     }


	3. To save employee in DB
		Authentication -> Bearer Token -> paste the token which is recieved after successful login
		Request = POST - http://192.168.0.123:7050/api/employee
		Body = raw -> json - {
   				 "empId":"558",
   				 "firstName":"Jayesh",
  				 "lastName":"Akshay",
   				 "address":"Surat",
   				 "dob":"2021-10-04T13:03:33",
   				 "mobile":"1234567980"
			      }

	4. To update employee 
		Authentication -> Bearer Token -> paste the token which is recieved after successful login
		Request = PUT - http://192.168.0.123:7050/api/employee
		Body = raw -> json - {
   				 "empId":"558",
   				 "firstName":"Bhavesh",
  				 "lastName":"Akshay",
   				 "address":"Mumbai",
   				 "dob":"2021-10-04T13:03:33",
   				 "mobile":"1234567980"
			      }

	5. To get all employee 
		Authentication -> Bearer Token -> paste the token which is recieved after successful login
		Request = GET - http://192.168.0.123:7050/api/employee


	6. To delete employee 
		Authentication -> Bearer Token -> paste the token which is recieved after successful login
		Request = DELETE - http://192.168.0.123:7050/api/employee?empId=558

