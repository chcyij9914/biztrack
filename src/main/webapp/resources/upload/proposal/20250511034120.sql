drop user c##biztrack cascade;
CREATE USER c##biztrack IDENTIFIED BY biztrack;
GRANT CONNECT, RESOURCE TO c##biztrack;
GRANT CREATE VIEW TO c##biztrack;
ALTER USER c##biztrack QUOTA 1024M ON USERS;