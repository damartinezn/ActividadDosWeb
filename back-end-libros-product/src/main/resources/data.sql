INSERT INTO libro (libro_id,anio_publicacion,autor,cantidad,editorial,imagen,isbn10,isbn13,sipnosis,titulo) VALUES
	(1,'2023-04-09','Un sueño perdido',5,'Micaela Manosalvas','test MCA','1234567MCA','1234567884MCA','Como no extrañarte.','Sin sueños'),
    (2,'2021-04-09','prueba',5,'Julio Berne','test JBE','1234567JBE','1234567884JBE','viaje al centro de la tierra.','viaje al centro de la tierra');

INSERT INTO critica(critica_id,calificacion,comentario,usuario,libro_id) VALUES
    (1,10, "una tontera","damartinez",1),
    (1,10, "una tontera","damartinez",2);