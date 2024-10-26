create table ABISCOURSES
(
    CID     CHAR(4),
    CSTITLE CHAR(45)     not null,
    CLTITLE VARCHAR2(60),
    CDUR    NUMBER       not null,
    CAPRICE NUMBER(9, 2) not null
)
/

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
)
/
ALTER TABLE ABISPERSONS ADD (
        P_FULL_INFO VARCHAR2(255) GENERATED ALWAYS AS (
            '{' ||
            '"PersonId":' || TO_CHAR(PNO) || ',' ||
            '"FirstName":"' || PLNAME || '",' ||
            '"LastName":"' || PFNAME || '",' ||
            '"Phone":"' || PTEL || '"' ||
            '}'
            ) VIRTUAL
        )

/

create table ABISENROLMENTS
(
    E_SNO     NUMBER       not null,
    ENO       NUMBER       not null,
    E_PNO     NUMBER,
    EPAY      NUMBER(9, 2) not null,
    E_CONO    NUMBER       not null,
    ECANCEL   CHAR,
    EINV_CONO NUMBER       not null
)
/

create table ABISCOMPANIES
(
    CONO     NUMBER,
    CONAME   CHAR(45)     not null,
    COSTREET VARCHAR2(45) not null,
    COSTRNO  VARCHAR2(10),
    COTOWN   CHAR(45)     not null,
    COTOWNNO CHAR(10),
    COCOUNTR CHAR(4),
    COTEL    CHAR(16),
    COVAT    CHAR(11),
    COBANKNO CHAR(14),
    COC_PNO  NUMBER
)
/

create table ABISSESSIONS
(
    SNO       NUMBER,
    SDATE     DATE         not null,
    SINS_PNO  NUMBER       not null,
    SLOC_CONO NUMBER       not null,
    SROOM     CHAR(8)      not null,
    SORG_CONO NUMBER,
    SKIND     CHAR         not null,
    SINCOMES  NUMBER(9, 2) not null,
    SCANCEL   CHAR,
    S_CID     CHAR(4)      not null
)
/

