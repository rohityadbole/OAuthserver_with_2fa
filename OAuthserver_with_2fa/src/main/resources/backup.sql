
CREATE TABLE assign_permission_to_role (
  ID int(11) NOT NULL AUTO_INCREMENT,
  PERMISSION_ID int(11) DEFAULT NULL,
  ROLE_ID int(11) DEFAULT NULL
);

--
-- Dumping data for table assign_permission_to_role
--

--INSERT INTO assign_permission_to_role (ID, PERMISSION_ID, ROLE_ID) VALUES
--(1, 1, 1),
--(2, 2, 1),
--(3, 3, 1),
--(4, 4, 1),
--(5, 5, 1),
--(6, 4, 2),
--(7, 5, 2),
--(8, 1, 1),
--(9, 2, 1),
--(10, 3, 1),
--(11, 4, 1),
--(12, 5, 1),
--(13, 6, 1),
--(14, 7, 1),
--(15, 8, 1),
--(16, 9, 1),
--(17, 10, 1),
--(18, 11, 1),
--(19, 12, 1),
--(20, 13, 1),
--(21, 15, 2);

INSERT INTO assign_permission_to_role (ID, PERMISSION_ID, ROLE_ID) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 4, 2),
(7, 5, 2),
(8, 1, 1),
(9, 2, 1),
(10, 3, 1),
(11, 4, 1),
(12, 5, 1),
(13, 6, 1),
(14, 7, 1),
(15, 8, 1),
(16, 9, 1),
(17, 10, 1),
(18, 11, 1),
(19, 12, 1),
(20, 13, 1),
(21, 15, 2),
(22, 11, 1),
(23, 12, 1),
(24, 32, 1),
(25, 33, 1);

-- --------------------------------------------------------

--
-- Table structure for table assign_user_to_role
--

CREATE TABLE assign_user_to_role (
  ID int(11) NOT NULL AUTO_INCREMENT,
  USER_ID int(11) DEFAULT NULL,
  ROLE_ID int(11) DEFAULT NULL
);

--
-- Dumping data for table assign_user_to_role
--

INSERT INTO assign_user_to_role (ID, USER_ID, ROLE_ID) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table permission
--

CREATE TABLE permission (
  ID int(11) NOT NULL AUTO_INCREMENT,
  PERMISSION_NAME varchar(50) DEFAULT NULL
);

--
-- Dumping data for table permission
--

--INSERT INTO permission (ID, PERMISSION_NAME) VALUES
--(1, 'CREATE_NOTE'),
--(2, 'EDIT_NOTE'),
--(3, 'DELETE_NOTE'),
--(4, 'VIEW_ALL_NOTE'),
--(5, 'VIEW_NOTE'),
--(6, 'view_permission'),
--(7, 'create_role'),
--(8, 'edit_role'),
--(9, 'view_role'),
--(10, 'delete_role'),
--(11, 'assign_permissions_to_role'),
--(12, 'view_permissions_by_role'),
--(13, 'create_users'),
--(14, 'edit_users'),
--(15, 'view_users'),
--(16, 'delete_users'),
--(17, 'assign_users_to_role'),
--(18, 'view_users_by_role'),
--(19, 'view_permission'),
--(20, 'create_role'),
--(21, 'edit_role'),
--(22, 'view_role'),
--(23, 'delete_role'),
--(24, 'assign_permissions_to_role'),
--(25, 'view_permissions_by_role'),
--(26, 'create_users'),
--(27, 'edit_users'),
--(28, 'view_users'),
--(29, 'delete_users'),
--(30, 'assign_users_to_role'),
--(31, 'view_users_by_role');

INSERT INTO permission (ID, PERMISSION_NAME) VALUES
(1, 'CREATE_NOTE'),
(2, 'EDIT_NOTE'),
(3, 'DELETE_NOTE'),
(4, 'VIEW_ALL_NOTE'),
(5, 'VIEW_NOTE'),
(6, 'view_permission'),
(7, 'create_role'),
(8, 'edit_role'),
(9, 'view_role'),
(10, 'delete_role'),
(11, 'delete_permission'),
(12, 'edit_permission'),
(13, 'create_users'),
(14, 'edit_users'),
(15, 'view_users'),
(16, 'delete_users'),
(17, 'assign_users_to_role'),
(18, 'view_users_by_role'),
(24, 'assign_permissions_to_role'),
(25, 'view_permissions_by_role'),
(30, 'assign_users_to_role'),
(31, 'view_users_by_role'),
(32, 'create_permission'),
(33, 'view_permission_by_role');

-- --------------------------------------------------------

--
-- Table structure for table role
--

CREATE TABLE role (
  ID int(11) NOT NULL AUTO_INCREMENT,
  ROLE_NAME varchar(20) DEFAULT NULL
);

--
-- Dumping data for table role
--

INSERT INTO role (ID, ROLE_NAME) VALUES
(1, 'super_admin'),
(2, 'administrator');

-- --------------------------------------------------------

--
-- Table structure for table user
--

CREATE TABLE user (
  ID int(11) NOT NULL AUTO_INCREMENT,
  LAST_NAME varchar(20) DEFAULT NULL,
  EMAIL_ID varchar(255) DEFAULT NULL,
  PASSWORD varchar(1000) DEFAULT NULL,
  COUNTRY varchar(20) DEFAULT NULL,
  FIRST_NAME varchar(50) DEFAULT NULL,
  MOBILE varchar(10) DEFAULT NULL,
  USER_TYPE varchar(15) DEFAULT NULL,
  IS_TFA_ENABLED varchar(1) DEFAULT NULL,
  TFA_CODE varchar(45) DEFAULT NULL,
  TFA_EXPIRE_TIME varchar(45) DEFAULT NULL,
  TFA_DEFAULT_TYPE varchar(10) DEFAULT NULL
);

--
-- Dumping data for table user
--

INSERT INTO `user` (`ID`, `LAST_NAME`, `EMAIL_ID`, `PASSWORD`, `COUNTRY`, `FIRST_NAME`, `MOBILE`, `USER_TYPE`, `IS_TFA_ENABLED`, `TFA_CODE`, `TFA_EXPIRE_TIME`, `TFA_DEFAULT_TYPE`) VALUES
(1, 'Dawson', 'john@gmail.com', '$2a$10$jbIi/RIYNm5xAW9M7IaE5.WPw6BZgD8wcpkZUg0jm8RHPtdfDcMgm', 'India', 'John', '8698612858', 'super_admin', 'Y', '9794', '1611554987', 'SMS'),
(2, 'Henry', 'mike@gmail.com', '$2a$10$jbIi/RIYNm5xAW9M7IaE5.WPw6BZgD8wcpkZUg0jm8RHPtdfDcMgm', 'America', 'Mike', '8099027394', 'administrator', 'Y', NULL, NULL, 'EMAIL'),
(3, 'Yadbole', 'rahul.yadbole@gmail.com', '$2a$10$RhMqlz03xEnx9Jo5IQ50MOSe/XaYJdkhCbXDrISPKc3VYaaxzIurC', 'India', 'Rahul', '9764417173', 'administrator', 'Y', NULL, NULL, 'EMAIL');

--
-- Indexes for dumped tables
--

--
-- Indexes for table assign_permission_to_role
--

  ALTER TABLE assign_permission_to_role ADD PRIMARY KEY (ID);
 ALTER TABLE assign_permission_to_role ADD KEY PERMISSION_ID (PERMISSION_ID);
 ALTER TABLE assign_permission_to_role ADD KEY ROLE_ID (ROLE_ID);

--
-- Indexes for table assign_user_to_role
--

 ALTER TABLE assign_user_to_role ADD PRIMARY KEY (ID);


--
-- Indexes for table permission
--

 ALTER TABLE permission ADD PRIMARY KEY (ID);

--
-- Indexes for table role
--

 ALTER TABLE role ADD PRIMARY KEY (ID);

--
-- Indexes for table user
--

ALTER TABLE user  ADD PRIMARY KEY (ID);
ALTER TABLE user  ADD UNIQUE KEY EMAIL_ID (EMAIL_ID);


--
-- Constraints for table assign_permission_to_role
--

ALTER TABLE assign_permission_to_role  ADD CONSTRAINT assign_permission_to_role_ibfk_1 FOREIGN KEY (PERMISSION_ID) REFERENCES permission (ID);
ALTER TABLE assign_permission_to_role  ADD CONSTRAINT assign_permission_to_role_ibfk_2 FOREIGN KEY (ROLE_ID) REFERENCES role (ID);

--
-- Constraints for table assign_user_to_role
--

 ALTER TABLE assign_user_to_role ADD CONSTRAINT assign_user_to_role_ibfk_1 FOREIGN KEY (USER_ID) REFERENCES user (ID);
 ALTER TABLE assign_user_to_role ADD CONSTRAINT assign_user_to_role_ibfk_2 FOREIGN KEY (ROLE_ID) REFERENCES role (ID);
