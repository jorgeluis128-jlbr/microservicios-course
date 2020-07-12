insert into usuarios (username, password, enabled, nombre, apellido, email) values ('gvidal', '$2a$10$geH3dLgQ4DOfCNt4AelpaONW.Nn8qSFehwHXl3ajPn4JfVfyW8h5q', 1, 'Graciela', 'Vidal Pascual', 'gvidal@gmail.com');
insert into usuarios (username, password, enabled, nombre, apellido, email) values ('jlbautista', '$2a$10$b1XoqFWO0VAGzQ4uhmqrfOfPlTgd09tgq012BozGhUVEGyYChDpTi', 1, 'Jorge Luis', 'Buatista Reyes', 'jlbautista@gmail.com');
insert into usuarios (username, password, enabled, nombre, apellido, email) values ('fosanchez', '$2a$10$m/eQW./fbgaGY11BfFbE7uI0mivAWvS4mUvD8Ajtp.Oi7LWMxjo.6', 1, 'Fernando Octavio', 'Sanchez', 'fosanchez@gmail.com');

insert into roles(nombre) values ('ROLE_USER');
insert into roles(nombre) values ('ROLE_ADMIN');

insert into usuarios_roles(usuario_id, role_id) values (1,1);
insert into usuarios_roles(usuario_id, role_id) values (2,2);
insert into usuarios_roles(usuario_id, role_id) values (2,1);