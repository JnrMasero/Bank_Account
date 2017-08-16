================================================================================
Author:    Masero Collins
Date:      15.08.2017
Copyright: Masero Collins
Module:    Pezesha Technical Interview
Email: 	   jnrmasero@gmail.com
================================================================================
Read Me File
================================================================================

A simple Read me file for deploying the application

================================================================================

1. REQUIREMENTS
================================================================================
	1. Apache Tomcat installed and running on port 8080
	2. Apache Ant installed and working
	3. Java JDK 7 or higher installed
	4. CXF 3.1.4
	5. System variable classpaths set for Java and Ant, ie :
	
		~$ echo $JAVA_HOME
			/usr/java/latest
		
		~$ echo $ANT_HOME
			/usr/local/ant
		~$ echo $CATALINA_HOME
			/usr/local/apache-tomcat-8.5.4
		~$ echo $CXF_HOME
			/usr/local/cxf_3.1.4
	6. Linux Machine, prefered Centos 6
	7. PostgreSQL database release 8 up and running on port 5432
			
	Without these don't proceed!
================================================================================
2. CONFIGURATION
================================================================================
	1. Copy the folder BankAccount to your home directory directory
			/home/USER_ACCT/
		
			
	2. Navigate to the directory 
			/home/USER_ACCT/BankAccount/masterbuild
			
	3. Effect the following changes on the file common_build.xml to include the project libraries:
	
			<path id="spring.and.commons">
       				 <fileset dir="/home/USER_ACCT/BankAccount/lib">
                			<include name="*.jar" />
       				 </fileset>
			</path>

			<path id="cxf.extra.libs">
        			<fileset dir="${env.CXF_HOME}">
                			<include name="*.jar" />
        			</fileset>
			</path>

	4. Copy bankaccount.sql to /var/lib/pgsql
			$ cp ../bankaccount.sql /var/lib/pgsql

	5. Login as postgres
 			su - postgres

	6. Start the postgres interractive terminal
			$ psql

	7. Resore bankaccount database
			postgres=# \i bankaccount.sql

================================================================================


3. DEPLOYING
================================================================================
	1. Open a terminal and navigate to:
			/home/USER_ACCT/BankAccount/masterbuild
			
	2. Type in the command
			ant -p
		to list available ant tasks
		
	3. Enter the command
			chmod a+x Script.sh; ./Script.sh
		to deploy the web service
			
	4. Open a browser window and enter the following url:
			http://localhost:8080/BankAccount/minibank/balance
			
	5. If successful you will see something similar to

	{

 	   "Balance": {
 	       "active_balance": 590,
 	       "date": "2017/07/03",
 	       "message": "Successful",
  	      "resultCode": 111
 	   }

	}

	 printed to your browser screen.

4. Testing an already up and running version
================================================================================

	To test an already deployed version from the terminal make sure you are connected to the internet:

	1. Balance
	------------

	~$ curl -vv http://196.202.219.252:8080/BankAccount/minibank/balance

	* About to connect() to 196.202.219.252 port 8080 (#0)
	*   Trying 196.202.219.252...
	* Connected to 196.202.219.252 (196.202.219.252) port 8080 (#0)
	> GET /BankAccount/minibank/balance HTTP/1.1
	> User-Agent: curl/7.29.0
	> Host: 196.202.219.252:8080
	> Accept: */*
	> 
	< HTTP/1.1 200 OK
	< Server: ZXWAS/1.1
	< Date: Sun, 02 Jul 2017 22:51:37 GMT
	< Content-Type: application/json
	< Transfer-Encoding: chunked
	< 
	* Connection #0 to host 196.202.219.252 left intact
	{"Balance":{"active_balance":590,"date":"2017/07/03","message":"Successful","resultCode":111}}[ramson@ramson ~]$

	2. Withdraw
	------------
	
	~$ curl -vv http://196.202.219.252:8080/BankAccount/minibank/withdraw/1000 --request post

	   * About to connect() to 196.202.219.252 port 8080 (#0)
	   *   Trying 196.202.219.252...
	   * Connected to 196.202.219.252 (196.202.219.252) port 8080 (#0)
	   > post /BankAccount/minibank/withdraw/1000 HTTP/1.1
	   > User-Agent: curl/7.29.0
	   > Host: 196.202.219.252:8080
	   > Accept: */*
	   > 
 	   < HTTP/1.1 200 OK
	   < Server: ZXWAS/1.1
	   < Date: Sun, 02 Jul 2017 22:53:42 GMT
	   < Content-Type: application/json
	   < Transfer-Encoding: chunked
	   < 
	   * Connection #0 to host 196.202.219.252 left intact
	   {"Withdraw":{"date":"2017/07/03","message":"Transaction could not be completed due to insufficient balance","resultCode":"000","withdraw_amount":1000}}


	3. Deposit
	-----------

	~$ curl -vv http://196.202.219.252:8080/BankAccount/minibank/deposit/10 --request post
	* About to connect() to 196.202.219.252 port 8080 (#0)
	*   Trying 196.202.219.252...
	* Connected to 196.202.219.252 (196.202.219.252) port 8080 (#0)
	> post /BankAccount/minibank/deposit/10 HTTP/1.1
	> User-Agent: curl/7.29.0
	> Host: 196.202.219.252:8080
	> Accept: */*
	> 
	< HTTP/1.1 200 OK
	< Server: ZXWAS/1.1
		< Date: Sun, 02 Jul 2017 22:57:39 GMT
	< Content-Type: application/json
	< Transfer-Encoding: chunked
	< 
	* Connection #0 to host 196.202.219.252 left intact
	{"Deposit":{"date":"2017/07/03","deposit_amount":10,"message":"Successful Transaction","resultCode":111}}

