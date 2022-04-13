CREATE TABLE Vaccinando (
Num_Tessera_Sanitaria           VARCHAR2(20) PRIMARY KEY,
Codice_Fiscale                  VARCHAR2(16) UNIQUE NOT NULL,
Nome                            VARCHAR2(25) NOT NULL,
Cognome                         VARCHAR2(25) NOT NULL,
Data_Nascita                    DATE NOT NULL,
Citta_Natale                    VARCHAR2(50) NOT NULL,
Citta                       	  VARCHAR2(50) NOT NULL,
Provincia                       VARCHAR2(2) NOT NULL,
CAP                             VARCHAR2(5),
e_mail                          VARCHAR2(40),
Username				  VARCHAR(50) NOT NULL,
Password				  VARCHAR2(50) NOT NULL,
NUMERO_TELEFONO             VARCHAR2(50) NOT NULL
);



CREATE TABLE Prenotazione(
Identificativo                      NUMBER(8, 0) PRIMARY KEY,
Codice_Fiscale                      VARCHAR2(50) NOT NULL,
Data_Ora_Previste                   DATE NOT NULL,
Luogo						VARCHAR2(50) NOT NULL,
Citta						VARCHAR2(50) NOT NULL,
Via						VARCHAR2(50) NOT NULL,
Civico					NUMBER(8,0) NOT NULL,
Provincia					VARCHAR2(50) NOT NULL,
Asl_di_appartenenza			VARCHAR2(50) NOT NULL,
Distretto					NUMBER(8,0) NOT NULL
);



CREATE TABLE Operatore_Sanitario_Responsabile (
Num_Iscrizione_Ordine_Medici            NUMBER(10, 0) PRIMARY KEY,
Nome                                    VARCHAR2(25) NOT NULL,
Cognome                                 VARCHAR2(25) NOT NULL,
Username 					    VARCHAR2(30) NOT NULL,
Password					    VARCHAR2(30) NOT NULL
);


CREATE TABLE Anamnesi (
Num_Protocollo                          NUMBER(10,0) NOT NULL,
Codice_Fiscale                          VARCHAR(50) NOT NULL,
Data_Ora_Controllo                      DATE NOT NULL,
Luogo                                   VARCHAR2(50) NOT NULL,
Esito                                   VARCHAR2(50) CHECK (Esito IN ('Idoneo','Non Idoneo Temporaneo','Non Idoneo Permanente')) NOT NULL,
Nome_medico                             VARCHAR2(50) NOT NULL,

PRIMARY KEY (Num_Protocollo)
);



CREATE TABLE Vaccinazione (
Nome_Vaccinando             VARCHAR2(50) NOT NULL,
Cognome_Vaccinando          VARCHAR2(50) NOT NULL,
Codice_Fiscale              VARCHAR2(50) NOT NULL,
Data_di_Nascita             DATE NOT NULL,
Numero_Dose                 NUMBER(8, 0) NOT NULL,
Nome_Farmaco                VARCHAR2(30) NOT NULL,
Num_Lotto                   VARCHAR2(10) NOT NULL,
Data_Scadenza               DATE NOT NULL,
Nome_Medico                 VARCHAR2(50) NOT NULL,
Data_Somministrazione       DATE,
Braccio_Inoculazione        VARCHAR2(10) CHECK (Braccio_Inoculazione IN ('Sinistro', 'Destro')) NOT NULL,
Nome_Luogo                  VARCHAR2(30) NOT NULL,

PRIMARY KEY (Codice_Fiscale, Numero_Dose)
);


CREATE TABLE Monitoraggio(
Codice_Fiscale				VARCHAR2(50) NOT NULL,
Data						DATE NOT NULL,
Temperatura					NUMBER(8,0) NOT NULL,
Mal_di_Gola					VARCHAR2(50) DEFAULT 'No' CHECK (Mal_di_Gola IN ('Si', 'No')) NOT NULL,
Mal_di_Testa				VARCHAR2(50) DEFAULT 'No' CHECK (Mal_di_Testa IN ('Si', 'No')) NOT NULL,
Dolori_Muscolari			VARCHAR2(50) DEFAULT 'No' CHECK (Dolori_Muscolari IN ('Si', 'No')) NOT NULL,
Nausea					    VARCHAR2(50) DEFAULT 'No' CHECK (Nausea IN ('Si', 'No')) NOT NULL,
Situazione_psicologica		VARCHAR2(50) DEFAULT 'Sereno' CHECK (Situazione_Psicologica IN ('Sereno', 'Abbastanza sereno', 'Abbastanza preoccupato', 'Molto preoccupato')) NOT NULL,
Tosse						VARCHAR2(50) DEFAULT 'Non presente' CHECK (Tosse IN ('Non presente', 'Secca', 'Grassa','Secca e stizzosa','Secca e insistente')) NOT NULL,
Respirazione				VARCHAR2(50) DEFAULT 'Normale' CHECK (Respirazione IN ('Normale', 'Respiro corto','Difficoltà a respirare e parlare')) NOT NULL,
Frequenza_cardiaca			NUMBER(8,0) NOT NULL,

PRIMARY KEY (CODICE_FISCALE,Data)
);