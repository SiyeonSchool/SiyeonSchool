
--------------------------------------------------------------------------------
--------------------                 °øÅë                    --------------------
--------------------------------------------------------------------------------

CREATE TABLE USERS(
    USER_NO NUMBER,
    USER_ID VARCHAR2(20),
    USER_PWD VARCHAR2(20),
    USER_NAME VARCHAR2(20),
    PHONE VARCHAR2(13),
    BIRTHDAY DATE,
    EMAIL VARCHAR2(100),
    ADDRESS VARCHAR2(100),
    ENROLL_DATE DATE,
    MODIFY_DATE DATE,
    PROFILE_FILE_NO NUMBER,
    QUESTION_NO NUMBER,
    QUESTION_ANSWER VARCHAR2(50),
    USER_AUTH CHAR(1),
    STATUS CHAR(1),
    GITHUB_URL VARCHAR2(100),
    NOTION_URL VARCHAR2(100)
)