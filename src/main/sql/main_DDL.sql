create table ABISCOMPANIES
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
);

create table ABISCOURSES
(
    CID     CHAR(4),
    CSTITLE CHAR(45)     not null,
    CLTITLE VARCHAR2(60),
    CDUR    NUMBER       not null,
    CAPRICE NUMBER(9, 2) not null
);

create table ABISENROLMENTS
(
    E_SNO     decimal,
    ENO       decimal,
    E_PNO     decimal,
    EPAY      decimal(9, 2),
    E_CONO    decimal,
    ECANCEL   character,
    EINV_CONO decimal
);

create table ABISPERSONS
(
    PNO     NUMBER,
    PLNAME  CHAR(40) not null,
    PFNAME  VARCHAR2(15),
    PFUNC   CHAR(20),
    PA_CONO NUMBER,
    PADEPT  CHAR(30),
    PTEL    CHAR(16),
    PSEX    CHAR
);

create table ABISSESSIONS
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
);

create table TUTCOMPANIES
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
);

create table TUTCOURSES
(
    CID     character(4),
    CSTITLE character(20),
    CLTITLE varchar(60),
    CDUR    decimal,
    CAPRICE decimal(9, 2)
);

create table TUTENROLMENTS
(
    E_SNO     decimal,
    ENO       decimal,
    E_PNO     decimal,
    EPAY      decimal(9, 2),
    E_CONO    decimal,
    ECANCEL   character,
    EINV_CONO decimal
);

create table TUTPERSONS
(
    PNO     decimal,
    PLNAME  character(20),
    PFNAME  varchar(15),
    PFUNC   character(20),
    PA_CONO decimal,
    PADEPT  character(20),
    PTEL    character(16),
    PSEX    character
);

create table TUTSESSIONS
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
);

create table sqlite_master
(
    type     TEXT,
    name     TEXT,
    tbl_name TEXT,
    rootpage INT,
    sql      TEXT
);

