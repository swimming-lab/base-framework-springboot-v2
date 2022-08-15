INSERT INTO AUTHORITY (AUTHORITY_NAME)
values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME)
values ('ROLE_ADMIN');

INSERT INTO users (id, email, password, image, name, created_at, updated_at)
VALUES (101, 'admin@admin.admin', '$2a$10$FwTyB4dy9JFalDTsOBMxV.gtUE3FikM0MRm3aQbPJqYd9JY3gcWtG', NULL, 'admin', now(), now());
-- password=admin
INSERT INTO users (id, email, password, image, name, created_at, updated_at)
VALUES (102, 'test@test.test', '$2a$10$eEom3YDJu1illn94/rtYY.JHwk193hQ6QPHsMNXeMqWjj/Xvgfbaq', NULL, 'test', now(), now());
-- password=test

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME)
values (101, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME)
values (102, 'ROLE_USER');