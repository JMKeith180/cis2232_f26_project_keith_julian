# For hccis.ca version of the database
# DROP DATABASE IF EXISTS bjmac_squash_skills_w26;
# CREATE DATABASE bjmac_squash_skills_w26;
# use bjmac_squash_skills_w26;

#For localhost
DROP DATABASE IF EXISTS cis2232_day_planner;
CREATE DATABASE cis2232_day_planner;
use cis2232_day_planner;

-- ------------------------------------------------------------------------------
-- Note the table below to hold data associated with your project.  Expect one
-- table with 7-9 fields.
-- ------------------------------------------------------------------------------

CREATE TABLE events (
                        eventId        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        eventParentId  INT NULL,
                        eventType      VARCHAR(50)  NOT NULL,
                        eventSubtype   VARCHAR(50)  NULL,
                        eventStartDate VARCHAR(10)  NOT NULL,
                        eventEndDate   VARCHAR(10)  NOT NULL,
                        eventStartTime SMALLINT     NOT NULL,
                        eventEndTime   SMALLINT     NOT NULL,
                        eventName      VARCHAR(100) NOT NULL,
                        FOREIGN KEY (eventParentId) REFERENCES events(eventId)
);

INSERT INTO events
(eventId, eventParentId, eventType, eventSubtype, eventStartDate, eventEndDate, eventStartTime, eventEndTime, eventName)
VALUES
    (1, NULL, 'Conference', NULL,          '2026-10-15', '2026-10-15', 0900,  0930,   'Tech Summit'),
    (2, 1,    'Conference', 'Keynote',     '2026-10-15', '2026-10-15', 0930, 1000,'Opening Keynote'),
    (3, 1,    'Conference', 'Workshop',    '2026-10-15', '2026-10-15',1015, 1130,  'Intro to SQL Workshop'),
    (4, NULL, 'Meeting',    NULL,          '2026-11-03', '2026-11-3',  0830, 1000,   'Project Planning'),
    (5, NULL, 'Birthday',   NULL,          '2026-12-20', '2026-12-20', 1730, 2000,'Alex''s Birthday Dinner'),
    (6, NULL, 'Rec', 'Basketball', '2027-03-12', '2027-03-15', 1000, 1200, 'Basketball Tryouts');

# ALTER TABLE SkillsAssessmentSquashTechnical
#     ADD PRIMARY KEY (id);
# ALTER TABLE SkillsAssessmentSquashTechnical
#     MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
#     AUTO_INCREMENT = 1;


# CREATE TABLE CodeType (codeTypeId int(3) COMMENT 'This is the primary key for code types',
#                        englishDescription varchar(100) NOT NULL COMMENT 'English description',
#                        frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
#                        createdDateTime datetime DEFAULT NULL,
#                        createdUserId varchar(20) DEFAULT NULL,
#                        updatedDateTime datetime DEFAULT NULL,
#                        updatedUserId varchar(20) DEFAULT NULL
# ) COMMENT 'This tables holds the code types that are available for the application';
#
# ALTER TABLE CodeType
#     ADD PRIMARY KEY (CodeTypeId);
#
# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 'Squash Technical Types', 'Squash Technical Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
#
#
#
# CREATE TABLE CodeValue (
#                            codeTypeId int(3) NOT NULL COMMENT 'see code_type table',
#                            codeValueSequence int(3) NOT NULL,
#                            englishDescription varchar(100) NOT NULL COMMENT 'English description',
#                            englishDescriptionShort varchar(20) NOT NULL COMMENT 'English abbreviation for description',
#                            frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
#                            frenchDescriptionShort varchar(20) DEFAULT NULL COMMENT 'French abbreviation for description',
#                            sortOrder int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
#                            createdDateTime datetime DEFAULT NULL,
#                            createdUserId varchar(20) DEFAULT NULL,
#                            updatedDateTime datetime DEFAULT NULL,
#                            updatedUserId varchar(20) DEFAULT NULL
# ) COMMENT='This will hold code values for the application.';
#
# ALTER TABLE CodeValue
#     ADD PRIMARY KEY (CodeTypeId, codeValueSequence);
#
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 1, 'Forehand Drives', 'FH Drives', 'Forehand DrivesFR', 'FH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
#     (2, 2, 'Backhand Drives', 'BH Drives', 'Backhand DrivesFR', 'BH DrivesFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
#

