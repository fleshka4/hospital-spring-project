# Reception department of hospital

* [Introduction](#introduction)
* [REST API](#rest-api)
* [GUI](#gui)

## Introduction

This application implements this database scheme which represents the main idea of reception department of hospital.
Patients are admitted to the hospital. They are examined, diagnosed and put in the ward for treatment

![image](https://user-images.githubusercontent.com/54627378/168794000-063fd4a3-cc21-48bf-8fe1-30a1db1f6a9f.png)  

For convenience, the [rest api](#rest-api) and [GUI](#gui) are implemented in this project

## REST API

* [Info](#info)
* [Authorisation](#authorisation)
* [Diagnosis](#diagnosis)
* [Ward](#ward)
* [Person](#person)
* [User](#user)
* [UserRole](#userrole)

### Info

All get requests except `/api/users/` and `/api/roles` are permitted to all. Get `/api/users` can user with role
`USER` or `ADMIN`. Get `/api/roles` can user with role `ADMIN`. Post `/api/roles` can user with role `ADMIN`

### Authorisation

POST-request `/api/login` to login and take jwt token. You should provide json like this:
```
{
	"username" : "admin",
  	"password" : "admin",
  	"role" : "admin"
}
```

### Diagnosis

- Get all diagnoses `/api/diagnoses` (GET-request)
- Get diagnosis by id `/api/diagnoses/{id}` where id is your provided id (here and next use GET-request
without curly braces)
- Get diagnoses in ward `/api/diagnoses/ward/{id}` where id is ID of ward (GET-request)
- Create diagnosis `/api/diagnoses/add` (POST-request). You should provide json:
```
{
	"name" : "diagnosis_name"
}
```
- Create diagnosis `/api/diagnoses/addwith?name=name_diagnosis` (POST-request). name_diagnosis is your diagnosis name
- Delete diagnosis by id `/api/diagnoses/{id}` (DELETE-request)

### Ward

- Get all wards `/api/wards` (GET-request)
- Get ward by id `/api/wards/{id}` (GET-request)
- Get wards with diagnosis `/api/wards/diagnosis/{id}` where id is ID of diagnosis (GET-request)
- Get wards by max count greater than `/api/wards/greater/{number}` (GET-request)
- Create ward `/api/wards/add` (POST-request). You should provide json:
```
{
	"name" : "ward_name",
	"maxCount" : "number"
}
```
- Create ward `/api/wards/addwith?name=name_ward&maxcount=number` (POST-request). name_diagnosis is your ward name, 
number is your max count
- Delete ward by id `/api/wards/{id}` (DELETE-request)

### Person

- Get all people `/api/people` (GET-request)
- Get person by id `/api/people/{id}` (GET-request)
- Get people by lastname `/api/people/lastname/{lastname}` (GET-request)
- Get people by full name `/api/people/search?firstname=your_firstname&lastname=
your_lastname&middlename=your_middlename` (GET-request)
- Get number of people with diagnosis `/api/people/numberwithdiagnosis/{id}` by diagnosis id (GET-request)
- Get number of people in ward `/api/people/numberinward/{id}` by ward id (GET-request)
- Create person `/api/people/add` (POST-request). You should provide json:
```
{
	"firstName" : "firstName",
	"lastName" : "lastName",
	"middleName" : "middleName",
	"diagnosisId" : "diagnosisId",
	"wardId" : "wardId"
}
```
- Create person `/api/people/addwith?irstname=your_firstname&lastname=
your_lastname&middlename=your_middlename` (POST-request)
- Update diagnosis by person id `/api/people/{id}/diagnosis/{diagnosisId}` (PATCH-request)
- Update ward by person id `/api/people/{id}/ward/{wardId}` (PATCH-request)
- Delete person by id `/api/people/{id}` (DELETE-request)

### User

- Get all users `/api/users` (GET-request)
- Create user `/api/users/add` (POST-request). You should provide json:
```
{
	"username" : "username",
  	"password" : "password",
  	"role" : "role"
}
```

### UserRole

- Get all roles `/api/roles` (GET-request)
- Create role `/api/roles/add` (POST-request). You should provide json:
```
{
  	"role" : "role"
}
```

## GUI
### Home page
![home](https://user-images.githubusercontent.com/54627378/168797821-fba94c9d-0e7d-4dfb-9de1-f1ef16ea2019.png)

### Login page
![login_page](https://user-images.githubusercontent.com/54627378/168798998-7555fd3a-87c9-474a-8309-eeb2198c1d5d.png)

### People
![people](https://user-images.githubusercontent.com/54627378/168797872-fa0a47d9-b5db-4fdc-93bb-11ecac0170b8.png)

### Diagnoses
![diagnoses](https://user-images.githubusercontent.com/54627378/168797927-5ff678e1-fa08-4130-a2b4-7ccad249de56.png)

### Wards
![wards](https://user-images.githubusercontent.com/54627378/168798003-a8c805ce-e304-4424-88e1-2e72ea74c312.png)

### Users
![users](https://user-images.githubusercontent.com/54627378/168798289-b449b331-f9d3-49b8-978c-3123c85b0be7.png)

### Add new person
![new_person](https://user-images.githubusercontent.com/54627378/168798063-c1cd5adc-0a37-4b11-8e6a-af8dd8ee21c7.png)

### Delete person
![delete_person](https://user-images.githubusercontent.com/54627378/168798131-c1650edc-db7c-4972-819d-db50da4f7827.png)

### Add new diagnosis
![new_diagnosis](https://user-images.githubusercontent.com/54627378/168798182-9e047778-5329-43b6-a009-8f208805bd57.png)

### Add new ward
![new_ward](https://user-images.githubusercontent.com/54627378/168798245-90c500b0-8b9c-4725-a2a9-8cf111ab8ba8.png)
