# EmaApp

### acualizar rotacion

UPDATE reporte_mercaderista AS r1
JOIN (
    SELECT t1.id, t1.tienda, t1.producto, t1.fecha, (t1.stock - t2.stock) AS rotacion
    FROM reporte_mercaderista AS t1
    LEFT JOIN reporte_mercaderista AS t2 ON t1.tienda = t2.tienda AND t1.producto = t2.producto AND WEEK(t1.fecha) = WEEK(t2.fecha) + 1
    WHERE WEEK(t1.fecha) <> WEEK(NOW())
) AS r2 ON r1.id = r2.id
SET r1.rotacion = r2.rotacion;


### Eliminar datos del dia 


DELETE FROM reporte_mercaderista WHERE DATE(fecha) = CURDATE();


