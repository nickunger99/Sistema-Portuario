    // contagem de importacao por cliente
SELECT  cliente, count(categoria) as importacao
 FROM challenger1.container
 where categoria = 'importacao'
 group by cliente;

    // contagem de exportacao por cliente
 SELECT cliente, count(categoria) as exportacao
 FROM challenger1.container
 where categoria = 'exportacao'
 group by cliente;
 
    // contagem de movimentos por cliente
 SELECT  container.cliente ,count(movimento.container_id) as qtd_movimentos
 FROM challenger1.container, challenger1.movimento
 where movimento.container_id = container.id
 group by cliente;
 
 
    // total de container de importacao
 SELECT count(categoria) as qtd_importacao
 from challenger1.container
 where categoria = "importacao";
    // total de container de exportacao
 SELECT count(categoria) as qtd_exportacao
 from challenger1.container
 where categoria = "exportacao";


use challenger1;
insert into container values(1,'importacao','Google', 'cheio', 40, 'abcd1234567');
insert into container values(2,'importacao','Facebook', 'cheio', 40, 'abcd1234568');
insert into container values(3,'importacao','Instagram', 'cheio', 40, 'abcd1234569');
insert into container values(4,'importacao','Youtube', 'cheio', 40, 'abcd1234561');
insert into container values(5,'exportacao','Google', 'cheio', 40, 'abcd2234567');
insert into container values(6,'exportacao','Facebook', 'cheio', 40, 'abcd2234568');
insert into container values(7,'exportacao','Instagram', 'cheio', 40, 'abcd2234569');
insert into container values(8,'exportacao','Youtube', 'cheio', 40, 'abcd2234561');
insert into container values(9,'importacao','Google', 'cheio', 40, 'abcd1234512');
insert into container values(10,'importacao','Google', 'cheio', 40, 'abcd1234513');
insert into container values(11,'importacao','Google', 'cheio', 40, 'abcd1234514');
insert into container values(12,'importacao','Google', 'cheio', 40, 'abcd1234515');
insert into container values(13,'importacao','Google', 'cheio', 40, 'abcd1234511');






