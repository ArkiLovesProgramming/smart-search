# Backend project of SmartSearch
This is backend project for SmartSearch, providing **Restful APIs** to frontend project. This is made with help of conception of **microservices architecture**.
**Springboot** framework and **Spring security** are used in this project. It introduces **JWT** (JSON Web Token) to implement single sign-on(SSO). 
The whole backend project is deployed on the **AWS** ECS as seperated docker containers. 

# APIs documentation

# Smartsearch API documentation

## 0 Basic variable

> Base url: www.xxx.com

## 1	User Controller

## 1.1	Create a new user

> POST  /api/user/create

### Response body

● 200 response data format：JSON

## 1.2	User login

> POST  /api/user/login

### Response body

● 200 response data format：JSON

##### API decription

> A token will be returned to caller

## 1.3 User signup

> POST  /api/user/signup

### Response body

● 200 response data format：JSON

##### API decription

> repeated account will bechecked before creating the account. This is formally for registration of new user.

## 2	History Controller

## 2.1	Get the histories by token

> GET  /api/history/get

### Response body

● 200 response data format：JSON

##### API description

> Return search histories basing on the token, which is used to identify a logined user.

## 3	Nlp Controller

## 3.1	

> GET  /api/nlp/detect/{text}

### Response body

● 200 Response data format：JSON

##### API description

> Used to detect the sentiment of document content. This will give youa possibility that the target keyword can classified to positive, negative, neutral or mixed. 

## 4	Seach Controller

## 4.1	Title mataching search

> GET  /api/search/keyword/title/{keyword}

### Path Variable

| name | default value | description    |
| -------- | ------ | ------- |
| keyword  |        | keyword |

### Response body

● 200 Response data format：JSON

##### API description

> This is used when you are typing the keyword, and the most similar results will popout bellow the input. The keyword only tries to match with the title column of data.

## 4.2	search by keyword

> GET  /api/search/keyword/{keyword}

### Path Variable

| name | default value | description    |
| -------- | ------ | ------- |
| keyword  |        | keyword |

### Query Param

| name | default value | description    |
| -------- | ------ | -------- |
| fromPage |        | fromPage |

### Response body

● 200 Response data format：JSON

##### API description

> This api is for searching by keyword, It will decide whether to search from certain pageif you specify fromPage parameter, It will also decide whether to record current search keyword into database by examining if token is existing.

## 4.3	test

> GET  /api/search/test

### Response body

● 200 Response data format：JSON

##### API description

> 


