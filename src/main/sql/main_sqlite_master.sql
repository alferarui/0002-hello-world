INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'ABISCOURSES', 'ABISCOURSES', 2, 'CREATE TABLE ABISCOURSES
(
    CID     CHAR(4),
    CSTITLE CHAR(45)     not null,
    CLTITLE VARCHAR2(60),
    CDUR    NUMBER       not null,
    CAPRICE NUMBER(9, 2) not null
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'ABISPERSONS', 'ABISPERSONS', 3, 'CREATE TABLE ABISPERSONS
(
    PNO     NUMBER,
    PLNAME  CHAR(40) not null,
    PFNAME  VARCHAR2(15),
    PFUNC   CHAR(20),
    PA_CONO NUMBER,
    PADEPT  CHAR(30),
    PTEL    CHAR(16),
    PSEX    CHAR
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'ABISCOMPANIES', 'ABISCOMPANIES', 6, 'CREATE TABLE ABISCOMPANIES
(
    CONO     decimal,
    CONAME   character(20),
    COSTREET varchar(45),
    COSTRNO  varchar(10),
    COTOWN   character(20),
    COTOWNNO character(10),
    COCOUNTR character(4),
    COTEL    character(16),
    COVAT    character(11),
    COBANKNO character(14),
    COC_PNO  decimal
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'ABISENROLMENTS', 'ABISENROLMENTS', 7, 'CREATE TABLE ABISENROLMENTS
(
    E_SNO     decimal,
    ENO       decimal,
    E_PNO     decimal,
    EPAY      decimal(9, 2),
    E_CONO    decimal,
    ECANCEL   character,
    EINV_CONO decimal
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'ABISSESSIONS', 'ABISSESSIONS', 8, 'CREATE TABLE ABISSESSIONS
(
    SNO       decimal,
    SDATE     datetime,
    SINS_PNO  decimal,
    SLOC_CONO decimal,
    SROOM     character(8),
    SORG_CONO decimal,
    SKIND     character,
    SINCOMES  decimal(9, 2),
    SCANCEL   character,
    S_CID     character(4)
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'TUTCOMPANIES', 'TUTCOMPANIES', 9, 'CREATE TABLE TUTCOMPANIES
(
    CONO     decimal,
    CONAME   character(20),
    COSTREET varchar(45),
    COSTRNO  varchar(10),
    COTOWN   character(20),
    COTOWNNO character(10),
    COCOUNTR character(4),
    COTEL    character(16),
    COVAT    character(11),
    COBANKNO character(14),
    COC_PNO  decimal
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'TUTCOURSES', 'TUTCOURSES', 10, 'CREATE TABLE TUTCOURSES
(
    CID     character(4),
    CSTITLE character(20),
    CLTITLE varchar(60),
    CDUR    decimal,
    CAPRICE decimal(9, 2)
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'TUTENROLMENTS', 'TUTENROLMENTS', 11, 'CREATE TABLE TUTENROLMENTS
(
    E_SNO     decimal,
    ENO       decimal,
    E_PNO     decimal,
    EPAY      decimal(9, 2),
    E_CONO    decimal,
    ECANCEL   character,
    EINV_CONO decimal
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'TUTPERSONS', 'TUTPERSONS', 12, 'CREATE TABLE TUTPERSONS
(
    PNO     decimal,
    PLNAME  character(20),
    PFNAME  varchar(15),
    PFUNC   character(20),
    PA_CONO decimal,
    PADEPT  character(20),
    PTEL    character(16),
    PSEX    character
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'TUTSESSIONS', 'TUTSESSIONS', 13, 'CREATE TABLE TUTSESSIONS
(
    SNO       decimal,
    SDATE     datetime,
    SINS_PNO  decimal,
    SLOC_CONO decimal,
    SROOM     character(8),
    SORG_CONO decimal,
    SKIND     character,
    SINCOMES  decimal(9, 2),
    SCANCEL   character,
    S_CID     character(4)
)');
