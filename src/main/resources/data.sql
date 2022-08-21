-- ROLE
INSERT INTO AUTHORITY (authority_name)
VALUES ('ROLE_USER');
INSERT INTO AUTHORITY (authority_name)
VALUES ('ROLE_ADMIN');

-- USERS
INSERT INTO USERS (id, email, password, image, name, created_at, updated_at)
VALUES (101, 'admin@admin.admin', '$2a$10$FwTyB4dy9JFalDTsOBMxV.gtUE3FikM0MRm3aQbPJqYd9JY3gcWtG', NULL, 'admin', NOW(), NOW());
-- password=admin
INSERT INTO USERS (id, email, password, image, name, created_at, updated_at)
VALUES (102, 'test@test.test', '$2a$10$eEom3YDJu1illn94/rtYY.JHwk193hQ6QPHsMNXeMqWjj/Xvgfbaq', NULL, 'test', NOW(), NOW());
-- password=test

-- USER_AUTHORITY
INSERT INTO USER_AUTHORITY (user_id, authority_name)
VALUES (101, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (user_id, authority_name)
VALUES (102, 'ROLE_USER');