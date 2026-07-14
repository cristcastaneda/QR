DROP DATABASE IF EXISTS sistema_inventario;
CREATE DATABASE sistema_inventario;
USE sistema_inventario;

CREATE TABLE PROVEEDOR (
    cod_pro INT PRIMARY KEY,
    nom_pro VARCHAR(100) NOT NULL,
    nit_pro VARCHAR(30),
    tel_pro VARCHAR(30),
    dir_pro VARCHAR(150),
    con_pro VARCHAR(100)
);

CREATE TABLE EMPLEADO (
    cod_emp INT PRIMARY KEY,
    nom_emp VARCHAR(100) NOT NULL,
    cargo_emp ENUM('Empleado de bodega', 'Operador comercial') NOT NULL,
    tel_emp VARCHAR(30),
    fecha_ing_emp DATE
);

CREATE TABLE CLIENTE (
    cod_cli INT PRIMARY KEY,
    nom_cli VARCHAR(100) NOT NULL,
    dir_cli VARCHAR(150),
    nit VARCHAR(30),
    con_cli VARCHAR(100),
    tel_cli VARCHAR(30)
);

CREATE TABLE PRODUCTO (
    cod_prod INT PRIMARY KEY,
    nom_prod VARCHAR(100) NOT NULL,
    categ_prod VARCHAR(80),
    fecha_venc DATE,
    precio_ven_prod DECIMAL(12, 2),
    unidad_med_prod VARCHAR(40)
);

CREATE TABLE MAQUINA (
    cod_maq INT PRIMARY KEY,
    cod_serie VARCHAR(80),
    ubic_maq VARCHAR(150),
    estado_maq ENUM('Activo', 'Desactivado') NOT NULL DEFAULT 'Activo',
    fecha_ins_maq DATE,
    cod_cli INT NOT NULL,
    FOREIGN KEY (cod_cli) REFERENCES CLIENTE(cod_cli)
);

CREATE TABLE ORDEN_COMPRA (
    cod_ord_com INT PRIMARY KEY,
    fecha_ord_com DATE NOT NULL,
    estado_ord_com ENUM('En proceso', 'Completado') NOT NULL DEFAULT 'En proceso',
    fecha_rec_ord_com DATE,
    cod_pro INT NOT NULL,
    cod_emp INT NOT NULL,
    FOREIGN KEY (cod_pro) REFERENCES PROVEEDOR(cod_pro),
    FOREIGN KEY (cod_emp) REFERENCES EMPLEADO(cod_emp)
);

CREATE TABLE DETALLE_ORDEN_COMPRA (
    cod_ord_com INT NOT NULL,
    cod_prod INT NOT NULL,
    cant_sol INT NOT NULL,
    precio_uni DECIMAL(12, 2) NOT NULL,
    sub_total DECIMAL(12, 2) NOT NULL,
    PRIMARY KEY (cod_ord_com, cod_prod),
    FOREIGN KEY (cod_ord_com) REFERENCES ORDEN_COMPRA(cod_ord_com),
    FOREIGN KEY (cod_prod) REFERENCES PRODUCTO(cod_prod)
);

CREATE TABLE DESPACHO (
    cod_desp INT PRIMARY KEY,
    fecha_desp DATE NOT NULL,
    cod_emp INT NOT NULL,
    cod_maq INT NOT NULL,
    FOREIGN KEY (cod_emp) REFERENCES EMPLEADO(cod_emp),
    FOREIGN KEY (cod_maq) REFERENCES MAQUINA(cod_maq)
);

CREATE TABLE DETALLE_DESPACHO (
    cod_desp INT NOT NULL,
    cod_prod INT NOT NULL,
    cantidad_desp INT NOT NULL,
    PRIMARY KEY (cod_desp, cod_prod),
    FOREIGN KEY (cod_desp) REFERENCES DESPACHO(cod_desp),
    FOREIGN KEY (cod_prod) REFERENCES PRODUCTO(cod_prod)
);

CREATE TABLE INVENTARIO_BODEGA (
    cod_prod INT PRIMARY KEY,
    cant_disp INT NOT NULL DEFAULT 0,
    stock_min INT NOT NULL DEFAULT 100,
    fecha_ult_act DATE,
    FOREIGN KEY (cod_prod) REFERENCES PRODUCTO(cod_prod)
);

CREATE TABLE INVENTARIO_MAQUINA (
    cod_maq INT NOT NULL,
    cod_prod INT NOT NULL,
    cant_act INT NOT NULL DEFAULT 0,
    stock_min INT NOT NULL DEFAULT 20,
    fecha_ult_rec DATE,
    PRIMARY KEY (cod_maq, cod_prod),
    FOREIGN KEY (cod_maq) REFERENCES MAQUINA(cod_maq),
    FOREIGN KEY (cod_prod) REFERENCES PRODUCTO(cod_prod)
);

INSERT INTO CLIENTE (cod_cli, nom_cli, dir_cli, nit, con_cli, tel_cli) VALUES
('101', 'Distribuciones La Sabana S.A.S.', 'Calle 80 # 68-45, Bogota', '901245778-3', 'Mariana Duarte', '601 482 1930'),
('102', 'Cafe Andino del Valle', 'Carrera 5 # 12-40, Armenia', '900732114-8', 'Jorge Salcedo', '606 741 2208');

INSERT INTO EMPLEADO (cod_emp, nom_emp, cargo_emp, tel_emp, fecha_ing_emp) VALUES
('201', 'Laura Martinez Rojas', 'Empleado de bodega', '310 584 9021', '2024-02-12'),
('202', 'Andres Felipe Mora', 'Operador comercial', '317 442 8806', '2023-09-04');

INSERT INTO PROVEEDOR (cod_pro, nom_pro, nit_pro, tel_pro, dir_pro, con_pro) VALUES
('301', 'Comercializadora Nutresa S.A.S.', '890903628-9', '604 335 1902', 'Carrera 52 # 2-38, Medellin', 'Claudia Restrepo'),
('302', 'Super de Alimentos S.A.S.', '890680065-2', '606 887 9620', 'Km 10 Via Magdalena, Manizales', 'Hector Molina');

INSERT INTO PRODUCTO (cod_prod, nom_prod, categ_prod, fecha_venc, precio_ven_prod, unidad_med_prod) VALUES
('401', 'Chocoramo', 'Ponques', '2026-08-30', 2500.00, 'Unidad'),
('402', 'Papas Margarita Limon 39g', 'Snacks salados', '2026-10-15', 2200.00, 'Unidad'),
('403', 'Galletas Festival Fresa', 'Galletas', '2026-11-10', 1600.00, 'Unidad'),
('404', 'Chocolatina Jet 12g', 'Chocolates', '2027-01-02', 1000.00, 'Unidad'),
('405', 'Todo Rico Original 45g', 'Snacks salados', '2026-09-21', 2500.00, 'Unidad'),
('406', 'Gomitas Trululu Casquitos', 'Dulces', '2027-02-19', 2000.00, 'Unidad'),
('407', 'Mani Moto Original', 'Snacks', '2026-12-11', 1800.00, 'Unidad'),
('408', 'Barquillos Piazza Chocolate', 'Galletas', '2026-10-05', 1800.00, 'Unidad'),
('409', 'Nucita Doble Sabor', 'Dulces', '2027-03-17', 1200.00, 'Unidad'),
('4010', 'Barrilete', 'Dulces', '2027-05-23', 800.00, 'Unidad');

INSERT INTO MAQUINA (cod_maq, cod_serie, ubic_maq, estado_maq, fecha_ins_maq, cod_cli) VALUES
('501', 'SN-BG-24018', 'Bodega principal Bogota', 'Activo', '2025-01-20', '101'),
('502', 'SN-QD-24044', 'Planta Armenia linea 2', 'Activo', '2025-03-07', '102');
