## ERD
![img.png](img.png)

## API 명세서

### 1) 일정 생성 API
- URL: POST /schedules

### Request 설명
```
CreateScheduleRequest
{
  - 할일 제목: String (필수)
  - 할일 내용: String (필수)
}
```

### Request 예시
```json
{
  "title": "11월 20일 일정",
  "contents": "병원 가는 날"
}
```

### Response 설명
```
CreateScheduleResponse
{
  - 일정 아이디: Long (필수)
  - 유저 아이디: Long (필수)
  - 할일 제목: String (필수)
  - 할일 내용: String (필수)
  - 작성일: LocalDateTime (필수)
  - 수정일: LocalDateTime (필수)
}
```

### Response 예시
```json
{ 
  "id": 1,
  "userId": 1,
  "title": "11월 20일 일정",
  "contents": "병원 가는 날",
  "createdAt": "2025-11-18T15:42:46.937858",
  "modifiedAt": "2025-11-18T15:42:46.937858"
}  
```
### 상황별 코드
`201 Created`: 일정 생성 성공

### 설명
- 작성 유저명, 제목, 내용을 입력받아 새로운 일정을 생성합니다.

### 2) 일정 조회 API
- URL: GET /schedules/{scheduleId}

### Request 예시
`GET /schedules/1`

### Response 설명
```
GetScheduleResponse
{
  - 일정 아이디: Long (필수)
  - 유저 아이디: Long (필수)
  - 할일 제목: String (필수)
  - 할일 내용: String (필수)
  - 작성일: LocalDateTime (필수)
  - 수정일: LocalDateTime (필수)
}
```

### Response 예시
```json
{
  "id": 1,
  "userId": 1,
  "title": "11월 20일 일정",
  "contents": "병원 가는 날",
  "createdAt": "2025-11-18T15:42:46.937858",
  "modifiedAt": "2025-11-18T15:42:46.937858"
}
```
### 상황별 코드
`200 OK`: 일정 조회 성공

### 설명
- 일정을 단건 조회합니다.

### 3) 일정 전체 조회 API
- URL: GET /schedules

### Request 예시
`GET /schedules`

### Response 설명
```
GetScheduleResponse
[  
  {
    - 일정 아이디: Long (필수)
    - 유저 아이디: Long (필수)
    - 할일 제목: String (필수)
    - 할일 내용: String (필수)
    - 작성일: LocalDateTime (필수)
    - 수정일: LocalDateTime (필수)
  }
]  
```

### Response 예시
```json
[
  { 
    "id": 1,
    "userId": 1,
    "title": "11월 20일 일정",
    "contents": "병원 가는 날",
    "createdAt": "2025-11-18T15:42:46.937858",
    "modifiedAt": "2025-11-18T15:42:46.937858"
  }
]
```
### 상황별 코드
`200 OK`: 일정 조회 성공

### 설명
- 전체 일정을 조회합니다.

### 4) 일정 수정 API
- URL: PUT /schedules/{scheduleId}

### Request 설명
```
UpdateScheduleRequest
{
  - 할일 제목: String (필수)
  - 할일 내용: String (필수)
}
```

### Request 예시
```json
{
  "title": "11월 20일 일정 변경",
  "contents": "병원 예약 취소"
}
```

### Response 설명
```
UpdateScheduleResponse
{
  - 일정 아이디: Long (필수)
  - 유저 아이디: Long (필수)
  - 할일 제목: String (필수)
  - 할일 내용: String (필수)
  - 작성일: LocalDateTime (필수)
  - 수정일: LocalDateTime (필수)
}
```

### Response 예시
```json
{
  "id": 1,
  "userId": 1,
  "title": "11월 20일 일정 변경",
  "contents": "병원 예약 취소",
  "createdAt": "2025-11-18T15:42:46.937858",
  "modifiedAt": "2025-11-19T11:10:08.937858"
}
```
### 상황별 코드
`200 OK`: 일정 수정 성공

### 설명
- 일정을 수정합니다.

### 5) 일정 삭제 API
- URL: DELETE /schedules/{scheduleId}

### Request 예시
`DELETE /schedules/1`

### Response 예시
```json
(없음) 
```
### 상황별 코드
`204 No Content`: 일정 삭제 성공

### 설명
- 일정을 삭제합니다.

### 6) 회원가입 API
- URL: POST /users

### Request 설명
```
CreateUserRequest
{
  - 유저명: String (필수)
  - 이메일: String (필수)
  - 비밀번호: String (필수)
}
```

### Request 예시
```json
{
  "userName": "오은지",
  "email": "abcdef@gmail.com",
  "password": "12345678"
}
```

### Response 설명
```
CreateUserResponse
{
  - 유저 아이디: Long (필수)
  - 유저명: String (필수)
  - 이메일: String (필수)
  - 생성일: LocalDateTime (필수)
  - 수정일: LocalDateTime (필수)
}
```

### Response 예시
```json
{ 
  "id": 1,
  "userName": "오은지",
  "email": "abcdef@gmail.com",
  "createdAt": "2025-11-17T11:30:46.937858",
  "modifiedAt": "2025-11-17T11:30:46.937858"
}  
```
### 상황별 코드
- `201 Created`: 회원가입 성공
- `400 Bad Request`
    - INVALID_USERNAME: 틀린 유저명
    - INVALID_EMAIL_FORMAT: 이메일 형식 오류
    - PASSWORD_NOT_MET: 비밀번호 조건 미충족
- `409 Conflict`
    - EMAIL_DUPLICATE: 이미 가입된 이메일

### 설명
- 유저명, 이메일, 비밀번호를 입력받아 새로운 유저를 생성합니다.

### 7) 유저 조회 API
- URL: GET /users/{userId}

### Request 예시
`GET /users/1`

### Response 설명
```
GetUserResponse
{
  - 유저 아이디: Long (필수)
  - 유저명: String (필수)
  - 이메일: String (필수)
  - 생성일: LocalDateTime (필수)
  - 수정일: LocalDateTime (필수)
}
```

### Response 예시
```json
{
  "id": 1,
  "userName": "오은지",
  "email": "abcdef@gmail.com",
  "createdAt": "2025-11-17T11:30:46.937858",
  "modifiedAt": "2025-11-17T11:30:46.937858"
}
```
### 상황별 코드
`200 OK`: 유저 조회 성공

### 설명
- 유저를 단건 조회합니다.

### 8) 유저 전체 조회 API
- URL: GET /users

### Request 예시
`GET /users`

### Response 설명
```
GetUserResponse
[
  {
    - 유저 아이디: Long (필수)
    - 유저명: String (필수)
    - 이메일: String (필수)
    - 생성일: LocalDateTime (필수)
    - 수정일: LocalDateTime (필수)
  }
]  
```

### Response 예시
```json
[
  {
    "id": 1,
    "userName": "오은지",
    "email": "abcdef@gmail.com",
    "createdAt": "2025-11-17T11:30:46.937858",
    "modifiedAt": "2025-11-17T11:30:46.937858"
  }
]
```
### 상황별 코드
`200 OK`: 유저 조회 성공

### 설명
- 전체 유저를 조회합니다.

### 9) 유저 수정 API
- URL: PUT /users/{userId}

### Request 설명
```
UpdateUserRequest
{
  - 유저명: String (필수)
  - 비밀번호: String (필수)
}
```

### Request 예시
```json
{
  "userName": "오은지",
  "password": "87654321"
}
```

### Response 설명
```
UpdateUserResponse
{
  - 유저 아이디: Long (필수)
  - 유저명: String (필수)
  - 이메일: String (필수)
  - 생성일: LocalDateTime (필수)
  - 수정일: LocalDateTime (필수)
}
```

### Response 예시
```json
{
  "id": 1,
  "userName": "오은지",
  "email": "abcdef@gmail.com",
  "createdAt": "2025-11-17T11:30:46.937858",
  "modifiedAt": "2025-11-25T16:10:46.937858"
}
```
### 상황별 코드
`200 OK`: 정보 수정 성공
- `400 Bad Request`
    - INVALID_USERNAME: 틀린 유저명
    - PASSWORD_NOT_MET: 비밀번호 조건 미충족

### 설명
- 유저 정보를 수정합니다.
- 이메일은 변경 불가합니다. 유저명, 비밀번호만 변경할 수 있습니다.

### 10) 유저 삭제 API
- URL: DELETE /users/{userId}

### Request 예시
`DELETE /users/1`

### Response 예시
```json
(없음) 
```
### 상황별 코드
`204 No Content`: 유저 삭제 성공

### 설명
- 유저를 삭제합니다.

### 11) 로그인 API
- URL: POST /logins

### Request 설명
```
LoginRequest
{
  - 이메일: String (필수)
  - 비밀번호: String (필수)
}
```

### Request 예시
```json
{
  "email": "sparta@gmail.com",
  "password": "87654321"
}
```

### Response 설명
```
LoginResponse
{
    "유저 아이디": Long (필수),
    "이메일": String (필수),
    "유저명": String (필수)
}
```

### Response 예시
```json
{
  "id": 1,
  "email": "sparta@gmail.com",
  "userName": "오은지"
}  
```
### 상황별 코드
- `200 OK`: 로그인 성공
- `400 Bad Request`
    - INVALID_EMAIL_FORMAT: 이메일 형식 오류
- `401 Unauthorized`
    - USER_NOT_FOUND: 이메일 없음
    - INVALID_PASSWORD: 비밀번호 틀림

### 설명
- 사용자의 이메일, 비밀번호를 입력받고 로그인할 수 있습니다.
- 로그인에 성공하면 세션 ID를 쿠키(Set-Cookie 헤더)로 발행합니다.
- 예시) `Set-Cookie: SESSION=abc123; HttpOnly; Secure; SameSite=Lax; Path=/`

