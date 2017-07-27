DROP PROCEDURE IF EXISTS valoraciones_calcular_puntaje_total;
CREATE PROCEDURE valoraciones_calcular_puntaje_total(IN idCab INT)
BEGIN
	update	sucursal_valoraciones_cab
	set		puntaje_total = (select (sum(puntaje)/count(*)) as total from sucursal_valoraciones_det where id_sucursal_valoracion_cab = idCab)
	where	id = idCab;
END;

DROP TRIGGER IF EXISTS sucursal_valoraciones_det_insert;
CREATE TRIGGER sucursal_valoraciones_det_insert 
AFTER INSERT ON sucursal_valoraciones_det
FOR EACH ROW
BEGIN
	CALL valoraciones_calcular_puntaje_total(NEW.id_sucursal_valoracion_cab);
END;

DROP TRIGGER IF EXISTS sucursal_valoraciones_det_update;
CREATE TRIGGER sucursal_valoraciones_det_update 
AFTER UPDATE ON sucursal_valoraciones_det
FOR EACH ROW
BEGIN
	CALL valoraciones_calcular_puntaje_total(NEW.id_sucursal_valoracion_cab);
END;