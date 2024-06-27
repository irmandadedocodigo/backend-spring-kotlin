INSERT INTO roles (id, "name")
VALUES(nextval('roles_id_seq'::regclass), 'ROLE_USER');

INSERT INTO roles (id, "name")
VALUES(nextval('roles_id_seq'::regclass), 'ROLE_ADMIN');
