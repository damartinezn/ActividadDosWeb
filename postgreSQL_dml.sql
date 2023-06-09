delete from libro_genero;
delete from critica;
delete from libro;
delete from genero;

INSERT INTO genero (codigo,descripcion,nombre) VALUES
	(1,'Descripcion de romance','Romance'),
	(2,'Descripcion de despido','Despido'),
	(3,'Descripcion de fantasía','Fantasía'),
	(4,'Descripcion de terror','Terror'),
	(5,'Descripcion de novela','Novela'),
	(6,'Descripcion de ensayo','Ensayo'),
	(7,'Descripcion de thriller','Thriller'),
	(8,'Descripcion de cuento','Cuento'),
	(9,'Descripcion de ciencia ficción','Ciencia Ficción'),
	(10,'Descripcion de ciencia','Ciencia');

INSERT INTO libro (libro_id,anio_publicacion,autor,cantidad,editorial,imagen,isbn10,isbn13,sinopsis,titulo) VALUES
	(10,'2021-12-31','Stephen King',10,'Estados Unidos','https://cdn.culturagenial.com/es/imagenes/cuento-hadas-king.jpg','123456789A','1234567890MCM','El libro se centra en Charlie, un muchacho que ha aprendido a valerse por sí mismo desde muy pequeño. Su vida dio un giro cuando su madre fue atropellada y su padre empezó a tomar sin control.','Cuento de hadas'),
	(11,'2022-01-01','Luz Gabás',5,'España','https://cdn.culturagenial.com/es/imagenes/lejos-de-luisiana.jpg','123456789B','1234567890MCA','En pleno siglo XVIII, Francia cede a España tierras de alrededor de río Misisipi. La transición de poder no es nada pacífica e Ishcate, un joven indio de la tribu de las Kaskaskia, sufre las consecuencias. Sumido en una etapa de incertidumbre, el protagonista ve como todos sus amigos franceses se marchan del lugar. La rebelión de indígenas contra los españoles se intensifica, además se suma la guerra de independencia de Norteamérica.','Lejos de Luisiana'),
	(12,'2021-10-10','María Martínez',8,'España','https://cdn.culturagenial.com/es/imagenes/cuando-no-queden-mas-estrellas-que-contar-cke.jpg','123456789C','1234567890MCD','Por qué te lo recomendamos: supone una historia narrada con mucha sensibilidad que no podrás parar de leer. Amor, superación y perdón son los ingredientes esenciales de esta emotiva historia.','Cuando no queden más estrellas que contar'),
	(13,'2021-08-08','Elizabeth Kolbert',0,'Estados Unidos','https://cdn.culturagenial.com/es/imagenes/bajo-un-cielo-blanco-portada-cke.jpg','123456789D','1234567890MCE','Elizabeth Kolbert reflexiona sobre el mundo que hemos creado y los cambios que hemos ocasionado en nuestro planeta. En su recorrido habla con especialistas biólogos, físicos, ingenieros de distintas partes del mundo que, con distintos proyectos, tratan de hacer un mundo más sostenible.','Bajo un cielo Blanco');

INSERT INTO libro_genero (libro_id,codigo) VALUES
	(10,4),
	(10,6),
	(11,5),
	(12,1),
	(12,5),
	(12,8),
	(13,5);

INSERT INTO critica (critica_id, calificacion, comentario, usuario, libro_id) VALUES
	(10, 10.0, 'Es ideal para adentrarte en un mundo de fantasía y un siniestro cuento de hadas que te hará reflexionar acerca del bien y el mal', 'damartinez', 10),
	(11, 10.0, 'Es un mundo de fantasía y un siniestro cuento de hadas que te hará reflexionar en la vida', 'usuario2', 10),
	(12, 8.0, 'Es perfecta para adentrarte en una historia de amor en tiempos convulsos, con una trama emocionante y un contexto histórico muy interesante', 'usuario3', 11),
	(13, 8.5, 'Linda historia de amor en tiempos convulsos, con una trama emocionante y un contexto histórico muy interesante', 'usuario4', 11);



