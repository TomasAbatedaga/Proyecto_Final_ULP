-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-10-2023 a las 07:17:35
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inmobiliaria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratoalquiler`
--

CREATE TABLE `contratoalquiler` (
  `idContratoAlquiler` int(11) NOT NULL,
  `idInquilino` int(11) NOT NULL,
  `idPropiedadInmueble` int(11) NOT NULL,
  `fechaFinal` datetime NOT NULL,
  `fechaInical` date NOT NULL,
  `fechaRealizacion` date NOT NULL,
  `marca` varchar(50) NOT NULL,
  `idVendedor` int(11) NOT NULL,
  `idEstadoContrato` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadocontrato`
--

CREATE TABLE `estadocontrato` (
  `idEstadoContrato` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadolocal`
--

CREATE TABLE `estadolocal` (
  `idEstadoLocal` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estadolocal`
--

INSERT INTO `estadolocal` (`idEstadoLocal`, `nombre`, `descripcion`) VALUES
(1, 'A refaccionar', 'evaluar'),
(2, 'Habiltable', 'evaluar'),
(3, 'No habitable', 'evaluar'),
(4, 'En obra', 'evaluar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inquilino`
--

CREATE TABLE `inquilino` (
  `idInquilino` int(1) NOT NULL,
  `cuit` varchar(30) NOT NULL,
  `nombre` varchar(99) NOT NULL,
  `apellido` varchar(99) NOT NULL,
  `lugarTrabajo` varchar(150) NOT NULL,
  `dniGarante` int(11) NOT NULL,
  `nombreGarante` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inquilino`
--

INSERT INTO `inquilino` (`idInquilino`, `cuit`, `nombre`, `apellido`, `lugarTrabajo`, `dniGarante`, `nombreGarante`) VALUES
(1, '30287198908', 'Marga', 'Agerez', 'la Luna', 28976144, 'Te Garantizo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inspector`
--

CREATE TABLE `inspector` (
  `idInspector` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `especialidad` varchar(70) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inspector`
--

INSERT INTO `inspector` (`idInspector`, `nombre`, `apellido`, `especialidad`, `estado`) VALUES
(1, 'Enrique', 'Discepolo', 'Arquitecto', 1),
(2, 'Facundo', 'Figueroa', 'Ingeniero Agronomo', 1),
(3, 'Julieta', 'Lamar', 'Ingeniera Civil', 1),
(4, 'Carlos', 'Benitez', 'Electricista', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propiedadinmueble`
--

CREATE TABLE `propiedadinmueble` (
  `idPropiedad` int(11) NOT NULL,
  `idPropietario` int(11) NOT NULL,
  `accesibilidad` varchar(100) DEFAULT NULL,
  `caracteristicas` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `idEstadoLocal` int(11) NOT NULL,
  `precioTrazado` float NOT NULL,
  `idInspector` int(11) NOT NULL,
  `idTipoLocal` int(11) NOT NULL,
  `idZona` int(11) NOT NULL,
  `disponibilidad` tinyint(1) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `propiedadinmueble`
--

INSERT INTO `propiedadinmueble` (`idPropiedad`, `idPropietario`, `accesibilidad`, `caracteristicas`, `direccion`, `idEstadoLocal`, `precioTrazado`, `idInspector`, `idTipoLocal`, `idZona`, `disponibilidad`, `estado`) VALUES
(1, 1, 'Colectivo, trenes', 'No tenemos datos', 'Corrientes 1245', 4, 23400.7, 2, 3, 6, 1, 0),
(2, 1, 'Colectivo, trenes', 'No tenemos datos', NULL, 4, 23400.7, 2, 3, 6, 1, 0),
(3, 2, 'si', 'No se', 'Republica II', 1, 1200, 2, 1, 4, 1, 0),
(4, 3, 'No', 'feo', 'No se ', 4, 45, 1, 2, 5, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `idPropietario` int(11) NOT NULL,
  `dni` int(15) NOT NULL,
  `nombre` varchar(99) NOT NULL,
  `apellido` varchar(99) NOT NULL,
  `domicilio` varchar(999) NOT NULL,
  `telefono` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`idPropietario`, `dni`, `nombre`, `apellido`, `domicilio`, `telefono`) VALUES
(1, 27618971, 'Martín', 'Del Potro', 'La Rivera 1655', 55557865),
(2, 67890000, 'Abel', 'Troid', 'La republica', 2147483647),
(3, 565555555, 'luis', 'Busto', 'lafinur', 342224554);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipolocal`
--

CREATE TABLE `tipolocal` (
  `idTipoLocal` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `superficieMinima` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipolocal`
--

INSERT INTO `tipolocal` (`idTipoLocal`, `nombre`, `descripcion`, `superficieMinima`) VALUES
(1, 'Casa', 'Familiar', 140),
(2, 'Departamente', 'D', 50),
(3, 'PH', 'PH', 80),
(4, 'Cocheras', 'C', 20),
(5, 'Local', 'L', 53),
(6, 'Oficina', 'O', 30),
(7, 'Consultorio', 'Con', 23),
(8, 'Galpón', 'G', 800);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE `vendedor` (
  `idvendedor` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `cantidadVentas` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vendedor`
--

INSERT INTO `vendedor` (`idvendedor`, `nombre`, `apellido`, `cantidadVentas`, `estado`) VALUES
(1, 'Martín', 'Hernandez', 0, 1),
(2, 'María', 'Salmor', 0, 1),
(3, 'Eduardo', 'Blanco', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `zona`
--

CREATE TABLE `zona` (
  `idZona` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `zona`
--

INSERT INTO `zona` (`idZona`, `nombre`, `descripcion`, `estado`) VALUES
(1, 'San Nicolás', 'Comuna 1', 1),
(2, 'La Plata', 'Ciudad de las diagonales', 1),
(3, 'San Luis', 'Zona centro', 1),
(4, 'San Luis', 'Capital', 1),
(5, 'Villa María', 'Prov Córdoba', 1),
(6, 'Rosario', 'Prov Santa Fe', 1),
(7, 'Mar del Plata', 'Prov Buenos Aires', 1),
(8, 'rtfgfg', 'fgf', 1),
(9, 'San Luis ', 'Pringles ', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `contratoalquiler`
--
ALTER TABLE `contratoalquiler`
  ADD PRIMARY KEY (`idContratoAlquiler`),
  ADD KEY `idInquilino` (`idInquilino`),
  ADD KEY `idPropiedadInmueble` (`idPropiedadInmueble`),
  ADD KEY `idVendedor` (`idVendedor`),
  ADD KEY `idEstadoContrato` (`idEstadoContrato`);

--
-- Indices de la tabla `estadocontrato`
--
ALTER TABLE `estadocontrato`
  ADD PRIMARY KEY (`idEstadoContrato`);

--
-- Indices de la tabla `estadolocal`
--
ALTER TABLE `estadolocal`
  ADD PRIMARY KEY (`idEstadoLocal`),
  ADD UNIQUE KEY `nombre_unico` (`nombre`);

--
-- Indices de la tabla `inquilino`
--
ALTER TABLE `inquilino`
  ADD PRIMARY KEY (`idInquilino`),
  ADD UNIQUE KEY `dni` (`dniGarante`),
  ADD UNIQUE KEY `el_cuit_debe_ser_unico` (`cuit`);

--
-- Indices de la tabla `inspector`
--
ALTER TABLE `inspector`
  ADD PRIMARY KEY (`idInspector`);

--
-- Indices de la tabla `propiedadinmueble`
--
ALTER TABLE `propiedadinmueble`
  ADD PRIMARY KEY (`idPropiedad`),
  ADD KEY `idEstadoLocal` (`idEstadoLocal`),
  ADD KEY `idInspector` (`idInspector`),
  ADD KEY `idPropietario` (`idPropietario`),
  ADD KEY `idTipoLocal` (`idTipoLocal`),
  ADD KEY `idZona` (`idZona`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`idPropietario`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `tipolocal`
--
ALTER TABLE `tipolocal`
  ADD PRIMARY KEY (`idTipoLocal`),
  ADD UNIQUE KEY `nombreLocal_unico` (`nombre`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`idvendedor`);

--
-- Indices de la tabla `zona`
--
ALTER TABLE `zona`
  ADD PRIMARY KEY (`idZona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `contratoalquiler`
--
ALTER TABLE `contratoalquiler`
  MODIFY `idContratoAlquiler` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estadocontrato`
--
ALTER TABLE `estadocontrato`
  MODIFY `idEstadoContrato` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estadolocal`
--
ALTER TABLE `estadolocal`
  MODIFY `idEstadoLocal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `inquilino`
--
ALTER TABLE `inquilino`
  MODIFY `idInquilino` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `inspector`
--
ALTER TABLE `inspector`
  MODIFY `idInspector` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `propiedadinmueble`
--
ALTER TABLE `propiedadinmueble`
  MODIFY `idPropiedad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `propietario`
--
ALTER TABLE `propietario`
  MODIFY `idPropietario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipolocal`
--
ALTER TABLE `tipolocal`
  MODIFY `idTipoLocal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  MODIFY `idvendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `zona`
--
ALTER TABLE `zona`
  MODIFY `idZona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contratoalquiler`
--
ALTER TABLE `contratoalquiler`
  ADD CONSTRAINT `contratoAlquiler_ibfk_1` FOREIGN KEY (`idInquilino`) REFERENCES `inquilino` (`idInquilino`),
  ADD CONSTRAINT `contratoAlquiler_ibfk_2` FOREIGN KEY (`idPropiedadInmueble`) REFERENCES `propiedadinmueble` (`idPropiedad`),
  ADD CONSTRAINT `contratoAlquiler_ibfk_3` FOREIGN KEY (`idVendedor`) REFERENCES `vendedor` (`idVendedor`),
  ADD CONSTRAINT `contratoAlquiler_ibfk_4` FOREIGN KEY (`idEstadoContrato`) REFERENCES `estadocontrato` (`idEstadoContrato`);

--
-- Filtros para la tabla `propiedadinmueble`
--
ALTER TABLE `propiedadinmueble`
  ADD CONSTRAINT `propiedadInmueble_ibfk_1` FOREIGN KEY (`idEstadoLocal`) REFERENCES `estadolocal` (`idEstadoLocal`),
  ADD CONSTRAINT `propiedadInmueble_ibfk_2` FOREIGN KEY (`idInspector`) REFERENCES `inspector` (`idInspector`),
  ADD CONSTRAINT `propiedadInmueble_ibfk_3` FOREIGN KEY (`idPropietario`) REFERENCES `propietario` (`idPropietario`),
  ADD CONSTRAINT `propiedadInmueble_ibfk_4` FOREIGN KEY (`idTipoLocal`) REFERENCES `tipolocal` (`idTipoLocal`),
  ADD CONSTRAINT `propiedadInmueble_ibfk_5` FOREIGN KEY (`idZona`) REFERENCES `zona` (`idZona`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
