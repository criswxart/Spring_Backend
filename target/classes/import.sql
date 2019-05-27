/* Populate tabla clientes */

INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(1, 'Andrés', 'Guzmán', 'profesor@bolsadeideas.com', '92983289138', '2018-01-01','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(2, 'Mr. John', 'Doe', 'john.doe@gmail.com', '92983289138', '2018-01-02','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(3, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com','92983289138', '2018-01-03','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(4, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com','92983289138','2018-01-04','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(5, 'Erich', 'Gamma', 'erich.gamma@gmail.com','92983289138', '2018-02-01','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(6, 'Richard', 'Helm', 'richard.helm@gmail.com','92983289138', '2018-02-10','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(7, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com','92983289138', '2018-02-18','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(8, 'John', 'Vlissides', 'john.vlissides@gmail.com', '92983289138','2018-02-28','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(9, 'Dr. James', 'Gosling', 'james.gosling@gmail.com', '92983289138','2018-03-03','2018-01-01', 'M');
INSERT INTO usuarios (id, nombre, apellidos, direccion, telefono, fecha_nacimiento, fecha_ingreso, sexo) VALUES(10, 'Magma', 'Lee', 'magma.lee@gmail.com','92983289138', '2018-03-04','2018-01-01', 'M');

INSERT INTO planes (usuario_id, id, tipoPlan, objetivos, fecha_incio, fecha_fin, observaciones, estado) VALUES (2, 1, 'Completo','Ayudar al usuario', '2018-01-01', '2018-01-02','Drogas fuertes', 'Evaluacion');
INSERT INTO planes (usuario_id, id, tipoPlan, objetivos, fecha_incio, fecha_fin, observaciones, estado) VALUES (3, 2, 'Entrada','Ayudar al usuario', '2018-03-01', '2018-01-02','Drogas fuertes', 'Evaluacion');
INSERT INTO planes (usuario_id, id, tipoPlan, objetivos, fecha_incio, fecha_fin, observaciones, estado) VALUES (5, 3, 'Cualquier cosa','Ayudar al usuario', '2019-01-01', '2020-01-02','Drogas fuertes', 'Evaluacion');
INSERT INTO planes (usuario_id, id, tipoPlan, objetivos, fecha_incio, fecha_fin, observaciones, estado) VALUES (7, 4, 'Completo','Ayudar al usuario', '2018-06-01', '2018-01-02','Drogas fuertes', 'Evaluacion');
INSERT INTO planes (usuario_id, id, tipoPlan, objetivos, fecha_incio, fecha_fin, observaciones, estado) VALUES (8, 5, 'Completo','Ayudar al usuario', '2018-01-01', '2018-01-02','Drogas fuertes', 'Evaluacion');

/* Creamos algunos usuarios con sus roles */
INSERT INTO profesionales (id, nombre, apellidos, especialidad, telefono, email, password, enabled, img) VALUES (1,'Cristian','Godoy','Terapeuta','+5698765646','criswxart@gmail.com', '$2a$10$899QyHVYAFmo0GnBT3BbwOpVQZw6i8ACnIealOH1NV3T9Z39jLQsu',1,'');
INSERT INTO profesionales (id, nombre, apellidos, especialidad, telefono, email, password, enabled, img) VALUES (2, 'Cristian','Godoy','Técnico','+5698765646','prueba@gmail.com','$2a$10$899QyHVYAFmo0GnBT3BbwOpVQZw6i8ACnIealOH1NV3T9Z39jLQsu',1,'');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO profesional_roles (profesional_id, role_id) VALUES (1, 1);
INSERT INTO profesional_roles (profesional_id, role_id) VALUES (2, 2);
INSERT INTO profesional_roles (profesional_id, role_id) VALUES (2, 1);

/* */
INSERT INTO citas (profesional_id, plan_id, id, estado, fecha, hora) VALUES (1,1, 1, 'Pendiente','2018-01-01','08:00:00');


/*Rescate*/
INSERT INTO rescates ( plan_id, id, tipo_rescate, fecha) VALUES (2, 1, 'pruebaa', '2018-02-28');

/*Profesional - rescate*/
INSERT INTO profesional_rescates (profesional_id, rescate_id) VALUES (1, 1);

/* tipo intervencion */
INSERT INTO tipointervencion (id, nombre_tipo) VALUES (1, 'Ambulatorio');

/* insertar intervenciones */
INSERT INTO intervenciones (profesional_id, tipointervencion_id, plan_id, id, objetivo, descripcion, fecha_intervencion) VALUES (1, 1, 1, 1, 'ayudarlo','Ayudar al usuario', '2018-01-01');

