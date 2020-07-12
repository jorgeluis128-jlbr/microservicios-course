INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('maverick','$2a$10$OlCyk5v5Z/VUDaecsQ/lZ.7GXvlHDxvsSMRFUTYRnnNCCERqwZxGe',true,'fernando', 'chavez', 'algo@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('maverickx','$2a$10$5pznmRK/ejz.YWtHFjqZQucpXyLmhGfWESIEHV65NkrKMi12iObOq',true,'Octavio', 'sanchez', 'algox@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('gvidal','$2a$10$5pznmRK/ejz.YWtHFjqZQucpXyLmhGfWESIEHV65NkrKMi12iObOq',true,'Graciela', 'Vidal Pascual', 'gvidal@gmail.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('jlbautista','$2a$10$5pznmRK/ejz.YWtHFjqZQucpXyLmhGfWESIEHV65NkrKMi12iObOq',true,'Jorge Luis', 'Bautista Reyes', 'jlbautista@gmail.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (4,1);


